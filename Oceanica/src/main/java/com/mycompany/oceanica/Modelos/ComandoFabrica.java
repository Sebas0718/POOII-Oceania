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
    
    
    public static Comando getComando(String[] args){
        String tipo = args[0].toUpperCase();
        
        switch(tipo){
            case "ATTACK":
                return new ComandoAtaque(args);
            case "MESSAGE":
                return new ComandoMensaje(args);
            case "PRIVATE_MESSAGE":
                return new ComandoPrivado(args);
            case "GIVE_UP":
                return new ComandoGiveup(args);
            case "NAME":    
                return new ComandoNombre(args);
            default:
                return null; //TODO: CommandError
            
        }
    }
}
