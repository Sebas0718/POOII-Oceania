/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Modelos;

import com.mycompany.Interfaz.InterfazPrincipal;
import com.mycompany.Personaje.Personaje;
import com.mycompany.Personaje.TipoPersonajeFabrica;

/**
 *
 * @author xsusk
 */
public class ComandoCrearPersonajeAsignaciones {
     public static boolean asignarValoresPersonaje(ComandoCrearPersonaje comando, Personaje personaje, InterfazPrincipal interfaz){
        try{
            personaje.setNombre((String) comando.getParametros()[7]);
            personaje.setPoder(Integer.parseInt(comando.getParametros()[4]));
            personaje.setResistencia(Integer.parseInt(comando.getParametros()[5]));
            personaje.setSanidad(Integer.parseInt(comando.getParametros()[6]));
            
            if (!ComandoCrearPersonajeValidaciones.validarEstadisticas(personaje, comando, interfaz)){
                return false;
            }
            
            personaje.setPorcentajeMapa(Integer.parseInt(comando.getParametros()[2]));
            
            String tipo = comando.getParametros()[1].toUpperCase();
            TipoPersonajeFabrica.getTipoPersonaje(tipo, personaje);
            if (personaje.getTipoPersonaje().equals(null)){
                ComandoCrearPersonajeErrores.error("!!!ERROR!!! Tipo de personaje no existente", interfaz.getUsuario(), comando);
                return false;
            }
        } catch(NumberFormatException ex){
                ComandoCrearPersonajeErrores.error("!!!ERROR!!! Tipo de formato no valido", interfaz.getUsuario(), comando);
                return false;
        }
        return true;
    }
}
