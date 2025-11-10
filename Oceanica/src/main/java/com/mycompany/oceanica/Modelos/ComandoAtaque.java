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
public class ComandoAtaque extends Comando {


    public ComandoAtaque(String[] args, String nombre) {
        super(TiposComandos.ATAQUE, args, nombre);
    }



    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setInfo(false);
        this.setIsBroadcast(false);
        if (threadServidor.getServer().getGestorAtaques().buscarUsuario(this.getParametros()[1])!=null) {
            System.out.println("TS50: Entro a procesoPorServer");
            Usuario usuarioAtacante = threadServidor.getServer().getGestorAtaques().buscarUsuario(threadServidor.getNombre());
            Usuario usuarioVictima = threadServidor.getServer().getGestorAtaques().buscarUsuario(this.getParametros()[1]);
            threadServidor.getServer().getGestorAtaques().atacarUsuario(this, usuarioAtacante, usuarioVictima);
        }
    }

    @Override
    public void procesoEnUsuario(Usuario usuario) {
            usuario.getInterfazPrincipal().writeMessage("Conectado el cliente: " + this.getParametros()[1], this);
            usuario.getInterfazPrincipal().actualizarInterfaz();
}
    
}
