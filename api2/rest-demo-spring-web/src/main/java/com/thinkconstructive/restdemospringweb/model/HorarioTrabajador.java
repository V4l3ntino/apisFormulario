package com.thinkconstructive.restdemospringweb.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "horarios_trabajador")
public class HorarioTrabajador {
    @Column(name = "empresa_codigo_terminal")
    private Integer empresa_codigo_terminal;
    @Id
    @Column(name = "desde")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "nombre")
    private String turno;
    @Column(name = "rotativo")
    private Integer rotacion;
    @Column(name = "codigo_empleado")
    private String codigo_empleado;

    public HorarioTrabajador() {
    }

    public HorarioTrabajador(Integer codigo_empleado, Date fecha, String turno, Integer rotacion) {
        this.empresa_codigo_terminal = codigo_empleado;
        this.fecha = fecha;
        this.turno = turno;
        this.rotacion = rotacion;
    }

    @Override
    public String toString() {
        return "HorarioTrabajador{" +
                "empresa_codigo_terminal=" + empresa_codigo_terminal +
                ", fecha='" + fecha + '\'' +
                ", turno='" + turno + '\'' +
                ", rotacion=" + rotacion +
                '}';
    }

    public String getCodigo_empleado() {
        return codigo_empleado;
    }

    public Integer getEmpresa_codigo_terminal() {
        return empresa_codigo_terminal;
    }

    public void setEmpresa_codigo_terminal(Integer empresa_codigo_terminal) {
        this.empresa_codigo_terminal = empresa_codigo_terminal;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Integer getRotacion() {
        return rotacion;
    }
}
