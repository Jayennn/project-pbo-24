package io.github.jayennn.BlockchainVoting.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class Utility untuk membaca atribut config.properties
 * Class ini menggunakan konsep Singleton methode
 */
public class ConfigManager {
    private static ConfigManager instance;
    private Properties properties;

    /**
     * Private Constructor sehingga insiasi objek dapat dikontrol
     * @Throws RuntimeException jika properties tidak ditemukan
     */
    private ConfigManager(){
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")){
            if (input == null){
                throw new RuntimeException("Unable to find config.properties");
            }
            properties.load(input);
        }catch (IOException e){
            throw new RuntimeException("Failed to load properties",e);
        }
    }

    /**
     * Dapatkan object tunggal class atau melakukan inisiasi apabila tidak ditemukan
     * @return instance dari property static class
     */
    public static ConfigManager getInstance(){
        if (instance == null){
            instance = new ConfigManager();
        }
        return instance;
    }

    /**
     * Dapatkan properti dari key yang diberikan
     * @param key kata kunci atau nama atribut properti
     * @return properti dari key, atau null jika tidak ditemukan
     */
    public String get(String key){
        return properties.getProperty(key);
    }

    public Properties getProperties() {
        return properties;
    }

    /**
     * Dapatkan properti dari key atau return nilai default
     * @param key kata kunci atau atribut properti
     * @param defaultValue nilai default yang akan dikembalikan
     * @return properti dari key, atau defaultValue jika tidak ditemukan
     */
    public String get(String key, String defaultValue){
        return properties.getProperty(key,defaultValue);
    }
}
