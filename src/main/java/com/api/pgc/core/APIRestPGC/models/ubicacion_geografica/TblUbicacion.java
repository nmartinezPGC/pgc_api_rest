/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.ubicacion_geografica;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_ubicaciones")
public class TblUbicacion {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_UBICACION", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idUbicacion;

    @Column(name = "PORCENTAJE_UBICACION", nullable = true)
    @ApiModelProperty(notes = "Porcentaje de participacion de la Ubicación")
    private boolean porcentajeUbicacion;

    // Mapeo de la Relacion de la Tabla de Ubicacion con Pais
    // Muchos Ubicacion = 1 Pais
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PAIS", referencedColumnName = "ID_PAIS")
    @ApiModelProperty(notes = "Entidad del País de la Ubicacion, se envia desde un Json (\"idPaisUbicacion\": { \"idPais\": \"valor\" })")
    private TblPais idPaisUbicacion;

    // Mapeo de la Relacion de la Tabla de Ubicacion con Departamento
    // Muchos Ubicacion = 1 Departamento
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO")
    @ApiModelProperty(notes = "Entidad del Departamento de la Ubicacion, se envia desde un Json (\"idDepartamentoUbicacion\": { \"idDepartamento\": \"valor\" })")
    private TblDepartamentos idDepartamentoUbicacion;

    // Mapeo de la Relacion de la Tabla de Ubicacion con Municipios
    // Muchos Ubicacion = 1 Municipio
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_MUNICIPIO", referencedColumnName = "ID_MUNICIPIO")
    @ApiModelProperty(notes = "Entidad del Municipio de la Ubicacion, se envia desde un Json (\"idMunicipioUbicacion\": { \"idMunicipio\": \"valor\" })")
    private TblMunicipios idMunicipioUbicacion;

    // Mapeo de la Relacion de la Tabla de Ubicacion de Implementacion con Municipios
    // Muchos Ubicacion = 1 Nivel de Ubicacion de Implementacion
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_UBICACION_IMPL", referencedColumnName = "ID_UBICACION_IMPLEMENTACION")
    @ApiModelProperty(notes = "Entidad del Nivel de Ubicacion de la Ubicacion, se envia desde un Json (\"idUbicacionImpl\": { \"idUbicacionImplementacion\": \"valor\" })")
    private TblUbicacionImplementacion idUbicacionImpl;

    /**
     * Constructor de la Clase, JPA realiza los CRUD
     */
    public TblUbicacion() {
    }

    /**
     * Metodos Getters y Setters de la Clase
     */


}
