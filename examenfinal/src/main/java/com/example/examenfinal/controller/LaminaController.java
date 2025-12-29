package com.example.examenfinal.controller;

import com.example.examenfinal.model.Lamina;
import com.example.examenfinal.service.LaminaService;
import com.example.examenfinal.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/laminas")
public class LaminaController {

    @Autowired
    private LaminaService laminaService;

    @Value("${file.upload-dir:./src/main/resources/uploads/}")
    private String uploadDir;

    @GetMapping
    public ResponseEntity<List<Lamina>> getAllLaminas() {
        List<Lamina> laminas = laminaService.getAllLaminas();
        return new ResponseEntity<>(laminas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lamina> getLaminaById(@PathVariable Long id) {
        Optional<Lamina> lamina = laminaService.getLaminaById(id);
        return lamina.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/album/{albumId}")
    public ResponseEntity<List<Lamina>> getLaminasByAlbumId(@PathVariable Long albumId) {
        List<Lamina> laminas = laminaService.getLaminasByAlbumId(albumId);
        return new ResponseEntity<>(laminas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Lamina> createLamina(@RequestBody Lamina lamina) {
        Lamina createdLamina = laminaService.createLamina(lamina);
        return new ResponseEntity<>(createdLamina, HttpStatus.CREATED);
    }

    @PostMapping("/upload")
    public ResponseEntity<Lamina> createLaminaWithImage(@RequestParam("nombre") String nombre,
                                                        @RequestParam("albumId") Long albumId,
                                                        @RequestParam(value = "numeroLamina", required = false) Integer numeroLamina,
                                                        @RequestParam("imagen") MultipartFile imagen) {
        try {
            // Guardar la imagen y obtener el nombre del archivo
            String fileName = FileUploadUtil.saveFile(uploadDir, imagen);
            String imageUrl = "/uploads/" + fileName; // URL relativa para acceder a la imagen

            // Crear la lámina con la información proporcionada
            Lamina lamina = new Lamina();
            lamina.setNombre(nombre);
            lamina.setNumeroLamina(numeroLamina);
            lamina.setImagen(imageUrl);

            // Crear la lámina asociada al álbum
            Lamina createdLamina = laminaService.createLaminaWithAlbum(albumId, lamina);
            if (createdLamina != null) {
                return new ResponseEntity<>(createdLamina, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Álbum no encontrado
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lamina> updateLamina(@PathVariable Long id, @RequestBody Lamina laminaDetails) {
        Lamina updatedLamina = laminaService.updateLamina(id, laminaDetails);
        if (updatedLamina != null) {
            return new ResponseEntity<>(updatedLamina, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLamina(@PathVariable Long id) {
        laminaService.deleteLamina(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
