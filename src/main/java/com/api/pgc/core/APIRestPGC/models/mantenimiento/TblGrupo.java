package com.api.pgc.core.APIRestPGC.models.mantenimiento;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_grupos",
        indexes = {@Index(name = "idx_cod_grupo", columnList = "COD_GRUPO")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"COD_GRUPO"})})
public class TblGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GRUPO", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idGrupo;

    @Column(name = "COD_GRUPO", nullable = false, length = 10)
    @ApiModelProperty(notes = "Codigo Grupo", required = true)
    private String codGrupo;

    @Column(name = "DESC_GRUPO", nullable = false, length = 150)
    @ApiModelProperty(notes = "Descripcion Grupo", required = true)
    private String descGrupo;

    @Column(name = "HABILITADA")
    private boolean habilitada;

    /*@OneToMany( cascade = CascadeType.ALL)
    @JoinColumn( name = "ID_GRUPO", referencedColumnName = "ID_GRUPO")
    private List<TblTipo> idGrupoTipo;*/


    //Constructor de la Clase Modelo
    public TblGrupo() {
    }


    //Metodos Getters y Setters
    public long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(String codGrupo) {
        this.codGrupo = codGrupo;
    }

    public String getDescGrupo() {
        return descGrupo;
    }

    public void setDescGrupo(String descGrupo) {
        this.descGrupo = descGrupo;
    }

    public boolean isHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }


}
