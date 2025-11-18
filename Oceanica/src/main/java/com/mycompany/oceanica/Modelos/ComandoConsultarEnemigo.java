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

public class ComandoConsultarEnemigo extends Comando {

    public ComandoConsultarEnemigo(String[] args, String nombre) {
        super(TiposComandos.CONSULTAR_ENEMIGO, args, nombre);
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setInfo(false);
        this.setIsBroadcast(false);
    }

    @Override
    public void procesoEnUsuario(Usuario usuario) {
            
    }

}
