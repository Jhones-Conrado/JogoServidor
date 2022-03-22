/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package memoria;

import java.util.HashMap;

/**
 *
 * @author jhones
 */
public class Chaves {
    
    private HashMap<Integer, Integer> chaves;
    
    private Chaves() {
        chaves = new HashMap<>();
    }
    
    public static Chaves getInstance() {
        return ChavesHolder.INSTANCE;
    }
    
    private static class ChavesHolder {

        private static final Chaves INSTANCE = new Chaves();
    }
}
