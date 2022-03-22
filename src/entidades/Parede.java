/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import jogador.Jogador;

/**
 *
 * @author jhones
 */
public class Parede extends Fixo{

    public Parede(int x, int y, int w, int h) {
        super();
        geografico.x = x;
        geografico.y = y;
        geografico.width = w;
        geografico.height = h;
    }
    
    @Override
    public String onUpdate(String msg) {
        return null;
    }

    @Override
    public String getFuncao(Jogador jogador) {
        return null;
    }

    @Override
    public void setColisao(Ente ente) {
    }

    @Override
    public String getColisao(Ente ente) {
        return null;
    }
    
}
