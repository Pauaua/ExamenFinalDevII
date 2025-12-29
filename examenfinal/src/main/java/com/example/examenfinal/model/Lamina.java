package com.example.examenfinal.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "laminas")
public class Lamina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String imagen; // URL o ruta de la imagen de la lámina

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @Column(name = "numero_lamina")
    private Integer numeroLamina; // número de la lámina en el álbum

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "cantidad_repetidas")
    private Integer cantidadRepetidas = 0; // cantidad de veces que se tiene la lámina repetida

    // Constructores
    public Lamina() {
    }

    public Lamina(String nombre, String imagen, Album album, Integer numeroLamina) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.album = album;
        this.numeroLamina = numeroLamina;
        this.fechaCreacion = LocalDate.now();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Integer getNumeroLamina() {
        return numeroLamina;
    }

    public void setNumeroLamina(Integer numeroLamina) {
        this.numeroLamina = numeroLamina;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getCantidadRepetidas() {
        return cantidadRepetidas;
    }

    public void setCantidadRepetidas(Integer cantidadRepetidas) {
        this.cantidadRepetidas = cantidadRepetidas;
    }
}