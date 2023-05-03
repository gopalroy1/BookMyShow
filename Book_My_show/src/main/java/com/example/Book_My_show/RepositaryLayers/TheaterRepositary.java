package com.example.Book_My_show.RepositaryLayers;

import com.example.Book_My_show.Entity.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepositary extends JpaRepository<TheaterEntity,Integer> {
    List<TheaterEntity> findByNameAndLocation(String name, String location);

}
