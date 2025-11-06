/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.TipoAtaques;

import com.mycompany.Interfaz.Celda;
import com.mycompany.Interfaz.InterfazPrincipal;

/**
 *
 * @author seb
 */
    public class ReleaseTheKraken {

    public void ataqueTentaculos(InterfazPrincipal interfazPrincipal, int fila, int columna){
        Celda[][] celdas = interfazPrincipal.getCeldas();
        for (int i = 0; i < celdas.length; i++){
            for (int j = 0; j < celdas[0].length; j++){
                if (Math.abs(fila-i) <= 1 || Math.abs(columna-j) <= 1){
                    celdas[i][j].recibirAtaqueDirecto(100);
                    }
                }
            }
        }
    public void ataqueKrakenBreath(InterfazPrincipal interfazPrincipal, int fila, int columna){
        
        
        
}

    
    
}
