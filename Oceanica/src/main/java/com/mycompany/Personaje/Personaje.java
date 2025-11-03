/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Personaje;

import com.mycompany.Interfaz.InterfazPrincipal;
import java.util.ArrayList;
import java.util.Random;
public class Personaje {
    
    private int poder;
    private int resistencia;
    private int sanidad;
    
    private int porcentajeMapa; 
    private String nombre;
    private TipoPersonaje tipoPersonaje;
    
   
    public Personaje(InterfazPrincipal interfazPrincipal){
        Random rand = new Random();
        int poderRandom = rand.nextInt(3);
        int resistenciaRandom = rand.nextInt(3);
        int sanidadRandom = rand.nextInt(3);
        
        
        int[] statsPoder = interfazPrincipal.getStatsPoder();
        int[] statsResistencia = interfazPrincipal.getStatsResistencia();
        int[] statsSanidad = interfazPrincipal.getStatsSanidad();
        
        while (statsPoder[poderRandom] == 0 || statsResistencia[resistenciaRandom] == 0 || statsSanidad[sanidadRandom] == 0){
            poderRandom = rand.nextInt(3);
            resistenciaRandom = rand.nextInt(3);
            sanidadRandom = rand.nextInt(3);
        }
        
        int antiguoPoder = poderRandom;
        int antiguaResistencia = resistenciaRandom;
        int antiguaSanidad = sanidadRandom;
        
        poderRandom = statsPoder[poderRandom];
        resistenciaRandom = statsResistencia[resistenciaRandom];
        sanidadRandom = statsSanidad[sanidadRandom];
        
        statsPoder[antiguoPoder] = 0; 
        statsResistencia[antiguaResistencia] = 0; 
        statsSanidad[antiguaSanidad] = 0;
        
        this.poder = poderRandom;
        this.resistencia = resistenciaRandom;
        this.sanidad = sanidadRandom;
        
        this.nombre = ("Personaje " + rand.nextInt(5));
        
        int tipoPersonaje = rand.nextInt(6);
        
        switch (tipoPersonaje){
        
            case 0:
                this.tipoPersonaje = TipoPersonaje.THE_TRIDDENT;
                break;
                
            case 1:
                this.tipoPersonaje = TipoPersonaje.FISH_TELEPATHY;
                break;
                
            case 2:
                this.tipoPersonaje = TipoPersonaje.RELEASE_THE_KRAKEN;
                break;
                
            case 3:
                this.tipoPersonaje = TipoPersonaje.THUNDERS_UTS;
                break;
                
            case 4:
                this.tipoPersonaje = TipoPersonaje.VOLCANO;
                break;
                
            case 5:
                this.tipoPersonaje = TipoPersonaje.WAVES_CONTROL;
                break;
        }
        
        if (interfazPrincipal.getListaPersonajes().isEmpty()){
            int porcentajeMapa = 50;
            this.porcentajeMapa = porcentajeMapa;
            interfazPrincipal.setPorcentajeOcupadoMapa(porcentajeMapa);
        } else if (interfazPrincipal.getListaPersonajes().size() == 1) {
            int porcentajeMapa = 30;
            this.porcentajeMapa = porcentajeMapa;
            interfazPrincipal.setPorcentajeOcupadoMapa(porcentajeMapa);
        } else {
            int porcentajeMapa = 20;
            this.porcentajeMapa = porcentajeMapa;
            interfazPrincipal.setPorcentajeOcupadoMapa(porcentajeMapa);
        }
        
        
        
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getSanidad() {
        return sanidad;
    }

    public void setSanidad(int sanidad) {
        this.sanidad = sanidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoPersonaje getTipoPersonaje() {
        return tipoPersonaje;
    }

    public void setTipoPersonaje(TipoPersonaje tipoPersonaje) {
        this.tipoPersonaje = tipoPersonaje;
    }

    public int getPorcentajeMapa() {
        return porcentajeMapa;
    }
    
    
    
    
    
    
}
