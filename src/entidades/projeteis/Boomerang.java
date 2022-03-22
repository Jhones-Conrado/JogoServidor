/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades.projeteis;

import controladores.Direcionais;
import entidades.Movel;

/**
 *
 * @author jhones
 */
public class Boomerang extends Projetil{
    
    int increment;
    int raio;
    int angulo;
    
    int xi, yi;
    
    public Boomerang(Movel atirador) {
        super(atirador);
        increment = 0;
        raio = 50;
        if(atirador.direcionais.direção == Direcionais.ESQUERDA){
            angulo = 0;
            xi = atirador.geografico.x - raio;
            yi = atirador.geografico.y;
        } else if(atirador.direcionais.direção == Direcionais.CIMA){
            angulo = 270;
            xi = atirador.geografico.x;
            yi = atirador.geografico.y - raio;
        } else if(atirador.direcionais.direção == Direcionais.DIREITA){
            angulo = 180;
            xi = atirador.geografico.x + raio;
            yi = atirador.geografico.y;
        } else if(atirador.direcionais.direção == Direcionais.BAIXO){
            angulo = 90;
            xi = atirador.geografico.x;
            yi = atirador.geografico.y + raio;
        }
        geografico.x = xi + ((int) (Math.cos(Math.toRadians(angulo+increment)) * raio));
        geografico.y = yi + ((int) (Math.sin(Math.toRadians(angulo+increment)) * raio));
    }
    
    @Override
    public String onUpdate(String msg) {
        increment += 5;
        geografico.x = xi + ((int) (Math.cos(Math.toRadians(angulo+increment)) * raio));
        geografico.y = yi + ((int) (Math.sin(Math.toRadians(angulo+increment)) * raio));
        if(increment > 360){
            selfDestruct();
        }
        return null;
    }

    @Override
    public String getString() {
        return super.getString()+",bm";
    }
    
}
