package com.dell.appliances.service;

import com.dell.appliances.exceptions.ApplianceException;
import com.dell.appliances.exceptions.Error;
import com.dell.appliances.service.interfaces.IFileService;
import net.lingala.zip4j.ZipFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Thursday 3/18/2021
 *
 */
@Service
public class FileService implements IFileService {

    public static final Logger LOG = LogManager.getLogger(FileService.class);

    @Value("${files.uploads.path}")
    private String uploadsDirectory;

    private static Path root;

    @Override
    @PostConstruct
    public void init(){
        try {
            root = Paths.get(uploadsDirectory);
            if(!Files.exists(root))
                Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public String save(MultipartFile[] files,String directory) throws ApplianceException {
        List<String> failed = new ArrayList<>();
        List<String> uploaded = new ArrayList<>();
        Path dir = root;
        try {
            dir = Paths.get(uploadsDirectory+File.separator+directory);

            if(!Files.exists(dir))
                Files.createDirectory(dir);
        } catch (IOException e) {
            LOG.error("Failed to create directory. Error: " , e);
        }
        for(MultipartFile file : files){
            try {
                Files.deleteIfExists(Paths.get(dir+File.separator+file.getOriginalFilename()));
                Files.copy(file.getInputStream(), dir.resolve(file.getOriginalFilename()));
                uploaded.add(file.getOriginalFilename());
            } catch (Exception e) {
                LOG.error("Could not store the file. Error: " , e);
                failed.add(file.getOriginalFilename());
            }
        }
        if(!failed.isEmpty()){
            throw new ApplianceException(Error.FAILED_TO_UPLOAD_FILE,new String[]{failed.toString()},null);
        }
        return uploaded.toString();
    }

    @Override
    public List<String> getAll(String directory)  throws ApplianceException {
        LOG.info("Getting list of all the files in directory:"+directory);
        try {
            List<String> files = new ArrayList<>();
            File[] sortedFiles = new File(uploadsDirectory+File.separator+directory).listFiles();
            Arrays.sort(sortedFiles, Comparator.comparingLong(File::lastModified));
            for(File file : sortedFiles){
                files.add(file.getName());
            }
            return files;
        }catch (Exception e){
            LOG.error("Failed to get files");
            throw new ApplianceException(Error.FAILED_TO_GET_FILE,new String[]{directory},e);
        }
    }

    @Override
    public File load(String directory)  throws ApplianceException {
        LOG.info("Downloading the zip file of directory:"+directory);
        String dir = uploadsDirectory+File.separator+directory;
        String zipFile = dir + "_licenses.zip";
        LOG.info("Building zip file:"+zipFile);
        try {
            Files.deleteIfExists(Paths.get(zipFile));
            ZipFile file = new ZipFile(zipFile);
            file.addFolder(new File(dir));
            return new File(zipFile);
        }catch (Exception e){
            LOG.error("Failed to create zip file");
            throw new ApplianceException(Error.FAILED_TO_GET_FILE,new String[]{directory+".zip"},e);
        }
    }

    @Override
    public File load(String directory,String fileName)  throws ApplianceException {
        LOG.info("Downloading the file "+fileName+" from directory:"+directory);
        try {
            for(File file:new File(uploadsDirectory+File.separator+directory).listFiles()){
                if(file.getName().contains(fileName)){
                    return file;
                }
            }
            return null;
        }catch (Exception e){
            LOG.error("Failed to get file");
            throw new ApplianceException(Error.FAILED_TO_GET_FILE,new String[]{directory+".zip"},e);
        }
    }

    @Override
    public File load(String directory,String[] fileNames)  throws ApplianceException {
        LOG.info("Downloading some files from directory:"+directory);
        try {
            String dir = uploadsDirectory+File.separator+directory;
            String zp = dir + "_licenses.zip";
            ZipFile zipFile = new ZipFile(zp);
            Files.deleteIfExists(Paths.get(zp));
            List<File> files = new ArrayList<>();
            for(File file:new File(uploadsDirectory+File.separator+directory).listFiles()){
                if(Arrays.asList(fileNames).contains(file.getName())){
                    files.add(file);
                }
            }
            zipFile.addFiles(files);
            return new File(zp);
        }catch (Exception e){
            LOG.error("Failed to get file");
            throw new ApplianceException(Error.FAILED_TO_GET_FILE,new String[]{directory+".zip"},e);
        }
    }
}
