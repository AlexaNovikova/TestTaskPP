package ru.AlexaNovikova.rating.entity;


import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "Films")
public class Film {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "film")
    private List<Rating> ratingList;

    @Column(name = "title")
    private String title;

    @Column(name = "yearN")
    private int year;

}
