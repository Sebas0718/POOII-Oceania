/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.TipoAtaques;

import com.mycompany.Interfaz.Celda;
import com.mycompany.Interfaz.InterfazPrincipal;
import java.util.Random;

/**
 *
 * @author seb
 */



public class FishTelepathy {
    
    
    public void ataqueTentaculos(InterfazPrincipal interfazPrincipal, int columna1, int fila1, int columna2, int fila2, int columna3, int fila3){
        
        Celda[][] celdas = interfazPrincipal.getCeldas();
        for (int i = 0; i < celdas.length; i++){
            for (int j = 0; j < celdas[0].length; j++){
            
                if (fila1 == i || fila2 == i || fila3 == i){
                    if (columna1 == j || columna2 == j || columna3 == j){
                        
                        celdas[i][j].recibirAtaque(100);
                        if ((j-1 >= 0 && j+1 <= celdas.length) && (j-1 >= 0 && j+1 <= celdas.length)) {
                            celdas[i ]
                        }
                    }
                } 
                
                
                
                }
            
            
            }
        
        
        }
        
        
        
        
        }
        
        
        
    
    
    }
    
    
    
    
    
    
    
    
}
