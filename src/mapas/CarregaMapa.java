/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhones
 */
public class CarregaMapa {

    public Mapa carregar(File pasta){
        File propriedades = new File(pasta.getPath()+"/mapa");
        if(propriedades.exists()){
            try {
                Properties p = new Properties();
                p.load(new FileInputStream(propriedades));
                if(p.containsKey("nome") && p.containsKey("x") && p.containsKey("y")){
                    Mapa m = new Mapa(Integer.valueOf(p.getProperty("id")), 
                            p.getProperty("nome"), Integer.valueOf(p.getProperty("x")), 
                            Integer.valueOf(p.getProperty("y")));
                    return m;
                }
            } catch (IOException ex) {
                Logger.getLogger(CarregaMapa.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Mapa não carregado. Não existe um arquivo de propriedades.\n"
                    + "-> "+pasta);
        }
        return null;
    }
    
}
