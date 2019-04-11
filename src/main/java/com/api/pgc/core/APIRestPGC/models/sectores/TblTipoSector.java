/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.sectores;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_tipo_sector",
        indexes = {@Index(name = "idx_cod_tipo_sector", columnList = "COD_TIPO_SECTOR")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"COD_TIPO_SECTOR"})})
public class TblTipoSector {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_SECTOR", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idTipoSector;

    @Column(name = "COD_TIPO_SECTOR", nullable = false, length = 10)
    @ApiModelProperty(notes = "Codigo Tipo de Sector OCDE/CAD", required = true)
    private String codTipoSector;

    @Column(name = "NOMBRE_SECTOR", nullable = false, length = 250)
    @ApiModelProperty(notes = "Nombre del Sector OCDE/CAD")
    private String nombreSector;

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
    public TblTipoSector() {
    }


    /**
     * Metodos Getters y Setters
     */
    public long getIdTipoSector() {
        return idTipoSector;
    }

    public void setIdTipoSector(long idTipoSector) {
        this.idTipoSector = idTipoSector;
    }

    public String getCodTipoSector() {
        return codTipoSector;
    }

    public void setCodTipoSector(String codTipoSector) {
        this.codTipoSector = codTipoSector;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    public void setNombreSector(String nombreSector) {
        this.nombreSector = nombreSector;
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
