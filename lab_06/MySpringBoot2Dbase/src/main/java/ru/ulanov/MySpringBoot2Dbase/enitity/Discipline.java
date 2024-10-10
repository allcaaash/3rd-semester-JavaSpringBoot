package ru.ulanov.MySpringBoot2Dbase.enitity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACADEMICDISCIPLINES")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "teacher")
    private String teacher;

    @Column(name = "hours")
    private Integer hours;
}
