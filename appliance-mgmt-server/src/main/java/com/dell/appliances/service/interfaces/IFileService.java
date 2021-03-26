package com.dell.appliances.service.interfaces;

import com.dell.appliances.exceptions.ApplianceException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface IFileService {
    public void init();

    public String save(MultipartFile[] files,String directory) throws ApplianceException;

    public File load(String directory) throws ApplianceException;

    public File load(String directory,String file) throws ApplianceException;
    public File load(String directory,String[] file) throws ApplianceException;

    public List<String> getAll(String directory) throws ApplianceException;

}
