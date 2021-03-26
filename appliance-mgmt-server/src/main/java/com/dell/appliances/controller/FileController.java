package com.dell.appliances.controller;

import com.dell.appliances.common.ApplianceUtil;
import com.dell.appliances.common.UIMessages;
import com.dell.appliances.exceptions.ApplianceException;
import com.dell.appliances.service.interfaces.IFileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Thursday 3/18/2021
 *
 */
@RestController
@RequestMapping(APIConstants.FILES)
public class FileController {

    public static final Logger LOG = LogManager.getLogger(FileController.class);

    @Autowired
    IFileService fileService;

    @PostMapping(APIConstants.UPLOAD_FILES)
    public ResponseEntity<?> uploadFiles(@RequestParam("files") MultipartFile[] files,@RequestParam("directory") String directory) {
        try {
            directory = directory.replaceAll(" ", "_").toLowerCase();
            String uploadedFiles = fileService.save(files,directory);
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(UIMessages.FILES_ADDED+": "+uploadedFiles, HttpStatus.CREATED), HttpStatus.OK);
        } catch (ApplianceException e) {
            LOG.error("Failed to get all appliance models ", e);
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e), e.getErrorCode());
        }
    }

    @GetMapping(APIConstants.GET_LIST)
    public ResponseEntity<?> getAllFiles(@PathVariable @RequestBody String applianceName) {
        try {
            applianceName = applianceName.replaceAll(" ", "_").toLowerCase();
            List<String> files = fileService.getAll(applianceName);
            return new ResponseEntity<>(files, HttpStatus.OK);
        } catch (ApplianceException e) {
            LOG.error("Failed to get files",e);
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e), e.getErrorCode());
        }catch (Exception e){
            LOG.error("Failed to get files",e);
            return new ResponseEntity<>("Failed to get files",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(APIConstants.DOWNLOAD_FILES)
    public ResponseEntity<?> getFiles(@PathVariable @RequestBody String applianceName) {
        try {
            applianceName = applianceName.replaceAll(" ", "_").toLowerCase();
            File file = fileService.load(applianceName);
            Path path = Paths.get(file.getAbsolutePath());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"").body(new ByteArrayResource(Files.readAllBytes(path)));
        } catch (ApplianceException e) {
            LOG.error("Failed to get files",e);
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e), e.getErrorCode());
        }catch (Exception e){
            LOG.error("Failed to get files",e);
            return new ResponseEntity<>("Failed to get files",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(APIConstants.DOWNLOAD_FILES)
    public ResponseEntity<?> getFiles(@PathVariable @RequestBody String applianceName,@RequestBody String[] files) {
        try {
            applianceName = applianceName.replaceAll(" ", "_").toLowerCase();
            File file = fileService.load(applianceName,files);
            Path path = Paths.get(file.getAbsolutePath());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"").body(new ByteArrayResource(Files.readAllBytes(path)));
        } catch (ApplianceException e) {
            LOG.error("Failed to get files",e);
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e), e.getErrorCode());
        }catch (Exception e){
            LOG.error("Failed to get files",e);
            return new ResponseEntity<>("Failed to get files",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(APIConstants.DOWNLOAD_SINGLE_FILE)
    public ResponseEntity<?> getFile(@PathVariable @RequestBody String applianceName,@PathVariable @RequestBody String fileName) {
        try {
            applianceName = applianceName.replaceAll(" ", "_").toLowerCase();
            File file = fileService.load(applianceName,fileName);
            Path path = Paths.get(file.getAbsolutePath());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"").body(new ByteArrayResource(Files.readAllBytes(path)));
        } catch (ApplianceException e) {
            LOG.error("Failed to get files",e);
            return new ResponseEntity<>(ApplianceUtil.buildApiResponse(e), e.getErrorCode());
        }catch (Exception e){
            LOG.error("Failed to get files",e);
            return new ResponseEntity<>("Failed to get files",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
