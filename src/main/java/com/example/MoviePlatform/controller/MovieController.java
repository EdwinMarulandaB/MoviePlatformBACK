package com.example.MoviePlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MoviePlatform.Dto.MovieDTO;
import com.example.MoviePlatform.model.Movie;
import com.example.MoviePlatform.service.MovieService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin()
@RestController
@RequestMapping(path = "api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService; // Inyección del servicio de películas

    // Método para convertir un DTO a una entidad Movie
    private Movie convertToEntity(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setMovieid(movieDTO.getMovieid());
        movie.setName(movieDTO.getName());
        movie.setImage(movieDTO.getImage());
        movie.setDescription(movieDTO.getDescription());
        movie.setScore(movieDTO.getScore());
        movie.setStatus(movieDTO.getStatus());
        return movie;
    }

    // Método para obtener todas las películas con filtros opcionales y opciones de ordenación
    @GetMapping
    public List<Movie> getAll(
        @RequestParam(required = false) String name, // Filtro por nombre (opcional)
        @RequestParam(required = false) Integer score, // Filtro por puntuación (opcional)
        @RequestParam(required = false) Integer status, // Filtro por estado (opcional)
        @RequestParam(defaultValue = "movieid") String sortBy, // Campo por el cual ordenar (por defecto 'movieid')
        @RequestParam(defaultValue = "asc") String sortDirection) { // Dirección de ordenamiento (asc/desc, por defecto 'asc')
        return movieService.getMovies(name, score, status, sortBy, sortDirection); // Llama al servicio para obtener las películas
    }

    // Método para obtener una película específica por su ID
    @GetMapping("/{movieid}")
    public ResponseEntity<Movie> getByid(@PathVariable("movieid") int movieid) {
        return movieService.getMovie(movieid); // Llama al servicio para obtener la película por ID
    }

    // Método para guardar o actualizar una película
    @PostMapping
    public void saveUpdate(@RequestBody MovieDTO movieDTO) {
        Movie movie = convertToEntity(movieDTO); // Convierte el DTO a una entidad Movie
        movieService.saveOrUpdate(movie); // Llama al servicio para guardar o actualizar la película
    }

    // Método para eliminar una película por su ID
    @DeleteMapping("/{movieid}")
    public void delete(@PathVariable("movieid") int movieid) {
        movieService.delete(movieid); // Llama al servicio para eliminar la película por ID
    }
}
