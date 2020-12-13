/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.view;

import br.ufes.gestao.imagem.view.tela_principal.TelaPrincipalPresenter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author bruno
 */
public class Principal {
    public static void main(String[] args) {
        Principal app = new Principal();
        Properties prop = app.loadPropertiesFile("config.properties");
        System.setProperty("db.name", prop.getProperty("db.name"));
        
        new TelaPrincipalPresenter();
    }
    
    public Properties loadPropertiesFile(String filePath) {
        Properties prop = new Properties();

        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            prop.load(resourceAsStream);
        } catch (IOException e) {
            System.err.println("Unable to load properties file : " + filePath);
        }

        return prop;
    }
    
}
