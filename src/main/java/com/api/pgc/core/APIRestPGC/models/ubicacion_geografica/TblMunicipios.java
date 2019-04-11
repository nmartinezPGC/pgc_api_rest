package com.api.pgc.core.APIRestPGC.models.ubicacion_geografica;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "tbl_municipios",
        indexes = {@Index(name = "idx_cod_municipio", columnList = "COD_MUNICIPIO")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"COD_MUNICIPIO"})})
public class TblMunicipios {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MUNICIPIO", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idMunicipio;

    @Column(name = "COD_MUNICIPIO", nullable = false, length = 5)
    @ApiModelProperty(notes = "Codigo de Municipio", required = true)
    private String codMunicipio;

    @Column(name = "DESC_MUNICIPIO", nullable = false, length = 150)
    @ApiModelProperty(notes = "Descripcion del Municipio", required = true)
    private String descMunicipio;

    @Column(name = "LATITTUD_MUNIC", nullable = true, length = 150)
    @ApiModelProperty(notes = "Latitud del Municipio", required = true)
    private String latittudMunic;

    @Column(name = "LONGITUD_MUNIC", nullable = true, length = 150)
    @ApiModelProperty(notes = "Longitud del Municipio", required = true)
    private String longitudMunic;

    // Mapeo de la Relacion de la Tabla de Municipios con Departamentos
    // Muchos Municipios = 1 Departamento
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO")
    @ApiModelProperty(notes = "Entidad del Deparamento, se envia desde un Json (\"idDepartamentoMunic\": { \"idDepartamento\": \"valor\" })")
    private TblDepartamentos idDepartamentoMunic;

    /**
     * Constructor de la Clase, JPA realiza los CRUD
     */
    public TblMunicipios() {
    }

    /**
     * Metodos Getters y Setters de la Clase
     */
    public long getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(long idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getCodMunicipio() {
        return codMunicipio;
    }

    public void setCodMunicipio(String codMunicipio) {
        this.codMunicipio = codMunicipio;
    }

    public String getDescMunicipio() {
        return descMunicipio;
    }

    public void setDescMunicipio(String descMunicipio) {
        this.descMunicipio = descMunicipio;
    }

    public String getLatittudMunic() {
        return latittudMunic;
    }

    public void setLatittudMunic(String latittudMunic) {
        this.latittudMunic = latittudMunic;
    }

    public String getLongitudMunic() {
        return longitudMunic;
    }

    public void setLongitudMunic(String longitudMunic) {
        this.longitudMunic = longitudMunic;
    }

    public TblDepartamentos getIdDepartamentoMunic() {
        return idDepartamentoMunic;
    }

    public void setIdDepartamentoMunic(TblDepartamentos idDepartamentoMunic) {
        this.idDepartamentoMunic = idDepartamentoMunic;
    }
}
