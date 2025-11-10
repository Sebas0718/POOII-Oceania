/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.TipoAtaques;


import java.util.Random;

import com.mycompany.Interfaz.Celda;
import com.mycompany.Interfaz.InterfazPrincipal;
import com.mycompany.Personaje.Personaje;
import com.mycompany.Personaje.TipoPersonaje;
import com.mycompany.oceanica.Modelos.Comando;
import com.mycompany.oceanica.Modelos.ComandoAtaque;

/**
 *
 * @author seb
 */
public class ReleaseTheKraken extends Personaje{
    
    private String[] ataques = new String[3];
    
    public ReleaseTheKraken(TipoPersonaje tipoPersonaje) {
        super(tipoPersonaje);
        this.ataques[0] = "Tentaculos";
        this.ataques[1] = "Kraken_breath";
        this.ataques[2] = "Release_the_kraken";
    }


        /*
         * Tentáculos: permite colocar en el
            tablero la aparción de 3 tentáculos
            que destruyen lo que esté en el
            radio de 1 casilla alrededor
         * 
         */
    public void ataqueTentaculos(InterfazPrincipal interfazPrincipal, ComandoAtaque comando) {
            
        Celda[][] celdas = interfazPrincipal.getCeldas();
        for (int i = 0; i < celdas.length; i++){
            for (int j = 0; j < celdas[0].length; j++){

                if (fila == i && columna == j){
                    celdas[i][j].recibirAtaqueDirecto(100);
                }

                if (Math.abs(fila-i) == 1 && Math.abs(columna-j) == 1){
                    celdas[i][j].recibirAtaqueDirecto(100);
                    }
                }
            }
    }
        
        /* 
        Kraken Breath: se selecciona una
        casilla donde el Kraken lanza su
        aliento hacia una dirección: arriba,
        abajo, derecha, izquierda. El aliento
        destruye entre 1 y 8 casillas en esa
        dirección
        */
    public void ataqueKrakenBreath(InterfazPrincipal interfazPrincipal,  ComandoAtaque comando){
    
        direccion = direccion.trim().toLowerCase();
        Random rand = new Random();
        int cantCasillasAtacadas = rand.nextInt(8) + 1;
        Celda[][] celdas = interfazPrincipal.getCeldas();
        int i = 0;
        while(i < cantCasillasAtacadas){
            switch (direccion) {
                case "abajo":
                    if (fila + i < 20) {
                        celdas[fila + i][columna].recibirAtaqueDirecto(100);
                    }
                    i++;
                    break;
                case "arriba":
                    if (fila- i >= 0) {
                        celdas[fila - i][columna].recibirAtaqueDirecto(100);
                    }
                    i++;
                    break;
                case "izquierda":
                    if (columna - i >= 0) {
                        celdas[fila][columna - i].recibirAtaqueDirecto(100);
                    }
                    i++;
                    break;

                case "derecha":
                if (columna + i < 20) {
                        celdas[fila][columna+i].recibirAtaqueDirecto(100);
                    }
                    i++;    
                break;
            default:
                System.out.println("ERROR500: ATAQUEKRAKENBREATH DIRECCION INVALIDA");
                    break;
            }
        }
    
    };  
    
    /*
     * Release the Kraken: el Kraken
     * aparece en un punto del mapa y
     * destruye todo en un radio de 
     * 1,2,3,4,5,6,7,8,9 casillas.
     * 
     */
    public void ataqueReleaseTheKraken(InterfazPrincipal interfazPrincipal,  ComandoAtaque comando) {
    

        Random rand = new Random();

        Celda[][] celdas = interfazPrincipal.getCeldas();
        int F = celdas.length;
        int C = celdas[0].length;
        int rango = 9;
        
        int fila = rand.nextInt(20);
        int columna = rand.nextInt(20);
        
            // 1. Determinar los límites de búsqueda seguros
        int r_inicio = Math.max(0, fila - rango);
        int r_fin = Math.min(F - 1, fila + rango);
        int c_inicio = Math.max(0, columna - rango);
        int c_fin = Math.min(C - 1, columna + rango);

        for (int i = r_inicio; i <= r_fin; i++) {
            for (int j = c_inicio; j <= c_fin; j++) {            
                celdas[i][j].recibirAtaqueDirecto(100);
            }
        }

    }

    public String[] getAtaques() {
        return ataques;
    }

    public void setAtaques(String[] ataques) {
        this.ataques = ataques;
    }

    
    
}
