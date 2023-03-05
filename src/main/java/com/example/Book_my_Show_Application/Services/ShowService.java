package com.example.Book_my_Show_Application.Services;

import com.example.Book_my_Show_Application.Entities.*;
import com.example.Book_my_Show_Application.EntryDtos.ShowEntryDto;
import com.example.Book_my_Show_Application.Genres.SeatType;
import com.example.Book_my_Show_Application.Repository.MovieRepository;
import com.example.Book_my_Show_Application.Repository.ShowRepository;
import com.example.Book_my_Show_Application.Repository.TheatreRepository;
import com.example.Book_my_Show_Application.convertors.ShowConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService
{
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    ShowRepository showRepository;

    public String addShow(ShowEntryDto showEntryDto)
    {
        ShowEntity showEntity = ShowConvertor.convertEntrytoEntity(showEntryDto);

        int movieId = showEntryDto.getMovieId();
        int theatreId = showEntryDto.getTheaterId();

        MovieEntity movieEntity = movieRepository.findById(movieId).get();
        TheatreEntity theatreEntity = theatreRepository.findById(theatreId).get();

        showEntity.setMovieEntity(movieEntity);
        showEntity.setTheaterEntity(theatreEntity);

        List<ShowSeatEntity> showSeatEntityList = createShowSeatEntity(showEntryDto,showEntity);

        showEntity.setListOfShowSeats(showSeatEntityList);

        List<ShowEntity> showEntityList = movieEntity.getShowEntityList();
        showEntityList.add(showEntity);
        movieEntity.setShowEntityList(showEntityList);

        movieRepository.save(movieEntity);

        List<ShowEntity> showEntityList1 = theatreEntity.getShowEntityList();
        showEntityList1.add(showEntity);
        theatreEntity.setShowEntityList(showEntityList1);

        theatreRepository.save(theatreEntity);


        return "Show has been Added Successfully";

    }

    private List<ShowSeatEntity> createShowSeatEntity(ShowEntryDto showEntryDto, ShowEntity showEntity)
    {

        TheatreEntity theatreEntity = showEntity.getTheaterEntity();

        List<TheatreSeatEntity> theatreSeatEntityList = theatreEntity.getTheaterSeatEntityList();

        List<ShowSeatEntity> seatEntityList = new ArrayList<>();

        for (TheatreSeatEntity theatreSeatEntity : theatreSeatEntityList)
        {
            ShowSeatEntity showSeatEntity = new ShowSeatEntity();
            showSeatEntity.setSeatNo((theatreSeatEntity.getSeatNo()));
            showSeatEntity.setSeatType(theatreSeatEntity.getSeatType());

            if (theatreSeatEntity.getSeatType().equals(SeatType.CLASSIC))
            {
                showSeatEntity.setPrice(showEntryDto.getClassicSeatPrice());
            }
            else
            {
                showSeatEntity.setPrice(showEntryDto.getPremiumSeatPrice());
            }

            showSeatEntity.setBooked(false);
            showSeatEntity.setShowEntity(showEntity);

            seatEntityList.add(showSeatEntity);
        }

        return seatEntityList;
    }

}
