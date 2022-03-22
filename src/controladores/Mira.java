/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import jogador.Jogador;
import java.awt.Point;
import java.util.HashSet;
import jogoservidor.conexao.Conexoes;

/**
 *
 * @author jhones
 */
public class Mira {

    private final Jogador jogador;
    private int angulo;
    private long previous;
    private final long pausa;
    private final HashSet<String> teclas;
    private final int angMax;
    private final int angMin;

    public Mira(Jogador j) {
        this.jogador = j;
        this.angulo = 0;
        this.previous = System.nanoTime();
        this.pausa = (long) (1e9 / 90);
        this.teclas = new HashSet<>();
        this.angMax = 45;
        this.angMin = -45;
    }
    
    public void update(String msg){
        if(jogador.estado == jogador.ATACANDO){
            if(msg.equals("w") || msg.equals("s") || msg.equals("a") || msg.equals("d")){
                teclas.add(msg);
            }
            if(System.nanoTime() > previous){
                previous = System.nanoTime() + pausa;
                switch (jogador.direcionais.direção) {
                    case Direcionais.ESQUERDA -> {
                        if(teclas.contains("w")){
                            if(angulo < angMax){
                                angulo += 5;
                            }
                        } else if(teclas.contains("s")){
                            if(angulo > angMin){
                                angulo -= 5;
                            }
                        }
                    }
                    case Direcionais.DIREITA -> {
                        if(teclas.contains("s")){
                            if(angulo < angMax){
                                angulo += 5;
                            }
                        } else if(teclas.contains("w")){
                            if(angulo > angMin){
                                angulo -= 5;
                            }
                        }
                    }
                    case Direcionais.CIMA -> {
                        if(teclas.contains("d")){
                            if(angulo < angMax){
                                angulo += 5;
                            }
                        } else if(teclas.contains("a")){
                            if(angulo > angMin){
                                angulo -= 5;
                            }
                        }
                    }
                    case Direcionais.BAIXO -> {
                        if(teclas.contains("a")){
                            if(angulo < angMax){
                                angulo += 5;
                            }
                        } else if(teclas.contains("d")){
                            if(angulo > angMin){
                                angulo -= 5;
                            }
                        }
                    }
                    default -> {
                    }
                }
                teclas.clear();
            }
            sendMsg();
        }
    }
    
    public Point getPonto(){
        int ang = 0;
        switch (jogador.direcionais.direção) {
            case Direcionais.CIMA -> ang = 270;
            case Direcionais.ESQUERDA -> ang = 180;
            case Direcionais.BAIXO -> ang = 90;
            default -> {
            }
        }
        Point p = new Point((int) ((Math.cos(Math.toRadians(ang+angulo)) * 50) + jogador.geografico.x), 
                (int) ((Math.sin(Math.toRadians(ang+angulo)) * 50) + jogador.geografico.y));
        return p;
    }
    
    public void sendMsg(){
        Point p = getPonto();
        Conexoes.get().conexoes.get(jogador).sendMsg("mr:"+String.valueOf(p.x-jogador.geografico.x)
                +","+String.valueOf(p.y-jogador.geografico.y));
    }
    
}
