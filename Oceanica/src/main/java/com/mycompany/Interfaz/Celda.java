/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Interfaz;

import com.mycompany.Personaje.Personaje;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author seb
 */
public class Celda {
    
    private int fila;
    private int columna;
    private JLabel refLabel;
    
    private float vida = 100;
    private boolean isCeldaDestruida = false;
    private boolean tieneVolcan = false;
    private boolean tieneRemolino = false;
    private boolean esRadioactiva = false;
    
    private String fueAsesinadaPor; 
    private ArrayList<String> atacadoPor; 
    private ArrayList<String> curadoPor;
    private ArrayList<String> protegidoPor;
    
    private Personaje personajeDueño;
    
    public Celda(JLabel label, Personaje personaje, int fila, int columna){
        
        this.personajeDueño = personaje;
        this.fila = fila;
        this.columna = columna;
    
    }    
    
    
    @Override
    public String toString(){

        String texto = "Casilla: " + fila + " " + columna
                     + "\nPersonaje: " + personajeDueño.getNombre()
                     + "\nTiene Volcan: " + tieneVolcan 
                     + "\nTiene Remolino: " + tieneRemolino 
                     + "\nEsta destruida: " + isCeldaDestruida;
        return texto;
    }
    
}
