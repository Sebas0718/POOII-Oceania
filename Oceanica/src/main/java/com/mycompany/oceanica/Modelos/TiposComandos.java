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
    ATTACK (5),  //attack usuario x y
    MESSAGE (3), //message saludo
    PRIVATE_MESSAGE(4), // private usuario saludo
    GIVEUP(2), // giveup
    NAME(2),
    ERROR(1);
    //Estos son ejemplos, si faltan se agregan mas comandos
    
    private int ParametrosRequeridos;

    private TiposComandos(int ParametrosRequeridos) {
        this.ParametrosRequeridos = ParametrosRequeridos;
    }

    public int getParametrosRequeridos() {
        return ParametrosRequeridos;
    }
    
    
    
    
}
