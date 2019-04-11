/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.sectores;

import com.api.pgc.core.APIRestPGC.models.sectores.TblNivelSector;
import com.api.pgc.core.APIRestPGC.models.sectores.TblTipoSector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NivelSectorRepository extends JpaRepository<TblNivelSector, Integer> {

    /**
     * Metodo que despliega el Nivel de Sector de la BD
     *
     * @param idNivelSector
     * @return Nivel de Sector de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 13/03/2019/v1.0
     */
    TblNivelSector findByIdNivelSector(long idNivelSector);


    /**
     * Metodo que despliega el Nivel de Sector de la BD
     *
     * @param codigoNivelSector
     * @return Nivel de Sector de la BD, por parametro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 13/03/2019/v1.0
     */
    TblTipoSector findByCodigoNivelSector(String codigoNivelSector);


    /**
     * Metodo que despliega el Nivel de Sector de la BD
     *
     * @param codigoNivelSector
     * @return Nivel de Sector de la BD, por paramtro de Codigo
     * @autor Nahum Martinez | NAM
     * @version 13/03/2019/v1.0
     */
    long countByCodigoNivelSector(String codigoNivelSector);


    /**
     * Metodo que despliega el Nivel de Sector de la BD
     *
     * @param idNivelSector
     * @return Nivel de Sector de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 13/03/2019/v1.0
     */
    long countByIdNivelSector(long idNivelSector);

}
