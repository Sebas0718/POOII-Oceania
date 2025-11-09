/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Threads;

import com.mycompany.oceanica.Modelos.Comando;
import com.mycompany.oceanica.Server.Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author xsusk
 */
public class ThreadServer extends Thread {
    
    private Server server;
    private Socket socket;
    
    private ObjectInputStream objetoLector;
    private ObjectOutputStream objetoEscritor;
    
    private String nombre;
    
    private int personajesCreados = 0;

    private boolean isActive = true;
    private boolean haPerdido = false;
    private boolean isRunning = true;
    private boolean isReady = false; 
    
    
    public ThreadServer(Server server, Socket socket) {
        try{
            this.server = server;
            this.socket = socket;
            objetoEscritor = new ObjectOutputStream(socket.getOutputStream());
            objetoEscritor.flush();
            objetoLector = new ObjectInputStream(socket.getInputStream());
        } catch(IOException ex){
            System.getLogger(Server.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    

    public void run(){
        Comando comando;
        
        while(isRunning){
            try{
                comando = (Comando)objetoLector.readObject();
                server.getRefPantalla().writeMessage("ThreadServer recibio: " + comando);
                comando.procesoPorServer(this);
                server.ejecutarComando(comando);;
                
            } catch (IOException | ClassNotFoundException ex) {
                manejarDesconexion();
                break;

            } 
        }
    }

    public void manejarDesconexion() {
        
        server.getRefPantalla().writeMessage("Usuario" + nombre + " se ha desconectado");

        server.getUsuariosConectados().remove(this);
        
        server.getRefPantalla().getLblJugadoresConectados().setText(
            server.getUsuariosConectados().size() + "/4 Jugadores conectados"
        );

        if (server.getGestorTurnos().isJuegoActivo()) {
            this.setHaPerdido(true);
        }
        if (server.getGestorTurnos().getJugadorActual() == this) {
            server.getGestorTurnos().siguienteTurno();
        }

        try {
            isActive = false;
            isRunning = false;
            if (objetoEscritor!= null) objetoEscritor.close();
            if (objetoLector != null) objetoLector.close();
            if (socket != null) socket.close();
        } catch (Exception e) {
            server.getRefPantalla().writeMessage("Error al cerrar la conexión del jugador " +  nombre);
        }


    }
    
    public void showAllClients (){
        this.server.showAllNames();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ObjectInputStream getObjetoLector() {
        return objetoLector;
    }

    public void setObjetoLector(ObjectInputStream objetoLector) {
        this.objetoLector = objetoLector;
    }

    public ObjectOutputStream getObjetoEscritor() {
        return objetoEscritor;
    }

    public void setObjetoEscritor(ObjectOutputStream objetoEscritor) {
        this.objetoEscritor = objetoEscritor;
    }

    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public boolean getHaPerdido() {
        return haPerdido;
    }

    public void setHaPerdido(boolean bool) {
        this.haPerdido = bool;
    }


    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void setIsReady(boolean bool) {
        this.isReady = bool;
    }

    public boolean getIsReady() {
        return this.isReady;
    }

    public int getPersonajesCreados() {
        return this.personajesCreados;
    }

    public void aumentarPersonajesCreados() {
        if (this.personajesCreados < 3) {
            this.personajesCreados++;
            return;
        }
    }



}
