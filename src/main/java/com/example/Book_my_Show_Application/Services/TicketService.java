package com.example.Book_my_Show_Application.Services;

import com.example.Book_my_Show_Application.Repository.ShowRepository;
import com.example.Book_my_Show_Application.Repository.TicketRepository;
import com.example.Book_my_Show_Application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
