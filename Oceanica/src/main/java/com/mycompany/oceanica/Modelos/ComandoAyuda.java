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
public class ComandoAyuda extends Comando {

    public ComandoAyuda(String[] args, String nombre) {
        super(TiposComandos.AYUDA, args, nombre);
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setInfo(true);
        this.setIsBroadcast(false);
    }
    
    public void procesoEnUsuario(Usuario usuario) {
            usuario.getInterfazPrincipal().writeMessage("ATAQUE nombreUsuario tipoDeAtaque x y\n" +
"MENSAJE \"mensaje\"\n" +
"MENSAJE_PRIVADO nombreUsuario \"mensaje\"\n" +
"RENDIRSE\n" +
"CREAR tipo stad1 stad2 stad3 nombre\n" +
"INICIAR\n" +
"SALTAR\n" +
"CONSULTAR_CELDA x y\n" +
"LOG detalle\n" +
"LOG_RESUMEN \n" +
"CONSULTAR_ENEMIGO nombreUsuario\n" +
"MOSTRAR_CELDAS_OCUPADAS\n" +
"MOSTRAR_PORCENTAJES_CELDAS\n" +
"PINTAR_VIVAS ");
}
    
}
