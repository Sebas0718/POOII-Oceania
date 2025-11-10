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
    
    public UnderseaFire(TipoPersonaje tipoPersonaje) {
        super(tipoPersonaje);
        this.ataques[0] = "Volcano_raising";
        this.ataques[1] = "Volcano_explosion";
        this.ataques[2] = "Termal_rush";
        
    }

    public void ataqueVolcanoRaising(InterfazPrincipal interfaz, ComandoAtaque comando){
    
    }
    
    public void ataqueVolcanoExplosion(InterfazPrincipal interfaz, ComandoAtaque comando){
        
    }
    
    public void ataqueTermalRush(InterfazPrincipal interfaz, ComandoAtaque comando){
        
    }
    
    public String[] getAtaques() {
        return ataques;
    }

    public void setAtaques(String[] ataques) {
        this.ataques = ataques;
    }
    
}
