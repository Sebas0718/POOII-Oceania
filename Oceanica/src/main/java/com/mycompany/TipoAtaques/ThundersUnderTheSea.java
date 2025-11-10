/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.TipoAtaques;

import com.mycompany.Personaje.Personaje;
import com.mycompany.Personaje.TipoPersonaje;

/**
 *
 * @author seb
 */
public class ThundersUnderTheSea extends Ataque {
    
    private String[] ataques = new String[3];
    
    public ThundersUnderTheSea(TipoPersonaje tipoPersonaje) {
        this.ataques[0] = "THUNDER_RAIN";
        this.ataques[1] = "POSEIDON_THUNDERS";
        this.ataques[2] = "EEL_ATTACK";
    }

    public String[] getAtaques() {
        return ataques;
    }

    public void setAtaques(String[] ataques) {
        this.ataques = ataques;
    }
    
}
