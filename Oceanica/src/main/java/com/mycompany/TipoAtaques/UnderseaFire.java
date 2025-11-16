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
        interfaz.borrarMensajes();
        interfaz.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");
        comando.getUsuario().getInterfazPrincipal().borrarMensajes();
        comando.getUsuario().getInterfazPrincipal().writeResultadoAtaque("EL RESULTADO DEL ATAQUE FUE:");
    }
    
    public void ataqueVolcanoExplosion(InterfazPrincipal interfaz, ComandoAtaque comando){
        interfaz.borrarMensajes();
        interfaz.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");
        comando.getUsuario().getInterfazPrincipal().borrarMensajes();
        comando.getUsuario().getInterfazPrincipal().writeResultadoAtaque("EL RESULTADO DEL ATAQUE FUE:");
    }
    
    public void ataqueTermalRush(InterfazPrincipal interfaz, ComandoAtaque comando){
        interfaz.borrarMensajes();
        interfaz.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");
        comando.getUsuario().getInterfazPrincipal().borrarMensajes();
        comando.getUsuario().getInterfazPrincipal().writeResultadoAtaque("EL RESULTADO DEL ATAQUE FUE:");
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
