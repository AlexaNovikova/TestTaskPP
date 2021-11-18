package ru.AlexaNovikova.rating.services;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.AlexaNovikova.rating.entity.Film;
import ru.AlexaNovikova.rating.entity.Rating;
import ru.AlexaNovikova.rating.parser.ElementParser;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URL;


@Service
@RequiredArgsConstructor
public class URLService {
    private final RatingService ratingService;
    private final FilmService filmService;

    @PostConstruct
    public void init() {
        addFilmsToDB();
    }

    @Scheduled(cron = "@daily")
    public void refreshDB() {
        addFilmsToDB();
    }

    public void addFilmsToDB() {
        System.out.println("Выполенено заполнение DB");
        try {
            Document doc = null;
                doc = Jsoup.connect("https://www.kinopoisk.ru/lists/top250/").get();
                String title = doc.title();
                System.out.println(title);
                Elements elements = doc.select("div.desktop-rating-selection-film-item");
                System.out.println(title);
                for (
                        Element element : elements) {
                    Rating rating = ElementParser.parseElementToRating(element);
                    Film film = ElementParser.parseElementToFilm(element);
                    if (!filmService.findByTitle(film.getTitle()).isPresent()) {
                        filmService.save(film);
                    }
                    Film filmFromDB = filmService.findByTitle(film.getTitle()).get();
                    rating.setFilm(filmFromDB);
                    System.out.println(filmFromDB.getId());
                    ratingService.save(rating);
                }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}