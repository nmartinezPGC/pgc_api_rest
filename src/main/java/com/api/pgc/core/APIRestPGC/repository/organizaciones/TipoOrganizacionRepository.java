package com.api.pgc.core.APIRestPGC.repository.organizaciones;

import com.api.pgc.core.APIRestPGC.models.organizaciones.TblTipoOrganizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoOrganizacionRepository extends JpaRepository<TblTipoOrganizacion, Integer> {

    /**
     * Metodo que despliega el Tipo de Organizacion de la BD
     *
     * @param idTipoOrganizacion
     * @return Tipo de Organizacion de la BD, por paramtro de ID
     * @autor Nahum Martinez | NAM
     * @version 13/10/2018/v1.0
     */
    TblTipoOrganizacion findByIdTipoOrganizacion(long idTipoOrganizacion);

}
