package com.example.Book_My_show.RepositaryLayers;

import com.example.Book_My_show.Entity.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepositary extends JpaRepository<TheaterEntity,Integer> {
    List<TheaterEntity> findByNameAndLocation(String name, String location);

    @Query(value = "select * from theater where name = :name",nativeQuery = true)
    List<TheaterEntity> findByName(String name);

}
