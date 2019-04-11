/*
 * Copyright (c) 2019.  Nahum Martinez
 */

/**
 * @author nahum.martinez
 * @date 22/03/2019
 * @name SectoresOcdeCadResources
 */

package com.api.pgc.core.APIRestPGC.resourses.sectores;


import com.api.pgc.core.APIRestPGC.models.sectores.TblNivelSector;
import com.api.pgc.core.APIRestPGC.models.sectores.TblSectorOcdeCad;
import com.api.pgc.core.APIRestPGC.repository.sectores.NivelSectorRepository;
import com.api.pgc.core.APIRestPGC.repository.sectores.SectorOcdeCadRepository;
import com.api.pgc.core.APIRestPGC.repository.sectores.TipoSectorRepository;
import com.api.pgc.core.APIRestPGC.utilities.msgExceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "SectoresApi", description = "Operaciones sobre el Modulo de Sectores", tags = "Sectores")
public class SectoresOcdeCadResources {
    //Propiedades de la Clase
    String msgMethod = null;

    @Autowired
    SectorOcdeCadRepository _sectorOcdeCadRepository;

    @Autowired
    TipoSectorRepository _tipoSectorRepository;

    @Autowired
    NivelSectorRepository _nivelSectorRepository;


    /**
     * Metodo que despliega la Lista de todos los Sectores OCDE/CAD de la BD
     *
     * @return Lista de las Sectores OCDE/CAD de la BD
     * @autor Nahum Martinez | NAM
     * @version 22/03/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Listado de Todos los Sectores OCDE/CAD de la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_OCDE_ENDPOINT, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getAllSectoresOcdeCad() throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        msgMethod = "Listado de todos los Sectores OCDE/CAD registrados en la BD";

        try {
            //Sobreescirbe el Metodo de Mensajes
            if ( _sectorOcdeCadRepository.findAll().isEmpty() || _sectorOcdeCadRepository.findAll() == null ) {
                msgMethod = "No Existen, Sectores OCDE/CAD resgitrados en la Base de Datos, Contacta al Administrador";

                msgExeptions.map.put("error", "No data found");
                msgExeptions.map.put("data", _sectorOcdeCadRepository.findAll( new Sort(Sort.Direction.DESC, "<idSector>" )));
                msgExeptions.map.put("totalRecors", _sectorOcdeCadRepository.count());
            } else {
                msgExeptions.map.put("data", _sectorOcdeCadRepository.findAll());
                msgExeptions.map.put("totalRecors", _sectorOcdeCadRepository.count());
            }
            // Retorno del JSON
            return msgExeptions.msgJson(msgMethod, 200);
        } catch (Exception ex) {
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getAllSectoresOcdeCad


    /**
     * Metodo que despliega el Sector OCDE/CAD de la BD
     *
     * @param idSector Identificador del Sector OCDE/CAD a Buscar
     * @return Sector OCDE/CAD de la BD
     * @autor Nahum Martinez | NAM
     * @version 22/03/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Sector OCDE/CAD enviado a buscar de la BD, con el ID como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_OCDE_ENDPOINT_FIND_BY_ID_SECTOR, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSectorOcdeCadById(@ApiParam(value = "Identificador del Sector OCDE/CAD a Buscar", required = true)
                                                       @PathVariable("idSector") long idSector) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        try {
            if ( _sectorOcdeCadRepository.findByIdSector(idSector) == null) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Sector OCDE/CAD consultado";

                msgExeptions.map.put("error", "No data found");

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            } else {
                //Sobreescirbe el Metodo de Mensajes
                msgMethod = "Detalle de Sector OCDE/CAD Consultado";
                msgExeptions.map.put("data", _sectorOcdeCadRepository.findByIdSector(idSector));

                //Retorno del json
                return msgExeptions.msgJson(msgMethod, 200);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato de Sector OCDE/CAD consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getSectorOcdeCadById



    /**
     * Metodo que despliega el Sector OCDE/CAD de la BD
     *
     * @param idNivelSector Identificador del Nivel de Sector OCDE/CAD a Buscar
     * @return Sector OCDE/CAD de la BD
     * @autor Nahum Martinez | NAM
     * @version 22/03/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Sector OCDE/CAD enviado a buscar de la BD, con el ID de Nivel como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_OCDE_ENDPOINT_FIND_BY_ID_NIVEL_SECTOR, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSectorOcdeCadByIdNivelSector(@ApiParam(value = "Identificador de Nivel del Sector OCDE/CAD a Buscar", required = true)
                                                        @PathVariable("idNivelSector") long idNivelSector) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Buscamos el Nivel Indicado por el Usuario
        try {
            TblNivelSector tblNivelSector = _nivelSectorRepository.findByIdNivelSector(idNivelSector);

            try {
                if (_sectorOcdeCadRepository.countSectorOCByIdNivelSector(tblNivelSector) == 0 ) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado datos de Sector OCDE/CAD consultado, con el Nivel indicado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de Sector OCDE/CAD Consultado";
                    msgExeptions.map.put("data", _sectorOcdeCadRepository.getSectorOCByIdNivelSector(tblNivelSector));
                    msgExeptions.map.put("totalRecords", _sectorOcdeCadRepository.countSectorOCByIdNivelSector(tblNivelSector));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Sector OCDE/CAD consultado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato del Nivel de Sector OCDE/CAD consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getSectorOcdeCadByIdNivelSector


    /**
     * Metodo que despliega el Sector OCDE/CAD de la BD
     *
     * @param idNivelSector Identificador del Nivel de Sector OCDE/CAD a Buscar
     * @param sectorPadreId Identificador del OCDE/CAD Padre a Buscar
     * @return Sector OCDE/CAD de la BD
     * @autor Nahum Martinez | NAM
     * @version 22/03/2019/v1.0
     */
    @ApiOperation(value = "Retorna el Sector OCDE/CAD enviado a buscar de la BD, con el ID de Nivel y Sector Padre como parametro", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = SECTORES_OCDE_ENDPOINT_FIND_BY_ID_TIPO_NIVEL_PADRE, produces = "application/json; charset=UTF-8")
    public HashMap<String, Object> getSectorOcdeCadByIdNivelSectorAndSectorPadreId(@ApiParam(value = "Identificador de Nivel del Sector y Sector Padre OCDE/CAD a Buscar", required = true)
                                                                   @PathVariable("idNivelSector") long idNivelSector,  @PathVariable("sectorPadreId") long sectorPadreId) throws Exception {
        //Ejecuta el try Cacth
        msgExceptions msgExeptions = new msgExceptions();

        // Buscamos el Nivel Indicado y el Sector Padre
        try {
            TblNivelSector _tblNivelSector = _nivelSectorRepository.findByIdNivelSector(idNivelSector);
            TblSectorOcdeCad _tblSectorOcdeCad = _sectorOcdeCadRepository.findByIdSector(sectorPadreId);

            try {
                if (_sectorOcdeCadRepository.countSectorOCByIdNivelSectorAndSectorPadreId(_tblNivelSector, _tblSectorOcdeCad) == 0 ) {
                    // Sobreescirbe el Metodo de Mensajes
                    msgMethod = "No se ha encontrado datos de Sector OCDE/CAD consultado, con el Sector Padre y Nivel indicado";

                    msgExeptions.map.put("error", "No data found");

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                } else {
                    //Sobreescirbe el Metodo de Mensajes
                    msgMethod = "Detalle de Sector OCDE/CAD Consultado";
                    msgExeptions.map.put("data", _sectorOcdeCadRepository.getSectorOCByIdNivelSectorAndSectorPadreId(_tblNivelSector, _tblSectorOcdeCad));
                    msgExeptions.map.put("totalRecords", _sectorOcdeCadRepository.countSectorOCByIdNivelSectorAndSectorPadreId(_tblNivelSector, _tblSectorOcdeCad));

                    //Retorno del json
                    return msgExeptions.msgJson(msgMethod, 200);
                }
            } catch (Exception ex) {
                // Sobreescirbe el Metodo de Mensajes
                msgMethod = "No se ha encontrado dato de Sector OCDE/CAD consultado";
                throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
            }
        } catch (Exception ex) {
            // Sobreescirbe el Metodo de Mensajes
            msgMethod = "No se ha encontrado dato del Nivel de Sector OCDE/CAD consultado";
            throw new RuntimeException("Se ha producido una excepción con el mensaje : " + msgMethod, ex);
        }
    }// FIN | getSectorOcdeCadByIdNivelSectorAndSectorPadreId
}
