package com.example.Book_my_Show_Application.Controller;

import com.example.Book_my_Show_Application.EntryDtos.TheatreEntryDto;
import com.example.Book_my_Show_Application.Services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theatre")
public class TheatreController
{
    @Autowired
    TheatreService theatreService;

    @PostMapping("/add")
    public ResponseEntity addTheatre(@RequestBody TheatreEntryDto theatreEntryDto)
    {
        try
        {
            String result = theatreService.addTheatre(theatreEntryDto);

            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

}
