/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.TipoAtaques;

import java.util.Random;

import com.mycompany.Interfaz.Celda;
import com.mycompany.Interfaz.InterfazPrincipal;
import com.mycompany.Personaje.Personaje;
import com.mycompany.Personaje.TipoPersonaje;

/**
 *
 * @author seb
 */
public class PoseidonTrident extends Personaje {

    /* 
     * Three lines: selecciona 3 puntos en el mapa. En cada punto destruye lo que esté de 1 a 4
     * casillas a la derecha, izquierda, arriba, abajo (aleatorio)
    */
    private String[] ataques = new String[3];
    public PoseidonTrident(TipoPersonaje tipoPersonaje) {
        super(tipoPersonaje);
        this.ataques[0] = "Three_lines";
        this.ataques[1] = "Three_numbers";
        this.ataques[2] = "Control_the_kraken";
    }

    
}
