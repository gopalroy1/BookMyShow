package com.example.Book_My_show.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usertable")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;

    @Column(unique = true,nullable = false)
    private String email;

    @NonNull
    @Column(unique = true)
    private String mobNo;

    private String address;
    private String password;

    ////////////////////Mapping starts from here////////

/////////////////// Parents
    /// wrt ticket entity
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<TicketsEntity> ticketBooked = new ArrayList<>();

}
