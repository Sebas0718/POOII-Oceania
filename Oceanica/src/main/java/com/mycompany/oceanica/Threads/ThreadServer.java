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
                
                switch(comando.getTipo()){
                    case ATTACK:
                        server.writeMessage("Se envio un ataque");
                        comando.process();
                        break;
                    case MESSAGE:
                        server.writeMessage("Se envio un mensaje");
                        comando.process();
                        break;
                    case PRIVATE_MESSAGE:
                        server.writeMessage("Se envio un mensaje privado");
                        comando.process();
                        break;
                    case GIVE_UP:
                        server.writeMessage("Se envio una rendicion");
                        comando.process();
                        break;
                    default:
                        throw new AssertionError();
                }
                
            } catch(IOException ex){
            
            } catch (ClassNotFoundException ex) {
                System.getLogger(ThreadServer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
    
    
}
