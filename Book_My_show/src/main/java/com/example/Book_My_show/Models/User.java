package com.example.Book_My_show.Models;

import javax.persistence.*;

@Entity
@Table(name="user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int age;
    private int mobNo;
    private String Address;


    ///Connecting user and tickets
    @OneToMany(mappedBy = "userfor",cascade = CascadeType.ALL)
    private Ticket ticketfor;
    ///constructor

    public User() {
    }

    ///Getter and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMobNo() {
        return mobNo;
    }

    public void setMobNo(int mobNo) {
        this.mobNo = mobNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Ticket getTicketfor() {
        return ticketfor;
    }

    public void setTicketfor(Ticket ticketfor) {
        this.ticketfor = ticketfor;
    }
}
