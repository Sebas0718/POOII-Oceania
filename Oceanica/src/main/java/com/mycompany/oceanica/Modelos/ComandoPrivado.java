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
public class ComandoPrivado extends Comando {

    public ComandoPrivado(String[] args, Usuario nombre) {
        super(TiposComandos.MENSAJE_PRIVADO, args, nombre);
    }


    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setInfo(true);
        this.setIsBroadcast(false);
    }

    @Override
    public void procesoEnUsuario(Usuario usuario) {
        usuario.getInterfazPrincipal().writeMessage("Mensaje para " + this.getParametros()[1] + " de parte de " + this.getUsuario().getNombre() + ": "+ this.getParametros()[2], this);
    }
    
}
