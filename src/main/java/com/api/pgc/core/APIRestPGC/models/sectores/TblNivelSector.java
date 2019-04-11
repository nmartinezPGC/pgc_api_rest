/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.sectores;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_nivel_sector",
        indexes = {@Index(name = "idx_codigo_nivel_sector", columnList = "CODIGO_NIVEL_SECTOR")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_NIVEL_SECTOR"})})
public class TblNivelSector {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NIVEL_SECTOR", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idNivelSector;

    @Column(name = "CODIGO_NIVEL_SECTOR", nullable = false, length = 10)
    @ApiModelProperty(notes = "Codigo Tipo de Sector", required = true)
    private String codigoNivelSector;

    @Column(name = "NOMBRE_NIVEL_SECTOR", nullable = false, length = 250)
    @ApiModelProperty(notes = "Nombre del Nivel de Sector")
    private String nombreNivelSector;

    @Column(name = "DESCRIPCION_NIVEL_SECTOR", nullable = false, length = 250)
    @ApiModelProperty(notes = "Descripcion del Nivel de Sector")
    private String descripcionNivelSector;

    @Column(name = "ACTIVO")
    @ApiModelProperty(notes = "Activo", readOnly = true)
    private boolean activo = true;

    @Column(name = "FECHA_CREACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha Creacion del Usuario, formato hh:mm:ss", readOnly = true)
    private Date fechaCreacion;

    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    @ApiModelProperty(notes = "Hora de Creacion del Usuario, formato hh:mm:ss", readOnly = true)
    private Date horaCreacion = new Date();


    /**
     * Constructor de la Clase
     */
    public TblNivelSector() {
    }


    /**
     * Metodos Getters y Setters
     */
    public long getIdNivelSector() {
        return idNivelSector;
    }

    public void setIdNivelSector(long idNivelSector) {
        this.idNivelSector = idNivelSector;
    }

    public String getCodigoNivelSector() {
        return codigoNivelSector;
    }

    public void setCodigoNivelSector(String codigoNivelSector) {
        this.codigoNivelSector = codigoNivelSector;
    }

    public String getNombreNivelSector() {
        return nombreNivelSector;
    }

    public void setNombreNivelSector(String nombreNivelSector) {
        this.nombreNivelSector = nombreNivelSector;
    }

    public String getDescripcionNivelSector() {
        return descripcionNivelSector;
    }

    public void setDescripcionNivelSector(String descripcionNivelSector) {
        this.descripcionNivelSector = descripcionNivelSector;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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
}
