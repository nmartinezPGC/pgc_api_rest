/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.resourses.fileUpload;

// Imports Extras
import com.api.pgc.core.APIRestPGC.payload.UploadFileResponse;
import com.api.pgc.core.APIRestPGC.service.fileUpload.FileStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RestController;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_BASE_PATH)
@Api(value = "uploadFileApi" , description = "Operaciones sobre el Modulo de Recursos del Pryecto", tags = "Recursos del Proyecto")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;


    /**
     * Metodo que sirve para ingresar un Documento a la ves en la BD
     * @autor Nahum Martinez | NAM
     * @version  06/04/2019/v1.0
     * @return URI del Documento ingresado
     */
    @ApiOperation(value = "Ingresa en Disco Duro local, la Información enviada por el archivo y grabarla en la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = RECURSOS_DOC_UPLOAD_FILE)
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/mant-actividades/recursos-proyecto/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    } // FIN | uploadFile

    /**
     * Metodo que sirve para ingresar varios Documentos a la ves en la BD
     * @autor Nahum Martinez | NAM
     * @version  06/04/2019/v1.0
     * @return Listado de los Documentos ingresados en la BD
     */
    @ApiOperation(value = "Ingresa en Disco Duro local, la Información enviada por los archivos y grabarlas en la BD", authorizations = {@Authorization(value = "Token-PGC")})
    @PostMapping(value = RECURSOS_DOC_UPLOAD_FILE_ARRAY)
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    } // FIN | uploadMultipleFiles


    /**
     * Metodo que sirve para descagar un Documento a la ves en la BD
     * @autor Nahum Martinez | NAM
     * @version  06/04/2019/v1.0
     * @return Dowload Documento
     */
    @ApiOperation(value = "Descarga el Documento solicitado", authorizations = {@Authorization(value = "Token-PGC")})
    @GetMapping(value = RECURSOS_DOC_UPLOAD_FILE_DOWLOAD)
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    } // FIN | downloadFile
}
