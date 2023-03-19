package com.example.Book_My_show.Models;

import javax.persistence.*;

@Entity
@Table(name="theater_table")
public class Theater {
    private String name;
    private String location;

    ///Connecting shows to theater
    @OneToMany(mappedBy = "theaterfor",cascade = CascadeType.ALL)
    private Shows showsfor;

    /////Constructor

    public Theater() {

    }
    /////Getetrs and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Shows getShowsfor() {
        return showsfor;
    }

    public void setShowsfor(Shows showsfor) {
        this.showsfor = showsfor;
    }
}
