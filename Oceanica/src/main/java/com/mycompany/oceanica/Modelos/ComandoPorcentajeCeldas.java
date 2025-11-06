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
public class ComandoPorcentajeCeldas extends Comando {

    public ComandoPorcentajeCeldas(String[] args, String nombre) {
        super(TiposComandos.MOSTRAR_PORCENTAJES_CELDAS, args, nombre);
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setIsBroadcast(false);
    }

    @Override
    public void procesoEnUsuario(Usuario usuario) {
            usuario.getRefPantalla().writeMessage("Conectado el cliente: " + this.getParametros()[1]);
}
    

    
}
