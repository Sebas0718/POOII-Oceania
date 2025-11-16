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
import java.util.List;
import java.util.Random;

/**
 *
 * @author seb
 */



public class FishTelepathy extends Personaje {
    
    private String[] ataques = new String[3];
   
    private boolean requiereCoordenadas = false;

    public FishTelepathy() {
        super(TipoPersonaje.FISH_TELEPATHY);
        this.ataques[0] = "CARDUMEN";
        this.ataques[1] = "SHARK_ATTACK";
        this.ataques[2] = "PULP";
    }
    
    
    public void ataqueCardumen (InterfazPrincipal interfaz, ComandoAtaque comando){
        Random rand = new Random();
        Celda[][] celdas = interfaz.getCeldas();

        // Número de peces entre 100 y 300
        int cantidadPeces = rand.nextInt(201) + 100; 

        for (int i = 0; i < cantidadPeces; i++) {

            int x = rand.nextInt(20);  // fila aleatoria
            int y = rand.nextInt(20);  // columna aleatoria
                celdas[x][y].recibirAtaque(comando.getUsuario(), 33);
        }
    }
    
    public void ataqueSharkAttack(InterfazPrincipal interfaz, ComandoAtaque comando){
        Random rand = new Random();
    Celda[][] celdas = interfaz.getCeldas();

    int rango = rand.nextInt(10) + 1; // 1 a 10

    // 🔹 1. Superior Izquierda (0,0)
    for (int i = 0; i < rango; i++){
        for (int j = 0; j < rango; j++){
            if (ComandoAtaqueValidacion.fueraDeAlcanceXY(i, j)) {
                celdas[i][j].recibirAtaque(comando.getUsuario(), 100);
            }
        }
    }

    // 🔹 2. Superior Derecha (0,19)
    for (int i = 0; i < rango; i++){
        for (int j = 19 - rango + 1; j <= 19; j++){
            if (ComandoAtaqueValidacion.fueraDeAlcanceXY(i, j)) {
                celdas[i][j].recibirAtaque(comando.getUsuario(), 100);
            }
        }
    }

    // 🔹 3. Inferior Izquierda (19,0)
    for (int i = 19 - rango + 1; i <= 19; i++){
        for (int j = 0; j < rango; j++){
            if (ComandoAtaqueValidacion.fueraDeAlcanceXY(i, j)) {
                celdas[i][j].recibirAtaque(comando.getUsuario(), 100);
            }
        }
    }

    // 🔹 4. Inferior Derecha (19,19)
    for (int i = 19 - rango + 1; i <= 19; i++){
        for (int j = 19 - rango + 1; j <= 19; j++){
            if (ComandoAtaqueValidacion.fueraDeAlcanceXY(i, j)) {
                celdas[i][j].recibirAtaque(comando.getUsuario(), 100);
            }
        }
    }
    }
    public void ataquePulp(InterfazPrincipal interfaz, ComandoAtaque comando){
        Random rand = new Random();
        Celda[][] celdas = interfaz.getCeldas();

        // Cantidad de pulpos entre 20 y 50
        int cantidadPulpos = rand.nextInt(31) + 20;

        // Cada pulpo lanza 8 tentáculos que dañan 25 cada uno
        for (int p = 0; p < cantidadPulpos; p++) {
            for (int t = 0; t < 8; t++) {

                int x = rand.nextInt(20);  // fila aleatoria
                int y = rand.nextInt(20);  // columna aleatoria

                celdas[x][y].recibirAtaque(comando.getUsuario(), 25);
            }
        }
}
    

    public String[] getAtaques() {
        return ataques;
    }

    public void setAtaques(String[] ataques) {
        this.ataques = ataques;
    }

    @Override
    public void realizarAtaque(ComandoAtaque comando, InterfazPrincipal interfaz) {
        String[] args = comando.getParametros();
        for (String ataque : this.ataques){
            if (ataque.equals(args[3].toUpperCase())){
                switch(ataque){
                    case "CARDUMEN":
                        ataqueCardumen(interfaz,comando);
                        return;
                    case "SHARK_ATTACK":
                        ataqueSharkAttack(interfaz, comando);
                        return;
                    case "PULP":
                        ataquePulp(interfaz, comando);
                        return;
                }
            }
        }
    }
}