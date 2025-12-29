package com.example.examenfinal.service;

import com.example.examenfinal.model.Album;
import com.example.examenfinal.model.Lamina;
import com.example.examenfinal.model.LaminaDTO;
import com.example.examenfinal.model.LaminaEstado;
import com.example.examenfinal.repository.AlbumRepository;
import com.example.examenfinal.repository.LaminaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumService implements AlbumServiceInterface {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private LaminaRepository laminaRepository;

    @Override
    public List<Album> getAllAlbumes() {
        return albumRepository.findAll();
    }

    @Override
    public Optional<Album> getAlbumById(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Album updateAlbum(Long id, Album albumDetails) {
        Album album = albumRepository.findById(id).orElse(null);
        if (album != null) {
            album.setNombre(albumDetails.getNombre());
            album.setImagen(albumDetails.getImagen());
            album.setFechaLanzamiento(albumDetails.getFechaLanzamiento());
            album.setTipoLaminas(albumDetails.getTipoLaminas());
            return albumRepository.save(album);
        }
        return null;
    }

    @Override
    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }

    // Funcionalidades especiales para el manejo de láminas

    /**
     * Agrega múltiples láminas a un álbum
     */
    @Override
    public List<Lamina> agregarLaminas(Long albumId, List<LaminaDTO> laminasDTO) {
        Optional<Album> albumOpt = albumRepository.findById(albumId);
        if (albumOpt.isPresent()) {
            Album album = albumOpt.get();
            List<Lamina> laminas = new ArrayList<>();

            for (LaminaDTO laminaDTO : laminasDTO) {
                Lamina lamina = new Lamina();
                lamina.setNombre(laminaDTO.getNombre());
                lamina.setNumeroLamina(laminaDTO.getNumeroLamina());
                lamina.setAlbum(album);
                laminas.add(laminaRepository.save(lamina));
            }

            return laminas;
        }
        return new ArrayList<>();
    }

    /**
     * Obtiene la lista de láminas faltantes para un álbum
     */
    @Override
    public List<LaminaEstado> getLaminasFaltantes(Long albumId) {
        Optional<Album> albumOpt = albumRepository.findById(albumId);
        if (albumOpt.isPresent()) {
            Album album = albumOpt.get();
            List<Lamina> laminasExistentes = laminaRepository.findByAlbumId(albumId);

            // Suponiendo que el número total de láminas en un álbum se puede inferir del número más alto
            // En una implementación real, esto podría venir de una configuración del álbum
            int totalLaminas = 0;
            if (!laminasExistentes.isEmpty()) {
                totalLaminas = laminasExistentes.stream()
                    .mapToInt(l -> l.getNumeroLamina() != null ? l.getNumeroLamina() : 0)
                    .max()
                    .orElse(0);
            }

            // Si no hay láminas registradas, podríamos tener un valor por defecto o una configuración específica
            // Por ahora, asumiremos que si no hay láminas, no podemos determinar las faltantes
            if (totalLaminas == 0) {
                // En una implementación completa, aquí se podría obtener el número total de láminas
                // del álbum desde una configuración o desde metadatos
                return new ArrayList<>();
            }

            List<Integer> numerosExistentes = laminasExistentes.stream()
                .map(Lamina::getNumeroLamina)
                .filter(num -> num != null)
                .collect(Collectors.toList());

            List<LaminaEstado> faltantes = new ArrayList<>();
            for (int i = 1; i <= totalLaminas; i++) {
                if (!numerosExistentes.contains(i)) {
                    LaminaEstado laminaFaltante = new LaminaEstado();
                    laminaFaltante.setNombre("Lámina #" + i);
                    laminaFaltante.setNumeroLamina(i);
                    laminaFaltante.setCantidadRepetidas(0);
                    faltantes.add(laminaFaltante);
                }
            }

            return faltantes;
        }
        return new ArrayList<>();
    }

    /**
     * Obtiene la lista de láminas repetidas para un álbum con su cantidad
     */
    @Override
    public List<LaminaEstado> getLaminasRepetidas(Long albumId) {
        Optional<Album> albumOpt = albumRepository.findById(albumId);
        if (albumOpt.isPresent()) {
            List<Lamina> laminas = laminaRepository.findByAlbumId(albumId);

            return laminas.stream()
                .filter(lamina -> lamina.getCantidadRepetidas() != null && lamina.getCantidadRepetidas() > 0)
                .map(lamina -> {
                    LaminaEstado estado = new LaminaEstado();
                    estado.setNombre(lamina.getNombre());
                    estado.setNumeroLamina(lamina.getNumeroLamina());
                    estado.setCantidadRepetidas(lamina.getCantidadRepetidas());
                    return estado;
                })
                .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}