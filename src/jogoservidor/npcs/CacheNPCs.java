/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogoservidor.npcs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhones
 */
public class CacheNPCs {
    
    private final HashMap<String, NPC> NPCs;

    private CacheNPCs() {
        this.NPCs = new HashMap<>();
    }
    
    public void carregar() throws FileNotFoundException, IOException{
        NPCs.clear();
        File pasta = new File("db/criaturas/npcs");
        for(File p : pasta.listFiles()){
            Properties a = new Properties();
            a.load(new FileInputStream(p));
            if(a.containsKey("nome") && a.containsKey("msg")){
                addNpc(new NPC(a.getProperty("nome"), a.getProperty("msg")));
            }
        }
    }
    
    public void addNpc(NPC e){
        synchronized(NPCs){
            NPCs.put(e.nome, e);
        }
    }
    
    public void removeNpc(NPC e){
        synchronized(NPCs){
            if(NPCs.containsKey(e.nome)){
                NPCs.remove(e);
            }
        }
    }
    
    public NPC getInstanciaNPC(String nome){
        try {
            if(NPCs.containsKey(nome)){
                return (NPC) NPCs.get(nome).clone();
            }
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(CacheNPCs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static CacheNPCs getInstance() {
        return NewNPCsHolder.INSTANCE;
    }
    
    private static class NewNPCsHolder {
        private static final CacheNPCs INSTANCE = new CacheNPCs();
    }
}
