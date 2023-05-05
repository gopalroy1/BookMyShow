package com.example.Book_My_show.ServiceLayers;

import com.example.Book_My_show.DTO.EntryDTO.BookTicketDTO;
import com.example.Book_My_show.Entity.*;
import com.example.Book_My_show.RepositaryLayers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    TicketRepositary ticketRepositary;
    @Autowired
    MovieRepositary movieRepositary;
    @Autowired
    TheaterRepositary theaterRepositary;
    @Autowired
    ShowRepositary showRepositary;
    @Autowired
    UserRepositary userRepositary;

    public String bookTicketService(BookTicketDTO bookTicketDTO)throws Exception{
        //making new ticket which is to be booked
        TicketsEntity ticketsEntity = new TicketsEntity();

        //Getting all the attributes from the book ticket dto
        MovieEntity movieEntity = movieRepositary.findById(bookTicketDTO.getMovieId()).get();
        TheaterEntity theaterEntity = theaterRepositary.findById(bookTicketDTO.getTheaterId()).get();
        ShowEntity showEntity = showRepositary.findById(bookTicketDTO.getShowId()).get();
        UserEntity userEntity = userRepositary.findById(bookTicketDTO.getUserId()).get();
        List<String> ticketRequestedList = bookTicketDTO.getTicketSeats();

        ///Do some validations

        ///Calculating amount and booking seats
        int amount =0;
        List<ShowSeatEntity> seatBooked = new ArrayList<>();
        String seatBookedString ="";

        //Getting show seat entity and booking tickets there
        List<ShowSeatEntity> showSeatList = showEntity.getShowSeatEntities();
        boolean foundTickets = false;

        for (ShowSeatEntity showSeat:showSeatList
             ) {
            for (String ticketRequested: ticketRequestedList
                 ) {
                if (ticketRequested.equals(showSeat.getSeatNo())){
                    if (showSeat.isBooked()){
                        throw new Exception(ticketRequested+" ticket is already booked choose another tickets");


                    }
                    else {
                        //Booking the seats here
                        showSeat.setBooked(true);
                        amount += showSeat.getPrice();
                        seatBookedString = seatBookedString+","+ticketRequested;
                        seatBooked.add(showSeat);
                        foundTickets= true;
                    }
                }
            }
        }
        //If tickets are not found then throw enter proper seats
        if (foundTickets== false){
            throw new Exception(" Tickets does not exists");
        }
        //Setting attributes
        ticketsEntity.setMovieName(movieEntity.getMovieName());
        ticketsEntity.setShowDate(showEntity.getShowDate());
        ticketsEntity.setShowTime(showEntity.getShowTime());
        ticketsEntity.setTicketId(UUID.randomUUID().toString());
        ticketsEntity.setTotalAmount(amount);
        ticketsEntity.setBookedSeats(seatBookedString);
        ticketsEntity.setTheaterName(theaterEntity.getName());

        //Setting the foreign key of tickets
        ticketsEntity.setShowEntity(showEntity);
        ticketsEntity.setUserEntity(userEntity);

        //To save the tickets just set one of it's parents here setting the user
        List<TicketsEntity> ticketsEntityList =new ArrayList<>();
        ticketsEntityList.add(ticketsEntity);
        userEntity.setTicketBooked(ticketsEntityList);


        //saving the parents
        userRepositary.save(userEntity);
        showRepositary.save(showEntity);
        return " Tickets "+seatBookedString+ " booked for "+ticketsEntity.getMovieName()+" at "+
                ticketsEntity.getTheaterName()+" sucessfully"+" Total bill is "+amount;



    }

}
