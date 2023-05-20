package com.example.Book_My_show.ServiceLayers;

import com.example.Book_My_show.Converters.MovieConverter;
import com.example.Book_My_show.DTO.EntryDTO.MovieEntryDTO;
import com.example.Book_My_show.Entity.MovieEntity;
import com.example.Book_My_show.Entity.ShowEntity;
import com.example.Book_My_show.Entity.ShowSeatEntity;
import com.example.Book_My_show.RepositaryLayers.MovieRepositary;
import com.example.Book_My_show.RepositaryLayers.ShowRepositary;
import com.example.Book_My_show.RepositaryLayers.ShowSeatRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepositary movieRepositary;
    @Autowired
    ShowRepositary showRepositary;
    @Autowired
    ShowSeatRepositary showSeatRepositary;

    public String addMovieService(MovieEntryDTO movieEntryDTO){
        MovieEntity movieEntity = MovieConverter.movieDtoToEntity(movieEntryDTO);
        movieRepositary.save(movieEntity);
        return "Movie "+movieEntity.getMovieName()+" added sucessfully";
    }
    public String deleteMovieService(int id){
        //Getting the movie and the movie name
        MovieEntity movieEntity = movieRepositary.findById(id).get();
        String name = movieEntity.getMovieName();

        //Getting all  the show lists and deleting the shows for a movie
        List<ShowEntity> showList = movieEntity.getShowEntityList();
        int showCount = showList.size();
        System.out.println(showCount);
//        List<ShowEntity> newShowList = new ArrayList<>();
//        movieEntity.setShowEntityList(newShowList);
//        movieRepositary.save(movieEntity);
        for (ShowEntity showEntity:showList
             ) {

            //just deleting the show entity as it will cascade and delete it's child showSeats

            movieEntity.getShowEntityList().remove(showEntity);
            showRepositary.delete(showEntity);
            movieRepositary.save(movieEntity);


        }

        movieRepositary.delete(movieEntity);
        return "Movie"+name+" with id "+id+ " , show:"+showCount+ " deleted sucessfully";

    }
    public String movieWithMaxShowService(){
        int max =0;
        String ans="";
        List<MovieEntity> movieEntityList = movieRepositary.findAll();
        for (MovieEntity movieEntity:movieEntityList
             ) {
            if (movieEntity.getShowEntityList().size()>max){
                max= movieEntity.getShowEntityList().size();
                ans = movieEntity.getMovieName()+ " with id "+movieEntity.getId();
            }
        }
        return ans;
    }


}
