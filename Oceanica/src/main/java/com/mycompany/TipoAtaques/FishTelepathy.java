/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.TipoAtaques;

import com.mycompany.Interfaz.Celda;
import com.mycompany.Interfaz.InterfazPrincipal;
import com.mycompany.Personaje.Personaje;
import com.mycompany.Personaje.TipoPersonaje;
import java.util.List;
import java.util.Random;

/**
 *
 * @author seb
 */



public class FishTelepathy extends Ataque {
    
    private String[] ataques = new String[3];
   
    private boolean requiereCoordenadas = false;

    public FishTelepathy(TipoPersonaje tipoPersonaje) {
        this.ataques[0] = "CARDUMEN";
        this.ataques[1] = "SHARK_ATTACK";
        this.ataques[2] = "PULP";
    }

    public String[] getAtaques() {
        return ataques;
    }

    public void setAtaques(String[] ataques) {
        this.ataques = ataques;
    }
    
    
    

}
