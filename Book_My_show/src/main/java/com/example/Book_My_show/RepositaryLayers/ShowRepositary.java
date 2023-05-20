package com.example.Book_My_show.RepositaryLayers;

import com.example.Book_My_show.Entity.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface ShowRepositary extends JpaRepository<ShowEntity,Integer> {
    @Query(value = "select * from shows where show_time= :time",nativeQuery = true)
    List<ShowEntity> showByTime(String time);

}
