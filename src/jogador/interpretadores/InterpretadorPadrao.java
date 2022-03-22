/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogador.interpretadores;

import jogador.Jogador;

/**
 *
 * @author jhones
 */
public abstract class InterpretadorPadrao {
    
    public final Jogador jogador;
    public final String chave;

    public InterpretadorPadrao(Jogador jogador, String chave) {
        this.jogador = jogador;
        this.chave = chave;
    }
    
    public void interpretar(String msg){
        if(msg.startsWith(chave)){
            onInterpretar(msg.substring(chave.length()));
        }
    }
    
    abstract void onInterpretar(String msg);
    
}
