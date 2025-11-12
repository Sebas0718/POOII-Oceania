/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.TipoAtaques;

import com.mycompany.Interfaz.InterfazPrincipal;
import com.mycompany.Personaje.Personaje;
import com.mycompany.Personaje.TipoPersonaje;
import com.mycompany.oceanica.Modelos.ComandoAtaque;

/**
 *
 * @author seb
 */
public class WavesControl extends Personaje {
    
    private String[] ataques = new String[3];
    
    public WavesControl() {
        super(TipoPersonaje.RELEASE_THE_KRAKEN);
        this.ataques[0] = "SWIRL_RAISING";
        this.ataques[1] = "SEND_HUMAN_GARBAGE";
        this.ataques[2] = "RADIOACTIVE_RUSH";
    }
    
    public void ataqueSwirlRaising(InterfazPrincipal interfaz, ComandoAtaque comando){
        
    }

    public void ataqueSendHumanGarbage(InterfazPrincipal interfaz, ComandoAtaque comando){
        
    }
    
    public void ataqueRadioactiveRush(InterfazPrincipal interfaz, ComandoAtaque comando){
    
    }
    
    public String[] getAtaques() {
        return ataques;
    }

    

    @Override
    public void realizarAtaque(ComandoAtaque comando, InterfazPrincipal interfaz) {
        String[] args = comando.getParametros();
        for (String ataque : this.ataques){
            if (ataque.equals(args[3].toUpperCase())){
                switch(ataque){
                    case "SWIRL_RAISING":
                        ataqueSwirlRaising(interfaz,comando);
                        return;
                    case "SEND_HUMAN_GARBAGE":
                        ataqueSendHumanGarbage(interfaz, comando);
                        return;
                    case "RADIOACTIVE_RUSH":
                        ataqueRadioactiveRush(interfaz, comando);
                        return;
                }
            }
        }
    }


    
}
