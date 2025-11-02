/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.oceanica.Modelos;

/**
 *
 * @author xsusk
 */
public enum TiposComandos {
    
    // Colocar cuantos parametros se recibira de cada tipo, para manejar errores
    ATTACK (4),  //attack usuario x y
    MESSAGE (2), //message saludo
    PRIVATE_MESSAGE(3), // private usuario saludo
    GIVE_UP(1), // giveup
    NAME(2);
    //Estos son ejemplos, si faltan se agregan mas comandos
    
    private int ParametrosRequeridos;

    private TiposComandos(int ParametrosRequeridos) {
        this.ParametrosRequeridos = ParametrosRequeridos;
    }

    public int getParametrosRequeridos() {
        return ParametrosRequeridos;
    }
    
    
    
    
}
