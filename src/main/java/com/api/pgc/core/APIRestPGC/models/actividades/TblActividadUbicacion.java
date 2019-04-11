/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.actividades;

import com.api.pgc.core.APIRestPGC.models.seguridad.TblUsuarios;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblDepartamentos;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblMunicipios;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblPais;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblUbicacionImplementacion;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_actividades_ubicaciones",
        indexes = {@Index(name = "idx_codigo_actividad_ubicacion", columnList = "CODIGO_ACTIVIDAD"),
        @Index(name = "idx_id_actividad", columnList = "ID_ACTIVIDAD"),
        @Index(name = "idx_id_ubicacion_impl", columnList = "ID_UBICACION_IMPL")})
public class TblActividadUbicacion {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ACTIVIDAD_UBICACION", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idActividadUbicacion;

    // Datos Generales de la Actividad
    @Column(name = "CODIGO_ACTIVIDAD", nullable = false, length = 50)
    @ApiModelProperty(notes = "Codigo de la Actividad", required = true)
    private String codigoActividad;

    @Column(name = "PORCENTAJE_UBICACION", nullable = true)
    @ApiModelProperty(notes = "Porcentaje de participacion de la Ubicación")
    private double porcentajeUbicacion;

    @Column(name = "FECHA_CREACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Fecha Creacion", readOnly = true)
    private Date fechaCreacion;

    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    @ApiModelProperty(notes = "Hora de Creacion, formato hh:mm:ss", readOnly = true)
    private Date horaCreacion = new Date();


    // Mapeo de la Relacion de la Tabla de Ubicacion con Actividades
    // Muchos Ubicacion = 1 Actividades
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID_ACTIVIDAD")
    @ApiModelProperty(notes = "Entidad de la Actividad de la Ubicacion, se envia desde un Json (\"idActividad\": { \"idActividad\": \"valor\" })")
    private TblActividad idActividad;

    // Mapeo de la Relacion de la Tabla de Ubicacion de Implementacion con Municipios
    // Muchos Ubicacion = 1 Nivel de Ubicacion de Implementacion
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_UBICACION_IMPL", referencedColumnName = "ID_UBICACION_IMPLEMENTACION")
    @ApiModelProperty(notes = "Entidad del Nivel de Ubicacion de la Ubicacion, se envia desde un Json (\"idUbicacionImpl\": { \"idUbicacionImplementacion\": \"valor\" })")
    private TblUbicacionImplementacion idUbicacionImpl;

    // Muchos Actividad = 1 Usuario
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USUARIO_CREADOR", referencedColumnName = "ID_USUARIO")
    @ApiModelProperty(notes = "Entidad de Usuario de la Ubicacion, se envia desde un Json (\"idUsuarioCreador\": { \"idUsuario\": \"valor\" })")
    private TblUsuarios idUsuarioCreador;

    /**
     * Constructor de la Clase, JPA realiza los CRUD
     */
    public TblActividadUbicacion() {
    }

    /**
     * Metodos Getters y Setters de la Clase
     */
    public long getIdActividadUbicacion() {
        return idActividadUbicacion;
    }

    public void setIdActividadUbicacion(long idActividadUbicacion) {
        this.idActividadUbicacion = idActividadUbicacion;
    }

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public double getPorcentajeUbicacion() {
        return porcentajeUbicacion;
    }

    public void setPorcentajeUbicacion(double porcentajeUbicacion) {
        this.porcentajeUbicacion = porcentajeUbicacion;
    }

    public TblActividad getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(TblActividad idActividad) {
        this.idActividad = idActividad;
    }

    public TblUbicacionImplementacion getIdUbicacionImpl() {
        return idUbicacionImpl;
    }

    public void setIdUbicacionImpl(TblUbicacionImplementacion idUbicacionImpl) {
        this.idUbicacionImpl = idUbicacionImpl;
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

    public TblUsuarios getIdUsuarioCreador() {
        return idUsuarioCreador;
    }

    public void setIdUsuarioCreador(TblUsuarios idUsuarioCreador) {
        this.idUsuarioCreador = idUsuarioCreador;
    }
}
