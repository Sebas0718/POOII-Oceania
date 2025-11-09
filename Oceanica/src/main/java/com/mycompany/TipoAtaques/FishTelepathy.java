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



public class FishTelepathy extends Personaje{
    
    private String[] ataques = new String[3];
   

    public FishTelepathy(TipoPersonaje tipoPersonaje) {
        super(tipoPersonaje);
        this.ataques[0] = "Cardumen";
        this.ataques[1] = "Shark_attack";
        this.ataques[2] = "Pulp";
    }
    
    
    

}
