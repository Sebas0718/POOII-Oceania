package com.mycompany.oceanica.Server;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.mycompany.oceanica.Modelos.ComandoMensaje;
import com.mycompany.oceanica.Threads.ThreadServer;
import com.mycompany.oceanica.Modelos.Comando;
import com.mycompany.oceanica.Modelos.ComandoVictoria;


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
        if (!jugadores.contains(jugador) && jugadoresPersonajesCompletos()) {
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
                server.getRefPantalla().writeMessage("No se puede iniciar, no todos los jugadores han creado todos los personajes");
                return false;
            }
        }
        return true;
    }
    


    //#######################################################################################

    public void siguienteTurno() {
        if (!juegoActivo)
            return;
        if (jugadores.get(jugadorActual).getHaPerdido()) {
            derrota(jugadores.get(jugadorActual));
            
            if (jugadores.size() == 1) {
                ganador(jugadores.getFirst());
                return;
            }
        }
        jugadorActual = (jugadorActual + 1) % jugadores.size();
        server.getRefPantalla().writeMessage("Turno de: " + getJugadorActual().getNombre());
    }
    
    public void derrota(ThreadServer jugadorPerdedor) {

        
        jugadores.remove(jugadorPerdedor);
        server.usuariosConectados.remove(jugadorPerdedor);
        

        if (jugadores.size() == 1) {
            ganador(jugadores.getFirst());
        } 
    }
    
    public void ganador(ThreadServer ganador) {
        this.juegoActivo = false;
        server.getRefPantalla().writeMessage("EL JUGADOR " + ganador.getNombre() + " HA GANADO LA PARTIDA");
        server.getRefPantalla().writeMessage("=================== FIN DE PARTIDA ========================");
        server.ejecutarComando(new ComandoVictoria(ganador.getNombre()));
    }



    //#######################################################################################

    public boolean isJuegoActivo() {
        return juegoActivo;
    }
    
    public void setIsJuegoActivo(boolean juego){
        this.juegoActivo = juego;
    }
    //#######################################################################################

    public ThreadServer getJugadorActual() {
        if (!juegoActivo || jugadores.isEmpty())
            return null;
        return jugadores.get(jugadorActual);
    }


    public ArrayList<ThreadServer> getJugadores() {
        return this.jugadores;
    }

}
