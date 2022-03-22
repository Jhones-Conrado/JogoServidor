/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades.projeteis;

import entidades.Ente;
import entidades.Movel;
import entidades.Parede;
import jogador.Jogador;

/**
 *
 * @author jhones
 */
public abstract class Projetil extends Movel{
    
    final Movel atirador;

    public Projetil(Movel atirador) {
        this.atirador = atirador;
    }

    @Override
    public String getFuncao(Jogador jogador) {
        return null;
    }

    @Override
    public void setColisao(Ente ente) {
        if(ente instanceof Parede){
            selfDestruct();
        }
    }

    @Override
    public String getColisao(Ente ente) {
        return null;
    }
    
}
