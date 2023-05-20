package com.example.Book_My_show.ServiceLayers;

import com.example.Book_My_show.Converters.ShowConverter;
import com.example.Book_My_show.DTO.EntryDTO.ShowEntryDTO;
import com.example.Book_My_show.Entity.*;
import com.example.Book_My_show.Enums.SeatType;
import com.example.Book_My_show.RepositaryLayers.MovieRepositary;
import com.example.Book_My_show.RepositaryLayers.ShowRepositary;
import com.example.Book_My_show.RepositaryLayers.TheaterRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    ShowRepositary showRepositary;
    @Autowired
    MovieRepositary movieRepositary;
    @Autowired
    TheaterRepositary theaterRepositary;

    public String addShowService(ShowEntryDTO showEntryDTO){
        ShowEntity showEntity = ShowConverter.convertToShowEntity(showEntryDTO);


        //Getting movieId and theaterId from the show entry DTO
        int movieId = showEntryDTO.getMovieId();
        int theaterId = showEntryDTO.getTheaterId();

        //Setting its name
        showEntity.setShowName(movieRepositary.findById(movieId).get().getMovieName()+" Show");



        //Setting foreign key of show entity
        showEntity.setMovieEntity(movieRepositary.findById(movieId).get());
        showEntity.setTheaterEntity(theaterRepositary.findById(theaterId).get());


        //Setting shows basic entites
        List<ShowSeatEntity> showSeatEntityList = createShowSeats(theaterRepositary.findById(theaterId).get(),showEntryDTO,showEntity );
        //Setting show seats
        showEntity.setShowSeatEntities(showSeatEntityList);
        showRepositary.save(showEntity);
        return ""+showEntity.getShowName()+"  have been added in "+ showEntity.getTheaterEntity().getName()+ " at "+
                showEntity.getTheaterEntity().getLocation() + " sucessfully";

    }
    public List<ShowSeatEntity> createShowSeats(TheaterEntity theaterEntity, ShowEntryDTO showEntryDTO
                                                ,ShowEntity showEntity){
        //Creating showSeat list which will be returned
        List<ShowSeatEntity> showSeatEntityList = new ArrayList<>();
        List<TheaterSeatEntity> theaterSeatEntityList= theaterEntity.getTheaterSeatEntityList();
        for (TheaterSeatEntity theaterSeatEntity: theaterSeatEntityList
             ) {
            //Creating show entitites and setting its attributes
            ShowSeatEntity showSeatEntity = new ShowSeatEntity();
            //Setting booked as false
            showSeatEntity.setBooked(false);
            //Setting Seat type and price for both classic and premium seats
            if (theaterSeatEntity.getSeatType().equals(SeatType.CLASSIC)){
                showSeatEntity.setSeatType(theaterSeatEntity.getSeatType());
                showSeatEntity.setPrice(showEntryDTO.getClassicPrice());
            }
            else {
                showSeatEntity.setSeatType(theaterSeatEntity.getSeatType());
                showSeatEntity.setPrice(showEntryDTO.getPremiumPrice());
            }
            showSeatEntity.setSeatNo(theaterSeatEntity.getSeatNo());
            showSeatEntity.setShows(showEntity);

            //Finally adding in the list after setting all it's attributes
            //Note that not saving the show seat entity
            showSeatEntityList.add(showSeatEntity);

        }
        return showSeatEntityList;

    }
    public String deleteShow(int id){
        ShowEntity showEntity = showRepositary.findById(id).get();
        String ans  = " Show "+showEntity.getShowName()+" deleted sucessfully";
        showRepositary.delete(showEntity);
        return ans;

    }
}
