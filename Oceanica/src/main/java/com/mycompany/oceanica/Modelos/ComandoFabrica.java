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
            case "INICIAR":    
                return new ComandoListo(args, nombre);
            case "CREAR":    
                return new ComandoCrearPersonaje(args, nombre);
            case "SALTAR":    
                return new ComandoSaltarTurno(args, nombre);
            case "CONSULTAR_CELDA":    
                return new ComandoConsultarCelda(args, nombre);
            case "LOG":    
                return new ComandoLog(args, nombre);
            case "LOG_RESUMEN":    
                return new ComandoLogResumen(args, nombre);
            case "CONSULTAR_ENEMIGO":    
                return new ComandoConsultarEnemigo(args, nombre);
            case "MOSTRAR_CELDAS_OCUPADAS":    
                return new ComandoCeldasOcupadas(args, nombre);
            case "MOSTRAR_PORCENTAJES_CELDAS":    
                return new ComandoPorcentajeCeldas(args, nombre);
            case "PINTAR_VIVAS ":    
                return new ComandoPintarVivas(args, nombre);
            default:
                return new ComandoError(args, nombre);
        }
    }
}
