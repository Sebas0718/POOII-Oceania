/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Personaje;

import com.mycompany.Interfaz.InterfazPrincipal;
import com.mycompany.oceanica.Modelos.ComandoAtaque;
import com.mycompany.oceanica.Modelos.ComandoResultadoAtaque;
import java.io.Serializable;

public abstract class Personaje implements Serializable {
    
    private int poder;
    private int resistencia;
    private int sanidad;
    
    private int porcentajeMapa; 
    private String nombre;
    private TipoPersonaje tipoPersonaje;

    public Personaje(TipoPersonaje tipoPersonaje) {
        this.tipoPersonaje = tipoPersonaje;
    }

    
    public abstract ComandoResultadoAtaque realizarAtaque(ComandoAtaque comando, InterfazPrincipal interfaz);


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

    public void setPorcentajeMapa(int porcentajeMapa) {
        this.porcentajeMapa = porcentajeMapa;
    }

    @Override
    public String toString() {
        return "Personaje{" + "poder=" + poder + ", resistencia=" + resistencia + ", sanidad=" + sanidad + ", porcentajeMapa=" + porcentajeMapa + ", nombre=" + nombre + ", tipoPersonaje=" + tipoPersonaje + '}';
    }
}
