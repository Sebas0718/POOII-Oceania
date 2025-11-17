/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.TipoAtaques;

import com.mycompany.Interfaz.Celda;
import com.mycompany.Interfaz.InterfazPrincipal;
import com.mycompany.Personaje.Personaje;
import com.mycompany.Personaje.TipoPersonaje;
import com.mycompany.oceanica.Modelos.ComandoAtaque;
import com.mycompany.oceanica.Modelos.ComandoResultadoAtaque;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author xsusk
 */

public class UnderseaFire extends Personaje {
    
    private String[] ataques = new String[3];
    
    public UnderseaFire() {
        super(TipoPersonaje.UNDERSEA_FIRE);
        this.ataques[0] = "VOLCANO_RAISING";
        this.ataques[1] = "VOLCANO_EXPLOSION";
        this.ataques[2] = "TERMAL_RUSH";
        
    }

    public ComandoResultadoAtaque ataqueVolcanoRaising(InterfazPrincipal interfaz, ComandoAtaque comando){
        
        Random rand = new Random();
        Celda[][] celdas = interfaz.getCeldas();
        List<String> mensajes = new ArrayList<>();
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
        String msg = "[Volcano Explosion] Celda (" + fila + "," + columna +
                ") Es el epicentro del volcan";
               mensajes.add(msg);
        for (int i = r_inicio; i <= r_fin; i++) {
            for (int j = c_inicio; j <= c_fin; j++) {
                celdas[i][j].recibirAtaque(comando, 100, interfaz);
                celdas[i][j].pintarVolcan();
                celdas[i][j].setTieneVolcan(true);
                msg = "[Volcano Explosion] Celda (" + i + "," + j +
                ") quedó con " + celdas[i][j].getVida() + " de vida.";
               mensajes.add(msg);
               
            }
        }
        
        celdas[fila][columna].aplicarEfecto(TipoEfecto.VOLCAN);
        celdas[fila][columna].pintarOrigenVolcan();
        celdas[fila][columna].setEsOrigenVolcan(true);
        String[] resultadoArray = new String[mensajes.size() + 2];

        resultadoArray[0] = "RESULTADO_ATAQUE";
        resultadoArray[1] = comando.getNombreUsuario();  // ✔ el atacante va aquí siempre

        for (int i = 0; i < mensajes.size(); i++) {
            resultadoArray[i + 2] = mensajes.get(i);
        }
        interfaz.reestablecerDefensa();

        // Entregamos el comando directamente
        return new ComandoResultadoAtaque(resultadoArray, interfaz.getUsuario().getNombre(), true);
            
    }
    
    public ComandoResultadoAtaque ataqueVolcanoExplosion(InterfazPrincipal interfaz, ComandoAtaque comando) {

        Random rand = new Random();
        int rango = 10 * interfaz.getRangoUltimoVolcan();
        if (rango == 0) {
            System.err.println("el rango es 0");
            rango = 10;
        }
        int cantPiedras = 0;
        Celda[][] celdas = interfaz.getCeldas();
        List<String> mensajes = new ArrayList<>();
        
        while (cantPiedras< rango) {
            int fila = rand.nextInt(20);
            int columna = rand.nextInt(20);
            
            celdas[fila][columna].recibirAtaque(comando, 20, interfaz);
            cantPiedras++;
            String msg = "[Volcano Explosion] Celda (" + fila + "," + columna +
                     ") quedó con " + celdas[fila][columna].getVida() + " de vida.";
            mensajes.add(msg);
        }
        String[] resultadoArray = new String[mensajes.size() + 2];

        resultadoArray[0] = "RESULTADO_ATAQUE";
        resultadoArray[1] = comando.getNombreUsuario();  // ✔ el atacante va aquí siempre

        for (int i = 0; i < mensajes.size(); i++) {
            resultadoArray[i + 2] = mensajes.get(i);
        }
        interfaz.reestablecerDefensa();

        // Entregamos el comando directamente
        return new ComandoResultadoAtaque(resultadoArray, interfaz.getUsuario().getNombre(), true);
    }
    
    public ComandoResultadoAtaque ataqueTermalRush(InterfazPrincipal interfaz, ComandoAtaque comando){
        
        Random rand = new Random();
        Celda[][] celdas = interfaz.getCeldas();
        List<String> mensajes = new ArrayList<>();
        ArrayList<Celda> celdasVolcanicas = new ArrayList<Celda>();
        for (Celda[] filaCeldas : celdas) {
            for (int i = 0; i < filaCeldas.length; i++) {
                if (filaCeldas[i].isEsOrigenVolcan()) {
                    celdasVolcanicas.add(filaCeldas[i]);
                }
            }
        }
        
        int ataque = interfaz.getRangoUltimoVolcan() + 5;
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
                        celdas[i][j].recibirAtaque(comando, ataque, interfaz);
                        String msg = "[Volcano Explosion] Celda (" + i + "," + j +
                        ") quedó con " + celdas[i][j].getVida() + " de vida.";
                        mensajes.add(msg);
                    }
                }
            }
            segundos--;
        }
        String[] resultadoArray = new String[mensajes.size() + 2];

        resultadoArray[0] = "RESULTADO_ATAQUE";
        resultadoArray[1] = comando.getNombreUsuario();  // ✔ el atacante va aquí siempre

        for (int i = 0; i < mensajes.size(); i++) {
            resultadoArray[i + 2] = mensajes.get(i);
        }
        interfaz.reestablecerDefensa();

        // Entregamos el comando directamente
        return new ComandoResultadoAtaque(resultadoArray, interfaz.getUsuario().getNombre(), true);
    }
    
    

    @Override
    public ComandoResultadoAtaque realizarAtaque(ComandoAtaque comando, InterfazPrincipal interfaz) {
        String[] args = comando.getParametros();
        ComandoResultadoAtaque result = null;
        for (String ataque : this.ataques){
            if (ataque.equals(args[3].toUpperCase())){
                switch(ataque){
                    case "VOLCANO_RAISING":
                         result = ataqueVolcanoRaising(interfaz,comando);
                        return result;
                    case "VOLCANO_EXPLOSION":
                        result = ataqueVolcanoExplosion(interfaz, comando);
                        return result;
                    case "TERMAL_RUSH":
                        result = ataqueTermalRush(interfaz, comando);
                        return result;
                }
            }
        }
    return result;
    }
}