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
public class ComandoResultadoAtaque extends Comando{
    
    private boolean exito;
    
    
    public ComandoResultadoAtaque(String[] parametros, String nombreUsuario, boolean exito) {
        super(TiposComandos.RESULTADO_ATAQUE, parametros, nombreUsuario);
        this.exito = exito;
        
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setInfo(false);
        this.setIsBroadcast(false);
    }
    
    
    public void procesoEnUsuario(Usuario usuario) {
        usuario.getInterfazPrincipal().borrarMensajes();
        String[] args = this.getParametros();
        usuario.setAtaquesEnviados(usuario.getAtaquesEnviados() + 1);
        if (this.exito){
            usuario.setAtaquesEnviados(usuario.getAtaquesEnviados() + 1);
            
        }
        
        usuario.getInterfazPrincipal().writeResultadoAtaque(args[2]);
        
    }
}
