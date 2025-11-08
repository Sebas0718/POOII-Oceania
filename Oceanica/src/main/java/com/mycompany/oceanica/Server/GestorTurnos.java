package com.mycompany.oceanica.Server;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.mycompany.oceanica.Modelos.ComandoListo;
import com.mycompany.oceanica.Threads.ThreadServer;
import com.mycompany.oceanica.Modelos.Comando;


public class GestorTurnos {

    private Server server;
    private ArrayList<ThreadServer> jugadores;
    
    private static final int TIEMPO_TURNO = 45;
    private Timer timerTurno;
    private int tiempoRestante;

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
            server.getRefPantalla().actualizarEstadoJuego("En progreso");
            notificarInicioJuego();
            iniciarTurno();
        } else {
            server.getRefPantalla().actualizarEstadoJuego("Esperando jugadores");
        }
    }


    private void iniciarTurno() {
        if (!juegoActivo) {
            finalizarJuego();
            return;
        }
        if (timerTurno != null) {
            timerTurno.cancel();
        }

        ThreadServer jugador = jugadores.get(jugadorActual);
        server.getRefPantalla().actualizarTurnoActual(jugador.getNombre());
        notificarTurno(jugador);
        inicializarTimer();
    }


    public void inicializarTimer(){
        tiempoRestante = TIEMPO_TURNO;
        timerTurno = new Timer();
        timerTurno.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tiempoRestante--;
                server.getRefPantalla().actualizarTiempoRestante(tiempoRestante);
                if (tiempoRestante <= 0) {
                    timeoutTurno();
                }
            }
        }, 0, 5000);
    }

    private void timeoutTurno() {
        ThreadServer jugador = jugadores.get(jugadorActual);
        String[] args = { "TIMEOUT", "Se acabo el tiempo del turno de " + jugador.getNombre() };
        Comando comandoTimeout = new ComandoListo(args, "Server");
        server.broadcast(comandoTimeout);
        siguienteTurno(); 
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

        if (timerTurno != null) {
            timerTurno.cancel();
        }

        do {
            jugadorActual = (jugadorActual + 1) % jugadores.size();
        } while (jugadores.get(jugadorActual).getHaPerdido() && hayJugadoresActivos());

        if (!hayJugadoresActivos()) {
            finalizarJuego();
            return;
        }
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

        if (timerTurno != null){
            timerTurno.cancel();
        }

        juegoActivo = false;

        ThreadServer ganador = null;
        for (ThreadServer jugador : jugadores) {
            if (!jugador.getHaPerdido()) {
                ganador = jugador;
                break;
            }
        }

        String mensaje = ganador != null ?
        "El juego ha terminado! " + ganador.getNombre() + " es el ganador" : 
                "El juego ha terminado en empate";

        String[] args = { "FIN", mensaje };
        Comando comandoFin = new ComandoListo(args, "Server");
        server.broadcast(comandoFin);

        server.getRefPantalla().actualizarEstadoJuego("Juego terminado");
        server.getRefPantalla().actualizarTurnoActual("-");
        server.getRefPantalla().actualizarTiempoRestante(0);
    } 


    public boolean isJuegoActivo() {
        return juegoActivo;
    }

    public ThreadServer getJugadorActual() {
        return jugadores.get(jugadorActual);
    }

    public boolean hayJugadoresActivos() {
        for (ThreadServer jugador : jugadores) {
            if (!jugador.getHaPerdido()) {
                return true;
            }
        }
        return false;
    }




}
