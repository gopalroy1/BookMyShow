package com.example.Book_My_show.DTO.EntryDTO;

import com.example.Book_My_show.Enums.ShowType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowEntryDTO {
    private ShowType showType;
    private LocalDate showDate;
    private LocalTime showTime;
    private int classicPrice;
    private int premiumPrice;
    private  int theaterId;
    private int movieId;

//    {
//        "showType":"_3D",
//            "showDate":"2023-11-09",
//            "showTime":"12:00:00",
//            "classicPrice": 60,
//            "premiumPrice":100,
//            "theaterId":10,
//            "movieId":4
//    }
}
