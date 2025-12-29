package com.example.examenfinal.model;

public class LaminaEstado {
    private String nombre;
    private Integer numeroLamina;
    private Integer cantidadRepetidas;

    public LaminaEstado() {
    }

    public LaminaEstado(String nombre, Integer numeroLamina, Integer cantidadRepetidas) {
        this.nombre = nombre;
        this.numeroLamina = numeroLamina;
        this.cantidadRepetidas = cantidadRepetidas;
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

    public Integer getCantidadRepetidas() {
        return cantidadRepetidas;
    }

    public void setCantidadRepetidas(Integer cantidadRepetidas) {
        this.cantidadRepetidas = cantidadRepetidas;
    }
}
