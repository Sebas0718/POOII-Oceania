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
import com.mycompany.oceanica.Modelos.ComandoAtaqueValidacion;
import com.mycompany.oceanica.Modelos.ComandoResultadoAtaque;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author seb
 */



public class FishTelepathy extends Personaje {
    
    private String[] ataques = new String[3];
   

    public FishTelepathy() {
        super(TipoPersonaje.FISH_TELEPATHY);
        this.ataques[0] = "CARDUMEN";
        this.ataques[1] = "SHARK_ATTACK";
        this.ataques[2] = "PULP";
    }
    
    
    public ComandoResultadoAtaque ataqueCardumen (InterfazPrincipal interfaz, ComandoAtaque comando){
        Random rand = new Random();
        Celda[][] celdas = interfaz.getCeldas();
        List<String> mensajes = new ArrayList<>();
        // Número de peces entre 100 y 300
        int cantidadPeces = rand.nextInt(201) + 100; 
        
        interfaz.borrarMensajes();
        interfaz.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");
        
        
        for (int i = 0; i < cantidadPeces; i++) {

            int x = rand.nextInt(20);  // fila aleatoria
            int y = rand.nextInt(20);  // columna aleatoria
                celdas[x][y].recibirAtaque(comando, 33, interfaz);
                String msg = "[Volcano Explosion] Celda (" + x + "," + y +
                ") quedó con " + celdas[x][y].getVida() + " de vida.";
                mensajes.add(msg);
        }
        String[] resultadoArray = new String[mensajes.size() + 2];

        resultadoArray[0] = "RESULTADO_ATAQUE";
        resultadoArray[1] = comando.getNombreUsuario();  // ✔ el atacante va aquí siempre

        for (int i = 0; i < mensajes.size(); i++) {
            resultadoArray[i + 2] = mensajes.get(i);
        }

        // Entregamos el comando directamente
        return new ComandoResultadoAtaque(resultadoArray, interfaz.getUsuario().getNombre(),true);
    }
    
    public ComandoResultadoAtaque ataqueSharkAttack(InterfazPrincipal interfaz, ComandoAtaque comando){
        Random rand = new Random();
        Celda[][] celdas = interfaz.getCeldas();
        List<String> mensajes = new ArrayList<>();
        int rango = rand.nextInt(10) + 1; // 1 a 10
        interfaz.borrarMensajes();
        interfaz.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");
        
        
        for (int i = 0; i < rango; i++){
            for (int j = 0; j < rango; j++){
                if (ComandoAtaqueValidacion.fueraDeAlcanceXY(i, j)) {
                    celdas[i][j].recibirAtaque(comando, 100, interfaz);
                    String msg = "[Volcano Explosion] Celda (" + i + "," + j +
                        ") quedó con " + celdas[i][j].getVida() + " de vida.";
                        mensajes.add(msg);
                }
            }
        }

       
        for (int i = 0; i < rango; i++){
            for (int j = 19 - rango + 1; j <= 19; j++){
                if (ComandoAtaqueValidacion.fueraDeAlcanceXY(i, j)) {
                    celdas[i][j].recibirAtaque(comando, 100, interfaz);
                    String msg = "[Volcano Explosion] Celda (" + i + "," + j +
                        ") quedó con " + celdas[i][j].getVida() + " de vida.";
                        mensajes.add(msg);
                }
            }
        }

        
        for (int i = 19 - rango + 1; i <= 19; i++){
            for (int j = 0; j < rango; j++){
                if (ComandoAtaqueValidacion.fueraDeAlcanceXY(i, j)) {
                    celdas[i][j].recibirAtaque(comando, 100, interfaz);
                    String msg = "[Volcano Explosion] Celda (" + i + "," + j +
                        ") quedó con " + celdas[i][j].getVida() + " de vida.";
                        mensajes.add(msg);
                }
            }
        }

        
        for (int i = 19 - rango + 1; i <= 19; i++){
            for (int j = 19 - rango + 1; j <= 19; j++){
                if (ComandoAtaqueValidacion.fueraDeAlcanceXY(i, j)) {
                    celdas[i][j].recibirAtaque(comando, 100, interfaz);
                    String msg = "[Volcano Explosion] Celda (" + i + "," + j +
                        ") quedó con " + celdas[i][j].getVida() + " de vida.";
                        mensajes.add(msg);
                }
            }
        }
        String[] resultadoArray = new String[mensajes.size() + 2];

        resultadoArray[0] = "RESULTADO_ATAQUE";
        resultadoArray[1] = comando.getNombreUsuario();  // ✔ el atacante va aquí siempre

        for (int i = 0; i < mensajes.size(); i++) {
            resultadoArray[i + 2] = mensajes.get(i);
        }

        // Entregamos el comando directamente
        return new ComandoResultadoAtaque(resultadoArray, interfaz.getUsuario().getNombre(),true);
    }
    
    
    public ComandoResultadoAtaque ataquePulp(InterfazPrincipal interfaz, ComandoAtaque comando){
        Random rand = new Random();
        Celda[][] celdas = interfaz.getCeldas();
        List<String> mensajes = new ArrayList<>();
        // Cantidad de pulpos entre 20 y 50
        int cantidadPulpos = rand.nextInt(31) + 20;
        interfaz.borrarMensajes();
        interfaz.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");
        
        // Cada pulpo lanza 8 tentáculos que dañan 25 cada uno
        for (int p = 0; p < cantidadPulpos; p++) {
            for (int t = 0; t < 8; t++) {

                int x = rand.nextInt(20);  // fila aleatoria
                int y = rand.nextInt(20);  // columna aleatoria

                celdas[x][y].recibirAtaque(comando, 25, interfaz);
                String msg = "[Volcano Explosion] Celda (" + x + "," + y +
                ") quedó con " + celdas[x][y].getVida() + " de vida.";
                mensajes.add(msg);
            }
        }
        String[] resultadoArray = new String[mensajes.size() + 2];

        resultadoArray[0] = "RESULTADO_ATAQUE";
        resultadoArray[1] = comando.getNombreUsuario();  // ✔ el atacante va aquí siempre

        for (int i = 0; i < mensajes.size(); i++) {
            resultadoArray[i + 2] = mensajes.get(i);
        }

        // Entregamos el comando directamente
        return new ComandoResultadoAtaque(resultadoArray, interfaz.getUsuario().getNombre(),true);
    }
    

    public String[] getAtaques() {
        return ataques;
    }

    public void setAtaques(String[] ataques) {
        this.ataques = ataques;
    }

    @Override
    public ComandoResultadoAtaque realizarAtaque(ComandoAtaque comando, InterfazPrincipal interfaz) {
        String[] args = comando.getParametros();
        ComandoResultadoAtaque result = null;
        for (String ataque : this.ataques){
            if (ataque.equals(args[3].toUpperCase())){
                switch(ataque){
                    case "CARDUMEN":
                        result = ataqueCardumen(interfaz,comando);
                        return result;
                    case "SHARK_ATTACK":
                        result = ataqueSharkAttack(interfaz, comando);
                        return result;
                    case "PULP":
                        result = ataquePulp(interfaz, comando);
                        return result;
                }
            }
        }
        return result;
    }
}