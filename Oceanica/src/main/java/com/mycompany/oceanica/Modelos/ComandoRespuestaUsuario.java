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
public class ComandoRespuestaUsuario extends Comando {

    public ComandoRespuestaUsuario(String[] parametros, String nombreUsuario) {
        super(TiposComandos.RESPUESTA_USUARIO, parametros, nombreUsuario);
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setInfo(false);
        this.setIsBroadcast(false);
    }
    
    @Override
    public void procesoEnUsuario(Usuario usuario){
        String[] args = this.getParametros();
        usuario.getInterfazPrincipal().borrarMensajes();
        usuario.getInterfazPrincipal().actualizarInterfaz();
        usuario.getInterfazPrincipal().writeResultadoAtaque("INFORMACION DEL USUARIO:");
        
        // Imprimir todos los mensajes del ataque
        for (int i = 2; i < args.length; i++) {
            usuario.getInterfazPrincipal().writeResultadoAtaque(args[i]);
        }
    }
}
