/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogoservidor.npcs;

/**
 *
 * @author jhones
 */
public abstract class Comando {
    
    final String chave;

    public Comando(String chave) {
        this.chave = chave;
    }
    
    public String analisa(String msg){
        if(msg.startsWith(chave)){
            return onAnalisa(msg.substring(chave.length()));
        } return null;
    }
    
    abstract String onAnalisa(String msg);
    
}
