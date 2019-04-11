package com.api.pgc.core.APIRestPGC.models.organizaciones;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_grupo_organizaciones",
        indexes = {@Index(name = "idx_cod_grupo_organizacion", columnList = "COD_GRUPO_ORGANIZACION")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"COD_GRUPO_ORGANIZACION"})})
public class TblGrupoOrganizacion {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GRUPO_ORGANIZACION", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idGrupoOrganizacion;

    @Column(name = "COD_GRUPO_ORGANIZACION", nullable = false, length = 10)
    @ApiModelProperty(notes = "Codigo de Grupo Organizacion", required = true)
    private String codGrupoOrganizacion;

    @Column(name = "DESCRIPCION_GRUPO_ORGANIZACION", length = 300)
    @ApiModelProperty(notes = "Descripción de Grupo Organizacion")
    private String descripcionGrupoOrganizacion;

    @Column(name = "NOMBRE_GRUPO_ORGANIZACION", nullable = false, length = 100)
    @ApiModelProperty(notes = "Nombre de Grupo Organizacion")
    private String nombreGrupoOrganizacion;

    @Column(name = "ACTIVO")
    private boolean activo;

    @Column(name = "FECHA_CREACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    //@ApiModelProperty(notes = "Fecha de Creacion del Usuario, formato hh:mm:ss")
    private Date fechaCreacion;

    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    //@ApiModelProperty(notes = "Hora de Creacion del Usuario, formato hh:mm:ss")
    private Date horaCreacion = new Date();


    //Mapeo de la Relacion de la Tabla de Organizacones con Tipo de Organizacion
    //Muchas Organizaciones = 1 Tipo de Organizacion
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TIPO_ORGANIZACION", referencedColumnName = "ID_TIPO_ORGANIZACION")
    @ApiModelProperty(notes = "Entidad del Tipo de Organizacion, se envia desde un Json (\"idTipoOrganizacion\": { \"idTipoOrganizacion\": \"valor\" })",
            required = true)
    private TblTipoOrganizacion idTipoOrganizacion;


    /**
     * Constructor de la Clase
     */
    public TblGrupoOrganizacion() {
    }


    /**
     * Metodos Getters y Setters
     */
    public long getIdGrupoOrganizacion() {
        return idGrupoOrganizacion;
    }

    public void setIdGrupoOrganizacion(long idGrupoOrganizacion) {
        this.idGrupoOrganizacion = idGrupoOrganizacion;
    }

    public String getCodGrupoOrganizacion() {
        return codGrupoOrganizacion;
    }

    public void setCodGrupoOrganizacion(String codGrupoOrganizacion) {
        this.codGrupoOrganizacion = codGrupoOrganizacion;
    }

    public String getDescripcionGrupoOrganizacion() {
        return descripcionGrupoOrganizacion;
    }

    public void setDescripcionGrupoOrganizacion(String descripcionGrupoOrganizacion) {
        this.descripcionGrupoOrganizacion = descripcionGrupoOrganizacion;
    }

    public String getNombreGrupoOrganizacion() {
        return nombreGrupoOrganizacion;
    }

    public void setNombreGrupoOrganizacion(String nombreGrupoOrganizacion) {
        this.nombreGrupoOrganizacion = nombreGrupoOrganizacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    public TblTipoOrganizacion getIdTipoOrganizacion() {
        return idTipoOrganizacion;
    }

    public void setIdTipoOrganizacion(TblTipoOrganizacion idTipoOrganizacion) {
        this.idTipoOrganizacion = idTipoOrganizacion;
    }
}
