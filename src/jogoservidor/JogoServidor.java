/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogoservidor;

import jogoservidor.conexao.Comunicador;
import java.io.IOException;
import java.io.StringReader;
import java.net.ServerSocket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapas.CacheMapas;
import jogoservidor.npcs.CacheNPCs;
import mapas.Mapa;

/**
 *
 * @author jhonesconrado
 */
public class JogoServidor {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        CacheNPCs.getInstance().carregar();
        CacheMapas.getInstance().carregar();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    for(Mapa m : CacheMapas.getInstance().mapas.values()){
                        m.update();
                        Thread.yield();
                    }
                }
            }
        }).start();
        
        ServerSocket servidor = new ServerSocket(15200);
        
        while(true){
            new Comunicador(servidor.accept());
        }

        
    }
    
}
