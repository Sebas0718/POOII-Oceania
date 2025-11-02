/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author xsusk
 */
public class Server {
    private final int IP = 60191;
    ServerSocket server;
    Socket socketUsuarios; //El socket de los usuarios

    public Server() {
        
    }
    
    public void conectarServer(){
        
        try {
            server = new ServerSocket(IP);
        } catch (IOException ex) {
            System.getLogger(Server.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public void recibirConexion(){
        System.out.println("Esperando conexion ...");
        try {
            socketUsuarios = server.accept();
            System.out.println("Se conecto alguien");
        } catch (IOException ex) {
            System.getLogger(Server.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    public static void main(String[] args) {
        Server s = new Server();
        s.conectarServer();
        s.recibirConexion();
    }
}
