/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogoservidor.npcs;

import entidades.Ente;
import entidades.Mensagem;
import entidades.Movel;
import jogador.Jogador;
import java.util.ArrayList;
import mapas.CacheMapas;

/**
 *
 * @author jhones
 */
public class NPC extends Movel{
    
    String msg;
    public String nome;
    public final ArrayList<Comando> comandos;
    public final ArrayList<Resposta> respostas;

    public NPC(String nome, String msg) {
        this.nome = nome;
        this.msg = msg;
        this.comandos = new ArrayList<>();
        this.respostas = new ArrayList<>();
        
        respostas.add(new Resposta("segredo", "Voce descobriu o meu segredo!"));
    }
    
    @Override
    public String onUpdate(String msg) {
        for(Comando c : comandos){
            String a = c.analisa(msg);
            if(a != null){
                return a;
            }
        }
        CacheMapas.getMapa(geografico.mapa).analisaImpacto(this);
        return null;
    }

    @Override
    public String getFuncao(Jogador jogador) {
        return msg;
    }

    @Override
    public String getColisao(Ente ente) {
        return null;
    }

    @Override
    public String getString() {
        return super.getString()+","+nome;
    }

    @Override
    public void setColisao(Ente ente) {
        if(ente instanceof Mensagem){
            for(Resposta r : respostas){
                r.analisa((Mensagem) ente);
            }
        }
    }
    
}
