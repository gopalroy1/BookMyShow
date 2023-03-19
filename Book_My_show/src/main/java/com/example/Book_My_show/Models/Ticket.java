package com.example.Book_My_show.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="ticket_table")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ticketId;
    private String movieName;
    private List<Shows> shows;

    ///Connecting user and ticket
    @ManyToOne
    @JoinColumn
    private User userfor;

    ///Connecting tickets to show
    @ManyToOne
    @JoinColumn
    private Shows showsfor;


    ////Constructors

    public Ticket() {
    }

    ////Getters and setters


    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public List<Shows> getShows() {
        return shows;
    }

    public void setShows(List<Shows> shows) {
        this.shows = shows;
    }

    public User getUserfor() {
        return userfor;
    }

    public void setUserfor(User userfor) {
        this.userfor = userfor;
    }

    public Shows getShowsfor() {
        return showsfor;
    }

    public void setShowsfor(Shows showsfor) {
        this.showsfor = showsfor;
    }
}
