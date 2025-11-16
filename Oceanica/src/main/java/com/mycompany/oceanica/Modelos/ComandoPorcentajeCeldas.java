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
public class ComandoPorcentajeCeldas extends Comando {

    public ComandoPorcentajeCeldas(String[] args, Usuario nombre) {
        super(TiposComandos.MOSTRAR_PORCENTAJES_CELDAS, args, nombre);
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setInfo(true);
        this.setIsBroadcast(false);
    }

    
    @Override
    public void procesoEnUsuario(Usuario usuario) {
        Celda[][] celdas = usuario.getInterfazPrincipal().getCeldas();
        StringBuilder informacion = new StringBuilder();

        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[i].length; j++) {
                Celda celda = celdas[i][j];
                float vida = celda.getVida();

                informacion.append("Celda (")
                           .append(i).append(", ")
                           .append(j).append(") tiene ")
                           .append(String.format("%.2f", vida))   
                           .append(" de vida\n");
            }
        }
        usuario.getInterfazPrincipal().writeMessage(informacion.toString(), this);
        
    }
    
}
