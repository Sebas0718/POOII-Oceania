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
public class ComandoLogResumen extends Comando {

    public ComandoLogResumen(String[] args, String nombre) {
        super(TiposComandos.LOG_RESUMEN, args, nombre);
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setInfo(true);
        this.setIsBroadcast(false);
    }

    @Override
    public void procesoEnUsuario(Usuario usuario) {
        int enviados = usuario.getAtaquesEnviados();
        int atinados = usuario.getAtaquesAtinados();
        int fallados = usuario.getAtaquesfallados();
        
        if (enviados == 0){
            String info = "Atauqes realizados: 0" + "\n"
                    + "El porcentaje de exito es de: 0%" + "\n" 
                    + "Se han atinado: 0 ataques" + "\n"
                    + "Se han fallado: 0 ataques";
            usuario.getInterfazPrincipal().writeMessage(info, this);
            return;
        }
        
        double porcentaje = (atinados * 100.0) / enviados;
        String porcentajeStr = String.format("%.2f", porcentaje);
        String info = "Ataques realizados: " + enviados + "\n"
                    + "El porcentaje de exito es de: " + porcentajeStr + "%\n" 
                    + "Se han atinado: " + atinados + " ataques" + "\n"
                    + "Se han fallado: " + fallados + " ataques";
        usuario.getInterfazPrincipal().writeMessage(info, this);
    }
    
}
    

