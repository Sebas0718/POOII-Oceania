package com.mycompany.TipoAtaques;

import java.util.Random;

import com.mycompany.Interfaz.Celda;
import com.mycompany.Interfaz.InterfazPrincipal;

public class Ataque {
    
    private int rango;

    public void atacarArea(InterfazPrincipal interfazPrincipal, int rango, int fila, int columna, int ataque){

        Celda[][] celdas = interfazPrincipal.getCeldas();
        int F = celdas.length;
        int C = celdas[0].length;
        
            // 1. Determinar los límites de búsqueda seguros
        int r_inicio = Math.max(0, fila - rango);
        int r_fin = Math.min(F - 1, fila + rango);
        int c_inicio = Math.max(0, columna - rango);
        int c_fin = Math.min(C - 1, columna + rango);

        for (int i = r_inicio; i <= r_fin; i++) {
            for (int j = c_inicio; j <= c_fin; j++) {            
                celdas[i][j].recibirAtaqueDirecto(ataque);
            }
        }

    }

    public void atacarLineaRecta(InterfazPrincipal interfazPrincipal, String direccion, int rango, int fila, int columna, int ataque){
        
        direccion = direccion.trim().toLowerCase();
        Celda[][] celdas = interfazPrincipal.getCeldas();
        int i = 0;
        while(i <  rango){
            switch (direccion) {
                case "abajo":
                    if (fila + i < 20) {
                        celdas[fila + i][columna].recibirAtaqueDirecto(ataque);
                    }
                    i++;
                    break;
                case "arriba":
                    if (fila- i >= 0) {
                        celdas[fila - i][columna].recibirAtaqueDirecto(ataque);
                    }
                    i++;
                    break;
                case "izquierda":
                    if (columna - i >= 0) {
                        celdas[fila][columna - i].recibirAtaqueDirecto(ataque);
                    }
                    i++;
                    break;
                case "derecha":
                if (columna + i < 20) {
                        celdas[fila][columna+i].recibirAtaqueDirecto(ataque);
                    }
                    i++;    
                break;
            default:
                System.out.println("ERROR300: NO FUNCIONA ATACARLINEARECTA");
                    break;
            }
        }
    
    };  


    public void ataquePersistente(InterfazPrincipal interfazPrincipal, int rango, int fila, int columna,TipoEfecto efecto){

        Celda[][] celdas = interfazPrincipal.getCeldas();
        int F = celdas.length;
        int C = celdas[0].length;
        
            // 1. Determinar los límites de búsqueda seguros
        int r_inicio = Math.max(0, fila - rango);
        int r_fin = Math.min(F - 1, fila + rango);
        int c_inicio = Math.max(0, columna - rango);
        int c_fin = Math.min(C - 1, columna + rango);

        for (int i = r_inicio; i <= r_fin; i++) {
            for (int j = c_inicio; j <= c_fin; j++) {            
                celdas[i][j].aplicarEfecto(efecto);
            }
        }
    }


}



