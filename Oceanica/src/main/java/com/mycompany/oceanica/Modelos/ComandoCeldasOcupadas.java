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
public class ComandoCeldasOcupadas extends Comando {

    public ComandoCeldasOcupadas(String[] args, Usuario nombre) {
        super(TiposComandos.MOSTRAR_CELDAS_OCUPADAS, args, nombre);
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
                if (celda.isEsRadioactiva()){
                    informacion.append("Celda (")
                    .append(i).append(", ")
                    .append(j).append(") Esta con radiacion\n");  
                    continue;
                }
                
                else if (celda.isTieneRemolino()){
                    informacion.append("Celda (")
                    .append(i).append(", ")
                    .append(j).append(") Esta con un remolino\n");  
                    continue;
                }
                
                else if(celda.isTieneVolcan()){
                    informacion.append("Celda (")
                    .append(i).append(", ")
                    .append(j).append(") Esta con un volcan\n");  
                    continue;
                }
            }
        }
        usuario.getInterfazPrincipal().writeMessage(informacion.toString(), this);
        
    }
    

    
}
