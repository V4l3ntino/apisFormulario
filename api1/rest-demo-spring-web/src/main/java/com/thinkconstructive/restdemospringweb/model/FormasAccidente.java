package com.thinkconstructive.restdemospringweb.model;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelSheet;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "apiapp_formaproducirseaccidente")
@ExcelSheet("formasAccidente")
public class FormasAccidente {
    @Id
    @ExcelCell(0)
    private Integer id;
    @Column(name = "nombre")
    @ExcelCell(1)
    private String nombre;

    public FormasAccidente() {
    }

    public FormasAccidente(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }


    @Override
    public String toString() {
        return "FormasAccidente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
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
}
