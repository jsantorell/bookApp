//This file is checked and Complete
package edu.wctc.distjava.jgl.bookwebapp.model.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This Class is used to easily get an instance of the current properties file
 * and use it in other classes.
 *
 * @author Jeremy Santorelli
 */
public final class ConfigurationParser {

    private static ConfigurationParser config;
    File file;

    private ConfigurationParser() {
    }

    public Properties getConfigutationProperties() {
        
        String fileName = 
                "C:\\Users\\jerem\\Documents\\NetBeansProjects\\BookWebApp\\" +
                "src" + File.separator + "main" + File.separator +
                "java" + File.separator + "edu" + File.separator + "wctc" +
                File.separator + "distjava" + File.separator + "jgl" +
                File.separator + "bookwebapp" + File.separator + "model" +
                File.separator + "config" + File.separator + "config.properties";
        file = new File(fileName);
        Properties properties = new Properties();

        FileInputStream fileInput;

        try {
            fileInput = new FileInputStream(file);
            properties.load(fileInput);
            fileInput.close();
        } catch (FileNotFoundException fnf) {

            System.out.println(fnf.getMessage());
        } catch (IOException io) {
             System.out.println(io.getMessage());
        }

        return properties;
    }

    public static ConfigurationParser getInstance() {

        if (ConfigurationParser.config == null) {
            ConfigurationParser.config = new ConfigurationParser();

        }

        return ConfigurationParser.config;

    }
    
//    
//    public static void main(String[] args) throws Exception {
//        
//        
//        ConfigurationParser c = ConfigurationParser.getInstance();
//        
//        Properties p = c.getConfigutationProperties();
//        
//        System.out.println(Double.parseDouble(p.getProperty("car.fee")));
//        
//    }

}
