package com.example.Book_My_show.Controllers;

import com.example.Book_My_show.DTO.EntryDTO.ShowEntryDTO;
import com.example.Book_My_show.ServiceLayers.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String> addShowController(@RequestBody ShowEntryDTO showEntryDTO){
        try {
            String result = showService.addShowService(showEntryDTO);
            return  new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteByIdController(@RequestParam int id){
        try {
            String result = showService.deleteShow(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }


}
