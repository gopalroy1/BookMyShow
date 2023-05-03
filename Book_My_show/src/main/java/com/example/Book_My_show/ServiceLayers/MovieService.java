package com.example.Book_My_show.ServiceLayers;

import com.example.Book_My_show.Converters.MovieConverter;
import com.example.Book_My_show.DTO.EntryDTO.MovieEntryDTO;
import com.example.Book_My_show.Entity.MovieEntity;
import com.example.Book_My_show.RepositaryLayers.MovieRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepositary movieRepositary;

    public String addMovieService(MovieEntryDTO movieEntryDTO){
        MovieEntity movieEntity = MovieConverter.movieDtoToEntity(movieEntryDTO);
        movieRepositary.save(movieEntity);
        return "Movie "+movieEntity.getMovieName()+" added sucessfully";
    }
    public String deleteMovieServiceSS(int id){
        MovieEntity movieEntity = movieRepositary.findById(id).get();
        String name = movieEntity.getMovieName();
        movieRepositary.delete(movieRepositary.findById(id).get());
        return "Movie"+name+" with id "+id+ " deleted sucessfully";

    }

}
