package com.api.pgc.core.APIRestPGC.models.organizaciones;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_tipo_organizacion",
        indexes = {@Index(name = "idx_cod_tipo_organizacion", columnList = "COD_TIPO_ORGANIZACION")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"COD_TIPO_ORGANIZACION"})})
public class TblTipoOrganizacion {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_ORGANIZACION", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idTipoOrganizacion;

    @Column(name = "COD_TIPO_ORGANIZACION", nullable = false, length = 10)
    @ApiModelProperty(notes = "Codigo Tipo de Organizacion", required = true)
    private String codTipoOrganizacion;

    @Column(name = "DESCRIPCION_TIPO_ORGANIZACION", nullable = false, length = 300)
    @ApiModelProperty(notes = "Descripción de Tipo de Organizacion")
    private String descTipoOrganizacion;

    @Column(name = "ACRONIMO_TIPO_ORGANIZACION", nullable = false, length = 10)
    @ApiModelProperty(notes = "Nombre de Tipo de Organizacion")
    private String acronimoTipoOrganizacion;

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


    /**
     * Constructor de la Clase
     */
    public TblTipoOrganizacion() {
    }


    /**
     * Metodos Getters y Setters
     */
    public long getIdTipoOrganizacion() {
        return idTipoOrganizacion;
    }

    public void setIdTipoOrganizacion(long idTipoOrganizacion) {
        this.idTipoOrganizacion = idTipoOrganizacion;
    }

    public String getCodTipoOrganizacion() {
        return codTipoOrganizacion;
    }

    public void setCodTipoOrganizacion(String codTipoOrganizacion) {
        this.codTipoOrganizacion = codTipoOrganizacion;
    }

    public String getDescTipoOrganizacion() {
        return descTipoOrganizacion;
    }

    public void setDescTipoOrganizacion(String descTipoOrganizacion) {
        this.descTipoOrganizacion = descTipoOrganizacion;
    }

    public String getAcronimoTipoOrganizacion() {
        return acronimoTipoOrganizacion;
    }

    public void setAcronimoTipoOrganizacion(String acronimoTipoOrganizacion) {
        this.acronimoTipoOrganizacion = acronimoTipoOrganizacion;
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
}
