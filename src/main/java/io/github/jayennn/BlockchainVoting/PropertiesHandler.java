package io.github.jayennn.BlockchainVoting;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHandler {
    public static Properties getProps(String name){
        Properties props = new Properties();


        try(InputStream input = new FileInputStream(name)){
            props.load(input);
        }catch (IOException e){
            return null;
        }
        return props;
    }
}
