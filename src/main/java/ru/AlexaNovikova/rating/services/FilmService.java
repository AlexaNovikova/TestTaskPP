package ru.AlexaNovikova.rating.services;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.AlexaNovikova.rating.dtos.FilmDto;
import ru.AlexaNovikova.rating.entity.Film;
import ru.AlexaNovikova.rating.repositories.FilmRepository;
import ru.AlexaNovikova.rating.repositories.RatingRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "filmervice")
public class FilmService {

    private final FilmRepository filmRepository;
    private final RatingRepository ratingRepository;
//
//    @Cacheable
    public Page<FilmDto> findAll(String date, int page, int pageSize) {
        System.out.println(date.toString());
        return ratingRepository.findAllByDate(date, PageRequest.of(page - 1, pageSize)).map(FilmDto::new);

    }

//   @CachePut(key = "#film.id")
    public void save(Film film) {
        filmRepository.save(film);
    }

//   @Cacheable
    public Optional<Film> findByTitle(String title) {
        return filmRepository.findByTitle(title);
    }
}
