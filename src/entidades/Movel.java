/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import controladores.Direcionais;

/**
 *
 * @author jhones
 */
public abstract class Movel extends Ente {
    
    public Direcionais direcionais;

    public Movel() {
        direcionais = new Direcionais(this);
    }
    
}
