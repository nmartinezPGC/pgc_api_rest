/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.ubicacion_geografica;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_ubicacion_implementacion",
        indexes = {@Index(name = "idx_codigo_ubicacion", columnList = "CODIGO_UBICACION")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_UBICACION"})})
public class TblUbicacionImplementacion {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_UBICACION_IMPLEMENTACION", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idUbicacionImplementacion;

    @Column(name = "CODIGO_UBICACION", nullable = false, length = 20)
    @ApiModelProperty(notes = "Codigo de Ubicación de Implementación", required = true)
    private String codigoUbicacion;

    @Column(name = "NOMBRE_UBICACION_IMPL", nullable = false, length = 150)
    @ApiModelProperty(notes = "Nombre de Ubicacion de Implementación", required = true)
    private String nombreUbicacionImpl;

    @Column(name = "DESCRIPCION_UBICACION_IMPL", nullable = true, length = 300)
    @ApiModelProperty(notes = "Descripcion de Ubicacion de Implementación")
    private String descripcionUbicacionImpl;

    @Column(name = "ACTIVO", nullable = true)
    @ApiModelProperty(notes = "Indicador de Activo")
    private boolean activo;

    // Mapeo de la Relacion de la Tabla de Ubicacion de Implementacion con Nivel Implementacion
    // Muchos Ubicacion de Implementacion = 1 Nivel de Implementacion
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_NIVEL_IMPLEMENTACION", referencedColumnName = "ID_NIVEL")
    @ApiModelProperty(notes = "Entidad del Nivel de Implementacion de la Ubicacion, se envia desde un Json (\"idNivelImplementacion\": { \"idNivel\": \"valor\" })")
    private TblNivelImplementacion idNivelImplementacion;

    // Mapeo de la Relacion de la Tabla de Ubicacion de Implementacion con Nivel de Ubicacion de Implementacion
    // Muchos Ubicacion de Implementacion = 1 Nivel de Ubicacion de Implementacion
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_NIVEL_UBICACION", referencedColumnName = "ID_NIVEL_UBICACION")
    @ApiModelProperty(notes = "Entidad del Nivel de Ubicacion de Implementacion de la Ubicacion, se envia desde un Json (\"idNivelUbicacion\": { \"idNivelUbicacion\": \"valor\" })")
    private TblNivelUbicacionImplementacion idNivelUbicacion;


    // Mapeo de la Relacion de la Tabla de Ubicacion de Implementacion con Pais de Ubicacion
    // Muchos Ubicacion de Implementacion = 1 Pais de Ubicacion
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PAIS_UBICACION", referencedColumnName = "ID_PAIS")
    @ApiModelProperty(notes = "Entidad del País de Ubicacion, se envia desde un Json (\"idPaisUbicacion\": { \"idPais\": \"valor\" })")
    private TblPais idPaisUbicacion;

    // Mapeo de la Relacion de la Tabla de Ubicacion de Implementacion con Departamentos de Ubicacion
    // Muchos Ubicacion de Implementacion = 1 Departamentos de Ubicacion
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_DEPARTAMENTO_UBICACION", referencedColumnName = "ID_DEPARTAMENTO")
    @ApiModelProperty(notes = "Entidad del Departamento de Ubicacion, se envia desde un Json (\"idDepartamentoUbicacion\": { \"idDepartamento\": \"valor\" })")
    private TblDepartamentos idDepartamentoUbicacion;

    // Mapeo de la Relacion de la Tabla de Ubicacion de Implementacion con Municipios de Ubicacion
    // Muchos Ubicacion de Implementacion = 1 Municipios de Ubicacion
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_MUNICIPIO_UBICACION", referencedColumnName = "ID_MUNICIPIO")
    @ApiModelProperty(notes = "Entidad del Municipio de Ubicacion, se envia desde un Json (\"idMunicipioUbicacion\": { \"idMunicipio\": \"valor\" })")
    private TblMunicipios idMunicipioUbicacion;

    /**
     * Constructor de la Clase, JPA realiza los CRUD
     */
    public TblUbicacionImplementacion() {
    }

    /**
     * Metodos Getters y Setters de la Clase
     */
    public long getIdUbicacionImplementacion() {
        return idUbicacionImplementacion;
    }

    public void setIdUbicacionImplementacion(long idUbicacionImplementacion) {
        this.idUbicacionImplementacion = idUbicacionImplementacion;
    }

    public String getCodigoUbicacion() {
        return codigoUbicacion;
    }

    public void setCodigoUbicacion(String codigoUbicacion) {
        this.codigoUbicacion = codigoUbicacion;
    }

    public String getNombreUbicacionImpl() {
        return nombreUbicacionImpl;
    }

    public void setNombreUbicacionImpl(String nombreUbicacionImpl) {
        this.nombreUbicacionImpl = nombreUbicacionImpl;
    }

    public String getDescripcionUbicacionImpl() {
        return descripcionUbicacionImpl;
    }

    public void setDescripcionUbicacionImpl(String descripcionUbicacionImpl) {
        this.descripcionUbicacionImpl = descripcionUbicacionImpl;
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

    public TblNivelUbicacionImplementacion getIdNivelUbicacion() {
        return idNivelUbicacion;
    }

    public void setIdNivelUbicacion(TblNivelUbicacionImplementacion idNivelUbicacion) {
        this.idNivelUbicacion = idNivelUbicacion;
    }

    public TblPais getIdPaisUbicacion() {
        return idPaisUbicacion;
    }

    public void setIdPaisUbicacion(TblPais idPaisUbicacion) {
        this.idPaisUbicacion = idPaisUbicacion;
    }

    public TblDepartamentos getIdDepartamentoUbicacion() {
        return idDepartamentoUbicacion;
    }

    public void setIdDepartamentoUbicacion(TblDepartamentos idDepartamentoUbicacion) {
        this.idDepartamentoUbicacion = idDepartamentoUbicacion;
    }

    public TblMunicipios getIdMunicipioUbicacion() {
        return idMunicipioUbicacion;
    }

    public void setIdMunicipioUbicacion(TblMunicipios idMunicipioUbicacion) {
        this.idMunicipioUbicacion = idMunicipioUbicacion;
    }
}
