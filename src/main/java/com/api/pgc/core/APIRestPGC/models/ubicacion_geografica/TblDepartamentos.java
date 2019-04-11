package com.api.pgc.core.APIRestPGC.models.ubicacion_geografica;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;


@Entity
@Table(name = "tbl_departamentos",
        indexes = {@Index(name = "idx_cod_departamento", columnList = "COD_DEPARTAMENTO")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"COD_DEPARTAMENTO"})})
public class TblDepartamentos {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DEPARTAMENTO", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idDepartamento;

    @Column(name = "COD_DEPARTAMENTO", nullable = false, length = 5)
    @ApiModelProperty(notes = "Codigo Departamento", required = true)
    private String codDepartamento;

    @Column(name = "DESC_DEPARTAMENTO", nullable = false, length = 150)
    @ApiModelProperty(notes = "Descripcion del Departamento", required = true)
    private String descDepartamento;

    @Column(name = "CABECERA", nullable = false, length = 150)
    @ApiModelProperty(notes = "Cabezera del Departamento")
    private String cabecera;

    @Column(name = "LATITUD_DEPTO", nullable = true, length = 150)
    @ApiModelProperty(notes = "Latitud del Departamento", required = true)
    private String latitudDepto;

    @Column(name = "LONGITUD_DEPTO", nullable = true, length = 150)
    @ApiModelProperty(notes = "Longitud del Departamento", required = true)
    private String longitudDepto;

    // Mapeo de la Relacion de la Tabla de Departamentos con Pais
    // Muchos Departamentos = 1 Pais
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PAIS", referencedColumnName = "ID_PAIS")
    @ApiModelProperty(notes = "Entidad del País del Deparamento, se envia desde un Json (\"idPaisDepto\": { \"idPais\": \"valor\" })")
    private TblPais idPaisDepto;

    /**
     * Constructor de la Clase, JPA realiza los CRUD
     */
    public TblDepartamentos() {
    }

    /**
     * Metodos Getters y Setters de la Clase
     */
    public long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(String codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public String getDescDepartamento() {
        return descDepartamento;
    }

    public void setDescDepartamento(String descDepartamento) {
        this.descDepartamento = descDepartamento;
    }

    public String getCabecera() {
        return cabecera;
    }

    public void setCabecera(String cabecera) {
        this.cabecera = cabecera;
    }

    public String getLatitudDepto() {
        return latitudDepto;
    }

    public void setLatitudDepto(String latitudDepto) {
        this.latitudDepto = latitudDepto;
    }

    public String getLongitudDepto() {
        return longitudDepto;
    }

    public void setLongitudDepto(String longitudDepto) {
        this.longitudDepto = longitudDepto;
    }

    public TblPais getIdPaisDepto() {
        return idPaisDepto;
    }

    public void setIdPaisDepto(TblPais idPaisDepto) {
        this.idPaisDepto = idPaisDepto;
    }
}
