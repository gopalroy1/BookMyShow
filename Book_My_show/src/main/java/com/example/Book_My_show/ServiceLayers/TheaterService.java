package com.example.Book_My_show.ServiceLayers;

import com.example.Book_My_show.Converters.TheaterConverter;
import com.example.Book_My_show.DTO.DeleteDTO.TheaterDeleteByNameAndLocationDTO;
import com.example.Book_My_show.DTO.EntryDTO.TheaterEntryDTO;
import com.example.Book_My_show.Entity.MovieEntity;
import com.example.Book_My_show.Entity.ShowEntity;
import com.example.Book_My_show.Entity.TheaterEntity;
import com.example.Book_My_show.Entity.TheaterSeatEntity;
import com.example.Book_My_show.Enums.SeatType;
import com.example.Book_My_show.RepositaryLayers.MovieRepositary;
import com.example.Book_My_show.RepositaryLayers.ShowRepositary;
import com.example.Book_My_show.RepositaryLayers.TheaterRepositary;
import com.example.Book_My_show.RepositaryLayers.TheaterSeatRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;

import static net.bytebuddy.matcher.ElementMatchers.is;

@Service
public class TheaterService {

    @Autowired
    TheaterRepositary theaterRepositary;
    @Autowired
    TheaterSeatRepositary theaterSeatRepositary;
    @Autowired
    MovieRepositary movieRepositary;
    @Autowired
    ShowRepositary showRepositary;
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

    public String getShowsByTheaterAndMovie  (int movieId, int theaterId) throws Exception{
        MovieEntity movieEntity = movieRepositary.findById(movieId).get();
        List<ShowEntity> showEntityList= movieEntity.getShowEntityList();
        List<ShowEntity> ansList = new ArrayList<>();
        String ans = "";
        for (ShowEntity showEntity: showEntityList
             ) {
            if (showEntity.getTheaterEntity().getId()==theaterId){
                ansList.add(showEntity);
                ans+= showEntity.getShowName()+" "+showEntity.getShowDate()+" \n";
            }
        }
        if (ansList.size()<=0){
            throw  new Exception("No show Availble");
        }
        return ans;

    }
    public String uniquelocationOfATheaterService(String name) throws Exception{
        //Getting all theater by a particular name
        List<TheaterEntity> theaterEntityList = theaterRepositary.findByName(name);

        //finding uniqur location in the list
        //List to check if a name has came or not
        int count=0;
        String ans = "";
        List<String> uniqueName = new ArrayList<>();
        for (TheaterEntity theaterEntity:theaterEntityList
             ) {
            //checking and then simply ading in the count and ans
            if (uniqueLocationfun(uniqueName,theaterEntity.getLocation())){
                uniqueName.add(theaterEntity.getLocation());
                count++;
                ans+=theaterEntity.getLocation()+" ";
            }
        }
        if (uniqueName.size()<=0){
            throw new Exception("Theater doesn't exists");
        }
        return count+" :"+ans;
    }
    //To check unique location or not
    public Boolean uniqueLocationfun(List<String> uniqueName,String theaterName){
        for (String s:uniqueName
             ) {
            if (s.equals(theaterName)){
                return  false;
            }
        }
        return true;
    }
    public String theaterListByTime(String time) throws Exception{
        List<ShowEntity> showEntityList= showRepositary.showByTime(time);
        if(showEntityList.size()==0){
            throw new Exception("No show exists");
        }
        //Iterating and ssaving the answer
        // Using hashmap to check if it has a unique theater or not
        int showCount = showEntityList.size();
        HashMap<Integer,Boolean> map = new HashMap<>();
        String ans = "";
        for (ShowEntity showEntity:showEntityList
             ) {
            int theaterId = showEntity.getTheaterEntity().getId();
            if (!map.containsKey(theaterId)){
                map.put(theaterId,true);
                ans+= showEntity.getTheaterEntity().getName()+" ";
            }
        }
        return  "Total "+showCount+" show exists in "+map.size()+" unique theater which are "+ans;
    }


}
