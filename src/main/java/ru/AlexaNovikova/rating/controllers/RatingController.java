package ru.AlexaNovikova.rating.controllers;


import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.AlexaNovikova.rating.dtos.FilmDto;
import ru.AlexaNovikova.rating.services.FilmService;

import java.text.ParseException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rating")
public class RatingController {
    private final FilmService filmService;

    @GetMapping
    public Page<FilmDto> getAllFilms(@RequestParam(name = "date") String d,
                                     @RequestParam(name = "p", defaultValue = "1") int page) throws ParseException {
        if (page < 1) {
            page = 1;
        }
        String date = d.split("T", 2)[0].replace(".", "-");
        return filmService.findAll(date, page, 10);
    }

}
