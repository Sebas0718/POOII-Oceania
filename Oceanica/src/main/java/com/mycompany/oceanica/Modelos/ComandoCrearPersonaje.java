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
public class ComandoCrearPersonaje extends Comando {

    public ComandoCrearPersonaje(TiposComandos tipo, String[] parametros, String nombre) {
        super(tipo, parametros, nombre);
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
