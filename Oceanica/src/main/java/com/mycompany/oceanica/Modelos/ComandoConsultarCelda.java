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
public class ComandoConsultarCelda extends Comando {

    public ComandoConsultarCelda(String[] args, String nombre) {
        super(TiposComandos.CONSULTAR_CELDA, args, nombre);
    }

@Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setInfo(true);
        this.setIsBroadcast(false);
    }

    @Override
    public void procesoEnUsuario(Usuario usuario) {
            usuario.getInterfazPrincipal().writeMessage("Se consulto la celda (" + this.getParametros()[1] +", " + this.getParametros()[2] + ")", this );
    }
    
}
