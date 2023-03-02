package com.example.Book_my_Show_Application.convertors;

import com.example.Book_my_Show_Application.Entities.MovieEntity;
import com.example.Book_my_Show_Application.EntryDtos.MovieEntryDto;

public class MovieConvertor
{
    public static MovieEntity convertEntryDtoToEntity(MovieEntryDto movieEntryDto)
    {
        MovieEntity movieEntity = MovieEntity.builder().movieName(movieEntryDto.getMovieName()).genre(movieEntryDto.getGenre())
                .duration(movieEntryDto.getDuration()).language(movieEntryDto.getLanguage()).ratings(movieEntryDto.getRatings()).build();

        return movieEntity;
    }
}
