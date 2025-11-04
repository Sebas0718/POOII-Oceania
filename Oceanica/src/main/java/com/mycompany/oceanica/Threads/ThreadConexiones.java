/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Threads;

import com.mycompany.oceanica.Server.Server;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author xsusk
 */
public class ThreadConexiones extends Thread {
    private Server server;

    public ThreadConexiones(Server server) {
        this.server = server;
    } 
    
    @Override
    public void run(){
        Socket newSocket = null;
        while( server.getUsuariosConectados().size() < server.getMaxConexiones()){
            try{
                newSocket = server.getServer().accept();
                ThreadServer newServerThread = new ThreadServer(server,newSocket);
                server.getUsuariosConectados().add(newServerThread);
                newServerThread.start();
                server.getRefPantalla().writeMessage("cliente conectado");
                this.server.getRefPantalla().getLblJugadoresConectados().setText(server.getUsuariosConectados().size() + "/4 Jugadores Conectados");
            } catch (IOException ex) {
                System.getLogger(ThreadConexiones.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                server.getRefPantalla().writeMessage("Error: " + ex.getMessage());
            }
        } 
        
    }
    
}
