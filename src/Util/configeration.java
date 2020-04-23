package Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class configeration {
    private Properties prop;
    private FileInputStream is;
    public configeration(String fileName){
        prop = new Properties();
        try {
            is = new FileInputStream(fileName);
        } catch (FileNotFoundException e){

        }
        try {
            prop.load(is);
        }catch (IOException e){

        }
    }

    public String getProp( String propName) {
        return prop.getProperty(propName);
    }
}
