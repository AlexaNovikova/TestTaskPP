package ru.AlexaNovikova.rating.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.AlexaNovikova.rating.entity.Rating;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class FilmDto implements Serializable {
    private Long id;

    private String title;

    private double ratingValue;

    private int voices;

    public FilmDto(Rating rating) {
        this.id = rating.getId();
        this.title = rating.getFilm().getTitle();
        this.ratingValue = rating.getValue();
        this.voices = rating.getVoices();
    }
}
