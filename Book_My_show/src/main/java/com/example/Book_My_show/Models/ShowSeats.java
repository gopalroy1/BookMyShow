package com.example.Book_My_show.Models;

import com.example.Book_My_show.Enums.SeatType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="showseats_table")
public class ShowSeats {

    private int seatNo;
    private boolean isBooked;

    private Date isBookedOn;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    ///Connecting shows to show seats
    @ManyToOne
    @JoinColumn
    private Shows showsfor;


    ////Constructors

    public ShowSeats() {
    }
    /////Getters and setters

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public Date getIsBookedOn() {
        return isBookedOn;
    }

    public void setIsBookedOn(Date isBookedOn) {
        this.isBookedOn = isBookedOn;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public Shows getShowsfor() {
        return showsfor;
    }

    public void setShowsfor(Shows showsfor) {
        this.showsfor = showsfor;
    }
}
