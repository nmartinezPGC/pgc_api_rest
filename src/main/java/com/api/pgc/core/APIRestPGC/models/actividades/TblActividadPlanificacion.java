/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.actividades;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_actividades_planificacion",
        indexes = {@Index(name = "idx_codigo_actividad_plan", columnList = "CODIGO_ACTIVIDAD" )})
public class TblActividadPlanificacion {
    // Propiedades de la tabla
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_PLANIFICACION", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idPlanificacion;

    // Fechas del Proyecto
    @Column(name = "FECHA_FIRMA", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha Firma")
    private Date fechaFirma;

    @Column(name = "FECHA_EFECTIVIDAD", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha Efectividad")
    private Date fechaEfectividad;

    @Column(name = "FECHA_CIERRE", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha Cierre")
    private Date fechaCierre;

    @Column(name = "FECHA_PROPUESTA_FINALIZACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha Propuesta de Finalizacion")
    private Date fechaPropuestaFinalizacion;

    @Column(name = "FECHA_FINALIZACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha de Finalizacion")
    private Date fechaFinalizacion;

    @Column(name = "FECHA_CREACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha de Creacion")
    private Date fechaCreacion;

    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    //@ApiModelProperty(notes = "Hora de Creacion de la Actividad, formato hh:mm:ss")
    private Date horaCreacion = new Date();

    @Column(name = "CODIGO_ACTIVIDAD", nullable = false, length=50)
    @ApiModelProperty(notes = "Codigo de la Estrategia de la Actividad", required = true)
    private String codigoActividad;

    // Mapeo de la Relacion de la Tabla de Planifiacion con Actividades
    // Muchos Planificacion = 1 Actividad
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "ID_ACTIVIDAD_PLAN", referencedColumnName = "ID_ACTIVIDAD")
    @ApiModelProperty(notes = "Entidad de la Actividad, se envia desde un Json (\"idActividadPlan\": { \"idActividad\": \"valor\" })", required = true)
    private TblActividad idActividadPlan;

    @Column(name = "FECHA_MODIFICACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha de Modificacion de la Planificacion")
    private Date fechaModificacion;

    @Column(name = "HORA_MODIFICACION")
    @Temporal(TemporalType.TIME)
    private Date horaModificacion;

    /*
     *Constructor vacio de la Clase, solo para Jpa
     */
    public TblActividadPlanificacion() {
        // Este lo usa Jpa para realizar los Mapping
    }

    /*
     * Defincion de los Metodos getters y Setters
     */

    public long getIdPlanificacion() {
        return idPlanificacion;
    }

    public void setIdPlanificacion(long idPlanificacion) {
        this.idPlanificacion = idPlanificacion;
    }

    public Date getFechaFirma() {
        return fechaFirma;
    }

    public void setFechaFirma(Date fechaFirma) {
        this.fechaFirma = fechaFirma;
    }

    public Date getFechaEfectividad() {
        return fechaEfectividad;
    }

    public void setFechaEfectividad(Date fechaEfectividad) {
        this.fechaEfectividad = fechaEfectividad;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Date getFechaPropuestaFinalizacion() {
        return fechaPropuestaFinalizacion;
    }

    public void setFechaPropuestaFinalizacion(Date fechaPropuestaFinalizacion) {
        this.fechaPropuestaFinalizacion = fechaPropuestaFinalizacion;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Date getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(Date horaCreacion) {
        this.horaCreacion = horaCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


    public TblActividad getIdActividadPlan() {
        return idActividadPlan;
    }

    public void setIdActividadPlan(TblActividad idActividadPlan) {
        this.idActividadPlan = idActividadPlan;
    }

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getHoraModificacion() {
        return horaModificacion;
    }

    public void setHoraModificacion(Date horaModificacion) {
        this.horaModificacion = horaModificacion;
    }
}
