/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package jogoservidor.conexao;

import jogador.Jogador;
import java.util.HashMap;

/**
 *
 * @author jhones
 */
public class Conexoes {
    
    public HashMap<Jogador, Comunicador> conexoes;
    
    private Conexoes() {
        this.conexoes = new HashMap<>();
    }
    
    public static Conexoes get() {
        return ConexoesHolder.INSTANCE;
    }
    
    private static class ConexoesHolder {

        private static final Conexoes INSTANCE = new Conexoes();
    }
}
