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
                return new PoseidonTrident();
            case "UNDERSEA_FIRE":
                return new UnderseaFire();
            case "THUNDERS_UTS":
                return new ThundersUnderTheSea();
            case "FISH_TELEPATHY":
                return new FishTelepathy();
            case "RELEASE_THE_KRAKEN":    
                return new ReleaseTheKraken();
            case "WAVES_CONTROL":    
                return new WavesControl();
            default:
                break;
        }
        return null;
    }
    
}
