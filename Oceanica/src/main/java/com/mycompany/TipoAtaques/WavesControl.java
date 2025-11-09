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
    }
    
}
