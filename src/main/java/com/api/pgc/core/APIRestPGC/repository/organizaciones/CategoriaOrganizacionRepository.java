/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.organizaciones;

import com.api.pgc.core.APIRestPGC.models.organizaciones.TblCategoriaOrganizacion;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblTipoOrganizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaOrganizacionRepository extends JpaRepository<TblCategoriaOrganizacion, Integer> {

    /**
     * Metodo que despliega la Categoria de Organizacion de la BD
     *
     * @param idCatOrganizacion
     * @return Categoria de Organizacion de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 17/01/2019/v1.0
     */
    TblCategoriaOrganizacion findByIdCatOrganizacion(long idCatOrganizacion);


    /**
     * Metodo que despliega la Categoria Organizacion de la BD
     *
     * @param idCatOrganizacion
     * @return Categoria Organizacion de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 18/01/2019/v1.0
     */
    long countByIdCatOrganizacion(long idCatOrganizacion);


    /**
     * Metodo que despliega la Categoria Organizacion de la BD
     *
     * @param tblTipoOrganizacion
     * @return Categoria Organizacion de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 21/01/2019/v1.0
     */
    @Query("SELECT e FROM TblCategoriaOrganizacion e WHERE e.idTipoOrganizacionCat = :idTipoOrganizacion ")
    List<TblCategoriaOrganizacion> getCategoriasByTipo(@Param("idTipoOrganizacion") TblTipoOrganizacion tblTipoOrganizacion);


    /**
     * Metodo que despliega la Categoria Organizacion de la BD
     *
     * @param tblTipoOrganizacion
     * @return Categoria Organizacion de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 21/01/2019/v1.0
     */
    @Query("SELECT COUNT(e) FROM TblCategoriaOrganizacion e WHERE e.idTipoOrganizacionCat = :idTipoOrganizacion ")
    long countByTipoOrganizacion(@Param("idTipoOrganizacion") TblTipoOrganizacion tblTipoOrganizacion);

}
