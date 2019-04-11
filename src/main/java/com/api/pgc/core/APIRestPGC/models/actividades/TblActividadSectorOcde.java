/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.actividades;

import com.api.pgc.core.APIRestPGC.models.sectores.TblSectorOcdeCad;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblUbicacionImplementacion;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_actividades_sectores_ocde",
        indexes = {@Index(name = "idx_codigo_actividad", columnList = "CODIGO_ACTIVIDAD"),
        @Index(name = "idx_id_sector_ocde", columnList = "ID_SECTOR_OCDE"),
        @Index(name = "idx_id_actividad", columnList = "ID_ACTIVIDAD")})
public class TblActividadSectorOcde {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ACTIVIDAD_SECTOR_OCDE", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idActividadSectorOcde;

    // Datos Generales de la Actividad
    @Column(name = "CODIGO_ACTIVIDAD", nullable = false, length = 50)
    @ApiModelProperty(notes = "Codigo de la Actividad", required = true)
    private String codigoActividad;

    @Column(name = "PORCENTAJE_PART", nullable = true)
    @ApiModelProperty(notes = "Porcentaje de participacion")
    private double porcentajePart;

    @Column(name = "FECHA_CREACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha Creacion", readOnly = true)
    private Date fechaCreacion;

    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    @ApiModelProperty(notes = "Hora de Creacion, formato hh:mm:ss", readOnly = true)
    private Date horaCreacion = new Date();

    @Column(name = "ACTIVO")
    @ApiModelProperty(notes = "ACTIVO", readOnly = true)
    private boolean activo;

    // Mapeo de la Relacion de la Tabla de Ubicacion con Actividades
    // Muchos Ubicacion = 1 Actividades
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID_ACTIVIDAD")
    @ApiModelProperty(notes = "Entidad de la Actividad de la Ubicacion, se envia desde un Json (\"idActividad\": { \"idActividad\": \"valor\" })")
    private TblActividad idActividad;

    // Mapeo de la Relacion de la Tabla de Sectores OCDE Actividad
    // Muchos Actividad = 1 Sector OCDE
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_SECTOR_OCDE", referencedColumnName = "ID_SECTOR")
    @ApiModelProperty(notes = "Entidad del Sector OCDE/CAD, se envia desde un Json (\"idSectorOcde\": { \"idSectorOcde\": \"valor\" })")
    private TblSectorOcdeCad idSectorOcde;

    // Muchos Actividad = 1 Usuario
    /*@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USUARIO_CREADOR", referencedColumnName = "ID_USUARIO")
    @ApiModelProperty(notes = "Entidad de Usuario de la Ubicacion, se envia desde un Json (\"idUsuarioCreador\": { \"idUsuario\": \"valor\" })")
    private TblUsuarios idUsuarioCreador;*/

    /**
     * Constructor de la Clase, JPA realiza los CRUD
     */
    public TblActividadSectorOcde() {
    }

    /**
     * Metodos Getters y Setters de la Clase
     */
    public long getIdActividadSectorOcde() {
        return idActividadSectorOcde;
    }

    public void setIdActividadSectorOcde(long idActividadSectorOcde) {
        this.idActividadSectorOcde = idActividadSectorOcde;
    }

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public double getPorcentajePart() {
        return porcentajePart;
    }

    public void setPorcentajePart(double porcentajePart) {
        this.porcentajePart = porcentajePart;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public TblActividad getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(TblActividad idActividad) {
        this.idActividad = idActividad;
    }

    public TblSectorOcdeCad getIdSectorOcde() {
        return idSectorOcde;
    }

    public void setIdSectorOcde(TblSectorOcdeCad idSectorOcde) {
        this.idSectorOcde = idSectorOcde;
    }
}
