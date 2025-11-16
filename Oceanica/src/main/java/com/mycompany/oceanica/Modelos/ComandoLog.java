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
public class ComandoLog extends Comando {

    public ComandoLog(String[] args, Usuario nombre) {
        super(TiposComandos.LOG, args, nombre);
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setInfo(true);
        this.setIsBroadcast(false);
    }

    @Override
    public void procesoEnUsuario(Usuario usuario) {
        StringBuilder informacion = new StringBuilder();
        if (this.getParametros()[1].toUpperCase().equals("RECIBIDOS")){
            for (String ataque : usuario.getResultadoAtaqueRecibido()){
                informacion.append(ataque).append("\n");
            }
            String resultado = informacion.toString();
            if (resultado.length() == 0){
                usuario.getInterfazPrincipal().writeMessage("NO SE HA RECIBIDO NINGUN ATAQUE", this);
            }
            else{
                usuario.getInterfazPrincipal().writeMessage(resultado, this);
            }
        } 
        else {
            for (String ataque : usuario.getResultadoAtaqueEnviado()){
                    informacion.append(ataque).append("\n");
            }
            
            String resultado = informacion.toString();
            if (resultado.length() == 0){
                usuario.getInterfazPrincipal().writeMessage("NO SE HA RECIBIDO NINGUN ATAQUE", this);
            }
            else{
                usuario.getInterfazPrincipal().writeMessage(resultado, this);
            }
        }
    }
    
    
    
}
    

