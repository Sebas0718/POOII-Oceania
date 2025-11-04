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
public class ComandoNombre extends Comando{
    public ComandoNombre(String[] args, String nombre){
        super(TiposComandos.NAME, args, nombre);
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setIsBroadcast(true);
        threadServidor.setNombre(getParametros()[1]);
        threadServidor.showAllClients();
    }

    @Override
    public void procesoEnUsuario(Usuario usuario) {
        usuario.getRefPantalla().writeMessage("Conectado el cliente: " + this.getParametros()[1]);
    }

}
