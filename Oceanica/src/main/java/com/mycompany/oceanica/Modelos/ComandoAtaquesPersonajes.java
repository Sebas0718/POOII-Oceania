/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Modelos;

import com.mycompany.oceanica.Threads.ThreadServer;
import com.mycompany.oceanica.Usuario.Usuario;

/**
 *
 * @author xsusk
 */
public class ComandoAtaquesPersonajes extends Comando {

    public ComandoAtaquesPersonajes(TiposComandos tipo, String[] parametros, String nombre) {
        super(tipo, parametros, nombre);
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setInfo(true);
        this.setIsBroadcast(false);
    }
    
    public void procesoEnUsuario(Usuario usuario) {
        String ayuda = """
        RELEASE THE KRAKEN:
         - ATAQUE enemigo personaje Tentáculos "x" "y" "x" "y" "x" "y"
         - ATAQUE enemigo personaje Kraken_Breath "x" "y"
         - ATAQUE enemigo personaje Release_the_Kraken

        POSEIDON TRIDENT:
         - ATAQUE enemigo personaje Three_lines "x" "y" "x" "y" "x" "y"
         - ATAQUE enemigo personaje Three_numbers num1 num2 num3
         - ATAQUE enemigo personaje Control_the_Kraken

        FISH TELEPATHY:
         - ATAQUE enemigo personaje Cardumen
         - ATAQUE enemigo personaje Shark_attack
         - ATAQUE enemigo personaje Pulp

        UNDERSEA FIRE:
         - ATAQUE enemigo personaje Volcano_raising
         - ATAQUE enemigo personaje Volcano_explosion num
         - ATAQUE enemigo personaje Termal_rush num

        THUNDERS UNDER THE SEA:
         - ATAQUE enemigo personaje Thunder_rain
         - ATAQUE enemigo personaje Poseidon_thunders
         - ATAQUE enemigo personaje Eel_atack

        WAVES CONTROL:
         - ATAQUE enemigo personaje Swirl_raising
         - ATAQUE enemigo personaje Send_human_garbage num
         - ATAQUE enemigo personaje Radioactive_rush
        """;
        usuario.getInterfazPrincipal().writeMessage(ayuda,this);
    }
    
}
