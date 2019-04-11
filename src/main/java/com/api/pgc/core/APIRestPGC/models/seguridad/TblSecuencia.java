/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.seguridad;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_secuencias",
        indexes = {@Index(name = "idx_cod_secuencia", columnList = "COD_SECUENCIA" )},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"COD_SECUENCIA"})})
public class TblSecuencia {
    // Propiedades de la tabla
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_SECUENCIA", columnDefinition = "serial")
    // @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idSecuencia;

    @Column(name = "COD_SECUENCIA", nullable = false, length=20)
    // @ApiModelProperty(notes = "Descripcion del Rol", required = true)
    private String codSecuencia;

    @Column(name = "DESC_SECUENCIA", nullable = false, length=20)
    // @ApiModelProperty(notes = "Descripcion del Rol", required = true)
    private String descSecuencia;

    @Column(name = "valor1", nullable = false, length=10)
    // @ApiModelProperty(notes = "Valor 1", required = true)
    private long valor1;

    @Column(name = "valor2", nullable = false, length=10)
    // @ApiModelProperty(notes = "Valor 1", required = true)
    private long valor2;

    @Column(name = "FECHA_CREACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    // @ApiModelProperty(notes = "Fecha de Creacion del Usuario, formato hh:mm:ss")
    private Date fechaCreacion;

    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    // @ApiModelProperty(notes = "Hora de Creacion del Usuario, formato hh:mm:ss")
    private Date horaCreacion = new Date();

    @Column(name = "HABILITADA", columnDefinition = " boolean DEFAULT true")
    // @ApiModelProperty(notes = "Habilitado")
    private boolean habilitada;

    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
    // @ApiModelProperty(notes = "Hora de Creacion del Usuario, formato hh:mm:ss")
    private Date fechaActualizacion = new Date();

    @Column(name = "HORA_ACTUALIZACION")
    @Temporal(TemporalType.TIME)
    private Date horaActualizacion = new Date();


    // Mapeo de la Relacion de la Tabla de Secuencia con Usuarios
    // Muchos Secuencia = 1 Usuario
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USUARIO_MOD", referencedColumnName = "ID_USUARIO")
    private TblUsuarios idUsuarioMod;


    /**
     * Constructor de la Clase
     */
    public TblSecuencia() {
    }


    /**
     * Metodos Getters y Setters
     */
    public long getIdSecuencia() {
        return idSecuencia;
    }

    public void setIdSecuencia(long idSecuencia) {
        this.idSecuencia = idSecuencia;
    }

    public String getCodSecuencia() {
        return codSecuencia;
    }

    public void setCodSecuencia(String codSecuencia) {
        this.codSecuencia = codSecuencia;
    }

    public String getDescSecuencia() {
        return descSecuencia;
    }

    public void setDescSecuencia(String descSecuencia) {
        this.descSecuencia = descSecuencia;
    }

    public long getValor1() {
        return valor1;
    }

    public void setValor1(long valor1) {
        this.valor1 = valor1;
    }

    public long getValor2() {
        return valor2;
    }

    public void setValor2(long valor2) {
        this.valor2 = valor2;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(Date horaCreacion) {
        this.horaCreacion = horaCreacion;
    }

    public boolean isHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    public TblUsuarios getIdUsuarioMod() {
        return idUsuarioMod;
    }

    public void setIdUsuarioMod(TblUsuarios idUsuarioMod) {
        this.idUsuarioMod = idUsuarioMod;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getHoraActualizacion() {
        return horaActualizacion;
    }

    public void setHoraActualizacion(Date horaActualizacion) {
        this.horaActualizacion = horaActualizacion;
    }
}
