/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Ente;
import entidades.Movel;
import java.util.HashSet;
import java.util.Set;
import jogador.Jogador;
import mapas.CacheMapas;

/**
 *
 * @author jhonesconrado
 */
public class Direcionais {
    public static final int CIMA = 1;
    public static final int DIREITA = 2;
    public static final int BAIXO = 3;
    public static final int ESQUERDA = 4;
    
    private final Movel movel;
    public int direção;
    int velocidade;
    private int mapaAtual, mw, mh;
    
    private long PreviosTick;
    private final long tempo;
    
    private final Set<String> comandos;

    public Direcionais(Movel movel) {
        this.movel = movel;
        this.mapaAtual = movel.geografico.mapa;
        try {
            this.mw = CacheMapas.getMapa(mapaAtual).x;
            this.mh = CacheMapas.getMapa(mapaAtual).y;
        } catch (Exception e) {
        }
        direção = BAIXO;
        velocidade = 5;
        PreviosTick = System.nanoTime();
        tempo = (long) (1e9/60);
        comandos = new HashSet<>();
    }
    
    public void update(String msg){
        boolean avança = true;
        if(movel instanceof Jogador){
            Jogador n = (Jogador) movel;
            if(n.estado == n.ATACANDO){
                avança = false;
            }
        }
        if(avança){
            if(msg.equals("a") || msg.equals("d") || msg.equals("s") || msg.equals("w")){
                comandos.add(msg);
                if(System.nanoTime() > PreviosTick+tempo){
                    PreviosTick = System.nanoTime();
                    CacheMapas.getMapa(movel.geografico.mapa).analisaImpacto(movel);
                    if(movel.geografico.mapa != mapaAtual){
                        mapaAtual = movel.geografico.mapa;
                        this.mw = CacheMapas.getMapa(movel.geografico.mapa).x-movel.geografico.width;
                        this.mh = CacheMapas.getMapa(movel.geografico.mapa).y-movel.geografico.height;
                    }
                    if(comandos.contains("a")){
                        direção = ESQUERDA;
                        if(movel.geografico.x - velocidade >= 0 && !movel.geografico.CONTATO_ESQUERDA){
                            movel.geografico.x -= velocidade;
                        }
                    }
                    if(comandos.contains("d")){
                        direção = DIREITA;
                        if(movel.geografico.x + velocidade + 30 <= mw && !movel.geografico.CONTATO_DIREITA){
                            movel.geografico.x += velocidade;
                        }
                    }
                    if(comandos.contains("w")){
                        direção = CIMA;
                        if(movel.geografico.y - velocidade >= 0 && !movel.geografico.CONTATO_CIMA){
                            movel.geografico.y -= velocidade;
                        }
                    }
                    if(comandos.contains("s")){
                        direção = BAIXO;
                        if(movel.geografico.y + velocidade + 30 <= mh && !movel.geografico.CONTATO_BAIXO){
                            movel.geografico.y += velocidade;
                        }
                    }
                    comandos.clear();
                }
            }
        }
    }
    
}
