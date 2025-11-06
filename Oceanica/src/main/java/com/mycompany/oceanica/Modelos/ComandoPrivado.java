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

    public ComandoPrivado(String[] args, String nombre) {
        super(TiposComandos.MENSAJE_PRIVADO, args, nombre);
    }


    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setIsBroadcast(false);
    }

    @Override
    public void procesoEnUsuario(Usuario usuario) {
        usuario.getRefPantalla().writeMessage("Mensaje para " + this.getParametros()[1] + " de parte de " + this.getNombre() + ": "+ this.getParametros()[2]);
    }
    
}
