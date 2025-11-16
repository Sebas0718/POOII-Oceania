/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Interfaz;

import java.awt.Color;

import com.mycompany.Personaje.Personaje;
import com.mycompany.TipoAtaques.TipoEfecto;

import java.util.ArrayList;

import javax.swing.JLabel;

import com.mycompany.oceanica.Usuario.Usuario;

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
    
    public void recibirAtaque(Usuario usuario, int ataque){
        
        this.vida -= ataque;
        this.atacadoPor.add(usuario.getNombre());
            if (this.vida <= 0){
                this.isCeldaDestruida = true;
                tieneVolcan = false;
                tieneRemolino = false;
                esRadioactiva = false;
            }

    }

    public void aplicarEfecto(TipoEfecto efecto){
        switch (efecto) {
            case RADIACTIVO:
                this.esRadioactiva = true;
                break;
            case VOLCAN:
                this.tieneVolcan = true;
                break;
            case REMOLINO:
                this.tieneRemolino = true;
                break;
            default:
                break;
        }
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public JLabel getRefLabel() {
        return refLabel;
    }

    public void setRefLabel(JLabel refLabel) {
        this.refLabel = refLabel;
    }

    public float getVida() {
        return vida;
    }

    public void setVida(float vida) {
        this.vida = vida;
    }

    public boolean isIsCeldaDestruida() {
        return isCeldaDestruida;
    }

    public void setIsCeldaDestruida(boolean isCeldaDestruida) {
        this.isCeldaDestruida = isCeldaDestruida;
    }

    public boolean isTieneVolcan() {
        return tieneVolcan;
    }

    public void setTieneVolcan(boolean tieneVolcan) {
        this.tieneVolcan = tieneVolcan;
    }

    public boolean isTieneRemolino() {
        return tieneRemolino;
    }

    public void setTieneRemolino(boolean tieneRemolino) {
        this.tieneRemolino = tieneRemolino;
    }

    public boolean isEsRadioactiva() {
        return esRadioactiva;
    }

    public void setEsRadioactiva(boolean esRadioactiva) {
        this.esRadioactiva = esRadioactiva;
    }

    public String getFueAsesinadaPor() {
        return fueAsesinadaPor;
    }

    public void setFueAsesinadaPor(String fueAsesinadaPor) {
        this.fueAsesinadaPor = fueAsesinadaPor;
    }

    public ArrayList<String> getAtacadoPor() {
        return atacadoPor;
    }

    public void setAtacadoPor(ArrayList<String> atacadoPor) {
        this.atacadoPor = atacadoPor;
    }

    public ArrayList<String> getCuradoPor() {
        return curadoPor;
    }

    public void setCuradoPor(ArrayList<String> curadoPor) {
        this.curadoPor = curadoPor;
    }

    public ArrayList<String> getProtegidoPor() {
        return protegidoPor;
    }

    public void setProtegidoPor(ArrayList<String> protegidoPor) {
        this.protegidoPor = protegidoPor;
    }

    public Personaje getPersonajeDueño() {
        return personajeDueño;
    }

    public void setPersonajeDueño(Personaje personajeDueño) {
        this.personajeDueño = personajeDueño;
    }
    
    
    
    
    
    

}



    