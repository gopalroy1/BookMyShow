package com.example.Book_My_show.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String movieName;
    private LocalDate showDate;
    private LocalTime showTime;
    private int totalAmount;
    private String ticketId = UUID.randomUUID().toString();
    private String theaterName;
    private String bookedSeats;

    ///////////////Mapping starts here///////////////

    ///////////////////Childs

    /// wrt User and ticket is child
    @JoinColumn
    @ManyToOne
    private UserEntity userEntity;

    /// wrt shows
    @JoinColumn
    @ManyToOne
    private ShowEntity showEntity;

}
