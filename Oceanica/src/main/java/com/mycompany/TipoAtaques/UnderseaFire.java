/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.TipoAtaques;

import com.mycompany.Interfaz.InterfazPrincipal;
import com.mycompany.Personaje.Personaje;
import com.mycompany.Personaje.TipoPersonaje;
import com.mycompany.oceanica.Modelos.ComandoAtaque;

/**
 *
 * @author seb
 */
public class UnderseaFire extends Personaje{
    
    private String[] ataques = new String[3];
    
    public UnderseaFire() {
        super(TipoPersonaje.UNDERSEA_FIRE);
        this.ataques[0] = "VOLCANO_RAISING";
        this.ataques[1] = "VOLCANO_EXPLOSION";
        this.ataques[2] = "TERMAL_RUSH";
        
    }

    public void ataqueVolcanoRaising(InterfazPrincipal interfaz, ComandoAtaque comando){
    
    }
    
    public void ataqueVolcanoExplosion(InterfazPrincipal interfaz, ComandoAtaque comando){
        
    }
    
    public void ataqueTermalRush(InterfazPrincipal interfaz, ComandoAtaque comando){
        
    }
    

    @Override
    public void realizarAtaque(ComandoAtaque comando, InterfazPrincipal interfaz) {
        String[] args = comando.getParametros();
        for (String ataque : this.ataques){
            if (ataque.equals(args[3].toUpperCase())){
                switch(ataque){
                    case "VOLCANO_RAISING":
                        ataqueVolcanoRaising(interfaz,comando);
                        return;
                    case "VOLCANO_EXPLOSION":
                        ataqueVolcanoExplosion(interfaz, comando);
                        return;
                    case "TERMAL_RUSH":
                        ataqueTermalRush(interfaz, comando);
                        return;
                }
            }
        }
    }
    
}
