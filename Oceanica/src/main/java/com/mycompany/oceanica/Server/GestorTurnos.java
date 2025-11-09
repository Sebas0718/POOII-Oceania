package com.mycompany.oceanica.Server;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.mycompany.oceanica.Modelos.ComandoMensaje;
import com.mycompany.oceanica.Threads.ThreadServer;
import com.mycompany.oceanica.Modelos.Comando;


public class GestorTurnos {
    private Server server;
    private ArrayList<ThreadServer> jugadores;
    private int jugadorActual = 0;
    private boolean juegoActivo = false;

    public GestorTurnos(Server server) {
        this.server = server;
        this.jugadores = new ArrayList<>();
    }


    //#######################################################################################

    public void agregarJugador(ThreadServer jugador) {
        if (!jugadores.contains(jugador)) {
            jugadores.add(jugador);
            server.getRefPantalla().writeMessage("Jugador agregado: " + jugador.getNombre());
        }
    }


    //#######################################################################################

    public void iniciarJuego() {
        if (jugadores.size() >= 2 && jugadoresPersonajesCompletos()) {
            juegoActivo = true;
            jugadorActual = 0;
            server.getRefPantalla().writeMessage("¡Juego iniciado! Turno de: " + getJugadorActual().getNombre());
        }
    }


    //#######################################################################################
    
    public boolean jugadoresPersonajesCompletos() {
        
        for (ThreadServer jugador : jugadores) {
            if (jugador.getPersonajesCreados() != 3) {
                return false;
            }
        }
        return true;
    }
    


    //#######################################################################################

    public void siguienteTurno() {
        if (!juegoActivo)
            return;
        jugadorActual = (jugadorActual + 1) % jugadores.size();
        server.getRefPantalla().writeMessage("Turno de: " + getJugadorActual().getNombre());
    }
    
    //#######################################################################################

    public boolean isJuegoActivo() {
        return juegoActivo;
    }
    //#######################################################################################

    public ThreadServer getJugadorActual() {
        if (!juegoActivo || jugadores.isEmpty())
            return null;
        return jugadores.get(jugadorActual);
    }

}
