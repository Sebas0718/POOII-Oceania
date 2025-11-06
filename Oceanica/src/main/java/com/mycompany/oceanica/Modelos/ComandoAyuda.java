/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Modelos;

import com.mycompany.oceanica.Threads.ThreadServer;

/**
 *
 * @author xsusk
 */
public class ComandoAyuda extends Comando {

    public ComandoAyuda(String[] args, String nombre) {
        super(TiposComandos.AYUDA, args, nombre);
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setIsBroadcast(false);
    }
    
}
