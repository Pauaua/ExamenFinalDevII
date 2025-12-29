package com.example.examenfinal.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "albumes")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String imagen; // URL o ruta de la imagen del álbum

    @Column(name = "fecha_lanzamiento")
    private LocalDate fechaLanzamiento;

    @Column(name = "tipo_laminas")
    private String tipoLaminas; // tipo de láminas que contiene el álbum

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lamina> laminas;

    // Constructores
    public Album() {
    }

    public Album(String nombre, String imagen, LocalDate fechaLanzamiento, String tipoLaminas) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.fechaLanzamiento = fechaLanzamiento;
        this.tipoLaminas = tipoLaminas;
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

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getTipoLaminas() {
        return tipoLaminas;
    }

    public void setTipoLaminas(String tipoLaminas) {
        this.tipoLaminas = tipoLaminas;
    }

    public List<Lamina> getLaminas() {
        return laminas;
    }

    public void setLaminas(List<Lamina> laminas) {
        this.laminas = laminas;
    }
}