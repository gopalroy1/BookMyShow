package com.example.Book_My_show.DTO.EntryDTO;

import lombok.Data;

@Data
public class TheaterEntryDTO {

    private String name;
    private String location;
    private int classicSeat;
    private int premiumSeat;

//
// "name" : "PVR",
//             "location" : "kolkata",
//             "classicSeat": 60,
//             "premiumSeat" : 20
}
