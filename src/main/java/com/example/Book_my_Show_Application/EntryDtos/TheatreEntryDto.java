package com.example.Book_my_Show_Application.EntryDtos;

import lombok.Data;

@Data
public class TheatreEntryDto
{
    private String name;

    private String location;

    private int classicSeatCount;

    private int premiumSeatCount;

}
