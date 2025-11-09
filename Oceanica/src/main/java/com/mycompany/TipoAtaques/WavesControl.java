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
public class WavesControl extends Personaje{
    
    private String[] ataques = new String[3];
    
    public WavesControl(TipoPersonaje tipoPersonaje) {
        super(tipoPersonaje);
        this.ataques[0] = "Swirl_raising";
        this.ataques[1] = "Send_human_garbage";
        this.ataques[2] = "Radioactive_rush";
    }

    public String[] getAtaques() {
        return ataques;
    }

    public void setAtaques(String[] ataques) {
        this.ataques = ataques;
    }
    
}
