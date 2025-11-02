/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Usuario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author xsusk
 */
public class Usuario {
    
    private PantallaUsuario refPantalla;
    private final String SERVER_IP = "localhost";
    Socket socket;
    private final int PORT = 12345;
    DataInputStream lector;
    DataOutputStream escritor;
    
    
    public Usuario() {
    }

    public PantallaUsuario getRefPantalla() {
        return refPantalla;
    }

    public void setRefPantalla(PantallaUsuario refPantalla) {
        this.refPantalla = refPantalla;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    
    public void conectar(){
        try {
            this.socket = new Socket(SERVER_IP, PORT);
            this.lector = new DataInputStream(socket.getInputStream()); 
            String mensaje = lector.readUTF();
            int numMensaje = lector.readInt();
            refPantalla.getTxaMessages().append("Recibido " + mensaje + "\n");
            refPantalla.getTxaMessages().append("Recibido " + numMensaje + "\n");
            
        } catch (IOException ex) {
            System.getLogger(Usuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}
