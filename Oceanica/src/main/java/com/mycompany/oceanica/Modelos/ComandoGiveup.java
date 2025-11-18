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
public class ComandoGiveup extends Comando {

    public ComandoGiveup(String[] args,String nombre) {
        super(TiposComandos.RENDIRSE, args, nombre);
    }



    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setIsBroadcast(true);
        this.setInfo(false);
        threadServidor.getServer().getGestorTurnos().derrota(threadServidor);
        
    }

    @Override
    public void procesoEnUsuario(Usuario usuario) {
        usuario.getInterfazPrincipal().getTxaHistorial()
                .setText("El usuario " + this.getNombreUsuario() + " se rindio");
        usuario.getInterfazPrincipal().limpiarInterfaz();
    }
    
}
