/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.TipoAtaques;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import com.mycompany.Interfaz.Celda;
import com.mycompany.Interfaz.InterfazPrincipal;
import com.mycompany.Personaje.Personaje;
import com.mycompany.Personaje.TipoPersonaje;
import com.mycompany.oceanica.Modelos.ComandoAtaque;

/**
 *
 * @author seb
 */
public class UnderseaFire extends Personaje {
    
    private String[] ataques = new String[3];
    
    public UnderseaFire() {
        super(TipoPersonaje.UNDERSEA_FIRE);
        this.ataques[0] = "VOLCANO_RAISING";
        this.ataques[1] = "VOLCANO_EXPLOSION";
        this.ataques[2] = "TERMAL_RUSH";
        
    }

    public void ataqueVolcanoRaising(InterfazPrincipal interfaz, ComandoAtaque comando){
        
        Random rand = new Random();
        Celda[][] celdas = interfaz.getCeldas();
        int F = celdas.length;
        int C = celdas[0].length;

        int rango = rand.nextInt(10) + 1;
        interfaz.setRangoUltimoVolcan(rango);
        
        int fila = rand.nextInt(20);
        int columna = rand.nextInt(20);

        
            // 1. Determinar los límites de búsqueda seguros
        int r_inicio = Math.max(0, fila - rango);
        int r_fin = Math.min(F - 1, fila + rango);
        int c_inicio = Math.max(0, columna - rango);
        int c_fin = Math.min(C - 1, columna + rango);

        for (int i = r_inicio; i <= r_fin; i++) {
            for (int j = c_inicio; j <= c_fin; j++) {
                celdas[i][j].recibirAtaque(interfaz.getUsuario(), 100);
            }
        }
        celdas[fila][columna].aplicarEfecto(TipoEfecto.VOLCAN);
        celdas[fila][columna].getRefLabel().setBackground(new Color(244, 172, 50));
    }
    
    public void ataqueVolcanoExplosion(InterfazPrincipal interfaz, ComandoAtaque comando) {

        Random rand = new Random();
        int rango = 10 * interfaz.getRangoUltimoVolcan();
        if (rango == 0) {
            rango = 10;
        }
        int cantPiedras = 0;
        Celda[][] celdas = interfaz.getCeldas();

        while (cantPiedras< rango) {
            int fila = rand.nextInt(20);
            int columna = rand.nextInt(20);
            
            celdas[fila][columna].recibirAtaque(interfaz.getUsuario(), 20);
            cantPiedras++;
        }
        
    }
    
    public void ataqueTermalRush(InterfazPrincipal interfaz, ComandoAtaque comando){
        
        Random rand = new Random();
        Celda[][] celdas = interfaz.getCeldas();
        
        ArrayList<Celda> celdasVolcanicas = new ArrayList<Celda>();
        for (Celda[] filaCeldas : celdas) {
            for (int i = 0; i < filaCeldas.length; i++) {
                if (filaCeldas[i].isTieneVolcan()) {
                    celdasVolcanicas.add(filaCeldas[i]);
                }
            }
        }
        
        int ataque = interfaz.getRangoUltimoVolcan();
        if (ataque == 0) {
            ataque = 5;
        }
        int segundos = rand.nextInt(2) + 5;
        
        while (0 < segundos) {
            for (Celda celda : celdasVolcanicas) {
                
                int F = celdas.length;
                int C = celdas[0].length;
                int radioAtaque = 5;
                    
                
                int fila = celda.getFila();
                int columna = celda.getColumna();
                
                int r_inicio = Math.max(0, fila - radioAtaque);
                int r_fin = Math.min(F - 1, fila + radioAtaque);
                int c_inicio = Math.max(0, columna - radioAtaque);
                int c_fin = Math.min(C - 1, columna + radioAtaque);
                
                for (int i = r_inicio; i <= r_fin; i++) {
                    for (int j = c_inicio; j <= c_fin; j++) {
                        celdas[i][j].recibirAtaque(interfaz.getUsuario(), ataque);
                    }
                }
            }
            segundos--;
        }
    }
    

    @Override
    public void realizarAtaque(ComandoAtaque comando, InterfazPrincipal interfaz) {
        String[] args = comando.getParametros();
        for (String ataque : this.ataques){
            if (ataque.equals(args[3].toUpperCase())){
                switch(ataque){
                    case "VOLCANO_RAISING":
                        ataqueVolcanoRaising(interfaz,comando);
                        return;
                    case "VOLCANO_EXPLOSION":
                        ataqueVolcanoExplosion(interfaz, comando);
                        return;
                    case "TERMAL_RUSH":
                        ataqueTermalRush(interfaz, comando);
                        return;
                }
            }
        }
    }
    
}
