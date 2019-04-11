/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.ubicacion_geografica;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_nivel_implementacion",
        indexes = {@Index(name = "idx_codigo_nivel_impl", columnList = "CODIGO_NIVEL_IMPL")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_NIVEL_IMPL"})})
public class TblNivelImplementacion {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NIVEL", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idNivel;

    @Column(name = "CODIGO_NIVEL_IMPL", nullable = false, length = 10)
    @ApiModelProperty(notes = "Codigo de Nivel de Implementación", required = true)
    private String codigoNivelImpl;

    @Column(name = "NOMBRE_NIVEL_IMPL", nullable = false, length = 150)
    @ApiModelProperty(notes = "Nombre de Nivel de Implementación", required = true)
    private String nombreNivelImpl;

    @Column(name = "DESCRIPCION_NIVEL_IMPL", nullable = true, length = 300)
    @ApiModelProperty(notes = "Descripcion de Nivel de Implementación")
    private String descripcionNivelImpl;

    @Column(name = "ACTIVO", nullable = true)
    @ApiModelProperty(notes = "Indicador de Activo")
    private boolean activo;

    /**
     * Constructor de la Clase, JPA realiza los CRUD
     */
    public TblNivelImplementacion() {
    }

    /**
     * Metodos Getters y Setters de la Clase
     */
    public long getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(long idNivel) {
        this.idNivel = idNivel;
    }

    public String getCodigoNivelImpl() {
        return codigoNivelImpl;
    }

    public void setCodigoNivelImpl(String codigoNivelImpl) {
        this.codigoNivelImpl = codigoNivelImpl;
    }

    public String getNombreNivelImpl() {
        return nombreNivelImpl;
    }

    public void setNombreNivelImpl(String nombreNivelImpl) {
        this.nombreNivelImpl = nombreNivelImpl;
    }

    public String getDescripcionNivelImpl() {
        return descripcionNivelImpl;
    }

    public void setDescripcionNivelImpl(String descripcionNivelImpl) {
        this.descripcionNivelImpl = descripcionNivelImpl;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
