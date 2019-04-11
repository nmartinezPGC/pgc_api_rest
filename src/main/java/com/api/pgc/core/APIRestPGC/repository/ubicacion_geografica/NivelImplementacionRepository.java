/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.repository.ubicacion_geografica;

import com.api.pgc.core.APIRestPGC.models.ubicacion_geografica.TblNivelImplementacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NivelImplementacionRepository extends JpaRepository<TblNivelImplementacion, Integer> {

    /**
     * Metodo que despliega el Nivel de Implementacion de la BD
     * @autor Nahum Martinez | NAM
     * @version  20/02/2019/v1.0
     * @return Nivel de Implementacion de la BD, por paramtro de ID
     * @param idNivel
     */
    TblNivelImplementacion findByIdNivel(long idNivel);
}
