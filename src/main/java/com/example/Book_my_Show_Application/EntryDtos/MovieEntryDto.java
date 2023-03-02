package com.example.Book_my_Show_Application.EntryDtos;

import com.example.Book_my_Show_Application.Genres.Genre;
import com.example.Book_my_Show_Application.Genres.Languages;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class MovieEntryDto
{

    private String movieName;

    private double ratings;

    private int duration;

    private Languages language;

    private Genre genre;

}
