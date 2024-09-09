package com.thinkconstructive.restdemospringweb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "apiapp_trabajador")
public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer experiencia;

    public Trabajador() {
    }

    public Trabajador(Integer id, String nombre, String apellido, Integer experiencia) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.experiencia = experiencia;
    }

    @Override
    public String toString() {
        return "Trabajador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", experiencia=" + experiencia +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
