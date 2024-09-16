package com.example.MoviePlatform.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;
import com.example.MoviePlatform.model.Movie;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Integer> {
    
    // Buscar películas cuyo nombre coincida parcialmente con el valor dado, con ordenamiento
    List<Movie> findByNameLike(String name, Sort sort);

    // Buscar películas con una puntuación específica, con ordenamiento
    List<Movie> findByScore(Integer score, Sort sort);

    // Buscar películas con un estado específico, con ordenamiento
    List<Movie> findByStatus(Integer status, Sort sort);

    // Buscar películas cuyo nombre coincida parcialmente y tengan una puntuación específica, con ordenamiento
    List<Movie> findByNameLikeAndScore(String name, Integer score, Sort sort);

    // Buscar películas cuyo nombre coincida parcialmente y tengan un estado específico, con ordenamiento
    List<Movie> findByNameLikeAndStatus(String name, Integer status, Sort sort);

    // Buscar películas con una puntuación y estado específicos, con ordenamiento
    List<Movie> findByScoreAndStatus(Integer score, Integer status, Sort sort);

    // Buscar películas cuyo nombre coincida parcialmente, tengan una puntuación y un estado específicos, con ordenamiento
    List<Movie> findByNameLikeAndScoreAndStatus(String name, Integer score, Integer status, Sort sort);

    // Método para traer todas las películas con ordenamiento
    List<Movie> findAll(Sort sort);
}
