/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.models.organizaciones;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_categoria_organizacion",
        indexes = {@Index(name = "idx_cod_tipo_organizacion", columnList = "COD_CAT_ORGANIZACION")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"COD_CAT_ORGANIZACION"})})
public class TblCategoriaOrganizacion {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CAT_ORGANIZACION", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idCatOrganizacion;

    @Column(name = "COD_CAT_ORGANIZACION", nullable = false, length = 10)
    @ApiModelProperty(notes = "Codigo Tipo de Organizacion", required = true)
    private String codCatOrganizacion;

    @Column(name = "DESC_CAT_ORGANIZACION", nullable = false, length = 200)
    @ApiModelProperty(notes = "Descripción de Tipo de Organizacion")
    private String descCatOrganizacion;

    @Column(name = "ACRONIMO_CAT_ORGANIZACION", nullable = false, length = 10)
    @ApiModelProperty(notes = "Nombre de Tipo de Organizacion")
    private String acronimoCatOrganizacion;

    @Column(name = "ACTIVO")
    private boolean activo;

    @Column(name = "FECHA_CREACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    //@ApiModelProperty(notes = "Hora de Creacion del Usuario, formato hh:mm:ss")
    private Date horaCreacion = new Date();

    // Mapeo de la Relacion de la Tabla de Categoria de Organizacon con Tipo de Organizacion
    // Muchos Tipos = 1 Categoria
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TIPO_ORGANIZACION", referencedColumnName = "ID_TIPO_ORGANIZACION")
    @ApiModelProperty(notes = "Entidad del Pais, se envia desde un Json (\"idTipoOrganizacionCat\": { \"idTipoOrganizacion\": \"valor\" })",
            required = true)
    private TblTipoOrganizacion idTipoOrganizacionCat;


    /**
     * Constructor de la Clase
     */
    public TblCategoriaOrganizacion() {
    }


    /**
     * Metodos Getters y Setters
     */
    public long getIdCatOrganizacion() {
        return idCatOrganizacion;
    }

    public void setIdCatOrganizacion(long idCatOrganizacion) {
        this.idCatOrganizacion = idCatOrganizacion;
    }

    public String getCodCatOrganizacion() {
        return codCatOrganizacion;
    }

    public void setCodCatOrganizacion(String codCatOrganizacion) {
        this.codCatOrganizacion = codCatOrganizacion;
    }

    public String getDescCatOrganizacion() {
        return descCatOrganizacion;
    }

    public void setDescCatOrganizacion(String descCatOrganizacion) {
        this.descCatOrganizacion = descCatOrganizacion;
    }

    public String getAcronimoCatOrganizacion() {
        return acronimoCatOrganizacion;
    }

    public void setAcronimoCatOrganizacion(String acronimoCatOrganizacion) {
        this.acronimoCatOrganizacion = acronimoCatOrganizacion;
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

    public TblTipoOrganizacion getIdTipoOrganizacionCat() {
        return idTipoOrganizacionCat;
    }

    public void setIdTipoOrganizacionCat(TblTipoOrganizacion idTipoOrganizacionCat) {
        this.idTipoOrganizacionCat = idTipoOrganizacionCat;
    }
}
