/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Modelos;

import com.mycompany.Interfaz.InterfazPrincipal;
import com.mycompany.Personaje.Personaje;

/**
 *
 * @author xsusk
 */
public class ComandoCrearPersonajeValidaciones {
    public static boolean validarPorcentajeMapa(Personaje personaje, ComandoCrearPersonaje comando, InterfazPrincipal interfaz){
        if (personaje.getPorcentajeMapa() > 98){
                ComandoCrearPersonajeErrores.error("!!!ERROR!!!  El maximo a ocupar de un personaje es 98", interfaz.getUsuario(), comando);
                return false;
            }
        int porcentajeActual = interfaz.getPorcentajeOcupadoMapa();
        interfaz.setPorcentajeOcupadoMapa(porcentajeActual += personaje.getPorcentajeMapa());
        if (interfaz.getPorcentajeOcupadoMapa() > 100){
            ComandoCrearPersonajeErrores.error("!!!ERROR!!! Se esta ocupando mas del 100% del mapa", interfaz.getUsuario(), comando);
            return false;
        }

        if (interfaz.getPorcentajeOcupadoMapa() < 100 && interfaz.getListaPersonajes().size() == 2){
            ComandoCrearPersonajeErrores.error("!!!ERROR!!! Se esta ocupando menos del 100% del mapa y es el ultimo personaje", interfaz.getUsuario(), comando);
            return false;
        }
        
        return true;
    }
    
    public static boolean validarEstadisticas(Personaje personaje, ComandoCrearPersonaje comando, InterfazPrincipal interfaz){
        if (interfaz.getListaPersonajes().size() > 1){
            for (Personaje personajeActual : interfaz.getListaPersonajes()){
                if (personaje.getPoder() == personajeActual.getPoder() || personaje.getResistencia() == personajeActual.getResistencia() || personaje.getSanidad() == personajeActual.getSanidad())
                    ComandoCrearPersonajeErrores.error("!!!ERROR!!! Estadistica usada en otro personaje", interfaz.getUsuario(), comando);
                    return false;
                    }
        }
        if (!validarPoder(personaje,interfaz)){
            ComandoCrearPersonajeErrores.error("!!!ERROR!!! La estadistica poder no es valida", interfaz.getUsuario(), comando);
            return false;
        }
        
        if (!validarResistencia(personaje, interfaz)){
            ComandoCrearPersonajeErrores.error("!!!ERROR!!! La estadistica poder no es valida", interfaz.getUsuario(), comando);
            return false;
        }
        
        if (!validarSanidad(personaje, interfaz)){
            ComandoCrearPersonajeErrores.error("!!!ERROR!!! La estadistica poder no es valida", interfaz.getUsuario(), comando);
            return false;
        }
        
        return true;
    }
    
    
    public static boolean validarPoder(Personaje personaje, InterfazPrincipal interfaz){
        if (interfaz.getStatsPoder()[0] == personaje.getPoder() || interfaz.getStatsPoder()[1] == personaje.getPoder() || interfaz.getStatsPoder()[2] == personaje.getPoder()){
            return true;
        }
        return false;
}
    public static boolean validarResistencia(Personaje personaje, InterfazPrincipal interfaz){
        if (interfaz.getStatsResistencia()[0] == personaje.getResistencia() || interfaz.getStatsResistencia()[1] == personaje.getResistencia() || interfaz.getStatsResistencia()[2] == personaje.getResistencia()){
            return true;
        }
        return false;
}
    public static boolean validarSanidad(Personaje personaje, InterfazPrincipal interfaz){
        if (interfaz.getStatsSanidad()[0] == personaje.getSanidad() || interfaz.getStatsSanidad()[1] == personaje.getSanidad() || interfaz.getStatsSanidad()[2] == personaje.getSanidad()){
            return true;
        }
        return false;
}
}
