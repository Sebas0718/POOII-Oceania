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
public class ComandoUsuarios extends Comando{

    public ComandoUsuarios(String[] parametros, String nombre) {
        super(TiposComandos.USUARIOS, parametros, nombre);
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setInfo(true);
        this.setIsBroadcast(false);
    }
    
}
