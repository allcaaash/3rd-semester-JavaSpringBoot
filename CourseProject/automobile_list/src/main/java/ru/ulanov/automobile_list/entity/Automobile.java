package ru.ulanov.automobile_list.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AUTOMOBILES")
public class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "mark")
    private String mark;

    @Column(name = "model")
    private String model;

    @Column(name = "year_of_release")
    private String year;

    @Column(name = "price")
    private int price;

//    @Column(name = "userId")
//    private long userId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
