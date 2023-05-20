package com.example.Book_My_show.ServiceLayers;

import com.example.Book_My_show.Converters.UserConverter;
import com.example.Book_My_show.DTO.EntryDTO.UserEntryDTO;
import com.example.Book_My_show.Entity.TicketsEntity;
import com.example.Book_My_show.Entity.UserEntity;
import com.example.Book_My_show.RepositaryLayers.UserRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepositary userRepositary;

    //Add user implementation with user DTOs
    public void addUserService(UserEntryDTO userEntryDTO){
        UserEntity user = UserConverter.convertToUserEntity(userEntryDTO);
        userRepositary.save(user);

    }
    public String allTickets(int id){
        UserEntity userEntity= userRepositary.findById(id).get();
        //getting tickets list from userEntity
        List<TicketsEntity> ticketBooked = userEntity.getTicketBooked();
        String ans ="";
        ans+= userEntity.getName()+" Total "+ticketBooked.size()+"Booked Ticket  ";

        // iterating and saving ans
        for (TicketsEntity t:ticketBooked
             ) {
            ans += t.getTicketId();
        }
        return ans;
    }

}
