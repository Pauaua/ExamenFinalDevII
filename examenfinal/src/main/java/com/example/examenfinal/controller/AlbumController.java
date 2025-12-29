package com.example.examenfinal.controller;

import com.example.examenfinal.model.Album;
import com.example.examenfinal.model.LaminaDTO;
import com.example.examenfinal.model.LaminaEstado;
import com.example.examenfinal.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/albumes")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbumes() {
        List<Album> albumes = albumService.getAllAlbumes();
        return new ResponseEntity<>(albumes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        Optional<Album> album = albumService.getAlbumById(id);
        return album.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        Album createdAlbum = albumService.createAlbum(album);
        return new ResponseEntity<>(createdAlbum, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album albumDetails) {
        Album updatedAlbum = albumService.updateAlbum(id, albumDetails);
        if (updatedAlbum != null) {
            return new ResponseEntity<>(updatedAlbum, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Funcionalidades especiales para el manejo de láminas

    /**
     * Agrega múltiples láminas a un álbum
     */
    @PostMapping("/{albumId}/laminas")
    public ResponseEntity<?> agregarLaminas(@PathVariable Long albumId, @RequestBody List<LaminaDTO> laminasDTO) {
        List<com.example.examenfinal.model.Lamina> laminas = albumService.agregarLaminas(albumId, laminasDTO);
        if (!laminas.isEmpty()) {
            return new ResponseEntity<>(laminas, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Álbum no encontrado
        }
    }

    /**
     * Obtiene la lista de láminas faltantes para un álbum
     */
    @GetMapping("/{albumId}/laminas/faltantes")
    public ResponseEntity<List<LaminaEstado>> getLaminasFaltantes(@PathVariable Long albumId) {
        List<LaminaEstado> laminasFaltantes = albumService.getLaminasFaltantes(albumId);
        return new ResponseEntity<>(laminasFaltantes, HttpStatus.OK);
    }

    /**
     * Obtiene la lista de láminas repetidas para un álbum con su cantidad
     */
    @GetMapping("/{albumId}/laminas/repetidas")
    public ResponseEntity<List<LaminaEstado>> getLaminasRepetidas(@PathVariable Long albumId) {
        List<LaminaEstado> laminasRepetidas = albumService.getLaminasRepetidas(albumId);
        return new ResponseEntity<>(laminasRepetidas, HttpStatus.OK);
    }
}