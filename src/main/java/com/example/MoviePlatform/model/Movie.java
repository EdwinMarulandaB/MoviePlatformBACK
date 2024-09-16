package com.example.MoviePlatform.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieid; // Identificador único de la película, generado automáticamente

    @Column(nullable = false, length = 255)
    private String name; // Nombre de la película, no puede ser nulo y tiene un tamaño máximo de 255 caracteres

    @Column(length = 255)
    private String image; // URL de la imagen de la película, puede ser nulo y tiene un tamaño máximo de 255 caracteres

    @Column(columnDefinition = "TEXT")
    private String description; // Descripción de la película, puede ser nula y puede contener texto largo

    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private int score; // Puntuación de la película, no puede ser nula y tiene un valor por defecto de 0

    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    private int status; // Estado de la película, no puede ser nulo y tiene un valor por defecto de 1

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt; // Timestamp de creación de la película, no puede ser nulo y se establece por defecto en el momento de la creación

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt; // Timestamp de la última actualización de la película, no puede ser nulo y se actualiza automáticamente en cada modificación

}
