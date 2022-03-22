/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import jogador.Jogador;
import mapas.CacheMapas;

/**
 *
 * @author jhones
 */
public class Mensagem extends Movel{
    
    public final String msg;
    public long duracao;
    public final Jogador jogador;
    public boolean lido;
    public final boolean toNpc;

    public Mensagem(Jogador jogador, String msg) {
        super();
        if(msg.length() < 301){
            this.msg = msg;
        } else {
            this.msg = msg.substring(0, 300);
        }
        this.jogador = jogador;
        geografico.transpassavel = true;
        geografico.width = 80;
        geografico.height = 80;
        geografico.x = jogador.geografico.x;
        if(jogador.geografico.y - 50 > 0){
            geografico.y = jogador.geografico.y - 50;
        } else {
            geografico.y = jogador.geografico.y;
        }
        geografico.mapa = jogador.geografico.mapa;
        this.duracao = (long) (System.nanoTime() + (1e9 * 5));
        lido = false;
        if(msg.startsWith("toNpc:")){
            toNpc = true;
        } else {
            toNpc = false;
        }
        CacheMapas.getMapa(geografico.mapa).adicionar(this);
    }
    
    @Override
    public String onUpdate(String msg) {
        if(System.nanoTime() > duracao){
            CacheMapas.getMapa(geografico.mapa).remove(this);
        }
        CacheMapas.getMapa(geografico.mapa).analisaImpacto(this);
        return null;
    }

    @Override
    public String getFuncao(Jogador jogador) {
        return null;
    }

    @Override
    public String getColisao(Ente ente) {
        return null;
    }

    @Override
    public String getString() {
        if(toNpc){
            return "";
        }
        return super.getString()+",msg,"+jogador.nome+": "+msg;
    }

    @Override
    public void setColisao(Ente ente) {
    }
    
}
