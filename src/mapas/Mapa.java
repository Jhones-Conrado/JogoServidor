/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas;

import entidades.Ente;
import jogador.Jogador;
import java.util.ArrayList;
import java.util.HashMap;
import controladores.Geografico;
import entidades.Fixo;
import entidades.Mensagem;
import entidades.Movel;
import jogoservidor.conexao.Conexoes;

/**
 *
 * @author jhonesconrado
 */
public class Mapa {
    
    private final String trava = "trava";
    
    public final int id;
    public final String nome;
    HashMap<String, Jogador> jogadores;
    final ArrayList<Ente> entesMoveis;
    final ArrayList<Ente> entesFixos;
    final ArrayList<Ente> toRemove;
    final ArrayList<Ente> toAdd;
    
    public final int x;
    public final int y;
    int ax, ay;
    
    public Mapa(int id, String nome, int x, int y) {
        jogadores = new HashMap<>();
        entesFixos = new ArrayList<>();
        entesMoveis = new ArrayList<>();
        this.toRemove = new ArrayList<>();
        this.toAdd = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.nome = nome;
        this.id = id;
    }
    
    public void update(){
        if(jogadores.size() > 0){
            synchronized(trava){
                toRemove.forEach(e -> {
                    delete(e);
                });
                toRemove.clear();
                
                toAdd.forEach(e -> {
                    add(e);
                });
                toAdd.clear();
                
                entesMoveis.forEach(e -> {
                    e.update("");
                });
                entesFixos.forEach(e -> {
                    e.update("");
                });
            }
            sendMsg();
            Thread.yield();
        }
    }
    
    private void sendMsg(){
        if(jogadores.size() > 0){
            synchronized(trava){
                StringBuilder s = new StringBuilder();
                entesMoveis.forEach(e -> {
                    s.append(e.getString()).append(";");
                });
                jogadores.values().forEach(e -> {
                    s.append(e.getString()).append(";");
                });
                jogadores.values().forEach(j -> {
                    Conexoes.get().conexoes.get(j).sendMsg("cr:"+s.toString());
                });
            }
        }
    }
    
    public void analisaImpacto(Ente ente){
        ente.geografico.limpaColisão();
        synchronized(trava){
            entesMoveis.forEach(e -> {
                analisa(ente, e);
            });
            jogadores.values().forEach(e -> {
                analisa(ente, e);
            });
            entesFixos.forEach(e -> {
                analisa(ente, e);
            });
        }
    }
    
    private void analisa(Ente um, Ente dois){
        if(um.geografico != dois.geografico){
            if(um.geografico.verificaColisão(dois.geografico.getRetangulo()) != Geografico.NULO){
                um.setColisao(dois);
            } else if(dois instanceof Mensagem && !(um instanceof Jogador)){
                if(um.geografico.verificaColisão(dois.geografico.getRetanguloNormal()) != Geografico.NULO){
                    um.setColisao(dois);
                }
            }
        }
    }
    
    public void adicionar(Ente ente){
        synchronized(trava){
            System.out.println("adicionou");
            if(jogadores.size() == 0){
                if(ente instanceof Jogador){
                    Jogador jogador = (Jogador) ente;
                    jogadores.put(jogador.nome, jogador);
                } else if(ente instanceof Movel){
                    entesMoveis.add(ente);
                } else if(ente instanceof Fixo){
                    entesFixos.add(ente);
                }
            } else {
                toAdd.add(ente);
            }
        }
    }
    
    private void add(Ente ente){
        synchronized(trava){
            if(ente instanceof Jogador){
                Jogador jogador = (Jogador) ente;
                jogadores.put(jogador.nome, jogador);
            } else if(ente instanceof Movel){
                entesMoveis.add(ente);
            } else if(ente instanceof Fixo){
                entesFixos.add(ente);
            }
        }
    }
    
    private void delete(Ente ente){
        synchronized(trava){
            if(ente instanceof Jogador){
                jogadores.remove(((Jogador) ente).nome);
            } else if(ente instanceof Movel){
                entesMoveis.remove(ente);
            } else if(ente instanceof Fixo){
                entesFixos.remove(ente);
            }
        }
    }
    
    public void remove(Ente ente){
        synchronized(trava){
            toRemove.add(ente);
        }
    }
    
    public int getCount(){
        return entesFixos.size()+entesMoveis.size()+jogadores.size();
    }
    
}
