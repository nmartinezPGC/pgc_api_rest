/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.mantenimiento.actividades;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;


@Entity
@Table(name = "tbl_tipo_iniciativa_css",
        indexes = {@Index(name = "idx_cod_tipo_iniciativa", columnList = "COD_TIPO_INICIATIVA" )},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"COD_TIPO_INICIATIVA"})})
public class TblTipoIniciativaCss {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_INICIATIVA", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idTipoIniciativa;

    @Column(name = "COD_TIPO_INICIATIVA", nullable = false, length=10)
    @ApiModelProperty(notes = "Codigo del Sector Ejecutor de la Actividad", required = true)
    private String codIniciativa;

    @Column(name = "DESC_TIPO_INICIATIVA", nullable = false, length=150)
    @ApiModelProperty(notes = "Descripcion Sector Ejecutor", required = true)
    private String desctipoiniciativa;

    @Column(name = "ACTIVO")
    private boolean activo;


    /**
     * Constructor de la Clase
     */
    public TblTipoIniciativaCss() {
    }

    /**
     * Getters y Setters
     * @return
     */
    public long getIdTipoIniciativa() {
        return idTipoIniciativa;
    }

    public void setIdTipoIniciativa(long idTipoIniciativa) {
        this.idTipoIniciativa = idTipoIniciativa;
    }

    public String getCodIniciativa() {
        return codIniciativa;
    }

    public void setCodIniciativa(String codIniciativa) {
        this.codIniciativa = codIniciativa;
    }

    public String getDesctipoiniciativa() {
        return desctipoiniciativa;
    }

    public void setDesctipoiniciativa(String desctipoiniciativa) {
        this.desctipoiniciativa = desctipoiniciativa;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
