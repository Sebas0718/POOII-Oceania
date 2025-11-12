/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.TipoAtaques;

import com.mycompany.Interfaz.Celda;
import com.mycompany.Interfaz.InterfazPrincipal;
import com.mycompany.Personaje.Personaje;
import com.mycompany.Personaje.TipoPersonaje;
import com.mycompany.oceanica.Modelos.ComandoAtaque;
import java.util.List;
import java.util.Random;

/**
 *
 * @author seb
 */



public class FishTelepathy extends Personaje {
    
    private String[] ataques = new String[3];
   
    private boolean requiereCoordenadas = false;

    public FishTelepathy() {
        super(TipoPersonaje.FISH_TELEPATHY);
        this.ataques[0] = "CARDUMEN";
        this.ataques[1] = "SHARK_ATTACK";
        this.ataques[2] = "PULP";
    }
    
    
    public void ataqueCardumen (InterfazPrincipal interfaz, ComandoAtaque comando){
        
    }
    
    public void ataqueSharkAttack(InterfazPrincipal interfaz, ComandoAtaque comando){
    
    }
    
    public void ataquePulp(InterfazPrincipal interfaz, ComandoAtaque comando){
    
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
                    case "CARDUMEN":
                        ataqueCardumen(interfaz,comando);
                        return;
                    case "SHARK_ATTACK":
                        ataqueSharkAttack(interfaz, comando);
                        return;
                    case "PULP":
                        ataquePulp(interfaz, comando);
                        return;
                }
            }
        }
    }

}
