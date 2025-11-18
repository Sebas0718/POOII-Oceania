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
public class ComandoListo extends Comando {

    public ComandoListo(String[] args, String nombre) {
        super(TiposComandos.INICIAR, args, nombre);
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setInfo(false);
        this.setIsBroadcast(true);
        threadServidor.setIsReady(true);
        
        if (threadServidor.getPersonajesCreados() == 3) {
            threadServidor.getServer().getGestorTurnos().agregarJugador(threadServidor);
            threadServidor.getServer().getGestorTurnos().iniciarJuego();
            return;
        } 
        threadServidor.getServer().getRefPantalla().writeMessage(this.getNombreUsuario() + " no puede iniciar, no ha creado todos los personajes");
    }

    @Override
    public void procesoEnUsuario(Usuario usuario) {
        usuario.getInterfazPrincipal().getTxaBitacora().append("El usuario " + this.getNombreUsuario() + " ya esta listo\n");
    }
    
}
