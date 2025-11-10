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
public class ComandoCrearPersonaje extends Comando {

    public ComandoCrearPersonaje(String[] args, String nombre) {
        super(TiposComandos.CREAR, args, nombre);
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setInfo(true);
        this.setIsBroadcast(false);
        threadServidor.aumentarPersonajesCreados();
        threadServidor.getServer().getRefPantalla().writeMessage(getNombre() + " ha creado un personaje" + threadServidor.getPersonajesCreados() + "/3");
        if (threadServidor.getPersonajesCreados() == 3) {
            threadServidor.getServer().getRefPantalla().writeMessage(getNombre() + " ha creado todos sus personajes");
        }
    }

    @Override
    public void procesoEnUsuario(Usuario usuario) {
            usuario.getInterfazPrincipal().crearPersonajes(this);
            
    }
    
}
    

