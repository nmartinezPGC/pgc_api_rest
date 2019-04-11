/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.mantenimiento.actividades;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_moneda_actividad",
        indexes = {@Index(name = "idx_codigo_moneda", columnList = "CODIGO_MONEDA" )},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_MONEDA"})})
public class TblMonedaActividad {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_MONEDA_ACTIVIDAD", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idMonedaActividad;

    @Column(name = "CODIGO_MONEDA", nullable = false, length=10)
    @ApiModelProperty(notes = "Codigo de la Moneda de la Actividad", required = true)
    private String codigoMoneda;

    @Column(name = "NOMBRE_MONEDA", nullable = false, length=100)
    @ApiModelProperty(notes = "Nombre de la Moneda de la Actividad", required = true)
    private String nombreMoneda;

    @Column(name = "TASA_CAMBIARIA", nullable = false)
    @ApiModelProperty(notes = "Tasa Cambiaria de la Moneda de la Actividad", required = true)
    private String tasaCambiaria;

    @Column(name = "HABILITADA" )
    private boolean habilitada = true;


    /*
     * Constructor vacio de la Clase, solo para Jpa
     */
    public TblMonedaActividad() {
        //Este lo usa Jpa para realizar los Mapping
    }


    /*
     * Metodos Getters y Setters de la Clase
     * 2019-01-11
     */

    public long getIdMonedaActividad() {
        return idMonedaActividad;
    }

    public void setIdMonedaActividad(long idMonedaActividad) {
        this.idMonedaActividad = idMonedaActividad;
    }

    public String getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(String codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public String getNombreMoneda() {
        return nombreMoneda;
    }

    public void setNombreMoneda(String nombreMoneda) {
        this.nombreMoneda = nombreMoneda;
    }

    public String getTasaCambiaria() {
        return tasaCambiaria;
    }

    public void setTasaCambiaria(String tasaCambiaria) {
        this.tasaCambiaria = tasaCambiaria;
    }

    public boolean isHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }
}
