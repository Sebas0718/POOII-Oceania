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
    ATAQUE (4),  //attack usuario x y
    MENSAJE (2), //message saludo
    MENSAJE_PRIVADO(3), // private usuario saludo
    RENDIRSE(1), // giveup
    NOMBRE(2),
    ERROR(1),
    CREAR(8),
    INICIAR(1),
    SALTAR(1),
    CONSULTAR_CELDA(3),
    LOG(2),
    LOG_RESUMEN(1),
    CONSULTAR_ENEMIGO(2),
    MOSTRAR_CELDAS_OCUPADAS(1),
    MOSTRAR_PORCENTAJES_CELDAS(1),
    PINTAR_VIVAS(1),
    AYUDA(1),
    USUARIOS(1),
    ATAQUES_PERSONAJES(1),
    RESULTADO_ATAQUE(0),
    DERROTA(0),
    VICTORIA(0),
    TURNO(0),
    RESPUESTA_USUARIO(0);
    
    
    private int ParametrosRequeridos;

    private TiposComandos(int ParametrosRequeridos) {
        this.ParametrosRequeridos = ParametrosRequeridos;
    }

    public int getParametrosRequeridos() {
        return ParametrosRequeridos;
    }
}
