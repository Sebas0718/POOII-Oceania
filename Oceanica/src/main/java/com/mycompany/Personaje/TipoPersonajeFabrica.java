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
    
    public static TipoPersonaje getTipoPersonaje (String tipo){
    
        tipo = tipo.toUpperCase();
        switch(tipo){
            case "THE_TRIDDENT":
                return TipoPersonaje.THE_TRIDDENT;
            case "UNDERSEA_FIRE":
                return TipoPersonaje.UNDERSEA_FIRE;
            case "THUNDERS_UTS":
                return TipoPersonaje.THUNDERS_UTS;
            case "FISH_TELEPATHY":
                return TipoPersonaje.FISH_TELEPATHY;
            case "RELEASE_THE_KRAKEN":    
                return TipoPersonaje.RELEASE_THE_KRAKEN;
            case "WAVES_CONTROL":    
                return TipoPersonaje.WAVES_CONTROL;
            default:
                break;
        }
        return null;
}
    
}
