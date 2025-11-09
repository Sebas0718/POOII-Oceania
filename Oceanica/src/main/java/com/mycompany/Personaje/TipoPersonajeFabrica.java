/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Personaje;

import com.mycompany.TipoAtaques.FishTelepathy;
import com.mycompany.TipoAtaques.PoseidonTrident;
import com.mycompany.TipoAtaques.ReleaseTheKraken;
import com.mycompany.TipoAtaques.ThundersUnderTheSea;
import com.mycompany.TipoAtaques.UnderseaFire;
import com.mycompany.TipoAtaques.WavesControl;
import com.mycompany.oceanica.Modelos.ComandoCrearPersonaje;

/**
 *
 * @author xsusk
 */
public class TipoPersonajeFabrica {
    
    public static Personaje getTipoPersonaje (String tipo){
    
        tipo = tipo.toUpperCase();
        switch(tipo){
            case "THE_TRIDDENT":
                return new PoseidonTrident(TipoPersonaje.THE_TRIDDENT);
            case "UNDERSEA_FIRE":
                return new UnderseaFire(TipoPersonaje.UNDERSEA_FIRE);
            case "THUNDERS_UTS":
                return new ThundersUnderTheSea(TipoPersonaje.THUNDERS_UTS);
            case "FISH_TELEPATHY":
                return new FishTelepathy(TipoPersonaje.FISH_TELEPATHY);
            case "RELEASE_THE_KRAKEN":    
                return new ReleaseTheKraken(TipoPersonaje.RELEASE_THE_KRAKEN);
            case "WAVES_CONTROL":    
                return new WavesControl(TipoPersonaje.WAVES_CONTROL);
            default:
                break;
        }
        return null;
}
    
}
