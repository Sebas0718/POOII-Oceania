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
public class ThundersUnderTheSea extends Personaje{
    
    private String[] ataques = new String[3];
    
    public ThundersUnderTheSea(TipoPersonaje tipoPersonaje) {
        super(tipoPersonaje);
        this.ataques[0] = "Volcano_raising";
        this.ataques[1] = "Volcano_explosion";
        this.ataques[2] = "Termal_rush";
    }
    
}
