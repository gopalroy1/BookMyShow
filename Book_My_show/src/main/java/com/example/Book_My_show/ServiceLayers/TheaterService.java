package com.example.Book_My_show.ServiceLayers;

import com.example.Book_My_show.Converters.TheaterConverter;
import com.example.Book_My_show.DTO.DeleteDTO.TheaterDeleteByNameAndLocationDTO;
import com.example.Book_My_show.DTO.EntryDTO.TheaterEntryDTO;
import com.example.Book_My_show.Entity.TheaterEntity;
import com.example.Book_My_show.Entity.TheaterSeatEntity;
import com.example.Book_My_show.Enums.SeatType;
import com.example.Book_My_show.RepositaryLayers.TheaterRepositary;
import com.example.Book_My_show.RepositaryLayers.TheaterSeatRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterRepositary theaterRepositary;
    @Autowired
    TheaterSeatRepositary theaterSeatRepositary;

    //     Theater already exists or not checking
    public  boolean theaterAlreadyExists(TheaterEntryDTO theaterEntryDTO){
        List<TheaterEntity> theaterEntityList = theaterRepositary.findAll();
        for (TheaterEntity theaterEntity : theaterEntityList
                ) {
            if (theaterEntity.getName().equals(theaterEntryDTO.getName()) &&
                    theaterEntity.getLocation().equals(theaterEntryDTO.getLocation())){
                return true;
            }
        }
        return false;
    }




    public String addTheaterService(TheaterEntryDTO theaterEntryDTO){
        //Add checkings
        // Adding null checkings
        if (theaterEntryDTO.getName()==null || theaterEntryDTO.getLocation()==null){
            return "Incorrect name or location";
        }
        //if name or location is empty
        if (theaterEntryDTO.getName().length()<1 || theaterEntryDTO.getLocation().length()<1){
            return "Incorrect name or location";
        }
        //  Checking of negative seats
        if (theaterEntryDTO.getClassicSeat()<0 || theaterEntryDTO.getPremiumSeat()<0){
            return "Please provide correct seat inputs";
        }
        if (theaterAlreadyExists(theaterEntryDTO)){
            return "This theater alreay exists";
        }
        TheaterEntity theaterEntity = TheaterConverter.convertToTheaterEntity(theaterEntryDTO);
        //Got the values of the classic and premium seat from the entry DTOs
        int classic = theaterEntryDTO.getClassicSeat();
        int premium = theaterEntryDTO.getPremiumSeat();

        /// Making List to save the theaterSeatList in a particular theater
        List<TheaterSeatEntity> theaterSeatEntityList = new ArrayList<>();

        /// Making Seats and saving them and also adding them in the list of the theaters
        for (int i =0 ; i<classic;i++){
            //Making new theater seat entity and saving all it's attribute
            TheaterSeatEntity theaterSeatEntity = new TheaterSeatEntity();
            // Setting seat type and seat number
            theaterSeatEntity.setSeatType(SeatType.CLASSIC);
            // setting seat number
            theaterSeatEntity.setSeatNo(i+1+"C");
            // setting foreing key in theaterSeat Entity
            theaterSeatEntity.setTheaterEntity(theaterEntity);
            //adding to the theater seat list
            theaterSeatEntityList.add(theaterSeatEntity);
            theaterSeatEntity.setTheaterEntity(theaterEntity);
        }

        //Same method for premium seats
        for (int i =0 ; i<premium;i++){
            TheaterSeatEntity theaterSeatEntity = new TheaterSeatEntity();
            theaterSeatEntity.setSeatType(SeatType.PREMIUM);
            theaterSeatEntity.setSeatNo(i+1+"P");
            theaterSeatEntity.setTheaterEntity(theaterEntity);
            theaterSeatEntityList.add(theaterSeatEntity);

        }

        /// Adding theater list and then saving theater in the repositary
        // We dont need to save the theater Seat Entity as it is the child it will
        // be saved automatically when theater is saved
        theaterEntity.setTheaterSeatEntityList(theaterSeatEntityList);
        theaterRepositary.save(theaterEntity);
        return "Theater "+ theaterEntity.getName() +" is sucessfully added at "+theaterEntity.getLocation();

    }

    //Note we dont have null values in our databases
    public String deleteTheaterService(TheaterDeleteByNameAndLocationDTO theaterDeleteByNameAndLocationDTO){
        if(theaterDeleteByNameAndLocationDTO.getName()== null || theaterDeleteByNameAndLocationDTO.getLocation()==null){
            return "Please give non-null values";
        }
        List<TheaterEntity> theaterEntityList = theaterRepositary.findByNameAndLocation(theaterDeleteByNameAndLocationDTO.getName(),
                theaterDeleteByNameAndLocationDTO.getLocation());
        if (theaterEntityList.size()<1){
            return "No such theater found";
        }
        for (TheaterEntity theaterEntity: theaterEntityList
             ) {
            //Just deleting the Parent and no need to delete the child as it will automatically get deleted
            theaterRepositary.deleteById(theaterEntity.getId());
        }
        return "Theater deleted sucessfully";

    }
    public String deleteByIdTheaterService(int id){
        TheaterEntity theaterEntity = theaterRepositary.findById(id).get();

        theaterRepositary.deleteById(id);
        return "Theater with id "+id+" is deleted sucessfully";

    }


    public String deleteAllTheaterService(){

        List<TheaterEntity> theaterEntityList = theaterRepositary.findAll();
        if (theaterEntityList.size()<1){
            return "No such theater found";
        }
        for (TheaterEntity theaterEntity: theaterEntityList
        ) {
            //Just deleting the Parent and no need to delete the child as it will automatically get deleted
            theaterRepositary.deleteById(theaterEntity.getId());
        }
        return "All Theaters are deleted sucessfully";

    }


}
