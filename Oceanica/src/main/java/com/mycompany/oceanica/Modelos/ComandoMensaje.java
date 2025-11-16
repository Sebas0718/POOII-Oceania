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
public class ComandoMensaje extends Comando {

    public ComandoMensaje(String[] args, Usuario nombre) {
        super(TiposComandos.MENSAJE, args, nombre);
    }


    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setIsBroadcast(true);
        this.setInfo(false);
    }

    @Override
    public void procesoEnUsuario(Usuario usuario) {
        usuario.getInterfazPrincipal().writeMessage("Mensaje recibido de " + this.getUsuario().getNombre() + ": "+ this.getParametros()[1],this);
    }
    
}
