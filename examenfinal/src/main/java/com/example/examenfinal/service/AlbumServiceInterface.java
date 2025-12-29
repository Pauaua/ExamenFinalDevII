package com.example.examenfinal.service;

import com.example.examenfinal.model.Album;
import com.example.examenfinal.model.Lamina;
import com.example.examenfinal.model.LaminaDTO;
import com.example.examenfinal.model.LaminaEstado;
import java.util.List;

public interface AlbumServiceInterface {
    List<Album> getAllAlbumes();
    java.util.Optional<Album> getAlbumById(Long id);
    Album createAlbum(Album album);
    Album updateAlbum(Long id, Album albumDetails);
    void deleteAlbum(Long id);
    List<Lamina> agregarLaminas(Long albumId, List<LaminaDTO> laminasDTO);
    List<LaminaEstado> getLaminasFaltantes(Long albumId);
    List<LaminaEstado> getLaminasRepetidas(Long albumId);
}
