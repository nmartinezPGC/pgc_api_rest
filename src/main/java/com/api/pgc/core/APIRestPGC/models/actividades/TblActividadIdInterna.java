/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.actividades;

import com.api.pgc.core.APIRestPGC.models.organizaciones.TblOrganizacion;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_actividades_id_internas",
        indexes = {@Index(name = "idx_codigo_id_interna", columnList = "CODIGO_ID_INTERNA" )},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_ID_INTERNA"})})
public class TblActividadIdInterna {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_INTERNA", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idInterna;

    @Column(name = "CODIGO_ID_INTERNA", nullable = false, length=50)
    @ApiModelProperty(notes = "Codigo de la Estrategia de la Actividad", required = true)
    private String codIdInterna;

    // Mapeo de la Relacion de la Tabla de Id Interna con Organizaciones
    // Muchos Id Internas = 1 Organizacion
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ID_ORGANIZACION", referencedColumnName = "ID_ORGANIZACION")
    @ApiModelProperty(notes = "Entidad de la Organizacion, se envia desde un Json (\"idOrganizacion\": { \"idOrganizacion\": \"valor\" })", required = true)
    private TblOrganizacion idOrganizacionIdInterna;


    // Mapeo de la Relacion de la Tabla de Id Interna con Actividades
    // Muchos Id Internas = 1 Actividad
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID_ACTIVIDAD")
    @ApiModelProperty(notes = "Entidad de la Proyectos, se envia desde un Json (\"idActividadIdInterna\": { \"idActividad\": \"valor\" })", required = true)
    private TblActividad idActividadIdInterna;

    @Column(name = "FECHA_CREACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    // @ApiModelProperty(notes = "Fecha de Creacion")
    private Date fechaCreacion;

    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    // @ApiModelProperty(notes = "Hora de Creacion de la Actividad, formato hh:mm:ss")
    private Date horaCreacion = new Date();


    // Constructor vacio de la Clase, solo para Jpa
    public TblActividadIdInterna() {
        // Este lo usa Jpa para realizar los Mapping
    }


    public long getIdInterna() {
        return idInterna;
    }

    public void setIdInterna(long idInterna) {
        this.idInterna = idInterna;
    }

    public TblOrganizacion getIdOrganizacionIdInterna() {
        return idOrganizacionIdInterna;
    }

    public void setIdOrganizacionIdInterna(TblOrganizacion idOrganizacionIdInterna) {
        this.idOrganizacionIdInterna = idOrganizacionIdInterna;
    }

    public String getCodIdInterna() {
        return codIdInterna;
    }

    public void setCodIdInterna(String codIdInterna) {
        this.codIdInterna = codIdInterna;
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

    public TblActividad getIdActividadIdInterna() {
        return idActividadIdInterna;
    }

    public void setIdActividadIdInterna(TblActividad idActividadIdInterna) {
        this.idActividadIdInterna = idActividadIdInterna;
    }

}
