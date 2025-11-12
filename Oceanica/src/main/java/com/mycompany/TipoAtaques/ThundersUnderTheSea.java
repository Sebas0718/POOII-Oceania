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
public class ThundersUnderTheSea extends Personaje {
    
    private String[] ataques = new String[3];
    
    public ThundersUnderTheSea() {
        super(TipoPersonaje.THUNDERS_UTS);
        this.ataques[0] = "THUNDER_RAIN";
        this.ataques[1] = "POSEIDON_THUNDERS";
        this.ataques[2] = "EEL_ATTACK";
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

    

    @Override
    public void realizarAtaque(ComandoAtaque comando, InterfazPrincipal interfaz) {
        String[] args = comando.getParametros();
        for (String ataque : this.ataques){
            if (ataque.equals(args[3].toUpperCase())){
                switch(ataque){
                    case "THUNDER_RAIN":
                        ataqueThunderRain(interfaz,comando);
                        return;
                    case "POSEIDON_THUNDERS":
                        ataquePoseidonThunders(interfaz, comando);
                        return;
                    case "EEL_ATTACK":
                        ataqueEelAtack(interfaz, comando);
                        return;
                }
            }
        }
    }
    
}
