package com.example.Book_my_Show_Application.Services;

import com.example.Book_my_Show_Application.Entities.TheatreEntity;
import com.example.Book_my_Show_Application.Entities.TheatreSeatEntity;
import com.example.Book_my_Show_Application.EntryDtos.TheatreEntryDto;
import com.example.Book_my_Show_Application.Genres.SeatType;
import com.example.Book_my_Show_Application.Repository.TheatreRepository;
import com.example.Book_my_Show_Application.Repository.TheatreSeatRepository;
import com.example.Book_my_Show_Application.convertors.TheatreConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService
{
    @Autowired
    TheatreSeatRepository theatreSeatRepository;

    @Autowired
    TheatreRepository theatreRepository;

        public String addTheatre(TheatreEntryDto theatreEntryDto) throws Exception {

            if(theatreEntryDto.getName()==null||theatreEntryDto.getLocation()==null)
            {
                throw new Exception("Name and Location Should be there");
            }


            TheatreEntity theatreEntity = TheatreConvertor.convertDtoToEntity(theatreEntryDto);

            List<TheatreSeatEntity> theatreSeatEntities = createTheatreSeats(theatreEntryDto, theatreEntity);

            theatreEntity.setTheaterSeatEntityList(theatreSeatEntities);

            theatreRepository.save(theatreEntity);

            return "Theatre Added Successfully";
        }

        private List<TheatreSeatEntity> createTheatreSeats(TheatreEntryDto theatreEntryDto, TheatreEntity theatreEntity)
        {
            int noClassicSeats = theatreEntryDto.getClassicSeatCount();
            int noPremiumSeats = theatreEntryDto.getPremiumSeatCount();

            List<TheatreSeatEntity> theatreSeatEntityList = new ArrayList<>();

            for (int count = 1; count <= noClassicSeats; count++)
            {
                TheatreSeatEntity theatreSeatEntity = TheatreSeatEntity.builder()
                        .seatType(SeatType.CLASSIC).seatNo(count+"C")
                        .theaterEntity(theatreEntity).build();

                theatreSeatEntityList.add(theatreSeatEntity);
            }

            for (int count = 1; count <= noPremiumSeats; count++)
            {
                TheatreSeatEntity theatreSeatEntity = TheatreSeatEntity.builder()
                        .seatType(SeatType.PREMIUM).seatNo(count+"P")
                        .theaterEntity(theatreEntity).build();

                theatreSeatEntityList.add(theatreSeatEntity);
            }

            //theatreSeatRepository.saveAll(theatreSeatEntityList);

            return theatreSeatEntityList;

        }
}
