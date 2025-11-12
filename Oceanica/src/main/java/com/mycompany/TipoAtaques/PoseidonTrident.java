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
import com.mycompany.oceanica.Modelos.ComandoAtaque;

/**
 *
 * @author seb
 */
public class PoseidonTrident extends Personaje{

    /* 
     * Three lines: selecciona 3 puntos en el mapa. En cada punto destruye lo que esté de 1 a 4
     * casillas a la derecha, izquierda, arriba, abajo (aleatorio)
    */
    
    private String[] ataques = new String[3];
    public PoseidonTrident() {
        super(TipoPersonaje.THE_TRIDDENT);
        this.ataques[0] = "THREE_LINES";
        this.ataques[1] = "THREE_NUMBERS";
        this.ataques[2] = "CONTROL_THE_KRAKEN";
    }
    
    
    public void ataqueThreeLines(InterfazPrincipal interfaz, ComandoAtaque comando){
        
    }
    
    public void ataqueThreeNumbers(InterfazPrincipal interfaz, ComandoAtaque comando){
    
    }
    
    public void ataqueControlTheKraken(InterfazPrincipal interfaz, ComandoAtaque comando){
    
    }
    
    public String[] getAtaques() {
        return ataques;
    }

    public void setAtaques(String[] ataques) {
        this.ataques = ataques;
    }

    @Override
    public void realizarAtaque(ComandoAtaque comando, InterfazPrincipal interfaz) {
        
        String[] args = comando.getParametros();
        for (String ataque : this.ataques){
            if (ataque.equals(args[3].toUpperCase())){
                switch(ataque){
                    case "THREE_LINES":
                        ataqueThreeLines(interfaz,comando);
                        return;
                    case "THREE_NUMBERS":
                        ataqueThreeNumbers(interfaz, comando);
                        return;
                    case "CONTROL_THE_KRAKEN":
                        ataqueControlTheKraken(interfaz, comando);
                        return;
                }
            }
        }
    }
}
