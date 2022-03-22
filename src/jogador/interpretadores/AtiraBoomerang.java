/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogador.interpretadores;

import entidades.projeteis.Boomerang;
import jogador.Jogador;

/**
 *
 * @author jhones
 */
public class AtiraBoomerang extends InterpretadorPadrao {

    public AtiraBoomerang(Jogador jogador) {
        super(jogador, "k:");
    }

    @Override
    public void interpretar(String msg) {
        if(msg.equals("k")){
            onInterpretar(msg);
        }
    }
    
    @Override
    void onInterpretar(String msg) {
        System.out.println("criou boom");
        Boomerang b = new Boomerang(jogador);
    }
    
}
