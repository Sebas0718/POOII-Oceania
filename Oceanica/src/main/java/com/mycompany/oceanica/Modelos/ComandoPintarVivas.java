/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Modelos;

import com.mycompany.Interfaz.Celda;
import com.mycompany.oceanica.Threads.ThreadServer;
import com.mycompany.oceanica.Usuario.Usuario;
import java.awt.Color;

/**
 *
 * @author xsusk
 */
public class ComandoPintarVivas extends Comando{

    public ComandoPintarVivas(String[] args, Usuario nombre) {
        super(TiposComandos.PINTAR_VIVAS, args, nombre);
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setInfo(true);
        this.setIsBroadcast(false);
    }

    
    @Override
    public void procesoEnUsuario(Usuario usuario) {
        Celda[][] celdas = usuario.getInterfazPrincipal().getCeldas();
        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[i].length; j++) {
                if (!celdas[i][j].isIsCeldaDestruida()){
                    celdas[i][j].getRefLabel().setBackground(Color.green);
                }
                else {
                    celdas[i][j].getRefLabel().setBackground(Color.gray);
                }
            }
        }
    }
}
