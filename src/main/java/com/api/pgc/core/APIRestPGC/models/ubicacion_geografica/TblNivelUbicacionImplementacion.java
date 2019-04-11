/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.ubicacion_geografica;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_nivel_ubicacion_implementacion",
        indexes = {@Index(name = "idx_codigo_nivel_ubicacion", columnList = "CODIGO_NIVEL_UBICACION")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_NIVEL_UBICACION"})})
public class TblNivelUbicacionImplementacion {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NIVEL_UBICACION", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idNivelUbicacion;

    @Column(name = "CODIGO_NIVEL_UBICACION", nullable = false, length = 10)
    @ApiModelProperty(notes = "Codigo de Nivel de Ubicación de Implementación", required = true)
    private String codigoNivelUbicacion;

    @Column(name = "NOMBRE_NIVEL_UBICACION", nullable = false, length = 150)
    @ApiModelProperty(notes = "Nombre de Nivel de Ubicacion de Implementación", required = true)
    private String nombreNivelUbicacion;

    @Column(name = "DESCRIPCION_NIVEL_UBICACION", nullable = true, length = 300)
    @ApiModelProperty(notes = "Descripcion de Nivel de Ubicacion de Implementación")
    private String descripcionNivelUbicacion;

    @Column(name = "ACTIVO", nullable = true)
    @ApiModelProperty(notes = "Indicador de Activo")
    private boolean activo;

    // Mapeo de la Relacion de la Tabla de Nivel Ubicacion con Nivel Implementacion
    // Muchos Niveles de Ubicacion = 1 Nivel de Implementacion
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_NIVEL_IMPLEMENTACION", referencedColumnName = "ID_NIVEL")
    @ApiModelProperty(notes = "Entidad del Nivel de Implementacion de la Ubicacion, se envia desde un Json (\"idNivelImplementacion\": { \"idNivel\": \"valor\" })")
    private TblNivelImplementacion idNivelImplementacion;

    /**
     * Constructor de la Clase, JPA realiza los CRUD
     */
    public TblNivelUbicacionImplementacion() {
    }

    /**
     * Metodos Getters y Setters de la Clase
     */
    public long getIdNivelUbicacion() {
        return idNivelUbicacion;
    }

    public void setIdNivelUbicacion(long idNivelUbicacion) {
        this.idNivelUbicacion = idNivelUbicacion;
    }

    public String getCodigoNivelUbicacion() {
        return codigoNivelUbicacion;
    }

    public void setCodigoNivelUbicacion(String codigoNivelUbicacion) {
        this.codigoNivelUbicacion = codigoNivelUbicacion;
    }

    public String getNombreNivelUbicacion() {
        return nombreNivelUbicacion;
    }

    public void setNombreNivelUbicacion(String nombreNivelUbicacion) {
        this.nombreNivelUbicacion = nombreNivelUbicacion;
    }

    public String getDescripcionNivelUbicacion() {
        return descripcionNivelUbicacion;
    }

    public void setDescripcionNivelUbicacion(String descripcionNivelUbicacion) {
        this.descripcionNivelUbicacion = descripcionNivelUbicacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public TblNivelImplementacion getIdNivelImplementacion() {
        return idNivelImplementacion;
    }

    public void setIdNivelImplementacion(TblNivelImplementacion idNivelImplementacion) {
        this.idNivelImplementacion = idNivelImplementacion;
    }
}
