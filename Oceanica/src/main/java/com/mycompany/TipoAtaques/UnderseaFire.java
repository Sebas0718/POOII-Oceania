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
public class UnderseaFire extends Ataque{
    
    private String[] ataques = new String[3];
    
    public UnderseaFire(TipoPersonaje tipoPersonaje) {
        this.ataques[0] = "VOLCANO_RAISING";
        this.ataques[1] = "VOLCANO_EXPLOSION";
        this.ataques[2] = "TERMAL_RUSH";
    }

    

    public String[] getAtaques() {
        return ataques;
    }

    public void setAtaques(String[] ataques) {
        this.ataques = ataques;
    }
    
}
