package com.example.Book_my_Show_Application.convertors;

import com.example.Book_my_Show_Application.Entities.TheatreEntity;
import com.example.Book_my_Show_Application.EntryDtos.TheatreEntryDto;

public class TheatreConvertor
{
    public static TheatreEntity convertDtoToEntity(TheatreEntryDto theatreEntryDto)
    {
        return TheatreEntity.builder().name(theatreEntryDto.getName())
                .location(theatreEntryDto.getLocation()).build();


    }
}
