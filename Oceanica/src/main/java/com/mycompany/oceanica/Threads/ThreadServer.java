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
    
    private boolean isActive = true;
    
    private boolean isRunning = true;
    
    
    
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
                if(isActive) 
                    server.ejecutarComando(comando);
                
            } catch(IOException ex){
            
            } catch (ClassNotFoundException ex) {
                System.getLogger(ThreadServer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
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

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
