/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapas;

import entidades.Parede;
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
public class CarregaParedes {

    public void carregarParedes(Mapa mapa){
        File[] mapas = new File("db/mapas").listFiles();
        
        String path = null;
        
        for(File f : mapas){
            try {
                Properties p = new Properties();
                p.load(new FileInputStream(new File(f.getPath()+"/mapa")));
                if(p.containsKey("id")){
                    if(Integer.valueOf(p.getProperty("id")) == mapa.id){
                        path = f.getPath()+"/paredes";
                        break;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(CarregaNpcsDoMapa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(path != null){
            if(new File(path).exists()){
                File[] paredes = new File(path).listFiles();
                for(File f : paredes){
                    try {
                        Properties p = new Properties();
                        p.load(new FileInputStream(f));
                        Parede pa = new Parede(Integer.valueOf(p.getProperty("x")), 
                                Integer.valueOf(p.getProperty("y")), 
                                Integer.valueOf(p.getProperty("w")), 
                                Integer.valueOf(p.getProperty("h")));
                        pa.geografico.mapa = mapa.id;
                        mapa.adicionar(pa);
                    } catch (IOException ex) {
                        Logger.getLogger(CarregaNpcsDoMapa.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            System.out.println("Mapa: \""+mapa.nome+"\" não possui Teleportes");
        }
    }
    
}
