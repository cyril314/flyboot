package com.fit.core.excel;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TemplateFileUtil {

    public static FileInputStream getTemplates(String tempName) throws IOException {
//        System.out.println("classpath:excel-templates: " + ResourceUtils.getFile("classpath:excel-templates/"));
//        return new FileInputStream(ResourceUtils.getFile("classpath:excel-templates/" + tempName));
        ClassPathResource resource = new ClassPathResource("excel-templates/" + tempName);
        InputStream inputStream = resource.getInputStream();

        File targetFile = new File(tempName);
        FileUtils.copyInputStreamToFile(inputStream, targetFile);
        return new FileInputStream(targetFile);
    }
}