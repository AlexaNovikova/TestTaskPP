package ru.AlexaNovikova.rating.parser;

import org.jsoup.nodes.Element;
import ru.AlexaNovikova.rating.entity.Film;
import ru.AlexaNovikova.rating.entity.Rating;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ElementParser {

    public static Rating parseElementToRating(Element element) {
        Rating rating = new Rating();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        rating.setDate(today.format(formatter));
        rating.setValue(Double.parseDouble(element.select("span.rating__value_positive").text()));
        rating.setVoices(Integer.parseInt(element.select("span.rating__count").text().replaceAll("\\s", "")));
        return rating;
    }

    public static Film parseElementToFilm(Element element) {

        String originalName = (element.select("p.selection-film-item-meta__original-name").text().split(" ").length > 1 ?
                element.select("p.selection-film-item-meta__original-name").text().split(", ", 3)[0] :
                element.select("p.selection-film-item-meta__name").text());

        int length = element.select("p.selection-film-item-meta__original-name").text().split(" ").length;

        int year = Integer.parseInt(element.select("p.selection-film-item-meta__original-name").text().split(" ")[length - 1]);

        Film film = new Film();
        film.setTitle(originalName);
        film.setYear(year);
        return film;
    }
}
