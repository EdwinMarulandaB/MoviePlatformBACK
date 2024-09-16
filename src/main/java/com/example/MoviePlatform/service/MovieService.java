package com.example.MoviePlatform.service;

import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.MoviePlatform.model.Movie;
import com.example.MoviePlatform.repository.IMovieRepository;

@Service
public class MovieService {

    @Autowired
    IMovieRepository movieRepository;
    
    // Método para obtener una lista de películas basadas en diferentes criterios de búsqueda y ordenamiento
    public List<Movie> getMovies(String name, Integer score, Integer status, String sortBy, String sortDirection) {
        // Configuración de ordenamiento
        Sort sort = Sort.by(Sort.Order.by(sortBy));
        if ("desc".equalsIgnoreCase(sortDirection)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        
        // Patrones para búsqueda por nombre
        String pattern = "%" + name + "%";

        // Condiciones para determinar qué método del repositorio utilizar basado en los parámetros proporcionados
        if (name != null && score != null && status != null) {
            return movieRepository.findByNameLikeAndScoreAndStatus(pattern, score, status, sort);
        } else if (name != null && score != null) {
            return movieRepository.findByNameLikeAndScore(pattern, score, sort);
        } else if (name != null && status != null) {
            return movieRepository.findByNameLikeAndStatus(pattern, status, sort);
        } else if (score != null && status != null) {
            return movieRepository.findByScoreAndStatus(score, status, sort);
        } else if (name != null) {
            return movieRepository.findByNameLike(pattern, sort);
        } else if (score != null) {
            return movieRepository.findByScore(score, sort);
        } else if (status != null) {
            return movieRepository.findByStatus(status, sort);
        } else {
            return movieRepository.findAll(sort); // Sin filtros, solo ordenamiento
        }
    }

    // Método para obtener una película por su id
    public ResponseEntity<Movie> getMovie(int id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            return ResponseEntity.ok(movie.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        }
    }

    // Método para guardar o actualizar una película
    public Movie saveOrUpdate(Movie movie) {
        // Validar que el nombre y la descripción no estén vacíos
        if (movie.getName() == null || movie.getDescription().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie name or Description cannot be empty");
        }

        if (movie.getMovieid() > 0) {
            // Si el id es mayor a 0, se trata de una actualización
            Optional<Movie> existingMovie = movieRepository.findById(movie.getMovieid());
            if (existingMovie.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found for update");
            }
            movie.setUpdatedAt(LocalDateTime.now()); // Actualiza el timestamp de la última modificación
        } else {
            // Si el id es 0 o menor, se trata de una creación
            movie.setCreatedAt(LocalDateTime.now()); // Establece el timestamp de creación
            movie.setUpdatedAt(LocalDateTime.now()); // Establece el timestamp de la última modificación
        }

        return movieRepository.save(movie); // Guarda o actualiza la película en la base de datos
    }

    // Método para eliminar una película por su id
    public void delete(int id) {
        if (!movieRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found for deletion");
        }
        movieRepository.deleteById(id); // Elimina la película de la base de datos
    }
}
