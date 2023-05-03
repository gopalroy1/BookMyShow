package com.example.Book_My_show.RepositaryLayers;

import com.example.Book_My_show.Entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepositary extends JpaRepository<MovieEntity,Integer> {
}
