package com.example.Book_My_show.DTO.EntryDTO;

import com.example.Book_My_show.Enums.Genre;
import com.example.Book_My_show.Enums.Languages;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class MovieEntryDTO {
    private String movieName;
    private double rating;
    private int duration;
    private Languages languages;
    private Genre genre;
}

//    {"movieName" : "Kal ho na ho",
//            "rating" : 8.9,
//            "duration" : 6,
//            "languages" : "HINDI",
//            "genre" : "DRAMA"
//    }
