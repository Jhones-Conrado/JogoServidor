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
public class ModoAtaque extends InterpretadorPadrao{

    public ModoAtaque(Jogador jogador) {
        super(jogador, "matk");
    }
    
    @Override
    void onInterpretar(String msg) {
        if(jogador.estado == jogador.ATACANDO){
            jogador.estado = jogador.PARADO;
        } else {
            jogador.estado = jogador.ATACANDO;
            jogador.mira.sendMsg();
        }
    }
    
}
