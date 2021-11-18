package ru.AlexaNovikova.rating.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table(name = "rating")
public class Rating {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "dateR")
    private String date;

    @Column(name = "valueR")
    private double value;

    @Column(name = "voices")
    private int voices;

}
