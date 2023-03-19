package com.example.Book_My_show.Models;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "shows_table")
public class Shows {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int showId;
    private Date showDate;
    private Time showTime;

    //connecting shows to ticket
    @OneToMany(mappedBy = "showsfor",cascade = CascadeType.ALL)
    private Ticket tickefor;


    ////coneccting shows to movie
    @ManyToOne
    @JoinColumn
    private Movie moviefor;

    ////Connecting shows to showseats
    @OneToMany(mappedBy = "showsfor",cascade = CascadeType.ALL)
    private ShowSeats showSeatsfor;

    ///Connecting shows to theater
    @ManyToOne
    @JoinColumn
    private Theater theaterfor;

    ////Constructor


    public Shows() {
    }
    //////Getter and setters

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }

    public Time getShowTime() {
        return showTime;
    }

    public void setShowTime(Time showTime) {
        this.showTime = showTime;
    }

    public Ticket getTickefor() {
        return tickefor;
    }

    public void setTickefor(Ticket tickefor) {
        this.tickefor = tickefor;
    }

    public Movie getMoviefor() {
        return moviefor;
    }

    public void setMoviefor(Movie moviefor) {
        this.moviefor = moviefor;
    }

    public ShowSeats getShowSeatsfor() {
        return showSeatsfor;
    }

    public void setShowSeatsfor(ShowSeats showSeatsfor) {
        this.showSeatsfor = showSeatsfor;
    }

    public Theater getTheaterfor() {
        return theaterfor;
    }

    public void setTheaterfor(Theater theaterfor) {
        this.theaterfor = theaterfor;
    }
}
