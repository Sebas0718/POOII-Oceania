/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Personaje;

import com.mycompany.oceanica.Modelos.ComandoCrearPersonaje;

/**
 *
 * @author xsusk
 */
public class TipoPersonajeFabrica {
    
    public static void getTipoPersonaje (String tipo,Personaje personaje){
    
        
        switch(tipo){
            case "THE_TRIDDENT":
                personaje.setTipoPersonaje(TipoPersonaje.THE_TRIDDENT);
                break;
            case "VOLCANO":
                personaje.setTipoPersonaje(TipoPersonaje.VOLCANO);
                break;
            case "THUNDERS_UTS":
                personaje.setTipoPersonaje(TipoPersonaje.THUNDERS_UTS);
                break;
            case "FISH_TELEPATHY":
                personaje.setTipoPersonaje(TipoPersonaje.FISH_TELEPATHY);
                break;
            case "RELEASE_THE_KRAKEN":    
                personaje.setTipoPersonaje(TipoPersonaje.RELEASE_THE_KRAKEN);
                break;
            case "WAVES_CONTROL":    
                personaje.setTipoPersonaje(TipoPersonaje.WAVES_CONTROL);
            default:
                break;
        }
}
    
}
