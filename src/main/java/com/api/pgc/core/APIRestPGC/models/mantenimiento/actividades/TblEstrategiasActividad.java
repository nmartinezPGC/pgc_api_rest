package com.api.pgc.core.APIRestPGC.models.mantenimiento.actividades;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_estrategias_actividad",
        indexes = {@Index(name = "idx_codigo_estrategia", columnList = "CODIGO_ESTRATEGIA" )},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_ESTRATEGIA"})})
public class TblEstrategiasActividad {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_ESTRATEGIA", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idEstrategia;

    @Column(name = "CODIGO_ESTRATEGIA", nullable = false, length=10)
    @ApiModelProperty(notes = "Codigo de la Estrategia de la Actividad", required = true)
    private String codigoEstrategia;

    @Column(name = "DESCRIPCION_ESTRATEGIA", nullable = false, length=150)
    @ApiModelProperty(notes = "Descripcion de la Estrategia", required = true)
    private String descEstrategia;

    @Column(name = "HABILITADA")
    private boolean habilitada;


    public TblEstrategiasActividad() {
    }

    public long getIdEstrategia() {
        return idEstrategia;
    }

    public void setIdEstrategia(long idEstrategia) {
        this.idEstrategia = idEstrategia;
    }

    public String getCodigoEstrategia() {
        return codigoEstrategia;
    }

    public void setCodigoEstrategia(String codigoEstrategia) {
        this.codigoEstrategia = codigoEstrategia;
    }

    public String getDescripcionEstrategia() {
        return descEstrategia;
    }

    public void setDescripcionEstrategia(String descripcionEstrategia) {
        this.descEstrategia = descripcionEstrategia;
    }

    public boolean isHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }
}
