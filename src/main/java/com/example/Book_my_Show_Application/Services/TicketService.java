package com.example.Book_my_Show_Application.Services;

import com.example.Book_my_Show_Application.Entities.ShowEntity;
import com.example.Book_my_Show_Application.Entities.ShowSeatEntity;
import com.example.Book_my_Show_Application.Entities.TicketEntity;
import com.example.Book_my_Show_Application.Entities.UserEntity;
import com.example.Book_my_Show_Application.EntryDtos.TicketEntryDto;
import com.example.Book_my_Show_Application.Repository.ShowRepository;
import com.example.Book_my_Show_Application.Repository.TicketRepository;
import com.example.Book_my_Show_Application.Repository.UserRepository;
import com.example.Book_my_Show_Application.convertors.TicketConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService
{

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    //@Autowired
    //JavaMailSender javaMailSender;

    public String addTicket(TicketEntryDto ticketEntryDto) throws Exception {

        TicketEntity ticketEntity = TicketConvertor.convertEntrytoEntity(ticketEntryDto);

        //chech if the requested seats is available or not
        boolean isValidRequest = checkValidityRequestSeats(ticketEntryDto);

        if (isValidRequest==false)
        {
            throw new Exception("Requested seats are available");
        }

        //if isValisrequest is true

        ShowEntity showEntity = showRepository.findById(ticketEntryDto.getShowId()).get();

        List<ShowSeatEntity> seatEntityList = showEntity.getListOfShowSeats();

        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();


        int totalAmount = 0;

        for (ShowSeatEntity showSeatEntity : seatEntityList)
        {
            if (requestedSeats.contains(showSeatEntity.getSeatNo()))
            {
                totalAmount = totalAmount + showSeatEntity.getPrice();
                showSeatEntity.setBooked(true);
                showSeatEntity.setBookedAt(new Date());


            }
        }

        ticketEntity.setTotalAmount(totalAmount);

        //setting other Attributes for ticketEntity
        ticketEntity.setMovieName(showEntity.getMovieEntity().getMovieName());
        ticketEntity.setShowDate(showEntity.getShowDate());
        ticketEntity.setShowTime(showEntity.getShowTime());
        ticketEntity.setTheaterName(showEntity.getTheaterEntity().getName());

        //Requested Seats
        String allotedSeats = getAllotedSeatsfromShowSeats(requestedSeats);
        ticketEntity.setBookedSeats(allotedSeats);

        //Setting the foreign Key
        UserEntity userEntity = userRepository.findById(ticketEntryDto.getUserId()).get();

        ticketEntity.setUserEntity(userEntity);
        ticketEntity.setShowEntity(showEntity);

        //Save the Parent
        List<TicketEntity> ticketEntityList = showEntity.getListOfBookedTickets();
        ticketEntityList.add(ticketEntity);
        showEntity.setListOfBookedTickets(ticketEntityList);

        showRepository.save(showEntity);

        List<TicketEntity> ticketEntityList1 = userEntity.getBookedTickets();
        ticketEntityList1.add(ticketEntity);
        userEntity.setBookedTickets(ticketEntityList1);

        userRepository.save(userEntity);



        return "Ticket has Been Booked";
    }

    private String getAllotedSeatsfromShowSeats(List<String> requestedSeats)
    {
        String result = "";

        for (String seat : requestedSeats)
        {
            result = result  +seat+ ", ";

        }
        return result;
    }

    private boolean checkValidityRequestSeats(TicketEntryDto ticketEntryDto)
    {
        int showId = ticketEntryDto.getShowId();

        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        ShowEntity showEntity = showRepository.findById(showId).get();

        List<ShowSeatEntity> listOfSeats = showEntity.getListOfShowSeats();

        for(ShowSeatEntity showSeatEntity : listOfSeats)
        {
            String seatNo = showSeatEntity.getSeatNo();

            if (requestedSeats.contains(seatNo))
            {
                if (showSeatEntity.isBooked()==true)
                {
                    return false;
                }
            }
        }
        //All seats that are requested are available
        return true;

    }
}
