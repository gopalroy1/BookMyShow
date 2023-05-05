package com.example.Book_My_show.DTO.EntryDTO;

import lombok.*;

import javax.persistence.Column;

@Data
public class UserEntryDTO {
    private String name;
    private int age;
    private String email;
    private String mobNo;
    private String address;
    private String password;
}
