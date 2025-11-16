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
    private String  nombreUsuario;

    public Comando(TiposComandos tipo, String[] parametros, String nombreUsuario) {
        this.tipo = tipo;
        this.parametros = parametros; 
        this.nombreUsuario = nombreUsuario;
    }
    
    public abstract void procesoPorServer(ThreadServer threadServidor);

    
    public void procesoEnUsuario(Usuario usuario){
        usuario.getInterfazPrincipal().writeMessage(this.toString(),this);
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

    public boolean isInfo() {
        return info;
    }

    public void setInfo(boolean info) {
        this.info = info;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    
        
    
}
