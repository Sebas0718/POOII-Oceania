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
import com.mycompany.oceanica.Modelos.ComandoAtaque;

/**
 *
 * @author seb
 */
public class WavesControl extends Personaje {
    
    private int rangoUltimoRemolino;
    private String[] ataques = new String[3];
    
    public WavesControl() {
        super(TipoPersonaje.RELEASE_THE_KRAKEN);
        this.ataques[0] = "SWIRL_RAISING";
        this.ataques[1] = "SEND_HUMAN_GARBAGE";
        this.ataques[2] = "RADIOACTIVE_RUSH";
    }
    
    public void ataqueSwirlRaising(InterfazPrincipal interfaz, ComandoAtaque comando){
        
        Random rand = new Random();
        
        Celda[][] celdas = interfaz.getCeldas();
        int F = celdas.length;
        int C = celdas[0].length;
        int rango = rand.nextInt(9) + 2;
        this.rangoUltimoRemolino = rango;
        
        int fila = rand.nextInt(20);
        int columna = rand.nextInt(20);

        
            // 1. Determinar los límites de búsqueda seguros
        int r_inicio = Math.max(0, fila - rango);
        int r_fin = Math.min(F - 1, fila + rango);
        int c_inicio = Math.max(0, columna - rango);
        int c_fin = Math.min(C - 1, columna + rango);
        
        interfaz.borrarMensajes();
        interfaz.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");
        comando.getUsuario().getInterfazPrincipal().borrarMensajes();
        comando.getUsuario().getInterfazPrincipal().writeResultadoAtaque("EL RESULTADO DEL ATAQUE FUE:");
        
        for (int i = r_inicio; i <= r_fin; i++) {
            for (int j = c_inicio; j <= c_fin; j++) {
                celdas[i][j].recibirAtaque(comando, 100, interfaz);
            }
        }
        celdas[fila][columna].aplicarEfecto(TipoEfecto.REMOLINO);
    }

    public void ataqueSendHumanGarbage(InterfazPrincipal interfaz, ComandoAtaque comando){
        
        Random rand = new Random();
        int remolino = Integer.parseInt(comando.getParametros()[4]);
        int rango = 10*this.rangoUltimoRemolino;
        int cantBasura = 0;
        Celda[][] celdas = interfaz.getCeldas();
        
        interfaz.borrarMensajes();
        interfaz.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");
        comando.getUsuario().getInterfazPrincipal().borrarMensajes();
        comando.getUsuario().getInterfazPrincipal().writeResultadoAtaque("EL RESULTADO DEL ATAQUE FUE:");
        
        while (cantBasura < rango) {
            int fila = rand.nextInt(20);
            int columna = rand.nextInt(20);
            int esRadioactiva = rand.nextInt(2); 
            for (int i = 0; i < celdas.length; i++) {
                for (int j = 0; j < celdas[0].length; j++){
                    celdas[i][j].recibirAtaque(comando, rango, interfaz);

                }
            }
        }

    }
    
    public void ataqueRadioactiveRush(InterfazPrincipal interfaz, ComandoAtaque comando) {

        Celda[][] celdas = interfaz.getCeldas();
        
        interfaz.borrarMensajes();
        interfaz.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");
        comando.getUsuario().getInterfazPrincipal().borrarMensajes();
        comando.getUsuario().getInterfazPrincipal().writeResultadoAtaque("EL RESULTADO DEL ATAQUE FUE:");
        
        for (Celda[] filaCeldas : celdas) {
            for (int i = 0; i < filaCeldas.length; i++) {
                if (filaCeldas[i].isEsRadioactiva()){
                    filaCeldas[i].recibirAtaque(comando, 10, interfaz);
                }
            }
        }
    }


    
    public String[] getAtaques() {
        return ataques;
    }

    

    @Override
    public void realizarAtaque(ComandoAtaque comando, InterfazPrincipal interfaz) {
        String[] args = comando.getParametros();
        for (String ataque : this.ataques){
            if (ataque.equals(args[3].toUpperCase())){
                switch(ataque){
                    case "SWIRL_RAISING":
                        ataqueSwirlRaising(interfaz,comando);
                        return;
                    case "SEND_HUMAN_GARBAGE":
                        ataqueSendHumanGarbage(interfaz, comando);
                        return;
                    case "RADIOACTIVE_RUSH":
                        ataqueRadioactiveRush(interfaz, comando);
                        return;
                }
            }
        }
    }


    
}
