/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogador;

import controladores.Direcionais;
import entidades.Ente;
import entidades.Movel;
import java.awt.Rectangle;
import jogoservidor.conexao.Conexoes;
import jogoservidor.npcs.NPC;
import mapas.CacheMapas;

/**
 *
 * @author jhones
 */
public class AlvoFuncao extends Movel{
    
    private boolean rodou;
    private Jogador jogador;

    public AlvoFuncao(Jogador jogador) {
        this.jogador = jogador;
        rodou = false;
        geografico.mapa = jogador.geografico.mapa;
        Rectangle r = jogador.geografico.getRetangulo();
        switch (jogador.direcionais.direção) {
            case Direcionais.BAIXO -> r.y += 30;
            case Direcionais.CIMA -> r.y -= 30;
            case Direcionais.DIREITA -> r.x += 30;
            case Direcionais.ESQUERDA -> r.x -= 30;
            default -> {
            }
        }
        geografico.x = r.x;
        geografico.y = r.y;
        CacheMapas.getMapa(jogador.geografico.mapa).adicionar(this);
    }

    @Override
    public String onUpdate(String msg) {
        if(!rodou){
            rodou = true;
            CacheMapas.getMapa(geografico.mapa).analisaImpacto(this);
            CacheMapas.getMapa(geografico.mapa).remove(this);
        }
        return null;
    }

    @Override
    public String getFuncao(Jogador jogador) {
        return null;
    }

    @Override
    public void setColisao(Ente ente) {
        if(ente instanceof NPC){
            Conexoes.get().conexoes.get(jogador).sendMsg(ente.getFuncao(jogador));
        }
    }

    @Override
    public String getColisao(Ente ente) {
        return null;
    }

    @Override
    public String getString() {
        return "";
    }
    
}
