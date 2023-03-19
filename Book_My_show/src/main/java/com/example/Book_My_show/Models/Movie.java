package com.example.Book_My_show.Models;

import com.example.Book_My_show.Enums.Genere;
import com.example.Book_My_show.Enums.Languages;

import javax.persistence.*;

@Entity
@Table(name="movies_table")
public class Movie {

    private String name;
    private double rating;
    private double duration;

    @Enumerated(value = EnumType.STRING)
    private Genere genere;

    @Enumerated(value = EnumType.STRING)
    private Languages languages;

    ///connecting shows to movie
    @OneToMany(mappedBy = "moviefor",cascade = CascadeType.ALL)
    private Shows showsfor;

    /////Constructor

    public Movie() {
    }


    /////////////Getter and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    public Languages getLanguages() {
        return languages;
    }

    public void setLanguages(Languages languages) {
        this.languages = languages;
    }

    public Shows getShowsfor() {
        return showsfor;
    }

    public void setShowsfor(Shows showsfor) {
        this.showsfor = showsfor;
    }
}

