package com.example.examenfinal.repository;

import com.example.examenfinal.model.Lamina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaminaRepository extends JpaRepository<Lamina, Long> {
    List<Lamina> findByAlbumId(Long albumId);
}