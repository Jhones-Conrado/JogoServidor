/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import jogador.Jogador;
import jogoservidor.conexao.Conexoes;
import mapas.CacheMapas;

/**
 *
 * @author jhones
 */
public class Teleporte extends Fixo{
    
    public int destino, dx, dy;

    public Teleporte(int destino, int dx, int dy) {
        this.destino = destino;
        this.dx = dx;
        this.dy = dy;
        geografico.width = 5;
        geografico.height = 5;
    }
    
    @Override
    public String onUpdate(String msg) {
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
    public void setColisao(Ente ente) {
        if(!(ente instanceof Fixo)){
            if(ente instanceof Jogador){
                Conexoes.get().conexoes.get((Jogador) ente).sendMsg("mp:"+String.valueOf(destino));
            }
            CacheMapas.getMapa(ente.geografico.mapa).remove(ente);
            ente.geografico.x = dx;
            ente.geografico.y = dy;
            ente.geografico.mapa = destino;
            CacheMapas.getMapa(destino).adicionar(ente);
        }
    }
    
    
    
}
