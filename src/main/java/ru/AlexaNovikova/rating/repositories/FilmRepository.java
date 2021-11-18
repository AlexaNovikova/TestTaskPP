package ru.AlexaNovikova.rating.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.AlexaNovikova.rating.entity.Film;

import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Optional<Film> findByTitle(String title);
}