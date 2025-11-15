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
import com.mycompany.oceanica.Modelos.ComandoAtaqueValidacion;

/**
 *
 * @author seb
 */
public class PoseidonTrident extends Personaje{

    /* 
     * Three lines: selecciona 3 puntos en el mapa. En cada punto destruye lo que esté de 1 a 4
     * casillas a la derecha, izquierda, arriba, abajo (aleatorio)
    */
    
    private String[] ataques = new String[3];
    public PoseidonTrident() {
        super(TipoPersonaje.THE_TRIDDENT);
        this.ataques[0] = "THREE_LINES";
        this.ataques[1] = "THREE_NUMBERS";
        this.ataques[2] = "CONTROL_THE_KRAKEN";
    }
    
    
    public void ataqueThreeLines(InterfazPrincipal interfaz, ComandoAtaque comando){
        Celda[][] celdas = interfaz.getCeldas();
        String[] args = comando.getParametros();
        int tentaculox1 = Integer.parseInt(args[4]);
        int tentaculox2 = Integer.parseInt(args[6]);
        int tentaculox3 = Integer.parseInt(args[8]);
        int tentaculoy1 = Integer.parseInt(args[5]);
        int tentaculoy2 = Integer.parseInt(args[7]);
        int tentaculoy3 = Integer.parseInt(args[9]);
        
        celdas[tentaculox1][tentaculoy1].recibirAtaque(comando.getNombre(), 100);
        this.aplicarThreeLines(celdas, tentaculox1, tentaculoy1, comando.getNombre());
        this.aplicarThreeLines(celdas, tentaculox2, tentaculoy2, comando.getNombre());
        this.aplicarThreeLines(celdas, tentaculox3, tentaculoy3, comando.getNombre());
        
    }
    
    public void ataqueThreeNumbers(InterfazPrincipal interfaz, ComandoAtaque comando){
        //TODO preguntar en la interfaz por 3 numeros entre 0 a 9
    }
    
    public void ataqueControlTheKraken(InterfazPrincipal interfaz, ComandoAtaque comando){
        Random rand = new Random();

        Celda[][] celdas = interfaz.getCeldas();
        int rango = rand.nextInt(9) + 1;
        
        int fila = rand.nextInt(20);
        int columna = rand.nextInt(20);
        
        for (int i = fila - rango; i <= fila + rango; i++) {
            for (int j = columna - rango; j <= columna + rango; j++) {
                if (ComandoAtaqueValidacion.fueraDeAlcanceXY(i, j)) {
                    celdas[i][j].recibirAtaque(comando.getNombre(),100);
                }
            }
        }
    }
    
    public String[] getAtaques() {
        return ataques;
    }

    public void setAtaques(String[] ataques) {
        this.ataques = ataques;
    }
    
    public void aplicarThreeLines(Celda[][] celdas, int x, int y, String nombre){
        Random rand = new Random();
        
        int rango = rand.nextInt(4) + 1;
        
        //Arriba
        for (int i = x - rango; i < x; i++ ){
            if (ComandoAtaqueValidacion.fueraDeAlcanceXY(i, y)) {
                celdas[i][y].recibirAtaque(nombre, 100);
                }
        }
        
        //Abajo
        for (int i = x + 1; i <= x + rango; i++){
            if (ComandoAtaqueValidacion.fueraDeAlcanceXY(i, y)) {
                celdas[i][y].recibirAtaque(nombre, 100);
                }
        }
        
        //Izquierda
        for (int j = y - rango; j < y; j++ ){
            if (ComandoAtaqueValidacion.fueraDeAlcanceXY(x, j)) {
                celdas[x][j].recibirAtaque(nombre, 100);
                }
        }
        
        //Derecha
        for (int j = y + 1; j <= y + rango; j++){
            if (ComandoAtaqueValidacion.fueraDeAlcanceXY(x, j)) {
                celdas[x][j].recibirAtaque(nombre, 100);
                }
        }
    }
    
    
    @Override
    public void realizarAtaque(ComandoAtaque comando, InterfazPrincipal interfaz) {
        
        String[] args = comando.getParametros();
        for (String ataque : this.ataques){
            if (ataque.equals(args[3].toUpperCase())){
                switch(ataque){
                    case "THREE_LINES":
                        ataqueThreeLines(interfaz,comando);
                        return;
                    case "THREE_NUMBERS":
                        ataqueThreeNumbers(interfaz, comando);
                        return;
                    case "CONTROL_THE_KRAKEN":
                        ataqueControlTheKraken(interfaz, comando);
                        return;
                }
            }
        }
    }
}
