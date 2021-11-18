package ru.AlexaNovikova.rating.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.AlexaNovikova.rating.entity.Rating;


@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Page<Rating> findAllBy(Pageable pageable);

    Page<Rating> findAllByDate(String date, Pageable pageable);
}