package com.example.Book_My_show.DTO.EntryDTO;

import lombok.Data;

import java.util.List;

@Data
public class BookTicketDTO {
    private int movieId;
    private int showId;
    private int theaterId;
    private int userId;
    private List<String> ticketSeats;
}
