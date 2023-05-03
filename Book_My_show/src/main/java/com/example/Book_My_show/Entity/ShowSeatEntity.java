package com.example.Book_My_show.Entity;

import com.example.Book_My_show.Enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "showseats")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowSeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isBooked;
    private int price;
    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private Date bookedAt;

    /////////////Mapping starts here/////////
///childs
    ///wrt ShowEntity
    @JoinColumn
    @ManyToOne
    private ShowEntity shows;

}
