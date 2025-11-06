/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Modelos;

/**
 *
 * @author xsusk
 */
public class ComandoFabrica {
    
    
    public static Comando getComando(String[] args, String nombre){
        String tipo = args[0].toUpperCase();
        
        switch(tipo){
            case "ATAQUE":
                return new ComandoAtaque(args, nombre);
            case "MENSAJE":
                return new ComandoMensaje(args, nombre);
            case "MENSAJE_PRIVADO":
                return new ComandoPrivado(args, nombre);
            case "RENDIRSE":
                return new ComandoGiveup(args, nombre);
            case "NOMBRE":    
                return new ComandoNombre(args, nombre);
            default:
                return new ComandoError(args, nombre);
        }
    }
}
