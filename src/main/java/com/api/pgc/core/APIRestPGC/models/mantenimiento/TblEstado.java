package com.api.pgc.core.APIRestPGC.models.mantenimiento;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_estados",
        indexes = {@Index(name = "idx_cod_estado", columnList = "COD_ESTADO" )},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"COD_ESTADO"})})
public class TblEstado {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_ESTADO", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idEstado;

    @Column(name = "COD_ESTADO", nullable = false, length=10)
    @ApiModelProperty(notes = "Codigo Estado", required = true)
    private String codEstado;

    @Column(name = "DESC_ESTADO", nullable = false, length=100)
    @ApiModelProperty(notes = "Descripcion Estado", required = true)
    private String descEstado;

    @Column(name = "HABILITADA")
    private boolean habilitada;

    //Mapeo de la Relacion de la Tabla de Estados con Grupos
    // Muchos Estados = 1 Grupo
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO")
    @ApiModelProperty(notes = "Entidad del Grupo, se envia desde un Json (\"idGrupo\": { \"idGrupo\": \"valor\" })", required = true)
    private TblGrupo idGrupo;


    public TblEstado() {
        //Sin Parametros
    }

    public long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(long idEstado) {
        this.idEstado = idEstado;
    }

    public String getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(String codEstado) {
        this.codEstado = codEstado;
    }

    public String getDescEstado() {
        return descEstado;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }

    public boolean isHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    public TblGrupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(TblGrupo idGrupo) {
        this.idGrupo = idGrupo;
    }
}
