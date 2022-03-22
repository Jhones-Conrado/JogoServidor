/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.Rectangle;

/**
 *
 * @author jhones
 */
public class Geografico {
    public static final int NULO = 0;
    public static final int CIMA = 1;
    public static final int DIREITA = 2;
    public static final int BAIXO = 3;
    public static final int ESQUERDA = 4;
    
    public int x, y, width, height, mapa;
    public boolean transpassavel;
    
    public boolean CONTATO_CIMA;
    public boolean CONTATO_DIREITA;
    public boolean CONTATO_BAIXO;
    public boolean CONTATO_ESQUERDA;

    public Geografico(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.mapa = 0;
        this.transpassavel = false;
        
        CONTATO_CIMA = false;
        CONTATO_DIREITA = false;
        CONTATO_BAIXO = false;
        CONTATO_ESQUERDA = false;
    }
    
    public void limpaColisão(){
        CONTATO_CIMA = false;
        CONTATO_DIREITA = false;
        CONTATO_BAIXO = false;
        CONTATO_ESQUERDA = false;
    }
    
    public int verificaColisão(Rectangle r){
        if(getRetangulo().intersects(r)){
            Rectangle colisao = getRetangulo().intersection(r);
            if(colisao.width > colisao.height){
                if(colisao.getCenterY() < r.getCenterY()){
                    CONTATO_BAIXO = true;
                    return BAIXO;
                } else {
                    CONTATO_CIMA = true;
                    return CIMA;
                }
            } else {
                if(colisao.getCenterX() < r.getCenterX()){
                    CONTATO_DIREITA = true;
                    return DIREITA;
                } else {
                    CONTATO_ESQUERDA = true;
                    return ESQUERDA;
                }
            }
        }
        return NULO;
    }
    
    public boolean temColisao(){
        return CONTATO_BAIXO || CONTATO_CIMA || CONTATO_DIREITA || CONTATO_ESQUERDA;
    }
    
    public Rectangle getRetangulo(){
        if(!transpassavel){
            return new Rectangle(x, y, width, height);
        }
        return new Rectangle(0, 0, 0, 0);
    }
    
    public Rectangle getRetanguloNormal(){
        return new Rectangle(x, y, width, height);
    }
}
