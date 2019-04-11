package com.api.pgc.core.APIRestPGC.repository.organizaciones;

import com.api.pgc.core.APIRestPGC.models.organizaciones.TblCategoriaOrganizacion;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblOrganizacion;
import com.api.pgc.core.APIRestPGC.models.organizaciones.TblTipoOrganizacion;
import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblPais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizacionRepository extends JpaRepository<TblOrganizacion, Integer> {

    /**
     * Metodo que despliega la Organizacion de la BD
     *
     * @param idOrganizacion
     * @return Organizacion de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 15/10/2018/v1.0
     */
    TblOrganizacion findByIdOrganizacion(long idOrganizacion);


    /**
     * Metodo que despliega la Organizacion de la BD
     *
     * @param codOrganizacion
     * @return Organizacion de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 09/01/2019/v1.0
     */
    TblOrganizacion findByCodOrganizacion(String codOrganizacion);


    /**
     * Metodo que despliega la Organizacion de la BD
     *
     * @param codOrganizacion
     * @return Organizacion de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 09/01/2019/v1.0
     */
    long countByCodOrganizacion(String codOrganizacion);


    /**
     * Metodo que despliega la Organizacion de la BD
     *
     * @param idOrganizacion
     * @return Organizacion de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 14/02/2019/v1.0
     */
    long countByIdOrganizacion(long idOrganizacion);


    /**
     * Metodo que despliega la Organizacion de la BD con el Tipo como parametro
     *
     * @param tblTipoOrganizacion
     * @return Organizacion de la BD, por paramtro de ID Tipo
     * @autor Nahum Martinez | NAM
     * @version 15/10/2018/v1.0
     */
    @Query("SELECT e FROM TblOrganizacion e WHERE e.idTipoOrganizacion = :idTipoOrganizacion ")
    List<TblOrganizacion> getTipoOrganizacion(@Param("idTipoOrganizacion") TblTipoOrganizacion tblTipoOrganizacion);


    /**
     * Metodo que despliega la Organizacion de la BD con el Tipo y Pais como parametro
     *
     * @param tblTipoOrganizacion
     * @param tblPaisOrganizacion
     * @return Organizacion de la BD, por paramtro de ID Tipo y ID Pais
     * @autor Nahum Martinez | NAM
     * @version 15/10/2018/v1.0
     */
    @Query("SELECT e FROM TblOrganizacion e WHERE e.idTipoOrganizacion = :idTipoOrganizacion AND e.idPaisOrganizacion = :idPaisOrganizacion ")
    List<TblOrganizacion> getTipoPaisOrganizacion(@Param("idTipoOrganizacion") TblTipoOrganizacion tblTipoOrganizacion, @Param("idPaisOrganizacion") TblPais tblPaisOrganizacion);



    /**
     * Metodo que despliega la Organizacion de la BD con el Tipo, Pais y Categoria como parametro
     *
     * @param tblTipoOrganizacion
     * @param tblPaisOrganizacion
     * @return Organizacion de la BD, por paramtro de ID Tipo, ID Pais y ID Categoria
     * @autor Nahum Martinez | NAM
     * @version 23/01/2019/v1.0
     */
    @Query("SELECT e FROM TblOrganizacion e WHERE e.idTipoOrganizacion = :idTipoOrganizacion " +
            "AND e.idPaisOrganizacion = :idPaisOrganizacion " +
            "AND e.idCatOrganizacion = :idCatOrganizacion ")
    List<TblOrganizacion> getTipoPaisCatOrganizacion(@Param("idTipoOrganizacion") TblTipoOrganizacion tblTipoOrganizacion,
                                                     @Param("idPaisOrganizacion") TblPais tblPaisOrganizacion,
                                                     @Param("idCatOrganizacion") TblCategoriaOrganizacion tblCategoriaOrganizacion);


    /**
     * Metodo que despliega la Organizacion de la BD con el Pais como parametro
     *
     * @param tblPais
     * @return Organizacion de la BD, por paramtro de ID Pais
     * @autor Nahum Martinez | NAM
     * @version 20/10/2018/v1.0
     */
    @Query("SELECT e FROM TblOrganizacion e WHERE e.idPaisOrganizacion = :idPaisOrganizacion ")
    List<TblOrganizacion> getPaisOrganizacion(@Param("idPaisOrganizacion") TblPais tblPais);
}
