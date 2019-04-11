package com.api.pgc.core.APIRestPGC.models.organizaciones;


import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblPais;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_organizaciones",
        indexes = {@Index(name = "idx_cod_organizacion", columnList = "COD_ORGANIZACION")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"COD_ORGANIZACION"})})
public class TblOrganizacion {
    //Propiedades de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORGANIZACION", columnDefinition = "serial")
    @ApiModelProperty(notes = "Identificador de la Tabla, se Autogenera")
    private long idOrganizacion;

    @Column(name = "COD_ORGANIZACION", nullable = false, length = 50)
    @ApiModelProperty(notes = "Codigo de Organizacion", required = true)
    private String codOrganizacion;

    @Column(name = "INICIALES_ORGANIZACION", nullable = false, length = 10)
    @ApiModelProperty(notes = "Iniciales de Organizacion")
    private String inicalesOrganizacion;

    @Column(name = "DESCRIPCION_ORGANIZACION", length = 500)
    @ApiModelProperty(notes = "Descripción de Organizacion")
    private String descOrganizacion;

    @Column(name = "NOMBRE_ORGANIZACION", nullable = false, length = 100)
    @ApiModelProperty(notes = "Nombre de Organizacion")
    private String nombreOrganizacion;

    @Column(name = "DIRECCION_FISICA_ORGANIZACION", length = 300)
    @ApiModelProperty(notes = "Direccion Fisica Organizacion")
    private String direccionFisicaOrganizacion;

    @Column(name = "TELEFONO_ORGANIZACION", length = 15)
    @ApiModelProperty(notes = "Telefono Organizacion")
    private String telefonoOrganizacion;

    @Column(name = "EMAIL_ORGANIZACION", length = 200)
    @ApiModelProperty(notes = "Email Organizacion")
    private String emailOrganizacion;

    @Column(name = "DEPTO_REFERENCIA", length = 150)
    @ApiModelProperty(notes = "Departamento de Referencia")
    private String deptoReferencia;

    @Column(name = "CONTACTO_REFERENCIA", length = 200)
    @ApiModelProperty(notes = "contacto Referencia")
    private String contactoReferencia;

    @Column(name = "WEB_ORGANIZACION", length = 150)
    @ApiModelProperty(notes = "Wweb de Referencia")
    private String webOrganizacion;

    @Column(name = "ACTIVO")
    private boolean activo;

    // Detalles de Cooperanate
    @Column(name = "SOCIO_DESARROLLO")
    private boolean socioDesarrollo;

    @Column(name = "AGENCIA_BENEFICIARIA")
    private boolean agenciaBeneficiaria;

    @Column(name = "UNIDAD_EJECUTORA")
    private boolean unidadEjecutora;

    @Column(name = "ADMINISTRADOR_FINANCIERO")
    private boolean administradorFinanciero;

    @Column(name = "FECHA_CREACION", columnDefinition = "date DEFAULT '2999-12-31'")
    @Temporal(TemporalType.DATE)
    //@ApiModelProperty(notes = "Fecha de Creacion del Usuario, formato hh:mm:ss")
    private Date fechaCreacion;

    @Column(name = "HORA_CREACION")
    @Temporal(TemporalType.TIME)
    //@ApiModelProperty(notes = "Hora de Creacion del Usuario, formato hh:mm:ss")
    private Date horaCreacion = new Date();

    // Relaciones con otras Tablas
    // Mapeo de la Relacion de la Tabla de Organizacones con Pais
    // Muchas Organizaciones = 1 Pais
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PAIS_ORGANIZACION", referencedColumnName = "ID_PAIS")
    @ApiModelProperty(notes = "Entidad del Pais, se envia desde un Json (\"idPaisOrganizacion\": { \"idPais\": \"valor\" })",
            required = true)
    private TblPais idPaisOrganizacion;

    // Mapeo de la Relacion de la Tabla de Organizacones con Tipo de Organizacion
    // Muchas Organizaciones = 1 Tipo de Organizacion
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TIPO_ORGANIZACION", referencedColumnName = "ID_TIPO_ORGANIZACION")
    @ApiModelProperty(notes = "Entidad del Tipo de Organizacion, se envia desde un Json (\"idTipoOrganizacion\": { \"idTipoOrganizacion\": \"valor\" })",
            required = true)
    private TblTipoOrganizacion idTipoOrganizacion;

    // Mapeo de la Relacion de la Tabla de Organizacones con Grupo de Organizacion
    // Muchas Grupo Organizaciones = 1 Grupo de Organizacion
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_GRUPO_ORGANIZACION", referencedColumnName = "ID_GRUPO_ORGANIZACION")
    @ApiModelProperty(notes = "Entidad del Grupo de Organizacion, se envia desde un Json (\"idGrupoOrganizazion\": { \"idGrupoOrganizazion\": \"valor\" })")
    private TblGrupoOrganizacion idGrupoOrganizazion;

    // Mapeo de la Relacion de la Tabla de Organizacones con Categorias de Organizacion
    // Muchas Organizaciones = 1 Categoria de Organizacion
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CAT_ORGANIZACION", referencedColumnName = "ID_CAT_ORGANIZACION")
    @ApiModelProperty(notes = "Entidad de la Categoria de Organizacion, se envia desde un Json (\"idCatOrganizacion\": { \"idCatOrganizacion\": \"valor\" })")
    private TblCategoriaOrganizacion idCatOrganizacion;

    /**
     * Constructor de la Clase
     */
    public TblOrganizacion() {
    }


    /**
     * Metodos Getters y Setters
     */
    public long getIdOrganizacion() {
        return idOrganizacion;
    }

    public void setIdOrganizacion(long idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }

    public String getCodOrganizacion() {
        return codOrganizacion;
    }

    public void setCodOrganizacion(String codOrganizacion) {
        this.codOrganizacion = codOrganizacion;
    }

    public String getInicalesOrganizacion() {
        return inicalesOrganizacion;
    }

    public void setInicalesOrganizacion(String inicalesOrganizacion) {
        this.inicalesOrganizacion = inicalesOrganizacion;
    }

    public String getDescOrganizacion() {
        return descOrganizacion;
    }

    public void setDescOrganizacion(String descOrganizacion) {
        this.descOrganizacion = descOrganizacion;
    }

    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    public String getDireccionFisicaOrganizacion() {
        return direccionFisicaOrganizacion;
    }

    public void setDireccionFisicaOrganizacion(String direccionFisicaOrganizacion) {
        this.direccionFisicaOrganizacion = direccionFisicaOrganizacion;
    }

    public String getTelefonoOrganizacion() {
        return telefonoOrganizacion;
    }

    public void setTelefonoOrganizacion(String telefonoOrganizacion) {
        this.telefonoOrganizacion = telefonoOrganizacion;
    }

    public String getEmailOrganizacion() {
        return emailOrganizacion;
    }

    public void setEmailOrganizacion(String emailOrganizacion) {
        this.emailOrganizacion = emailOrganizacion;
    }

    public String getDeptoReferencia() {
        return deptoReferencia;
    }

    public void setDeptoReferencia(String deptoReferencia) {
        this.deptoReferencia = deptoReferencia;
    }

    public String getContactoReferencia() {
        return contactoReferencia;
    }

    public void setContactoReferencia(String contactoReferencia) {
        this.contactoReferencia = contactoReferencia;
    }

    public String getWebOrganizacion() {
        return webOrganizacion;
    }

    public void setWebOrganizacion(String webOrganizacion) {
        this.webOrganizacion = webOrganizacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isSocioDesarrollo() {
        return socioDesarrollo;
    }

    public void setSocioDesarrollo(boolean socioDesarrollo) {
        this.socioDesarrollo = socioDesarrollo;
    }

    public boolean isAgenciaBeneficiaria() {
        return agenciaBeneficiaria;
    }

    public void setAgenciaBeneficiaria(boolean agenciaBeneficiaria) {
        this.agenciaBeneficiaria = agenciaBeneficiaria;
    }

    public boolean isUnidadEjecutora() {
        return unidadEjecutora;
    }

    public void setUnidadEjecutora(boolean unidadEjecutora) {
        this.unidadEjecutora = unidadEjecutora;
    }

    public boolean isAdministradorFinanciero() {
        return administradorFinanciero;
    }

    public void setAdministradorFinanciero(boolean administradorFinanciero) {
        this.administradorFinanciero = administradorFinanciero;
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

    public TblPais getIdPaisOrganizacion() {
        return idPaisOrganizacion;
    }

    public void setIdPaisOrganizacion(TblPais idPaisOrganizacion) {
        this.idPaisOrganizacion = idPaisOrganizacion;
    }

    public TblTipoOrganizacion getIdTipoOrganizacion() {
        return idTipoOrganizacion;
    }

    public void setIdTipoOrganizacion(TblTipoOrganizacion idTipoOrganizacion) {
        this.idTipoOrganizacion = idTipoOrganizacion;
    }

    public TblCategoriaOrganizacion getIdCatOrganizacion() {
        return idCatOrganizacion;
    }

    public void setIdCatOrganizacion(TblCategoriaOrganizacion idCatOrganizacion) {
        this.idCatOrganizacion = idCatOrganizacion;
    }
}
