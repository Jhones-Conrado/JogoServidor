/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapas;

import java.io.File;
import java.util.HashMap;

/**
 *
 * @author jhones
 */
public class CacheMapas {
    
    public final HashMap<Integer, Mapa> mapas;
    
    private final CarregaMapa carregaMapa;
    private final CarregaNpcsDoMapa carregaNPCs;
    private final CarregaTeleportes carregaTeleportes;
    private final CarregaParedes carregaParedes;

    private CacheMapas() {
        this.mapas = new HashMap<>();
        this.carregaMapa = new CarregaMapa();
        this.carregaNPCs = new CarregaNpcsDoMapa();
        this.carregaTeleportes = new CarregaTeleportes();
        this.carregaParedes = new CarregaParedes();
    }
    
    public static Mapa getMapa(int mapa){
        if(getInstance().mapas.containsKey(mapa)){
            return getInstance().mapas.get(mapa);
        }
        return null;
    }
    
    public static void carregar(){
        getInstance().load();
    }
    
    private void load(){
        File pasta = new File("db/mapas");
        boolean load = false;
        for(File a : pasta.listFiles()){
            load = false;
            Mapa mapa = carregaMapa.carregar(a);
            if(mapa != null){
                carregaNPCs.carregarNpcs(mapa);
                carregaTeleportes.carregarTeleportes(mapa);
                carregaParedes.carregarParedes(mapa);
                if(mapas.containsKey(mapa.id)){
                    if(mapas.get(mapa.id).jogadores.size() > 0){
                        System.out.println("Há jogadores no mapa "+mapa.id+".\n"
                                + "Por isso não poderá ser carregado ou atualizado.");
                    } else{
                        mapas.put(mapa.id, mapa);
                        load = true;
                    }
                } else {
                    mapas.put(mapa.id, mapa);
                    load = true;
                }
            }
            if(load){
                System.out.println("Mapa: \""+mapa.nome+"\" carregado com sucesso."
                    + "\nEntidades: "+String.valueOf(mapa.getCount()));
            }
        }
    }
    
    public static CacheMapas getInstance(){
        return MapaHolder.instance;
    }
    
    private static class MapaHolder {
        public static CacheMapas instance = new CacheMapas();
    }
    
}
