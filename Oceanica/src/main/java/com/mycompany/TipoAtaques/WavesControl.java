/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.TipoAtaques;

import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import com.mycompany.Interfaz.Celda;
import com.mycompany.Interfaz.InterfazPrincipal;
import com.mycompany.Personaje.Personaje;
import com.mycompany.Personaje.TipoPersonaje;
import com.mycompany.oceanica.Modelos.ComandoAtaque;
import com.mycompany.oceanica.Modelos.ComandoResultadoAtaque;
import java.util.List;

/**
 *
 * @author seb
 */
public class WavesControl extends Personaje {
    
    private String[] ataques = new String[3];
    
    public WavesControl() {
        super(TipoPersonaje.WAVES_CONTROL);
        this.ataques[0] = "SWIRL_RAISING";
        this.ataques[1] = "SEND_HUMAN_GARBAGE";
        this.ataques[2] = "RADIOACTIVE_RUSH";
    }
    
    public ComandoResultadoAtaque ataqueSwirlRaising(InterfazPrincipal interfaz, ComandoAtaque comando){
        
        Random rand = new Random();
        List<String> mensajes = new ArrayList<>();
        Celda[][] celdas = interfaz.getCeldas();
        int F = celdas.length;
        int C = celdas[0].length;
        int rango = rand.nextInt(9) + 2;
        interfaz.setRangoUltimoRemolino(rango);

        
        int fila = rand.nextInt(20);
        int columna = rand.nextInt(20);
        int ataque = 100;
        if (comando.getPersonaje().isTieneMultiplicadorPoder()) {
            ataque = 100 + (comando.getPersonaje().getPoder() *100/ 100);
        }
        
            // 1. Determinar los límites de búsqueda seguros
        int r_inicio = Math.max(0, fila - rango);
        int r_fin = Math.min(F - 1, fila + rango);
        int c_inicio = Math.max(0, columna - rango);
        int c_fin = Math.min(C - 1, columna + rango);
        
        interfaz.borrarMensajes();
        interfaz.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");
        
        String msg = "[Swirl_Raising] Celda (" + fila + "," + columna +
                ") Es el epicentro del tornado";
               mensajes.add(msg);
        for (int i = r_inicio; i <= r_fin; i++) {
            for (int j = c_inicio; j <= c_fin; j++) {
                celdas[i][j].recibirAtaque(comando, ataque, interfaz);
                celdas[i][j].pintarRemolino();
                celdas[i][j].setTieneRemolino(true);
                msg = "[Swirl_Raising] Celda (" + i + "," + j +
                ") quedó con " + celdas[j][i].getVida() + " de vida.";
               mensajes.add(msg);
            }
        }
        celdas[fila][columna].aplicarEfecto(TipoEfecto.REMOLINO);
        celdas[fila][columna].pintarOrigenRemolino();
        celdas[fila][columna].setEsOrigenRemolino(true);
        String[] resultadoArray = new String[mensajes.size() + 2];

        resultadoArray[0] = "RESULTADO_ATAQUE";
        resultadoArray[1] = comando.getNombreUsuario();  // ✔ el atacante va aquí siempre

        for (int i = 0; i < mensajes.size(); i++) {
            resultadoArray[i + 2] = mensajes.get(i);
        }
        interfaz.reestablecerDefensa();
        interfaz.reestablecerPoderPersonaje(comando.getPersonaje());

        // Entregamos el comando directamente
        return new ComandoResultadoAtaque(resultadoArray, interfaz.getUsuario().getNombre(), true);
    }

    // TODO: FALTA HACER QUE FUNCIONE UTILIZANDO EL REMOLINO SELECCIONADO, AHORITA SOLO AGARRA EL ULTIMO RANGO
    public ComandoResultadoAtaque ataqueSendHumanGarbage(InterfazPrincipal interfaz, ComandoAtaque comando){
        
        Random rand = new Random();
        int rango = 10 * interfaz.getRangoUltimoRemolino();
        if (rango == 0) {
            rango = 10;
        }
        int ataque = 25;
        if (comando.getPersonaje().isTieneMultiplicadorPoder()) {
            ataque = 25 + (comando.getPersonaje().getPoder() *25/ 100);
        }
        int cantBasura = 0;
        Celda[][] celdas = interfaz.getCeldas();
        List<String> mensajes = new ArrayList<>();
        interfaz.borrarMensajes();
        interfaz.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");

        while (cantBasura < rango) {
            int fila = rand.nextInt(20);
            int columna = rand.nextInt(20);
            int esRadioactiva = rand.nextInt(2); 
            celdas[fila][columna].recibirAtaque(comando, ataque, interfaz);
            String msg = "[Send_Human_Garbage] Celda (" + fila + "," + columna +
                ") quedó con " + celdas[fila][columna].getVida() + " de vida.";
               mensajes.add(msg);

               if (esRadioactiva == 1) {
                celdas[fila][columna].aplicarEfecto(TipoEfecto.RADIACTIVO);
            }
            cantBasura++;
        }
        String[] resultadoArray = new String[mensajes.size() + 2];

        resultadoArray[0] = "RESULTADO_ATAQUE";
        resultadoArray[1] = comando.getNombreUsuario();  // ✔ el atacante va aquí siempre

        for (int i = 0; i < mensajes.size(); i++) {
            resultadoArray[i + 2] = mensajes.get(i);
        }
        interfaz.reestablecerDefensa();
        interfaz.reestablecerPoderPersonaje(comando.getPersonaje());

        // Entregamos el comando directamente
        return new ComandoResultadoAtaque(resultadoArray, interfaz.getUsuario().getNombre(), true);
    }
    
    public ComandoResultadoAtaque ataqueRadioactiveRush(InterfazPrincipal interfaz, ComandoAtaque comando) {

        Random rand = new Random();
        Celda[][] celdas = interfaz.getCeldas();
        List<String> mensajes = new ArrayList<>();
        ArrayList<Celda> celdasRadioactivas = new ArrayList<Celda>();
        for (Celda[] filaCeldas : celdas) {
            for (int i = 0; i < filaCeldas.length; i++) {
                if (filaCeldas[i].isEsRadioactiva()) {
                    celdasRadioactivas.add(filaCeldas[i]);
                }
            }
        }
        int ataque = 25;
        if (comando.getPersonaje().isTieneMultiplicadorPoder()) {
            ataque = 25 + (comando.getPersonaje().getPoder() *25/ 100);
        }
        int segundos = rand.nextInt(11);
        while (0 < segundos) {
            for (Celda celda : celdasRadioactivas) {
                celda.recibirAtaque(comando, ataque, interfaz);
                String msg = "[Radioactive_Rush] Celda (" + celda.getFila() + "," + celda.getColumna() +
                ") quedó con " + celda.getVida() + " de vida.";
               mensajes.add(msg);
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
        interfaz.reestablecerPoderPersonaje(comando.getPersonaje());

        // Entregamos el comando directamente
        return new ComandoResultadoAtaque(resultadoArray, interfaz.getUsuario().getNombre(), true);
    }


    
    public String[] getAtaques() {
        return ataques;
    }

    

    @Override
    public ComandoResultadoAtaque realizarAtaque(ComandoAtaque comando, InterfazPrincipal interfaz) {
        String[] args = comando.getParametros();
        ComandoResultadoAtaque result = null;
        System.out.println("ts10");
        for (String ataque : this.ataques){
            if (ataque.equals(args[3].toUpperCase())){
                switch(ataque){
                    case "SWIRL_RAISING":
                        result = ataqueSwirlRaising(interfaz,comando);
                        return result;
                    case "SEND_HUMAN_GARBAGE":
                        result = ataqueSendHumanGarbage(interfaz, comando);
                        return result;
                    case "RADIOACTIVE_RUSH":
                        result = ataqueRadioactiveRush(interfaz, comando);
                        return result;
                }
            }
        }
        return result;
    }


    
}
