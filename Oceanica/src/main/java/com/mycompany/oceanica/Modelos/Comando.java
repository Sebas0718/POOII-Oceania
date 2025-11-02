/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Modelos;

import java.io.Serializable;
/**
 *
 * @author xsusk
 */
public abstract class Comando implements Serializable {
    private TiposComandos tipo;
    private String[] parametros;

    public Comando(TiposComandos tipo) {
        this.tipo = tipo;
        parametros = new String[100]; 
    }
    
    public abstract void process();

    public TiposComandos getTipo() {
        return tipo;
    }

    public String[] getParametros() {
        return parametros;
    }
        
}
