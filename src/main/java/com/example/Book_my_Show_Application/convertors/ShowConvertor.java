package com.example.Book_my_Show_Application.convertors;

import com.example.Book_my_Show_Application.Entities.ShowEntity;
import com.example.Book_my_Show_Application.EntryDtos.ShowEntryDto;

public class ShowConvertor
{
    public static ShowEntity convertEntrytoEntity(ShowEntryDto showEntryDto)
    {
        ShowEntity showEntity = ShowEntity.builder().showDate(showEntryDto.getLocalDate())
                .showTime(showEntryDto.getLocalTime())
                .showType(showEntryDto.getShowType()).build();

        return showEntity;
    }
}
