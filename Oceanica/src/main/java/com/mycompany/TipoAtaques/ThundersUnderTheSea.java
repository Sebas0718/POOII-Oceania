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
public class ThundersUnderTheSea extends Ataque {
    
    private String[] ataques = new String[3];
    
    public ThundersUnderTheSea(TipoPersonaje tipoPersonaje) {
        super(tipoPersonaje);
        this.ataques[0] = "Thunder_rain";
        this.ataques[1] = "Poseidon_thunders";
        this.ataques[2] = "Eel_Atack";
    }
    
    public void ataqueThunderRain(InterfazPrincipal interfaz, ComandoAtaque comando){
    
    }
    
    public void ataquePoseidonThunders(InterfazPrincipal interfaz, ComandoAtaque comando){
    
    }
    
    public void ataqueEelAtack(InterfazPrincipal interfaz, ComandoAtaque comando){
    
    }
    
    public String[] getAtaques() {
        return ataques;
    }

    public void setAtaques(String[] ataques) {
        this.ataques = ataques;
    }
    
}
