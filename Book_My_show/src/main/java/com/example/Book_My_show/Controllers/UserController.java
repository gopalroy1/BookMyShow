package com.example.Book_My_show.Controllers;

import com.example.Book_My_show.DTO.EntryDTO.UserEntryDTO;
import com.example.Book_My_show.Entity.UserEntity;
import com.example.Book_My_show.ServiceLayers.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUserController(@RequestBody UserEntryDTO userEntryDTO){
        try {
            userService.addUserService(userEntryDTO);
            return new ResponseEntity<>("user added sucessfully", HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(),HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/alltickets")
    public ResponseEntity<String> userTicketsController(@RequestParam int id){
        try {
            String result =userService.allTickets(id);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(),HttpStatus.BAD_REQUEST);
        }

    }
}
