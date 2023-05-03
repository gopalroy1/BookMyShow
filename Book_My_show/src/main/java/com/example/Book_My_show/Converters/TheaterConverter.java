package com.example.Book_My_show.Converters;

import com.example.Book_My_show.DTO.EntryDTO.TheaterEntryDTO;
import com.example.Book_My_show.Entity.TheaterEntity;

public class TheaterConverter {
    public static TheaterEntity convertToTheaterEntity(TheaterEntryDTO theaterEntryDTO){
        return TheaterEntity.builder().name(theaterEntryDTO.getName()).location(theaterEntryDTO.getLocation()).build();
    }
}
