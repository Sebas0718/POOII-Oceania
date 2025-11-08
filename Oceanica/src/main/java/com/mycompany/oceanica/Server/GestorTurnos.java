package com.mycompany.oceanica.Server;

import java.util.ArrayList;

import com.mycompany.oceanica.Modelos.ComandoListo;
import com.mycompany.oceanica.Threads.ThreadServer;
import com.mycompany.oceanica.Modelos.Comando;


public class GestorTurnos {

    private Server server;
    private ArrayList<ThreadServer> jugadores;
    

    private int jugadorActual = 0;
    private boolean juegoActivo = false;

    public GestorTurnos(Server server){
        this.server = server;
        this.jugadores = server.getUsuariosConectados(); 
    }


    public void iniciarJuego() {
        if (jugadores.size() >= 2) {
            juegoActivo = true;
            jugadorActual = 0;
            notificarInicioJuego();
            iniciarTurno();
        }
    }

    private void iniciarTurno() {
        if (!juegoActivo) {
            finalizarJuego();
            return;
        }
        ThreadServer jugador = jugadores.get(jugadorActual);
        notificarTurno(jugador);
    }

    public void procesarComando(Comando comando, ThreadServer jugador) {
        if (!juegoActivo)
            return;
        if (jugador != jugadores.get(jugadorActual))
            return;
        server.ejecutarComando(comando);

        if (verificarGanador()) {
            finalizarJuego();
            return;
        }
        siguienteTurno();
    }

    public void siguienteTurno(){
        jugadorActual = (jugadorActual + 1) % jugadores.size();
        iniciarTurno();
    }

    public void notificarInicioJuego() {
        String[] args = { "INICIAR", "El juego ha comenzado" };
        Comando comandoInicio = new ComandoListo(args, "Server");
        server.broadcast(comandoInicio);
    }

    public void notificarTurno(ThreadServer jugador) {
        String[] args = { "TURNO", "Es tu turno " + jugador.getNombre() };
        Comando comandoTurno = new ComandoListo(args, "Server");
        try {
            jugador.getObjetoEscritor().writeObject(comandoTurno);
        } catch (Exception e) {
            System.err.println("Error al notificar el turno " + e.getMessage());
        }
    }
    
    private boolean verificarGanador() {

        int ganadores = 0;
        for (int i = 0; i < jugadores.size(); i++) {
            ThreadServer jugador = jugadores.get(i);
            if (!jugador.getHaPerdido()) {
                ganadores++;
            }
        }
        if (ganadores != 1) {
            return false;
        }
        return true;
    }

    private void finalizarJuego(){
        juegoActivo = false;
        String[] args = { "FIN", "El juego ha terminado" };
        Comando comandoFin = new ComandoListo(args, "Server");
        server.broadcast(comandoFin);
    } 


    public boolean isJuegoActivo() {
        return juegoActivo;
    }

    public ThreadServer getJugadorActual() {
        return jugadores.get(jugadorActual);
    }






}
