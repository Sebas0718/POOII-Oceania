/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Threads;

import com.mycompany.oceanica.Server.Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author xsusk
 */
public class ThreadServer extends Thread{
    private Server server;
    private Socket socket;
    private DataInputStream lector;
    private DataOutputStream escritor;
    
    private boolean isRunning = true;

    public ThreadServer(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
        try{
        escritor = new DataOutputStream(this.socket.getOutputStream());
        lector = new DataInputStream(this.socket.getInputStream());
        
        } catch(IOException ex){
            System.getLogger(Server.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    
    
    
    
    
}
