/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogoservidor.npcs;

import entidades.Mensagem;
import jogoservidor.conexao.Conexoes;

/**
 *
 * @author jhones
 */
public class Resposta {
    
    private final String chave;
    private final String msgRetorno;

    public Resposta(String chave, String msgRetorno) {
        this.chave = chave;
        this.msgRetorno = msgRetorno;
    }
    
    public void analisa(Mensagem msg){
        if(msg.msg.equals(chave) && !msg.lido){
            msg.lido = true;
            Conexoes.get().conexoes.get(msg.jogador).sendMsg("npcmsg:"+msgRetorno);
        }
    }
    
}
