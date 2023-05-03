package com.example.Book_My_show.Entity;

import com.example.Book_My_show.Enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Theater_seat")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterSeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private String seatNo;

    /////Mapping Starts here ///////////////
/////Childs
    /// wrt TheaterEntity

    @ManyToOne
    @JoinColumn
    private TheaterEntity theaterEntity;
}
