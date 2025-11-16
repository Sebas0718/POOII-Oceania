/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Modelos;

import com.mycompany.oceanica.Threads.ThreadServer;
import com.mycompany.oceanica.Usuario.Usuario;

/**
 *
 * @author xsusk
 */
public class ComandoAyuda extends Comando {

    public ComandoAyuda(String[] args, String nombre) {
        super(TiposComandos.AYUDA, args, nombre);
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setInfo(true);
        this.setIsBroadcast(false);
    }
    
    public void procesoEnUsuario(Usuario usuario) {
            usuario.getInterfazPrincipal().writeMessage("COMANDOS: \n" +
                                                            "MENSAJE  \"mensaje\"  (Mensaje para todos los usuarios, es necesario colocar parentesis en el mensaje)\n" +
                                                            "MENSAJE_PRIVADO  \"nombreUsuario\"  \"mensaje\"  (Mensaje para un solo usuario, es necesario colocar parentesis en el mensaje)\n" +
                                                            "RENDIRSE  (Te rindes)\n" +
                                                            "CREAR  \"tipo\" \"porcentaje a representar\" \"Imagen\"  \"Poder\"  \"Resistencia\"  \"Sanidad\"  \"nombre\" (Creas un personaje) \n" +
                                                            "INICIAR  (Darle a listo)\n" +
                                                            "SALTAR  (Salta el turno)\n" +
                                                            "CONSULTAR_CELDA  \"x\"  \"y\"  (Consulta una celda de tu tablero)\n " +
                                                            "LOG \"detalle\" (muestra un detalle de TODOS los eventos que han sucedido, puede elegir entre: recibidos y realizados)\n " +
                                                            "LOG_RESUMEN  (da cuántos ataques se han realizado y cuál es el porcentaje de éxito) \n" +
                                                            "CONSULTAR_ENEMIGO  \"nombreUsuario\"  (muestra el estado del enemigo)\n" +
                                                            "MOSTRAR_CELDAS_OCUPADAS  (muestra en el mapa las celdas ocupadas por volcanes y remolinos.)\n" +
                                                            "MOSTRAR_PORCENTAJES_CELDAS  (muestra en el mapa la vida de cada celda)\n" +
                                                            "PINTAR_VIVAS  (muestra en el mapa las celdas que están vivas de un color, de otro las muertas.) \n" +
                                                            "ATAQUES_PERSONAJES (muestra como enviar ataques segun el tipo de personaje)", this);
                                                            }
}