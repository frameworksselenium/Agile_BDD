package com.open.qa.util.loadConfig;

import com.open.qa.core.exception.Exceptions;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Config {
    public static Properties properties = null;
    public static Properties init(){
        properties = new Properties();
        try{
            File directory = new File (".");
            String rootFolderName = directory.getCanonicalPath();
            String configFilePath = rootFolderName + "\\src\\test\\resources\\config";
            FileInputStream fis = new FileInputStream( configFilePath + "\\Sys.properties");
            properties.load(fis);
            fis.close();
             }catch(Exception e){
            throw new Exceptions(e);
        }
        return properties;
    }

    public static void createFolder(String folderPath){
        File objDir=new File(folderPath);
        if(!objDir.exists()) {
            objDir.mkdir();
        }
    }
}
