package com.example.examenfinal.model; 
public class LaminaDTO { 
    private String nombre; 
    private Integer numeroLamina; 
 
    public LaminaDTO() { 
    } 
 
    public LaminaDTO(String nombre, Integer numeroLamina) { 
        this.nombre = nombre; 
        this.numeroLamina = numeroLamina; 
    } 
 
    // Getters y Setters 
    public String getNombre() { 
        return nombre; 
    } 
 
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    } 
 
    public Integer getNumeroLamina() { 
        return numeroLamina; 
    } 
 
    public void setNumeroLamina(Integer numeroLamina) { 
        this.numeroLamina = numeroLamina; 
    } 
} 
