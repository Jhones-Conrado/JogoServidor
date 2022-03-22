/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogoservidor.conexao;

import jogador.Jogador;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import mapas.CacheMapas;

/**
 *
 * @author jhonesconrado
 */
public class Comunicador {
    
    public Jogador jogador;
    
    private final PrintWriter escritor;
    private final Scanner leitor;
    

    public Comunicador(Socket socket) throws IOException {
        escritor = new PrintWriter(socket.getOutputStream());
        leitor = new Scanner(socket.getInputStream());
        jogador = new Jogador("Jogador"+this.toString().substring(this.toString().indexOf("@")));
        Conexoes.get().conexoes.put(jogador, this);
        sendMsg(jogador.nome);
        CacheMapas.getMapa(jogador.geografico.mapa).adicionar(jogador);
        sendMsg("mp:"+String.valueOf(jogador.geografico.mapa));
        System.out.println("Nova conexão: "+socket.getInetAddress());
        new Thread(new Ouvidor()).start();
    }
    
    public void sendMsg(String msg){
        escritor.println(msg);
        escritor.flush();
    }
    
    private class Ouvidor implements Runnable{

        @Override
        public void run() {
            while(leitor.hasNextLine()){
                String t = leitor.nextLine();
                jogador.onUpdate(t);
                Thread.yield();
            }
            CacheMapas.getMapa(jogador.geografico.mapa).remove(jogador);
            Conexoes.get().conexoes.remove(jogador);
        }
        
    }
    
}
