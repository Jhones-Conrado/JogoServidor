/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogador.interpretadores;

import entidades.Mensagem;
import jogador.Jogador;

/**
 *
 * @author jhones
 */
public class CriaMensagem extends InterpretadorPadrao{

    public CriaMensagem(Jogador jogador) {
        super(jogador, "msg:");
    }
    
    @Override
    void onInterpretar(String msg) {
        Mensagem m = new Mensagem(jogador, msg);
    }
    
}
