package com.example.Book_My_show.Converters;

import com.example.Book_My_show.DTO.EntryDTO.MovieEntryDTO;
import com.example.Book_My_show.Entity.MovieEntity;

public class MovieConverter {
    public static MovieEntity movieDtoToEntity(MovieEntryDTO movieEntryDTO){
        MovieEntity movieEntity = MovieEntity.builder().movieName(movieEntryDTO.getMovieName())
                .genre(movieEntryDTO.getGenre()).languages(movieEntryDTO.getLanguages()).duration(movieEntryDTO.getDuration())
                .rating(movieEntryDTO.getRating()).build();
        return movieEntity;
    }
}
