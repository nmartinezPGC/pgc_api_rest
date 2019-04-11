/*
 * Copyright (c) 2019.  Nahum Martinez
 */

/**
 * @author nahum.martinez
 * @date 07/01/2019
 * @name PerfilesRepository
 */

package com.api.pgc.core.APIRestPGC.repository.seguridad;

import com.api.pgc.core.APIRestPGC.models.seguridad.TblPerfiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilesRepository extends JpaRepository<TblPerfiles, Integer> {
    /**
     * Metodo que despliega el Perfil de la BD
     * @autor Nahum Martinez | NAM
     * @version  07/01/2019/v1.0
     * @return usuario de la BD, por parametro de ID
     * @param idPerfil
     */
    TblPerfiles findByIdPerfil(long idPerfil );
}
