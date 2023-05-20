package com.example.Book_My_show.ServiceLayers;

import com.example.Book_My_show.DTO.EntryDTO.BookTicketDTO;
import com.example.Book_My_show.Entity.*;
import com.example.Book_My_show.RepositaryLayers.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.*;

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
    @Autowired
    JavaMailSender javaMailSender;

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
                        showSeat.setBookedAt(bookTicketDTO.getDateOfBooking());
                        amount += showSeat.getPrice();
                        seatBookedString = seatBookedString+" "+ticketRequested;
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

        //configuration for sending the mails
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("backeendacciojob@gmail.com");
        mimeMessageHelper.setTo("gopalroy5397@gmail.com");

        mimeMessageHelper.setText(" Tickets "+seatBookedString+ " booked for "+ticketsEntity.getMovieName()+" at "+
                ticketsEntity.getTheaterName()+" sucessfully"+" Total bill is "+amount);

        mimeMessageHelper.setSubject("Confirming your booked Ticket");

        javaMailSender.send(mimeMessage);


        return " Tickets "+seatBookedString+ " booked for "+ticketsEntity.getMovieName()+" at "+
                ticketsEntity.getTheaterName()+" sucessfully"+" Total bill is "+amount;



    }
    public String cancelTickets(int id){
        TicketsEntity ticketsEntity = ticketRepositary.findById(id).get();
        //Getting all seats of the particular show
        List<ShowSeatEntity> showSeatEntityList = ticketsEntity.getShowEntity().getShowSeatEntities();

        //Getting seat booked form the ticket entity
        String seatBooked= ticketsEntity.getBookedSeats();
        //converting in to hashmap by a function
        HashMap<String,Boolean> mp =convertToHashmap(seatBooked);
        int refund =0;
        int count =0;
        for (ShowSeatEntity seat:showSeatEntityList
             ) {
            //going to each seat and checking if that is booked or not

            if(mp.containsKey(seat.getSeatNo())){
                //if booked then adding prize from the seat to refund setting free the seat
                refund+=seat.getPrice();
                seat.setBooked(false);
                mp.remove(seat.getSeatNo());
                count++;

            }
        }


        //Deleting the ticket entity from the data base
        ticketRepositary.delete(ticketsEntity);
        return "Total "+count+" seats cancelled "+"refund "+refund;

    }
    //To make string of tickets booked to a hasmap
    public HashMap<String,Boolean> convertToHashmap(String s){
        HashMap<String,Boolean> map = new HashMap<>();
       for (int i =0;i<s.length();i++){
           char ch = s.charAt(i);
           if (ch=='0' || ch=='1' || ch=='2' || ch=='3' || ch=='4' || ch=='5' ||
                   ch=='6' || ch=='7' || ch=='8' || ch=='9' ){
               String ticket ="";
               int j =i;
               while (j<s.length()){

                   ticket+=ch;
                   j++;
                   if (j==s.length()){
                       break;
                   }
                   ch = s.charAt(j);
                   if (ch=='0' || ch=='1' || ch=='2' || ch=='3' || ch=='4' || ch=='5' ||
                           ch=='6' || ch=='7' || ch=='8' || ch=='9'|| ch ==','){
                       break;
                   }


               }
               i=j-1;
               map.put(ticket,true);
           }
       }
       return map;
    }

    public String soldTicket(int movieId){
        MovieEntity movieEntity = movieRepositary.findById(movieId).get();
        List<ShowEntity> showEntityList = movieEntity.getShowEntityList();
        int countTicket =0;
        int countSeat =0;
        int collection=0;
        int showCount=0;
        String ans = "";
        for (ShowEntity show:showEntityList
             ) {
            showCount++;
            List<TicketsEntity> ticketsForAShow = show.getTicketsEntityList();
            String seatBooked ="";
            for (TicketsEntity ticket:ticketsForAShow
                 ) {
                countTicket++;
                HashMap<String,Boolean> map = convertToHashmap(ticket.getBookedSeats());
                countSeat+=map.size();
                collection+=ticket.getTotalAmount();

            }
            ans+=show.getShowName()+" "+seatBooked;
        }
        return "Total Tickets :"+countTicket+ " ,Seats Booked: "+countSeat+", Total Show: "+showCount+" ,total revenue:"+collection+"Rs";
    }


}
