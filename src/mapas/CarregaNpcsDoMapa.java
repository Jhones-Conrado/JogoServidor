/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapas;

import controladores.Geografico;
import jogoservidor.npcs.NPC;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import jogoservidor.npcs.CacheNPCs;

/**
 *
 * @author jhones
 */
public class CarregaNpcsDoMapa {
    
    public void carregarNpcs(Mapa mapa){
        File[] mapas = new File("db/mapas").listFiles();
        
        String path = null;
        
        for(File f : mapas){
            try {
                Properties p = new Properties();
                p.load(new FileInputStream(new File(f.getPath()+"/mapa")));
                if(p.containsKey("id")){
                    if(Integer.valueOf(p.getProperty("id")) == mapa.id){
                        path = f.getPath()+"/npcs";
                        break;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(CarregaNpcsDoMapa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(path != null){
            if(new File(path).exists()){
                File[] npcs = new File(path).listFiles();
                for(File f : npcs){
                    try {
                        Properties p = new Properties();
                        p.load(new FileInputStream(f));
                        if(p.containsKey("npc")){
                            NPC np = CacheNPCs.getInstance().getInstanciaNPC(p.getProperty("npc"));
                            if(np != null){
                                np.geografico = new Geografico(Integer.valueOf(p.getProperty("x"))
                                        , Integer.valueOf(p.getProperty("y")), 30, 30);
                                np.geografico.mapa = 
                                        CacheNPCs.getInstance().getInstanciaNPC(p.getProperty("npc")).geografico.mapa;
                                mapa.adicionar(np);
                            } else {
                                System.out.println("NPC não encontrado: "+p.getProperty("npc")+"\n"
                                        + "no mapa: "+p.getProperty("nome"));
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(CarregaNpcsDoMapa.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            System.out.println("Mapa: \""+mapa.nome+"\" não possui NPCs");
        }
    }
    
}
