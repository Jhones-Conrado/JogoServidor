/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import jogador.Jogador;
import java.io.Serializable;
import controladores.Geografico;
import mapas.CacheMapas;

/**
 *
 * @author jhones
 */
public abstract class Ente implements Cloneable, Serializable{
    public Geografico geografico;

    public Ente() {
        this.geografico = new Geografico(0, 0, 30, 30);
    }
    
    public Ente(Geografico geografico){
        this.geografico = geografico;
    }
    
    public String update(String msg){
        if(msg != null){
            return onUpdate(msg);
        } return null;
    }
    
    public void selfDestruct(){
        CacheMapas.getMapa(geografico.mapa).remove(this);
    }
    
    public abstract String onUpdate(String msg);
    public abstract String getFuncao(Jogador jogador);
    public abstract void setColisao(Ente ente);
    public abstract String getColisao(Ente ente);
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getString(){
        return String.valueOf(geografico.x)+","+String.valueOf(geografico.y);
    }
    
}
