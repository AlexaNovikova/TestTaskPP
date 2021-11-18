package ru.AlexaNovikova.rating.services;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import ru.AlexaNovikova.rating.entity.Rating;
import ru.AlexaNovikova.rating.repositories.RatingRepository;

@RequiredArgsConstructor
@Service
@CacheConfig(cacheNames = "ratingService")
public class RatingService {

    private final RatingRepository ratingRepository;

    @CachePut(key = "#rating.id")
    public void save(Rating rating) {
        ratingRepository.save(rating);
    }
}
