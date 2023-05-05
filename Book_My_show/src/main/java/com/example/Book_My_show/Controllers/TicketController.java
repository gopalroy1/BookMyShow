package com.example.Book_My_show.Controllers;

import com.example.Book_My_show.DTO.EntryDTO.BookTicketDTO;
import com.example.Book_My_show.ServiceLayers.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@RequestBody BookTicketDTO bookTicketDTO){
        try {
            String result = ticketService.bookTicketService(bookTicketDTO);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(),HttpStatus.BAD_REQUEST);
        }
    }
}
