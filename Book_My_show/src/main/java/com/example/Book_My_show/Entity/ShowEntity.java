package com.example.Book_My_show.Entity;

import com.example.Book_My_show.Enums.ShowType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Shows")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String showName;

    private LocalDate showDate;
    private LocalTime showTime;

    @Enumerated(value = EnumType.STRING)
    private ShowType showType;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    /////Mapping starts here////////////

    ///////////////Parents
    ///wrt tickets
    @OneToMany(mappedBy = "showEntity",cascade = CascadeType.ALL)
    private List<TicketsEntity> ticketsEntityList = new ArrayList<>();

    /// wrt show seats
    @OneToMany(mappedBy = "shows", cascade = CascadeType.ALL)
    private List<ShowSeatEntity> showSeatEntities = new ArrayList<>();

    /////////////childs
    //// wrt to TheaterEntity
    @JoinColumn
    @ManyToOne
    private TheaterEntity theaterEntity;

    /// wrt to MoviesEntity
    @JoinColumn
    @ManyToOne
    private MovieEntity movieEntity;




}
