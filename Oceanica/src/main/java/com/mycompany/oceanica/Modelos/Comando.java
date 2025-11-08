/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Modelos;

import com.mycompany.oceanica.Threads.ThreadServer;
import com.mycompany.oceanica.Usuario.Usuario;
import java.io.Serializable;
import java.util.Arrays;
/**
 *
 * @author xsusk
 */
public abstract class Comando implements Serializable {
    private TiposComandos tipo;
    private String[] parametros;
    private boolean isBroadcast;
    private boolean info;
    private String  nombre;

    public Comando(TiposComandos tipo, String[] parametros, String nombre) {
        this.tipo = tipo;
        this.parametros = parametros; 
        this.nombre = nombre;
    }
    
    public abstract void procesoPorServer(ThreadServer threadServidor);

    
    public void procesoEnUsuario(Usuario usuario){
        usuario.getInterfazPrincipal().writeMessage(this.toString());
    }


    public TiposComandos getTipo() {
        return tipo;
    }

    public String[] getParametros() {
        return parametros;
    }
    
    @Override
    public String toString(){
        return tipo.toString() + "->" + Arrays.toString(parametros);
    }
    
    public boolean isIsBroadcast() {
        return isBroadcast;
    }

    public void setIsBroadcast(boolean isBroadcast) {
        this.isBroadcast = isBroadcast;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isInfo() {
        return info;
    }

    public void setInfo(boolean info) {
        this.info = info;
    }
        
    
}
