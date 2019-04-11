package com.api.pgc.core.APIRestPGC.models.mantenimiento.actividades;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_presupuesto_actividad",
        indexes = {@Index(name = "idx_codigo_presupuesto", columnList = "CODIGO_PRESUPUESTO" )},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODIGO_PRESUPUESTO"})})
public class TblPresupuestoActividad {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_PRESUPUESTO", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idPresupuesto;

    @Column(name = "CODIGO_PRESUPUESTO", nullable = false, length=10)
    @ApiModelProperty(notes = "Codigo del Presupuesto de la Actividad", required = true)
    private String codPresupuesto;

    @Column(name = "DESCRIPCION_PRESUPUESTO", nullable = false, length=150)
    @ApiModelProperty(notes = "Descripcion deL Presupuesto", required = true)
    private String descPresupuesto;

    @Column(name = "HABILITADA")
    private boolean habilitada;


    public TblPresupuestoActividad() {
    }


    public long getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(long idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public String getCodPresupuesto() {
        return codPresupuesto;
    }

    public void setCodPresupuesto(String codPresupuesto) {
        this.codPresupuesto = codPresupuesto;
    }

    public String getDescPresupuesto() {
        return descPresupuesto;
    }

    public void setDescPresupuesto(String descPresupuesto) {
        this.descPresupuesto = descPresupuesto;
    }

    public boolean isHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }
}
