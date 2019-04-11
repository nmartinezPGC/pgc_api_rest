/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.sectores;

import com.api.pgc.core.APIRestPGC.models.sectores.TblTipoSector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoSectorRepository extends JpaRepository<TblTipoSector, Integer> {

    /**
     * Metodo que despliega el Tipo de Sector de la BD
     *
     * @param idTipoSector
     * @return Organizacion de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 13/03/2019/v1.0
     */
    TblTipoSector findByIdTipoSector(long idTipoSector);


    /**
     * Metodo que despliega el Tipo de Sector de la BD
     *
     * @param codTipoSector
     * @return Organizacion de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 13/03/2019/v1.0
     */
    TblTipoSector findByCodTipoSector(String codTipoSector);


    /**
     * Metodo que despliega el Tipo de Sector de la BD
     *
     * @param codTipoSector
     * @return Tipo de Sector de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 13/03/2019/v1.0
     */
    long countByCodTipoSector(String codTipoSector);


    /**
     * Metodo que despliega el Tipo de Sector de la BD
     *
     * @param idTipoSector
     * @return Organizacion de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 13/03/2019/v1.0
     */
    long countByIdTipoSector(long idTipoSector);

}
