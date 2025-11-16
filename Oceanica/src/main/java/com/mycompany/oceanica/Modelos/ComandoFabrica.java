/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Modelos;

import com.mycompany.Personaje.Personaje;
import com.mycompany.oceanica.Usuario.Usuario;

/**
 *
 * @author xsusk
 */
public class ComandoFabrica {
    
    
    public static Comando getComando(String[] args, Usuario nombre){
        String tipo = args[0].toUpperCase();
        
        switch(tipo){
            case "MENSAJE":
                if (TiposComandos.MENSAJE.getParametrosRequeridos() < args.length)
                    return new ComandoError(args, nombre);
                return new ComandoMensaje(args, nombre);
            case "MENSAJE_PRIVADO":
                if (TiposComandos.MENSAJE_PRIVADO.getParametrosRequeridos() < args.length)
                    return new ComandoError(args, nombre);
                return new ComandoPrivado(args, nombre);
            case "RENDIRSE":
                if (TiposComandos.RENDIRSE.getParametrosRequeridos() < args.length)
                    return new ComandoError(args, nombre);
                return new ComandoGiveup(args, nombre);
            case "NOMBRE":    
                if (TiposComandos.NOMBRE.getParametrosRequeridos() < args.length)
                    return new ComandoError(args, nombre);
                return new ComandoNombre(args, nombre);
            case "INICIAR":    
                if (TiposComandos.INICIAR.getParametrosRequeridos() < args.length)
                    return new ComandoError(args, nombre);
                return new ComandoListo(args, nombre);
            case "CREAR":    
                if (TiposComandos.CREAR.getParametrosRequeridos() < args.length)
                    return new ComandoError(args, nombre);
                return new ComandoCrearPersonaje(args, nombre);
            case "SALTAR":    
                if (TiposComandos.SALTAR.getParametrosRequeridos() < args.length)
                    return new ComandoError(args, nombre);
                return new ComandoSaltarTurno(args, nombre);
            case "CONSULTAR_CELDA":    
                if (TiposComandos.CONSULTAR_CELDA.getParametrosRequeridos() < args.length){
                    return new ComandoError(args, nombre);
                }
                if (!ComandoAtaqueValidacion.fueraDeAlcanceXY(Integer.parseInt(args[1]), Integer.parseInt(args[2]))){
                    return new ComandoError(args, nombre);
                }
                return new ComandoConsultarCelda(args, nombre);
            case "LOG":    
                if (TiposComandos.LOG.getParametrosRequeridos() < args.length)
                    return new ComandoError(args, nombre);
                if (args[1].toUpperCase().equals("RECIBIDOS")){
                    return new ComandoLog(args, nombre);
                }
                else if (args[2].toUpperCase().equals("REALIZADOS")){
                    return new ComandoLog(args, nombre);
                }
                return new ComandoError(args, nombre);
            case "LOG_RESUMEN":    
                if (TiposComandos.LOG_RESUMEN.getParametrosRequeridos() < args.length)
                    return new ComandoError(args, nombre);
                return new ComandoLogResumen(args, nombre);
            case "CONSULTAR_ENEMIGO":    
                if (TiposComandos.CONSULTAR_ENEMIGO.getParametrosRequeridos() < args.length)
                    return new ComandoError(args, nombre);
                return new ComandoConsultarEnemigo(args, nombre);
            case "MOSTRAR_CELDAS_OCUPADAS":    
                if (TiposComandos.MOSTRAR_CELDAS_OCUPADAS.getParametrosRequeridos() < args.length)
                    return new ComandoError(args, nombre);
                return new ComandoCeldasOcupadas(args, nombre);
            case "MOSTRAR_PORCENTAJES_CELDAS":    
                if (TiposComandos.MOSTRAR_PORCENTAJES_CELDAS.getParametrosRequeridos() < args.length)
                    return new ComandoError(args, nombre);
                return new ComandoPorcentajeCeldas(args, nombre);
            case "PINTAR_VIVAS":    
                if (TiposComandos.PINTAR_VIVAS.getParametrosRequeridos() < args.length)
                    return new ComandoError(args, nombre);
                return new ComandoPintarVivas(args, nombre);
            case "AYUDA":    
                if (TiposComandos.AYUDA.getParametrosRequeridos() < args.length)
                    return new ComandoError(args, nombre);
                return new ComandoAyuda(args, nombre);
            case "USUARIOS":
                if (TiposComandos.USUARIOS.getParametrosRequeridos() < args.length)
                    return new ComandoError(args, nombre);
                return new ComandoUsuarios(args,nombre);
            case "ATAQUES_PERSONAJES":
                if (TiposComandos.USUARIOS.getParametrosRequeridos() < args.length)
                    return new ComandoError(args, nombre);
                return new ComandoAtaquesPersonajes(args,nombre);
            default:
                return new ComandoError(args, nombre);
        }
    }
}
