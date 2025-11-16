/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Modelos;

import com.mycompany.Interfaz.Celda;
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
        Celda[][] celdas = usuario.getInterfazPrincipal().getCeldas();    
        usuario.getInterfazPrincipal().writeMessage(celdas[Integer.parseInt(this.getParametros()[1])][Integer.parseInt(this.getParametros()[2])].toString(),this);
    }
    
}
