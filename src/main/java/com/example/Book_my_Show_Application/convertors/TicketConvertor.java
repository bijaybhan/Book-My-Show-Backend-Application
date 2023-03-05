package com.example.Book_my_Show_Application.convertors;

import com.example.Book_my_Show_Application.Entities.TicketEntity;
import com.example.Book_my_Show_Application.EntryDtos.TicketEntryDto;

public class TicketConvertor
{
    public static TicketEntity convertEntrytoEntity(TicketEntryDto ticketEntryDto)
    {
        TicketEntity ticketEntity = new TicketEntity();
        return ticketEntity;
    }
}
