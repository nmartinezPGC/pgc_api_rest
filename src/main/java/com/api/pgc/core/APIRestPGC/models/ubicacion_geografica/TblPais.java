package com.api.pgc.core.APIRestPGC.models.ubicacion_geografica;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_pais",
        indexes = {@Index(name = "idx_cod_pais", columnList = "COD_PAIS")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"COD_PAIS"})})
public class TblPais {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAIS", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idPais;

    @Column(name = "COD_PAIS", nullable = false, length = 5)
    @ApiModelProperty(notes = "Codigo Estado", required = true)
    private String codEstado;

    @Column(name = "DESC_PAIS", nullable = false, length = 150)
    @ApiModelProperty(notes = "Descripcion Estado", required = true)
    private String descPais;

    @Column(name = "INICIALES_PAIS", nullable = false, length = 5)
    private String inicialesPais;

    @Column(name = "CODIGO_POSTAL", nullable = true, length = 5)
    private String codigoPostal;

    @Column(name = "COD_CONTINENTE", nullable = true, length = 5)
    private String codContinente;

    @Column(name = "LATITUD_PAIS", nullable = true, length = 150)
    private String latitudPais;

    @Column(name = "LONGITUD_PAIS", nullable = true, length = 150)
    private String longitudPais;


    /*
     * Constructor de la Clase
     */
    public TblPais() {
    }

    // Meotod Getters y Setters
    public long getIdPais() {
        return idPais;
    }

    public void setIdPais(long idPais) {
        this.idPais = idPais;
    }

    public String getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(String codEstado) {
        this.codEstado = codEstado;
    }

    public String getDescPais() {
        return descPais;
    }

    public void setDescPais(String descPais) {
        this.descPais = descPais;
    }

    public String getInicialesPais() {
        return inicialesPais;
    }

    public void setInicialesPais(String inicialesPais) {
        this.inicialesPais = inicialesPais;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCodContinente() {
        return codContinente;
    }

    public void setCodContinente(String codContinente) {
        this.codContinente = codContinente;
    }

    public String getLatitudPais() {
        return latitudPais;
    }

    public void setLatitudPais(String latitudPais) {
        this.latitudPais = latitudPais;
    }

    public String getLongitudPais() {
        return longitudPais;
    }

    public void setLongitudPais(String longitudPais) {
        this.longitudPais = longitudPais;
    }
} // FIN | Clasa Modelo de Paises
