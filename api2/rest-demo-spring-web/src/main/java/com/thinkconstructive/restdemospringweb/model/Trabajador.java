package com.thinkconstructive.restdemospringweb.model;

import org.springframework.stereotype.Component;

@Component
public class Trabajador {
    private String nombre;
    private String apellido;
    private Integer experiencia;

    public Trabajador() {
    }

    public Trabajador(String nombre, String apellido, Integer experiencia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.experiencia = experiencia;
    }

    @Override
    public String toString() {
        return "Trabajador{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", experiencia='" + experiencia + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }



    public Integer getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
    }
}
