/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapas;

import entidades.Teleporte;
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
public class CarregaTeleportes {
    
    public void carregarTeleportes(Mapa mapa){
        File[] mapas = new File("db/mapas").listFiles();
        
        String path = null;
        
        for(File f : mapas){
            try {
                Properties p = new Properties();
                p.load(new FileInputStream(new File(f.getPath()+"/mapa")));
                if(p.containsKey("id")){
                    if(Integer.valueOf(p.getProperty("id")) == mapa.id){
                        path = f.getPath()+"/tps";
                        break;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(CarregaNpcsDoMapa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(path != null){
            if(new File(path).exists()){
                File[] tps = new File(path).listFiles();
                for(File f : tps){
                    try {
                        Properties p = new Properties();
                        p.load(new FileInputStream(f));
                        if(p.containsKey("destino")){
                            Teleporte t = new Teleporte(Integer.valueOf(p.getProperty("destino")), 0, 0);
                            t.geografico.mapa = mapa.id;
                            if(p.containsKey("x")){
                                t.geografico.x = Integer.valueOf(p.getProperty("x"));
                            } else {
                                System.out.println("Teleporte sem posição X\nMapa: "+mapa.nome);
                            }
                            if(p.containsKey("y")){
                                t.geografico.y = Integer.valueOf(p.getProperty("y"));
                            } else {
                                System.out.println("Teleporte sem posição Y\nMapa: "+mapa.nome);
                            }
                            if(p.containsKey("dx")){
                                t.dx = Integer.valueOf(p.getProperty("dx"));
                            }
                             else {
                                System.out.println("Teleporte sem posição Destino X\nMapa: "+mapa.nome);
                            }
                            if(p.containsKey("dy")){
                                t.dy = Integer.valueOf(p.getProperty("dy"));
                            } else {
                                System.out.println("Teleporte sem posição Destino Y\nMapa: "+mapa.nome);
                            }
                            mapa.adicionar(t);
                        }
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
