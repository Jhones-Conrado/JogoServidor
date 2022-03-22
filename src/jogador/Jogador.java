/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogador;

import controladores.Direcionais;
import controladores.Mira;
import entidades.Ente;
import entidades.Movel;
import java.util.ArrayList;
import java.util.List;
import jogador.interpretadores.*;

/**
 *
 * @author jhones
 */
public class Jogador extends Movel{
    
    public final int PARADO = 0;
    public final int ANDANDO = 1;
    public final int ATACANDO = 2;
    
    public final String nome;
    public final Direcionais direcionais;
    public final Mira mira;
    public int estado = 0;
    
    private final List<InterpretadorPadrao> inters;

    public Jogador(String nome) {
        super();
        this.inters = new ArrayList<>();
        carregaInterpretadores();
        this.nome = nome;
        this.direcionais = new Direcionais(this);
        this.mira = new Mira(this);
    }
    
    private void carregaInterpretadores(){
        inters.add(new ModoAtaque(this));
        inters.add(new RequisitaFuncao(this));
        inters.add(new CriaMensagem(this));
        inters.add(new AtiraBoomerang(this));
    }
    
    @Override
    public String onUpdate(String msg) {
        direcionais.update(msg);
        mira.update(msg);
        for(InterpretadorPadrao i : inters){
            i.interpretar(msg);
        }
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
        return super.getString()+","+nome+","+String.valueOf(estado);
    }

    @Override
    public void setColisao(Ente ente) {
    }
    
}
