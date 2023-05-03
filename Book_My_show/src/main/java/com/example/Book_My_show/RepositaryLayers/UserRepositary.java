package com.example.Book_My_show.RepositaryLayers;

import com.example.Book_My_show.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositary extends JpaRepository<UserEntity,Integer> {

}
