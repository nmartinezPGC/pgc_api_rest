/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica;

import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblMunicipios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicRepository extends JpaRepository<TblMunicipios, Integer> {

    /**
     * Metodo que despliega el Municipio de la BD
     * @autor Nahum Martinez | NAM
     * @version  18/02/2019/v1.0
     * @return Municipio de la BD, por paramtro de ID
     * @param idMunicipio
     */
    TblMunicipios findByIdMunicipio(long idMunicipio);
}
