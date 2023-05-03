package com.example.Book_My_show.Controllers;

import com.example.Book_My_show.DTO.DeleteDTO.TheaterDeleteByNameAndLocationDTO;
import com.example.Book_My_show.DTO.EntryDTO.TheaterEntryDTO;
import com.example.Book_My_show.ServiceLayers.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theater")
public class TheaterController  {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/add")
    public ResponseEntity<String> addTheaterController(@RequestBody TheaterEntryDTO theaterEntryDTO){
        try {
            String result = theaterService.addTheaterService(theaterEntryDTO);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteByNameAndLocation")
    public ResponseEntity<String> deleteTheaterController(@RequestBody TheaterDeleteByNameAndLocationDTO theaterDeleteByNameAndLocationDTO){
        try {
            String result = theaterService.deleteTheaterService(theaterDeleteByNameAndLocationDTO);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllTheaterController(){
        try {
            String result = theaterService.deleteAllTheaterService();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteByIdController(@RequestParam int id){
        try {
            String result = theaterService.deleteByIdTheaterService(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }


}
