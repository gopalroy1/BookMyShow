package com.example.Book_My_show.Entity;

import com.example.Book_My_show.Enums.Genre;
import com.example.Book_My_show.Enums.Languages;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Movies")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity {
    /////////Declaring primary and other variables for movie class
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true,nullable = false)
    private String movieName;

    @Column(name = "imdb_rating")
    private double rating;
    @Column(name = "duration_in_hr")
    private int duration;

    @Enumerated(value = EnumType.STRING)
    private Languages languages;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    //////////////Mapping//////

    ///////////Parents
    ///wrt ShowEntity
    @OneToMany(mappedBy = "movieEntity", cascade ={CascadeType.MERGE,CascadeType.PERSIST})
    private List<ShowEntity> showEntityList = new ArrayList<>();




}
