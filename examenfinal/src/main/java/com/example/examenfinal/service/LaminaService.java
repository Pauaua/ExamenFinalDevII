package com.example.examenfinal.service;

import com.example.examenfinal.model.Album;
import com.example.examenfinal.model.Lamina;
import com.example.examenfinal.repository.AlbumRepository;
import com.example.examenfinal.repository.LaminaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaminaService {

    @Autowired
    private LaminaRepository laminaRepository;

    @Autowired
    private AlbumRepository albumRepository;

    public List<Lamina> getAllLaminas() {
        return laminaRepository.findAll();
    }

    public Optional<Lamina> getLaminaById(Long id) {
        return laminaRepository.findById(id);
    }

    public List<Lamina> getLaminasByAlbumId(Long albumId) {
        return laminaRepository.findByAlbumId(albumId);
    }

    public Lamina createLamina(Lamina lamina) {
        return laminaRepository.save(lamina);
    }

    public Lamina createLaminaWithAlbum(Long albumId, Lamina lamina) {
        Optional<Album> album = albumRepository.findById(albumId);
        if (album.isPresent()) {
            lamina.setAlbum(album.get());
            return laminaRepository.save(lamina);
        }
        return null;
    }

    public Lamina updateLamina(Long id, Lamina laminaDetails) {
        Lamina lamina = laminaRepository.findById(id).orElse(null);
        if (lamina != null) {
            lamina.setNombre(laminaDetails.getNombre());
            lamina.setImagen(laminaDetails.getImagen());
            lamina.setAlbum(laminaDetails.getAlbum());
            lamina.setNumeroLamina(laminaDetails.getNumeroLamina());
            lamina.setCantidadRepetidas(laminaDetails.getCantidadRepetidas());
            return laminaRepository.save(lamina);
        }
        return null;
    }

    public void deleteLamina(Long id) {
        laminaRepository.deleteById(id);
    }
}