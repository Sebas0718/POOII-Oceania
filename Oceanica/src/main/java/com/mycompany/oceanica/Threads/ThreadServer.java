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
public class ThreadServer extends Thread{
    private Server server;
    private Socket socket;
    
    private ObjectInputStream objetoLector;
    private ObjectOutputStream objetoEscritor;
    

    
    private boolean isRunning = true;

    public ThreadServer(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
        try{
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
                
                server.writeMessage("ThreadServer recibio: " + comando);
                comando.procesoPorServer(this);
                server.ejecutarComando(comando);
                
            } catch(IOException ex){
            
            } catch (ClassNotFoundException ex) {
                System.getLogger(ThreadServer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
    
    
}
