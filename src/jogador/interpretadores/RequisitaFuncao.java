/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogador.interpretadores;

import jogador.AlvoFuncao;
import jogador.Jogador;

/**
 *
 * @author jhones
 */
public class RequisitaFuncao extends InterpretadorPadrao{

    public RequisitaFuncao(Jogador jogador) {
        super(jogador, "f");
    }
    
    @Override
    void onInterpretar(String msg) {
        if(jogador.estado != jogador.ATACANDO){
            new AlvoFuncao(jogador);
        }
    }
    
}
