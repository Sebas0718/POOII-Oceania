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
    private int contador = 5; //Esta es para pruebas borrar luego
    
    public Usuario() {
    }

    public DataInputStream getLector() {
        return lector;
    }

    public void setLector(DataInputStream lector) {
        this.lector = lector;
    }

    public DataOutputStream getEscritor() {
        return escritor;
    }

    public void setEscritor(DataOutputStream escritor) {
        this.escritor = escritor;
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
            this.escritor = new DataOutputStream(socket.getOutputStream()); 
            String mensaje = lector.readUTF();
            int numMensaje = lector.readInt();
            refPantalla.getTxaMessages().append("Recibido " + mensaje + "\n");
            refPantalla.getTxaMessages().append("Recibido " + numMensaje + "\n");
            
        } catch (IOException ex) {
            System.getLogger(Usuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }


//Por ahora es un metodo de pruebas, todavia no se si es necesario para la progra final    
    public void sendMessage(){
        if (this.contador-- > 0){
            String msg = refPantalla.getTxfMesagge().getText();
            try {
                escritor.writeUTF(msg);
                refPantalla.getTxaMessages().append("Enviado " + msg + "\n");
            } catch (IOException ex) {
                System.getLogger(Usuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
    
     public void receivedMesagge(String msg){
         refPantalla.getTxaMessages().append("Mensaje recibido:   " + msg + "\n");
    }
}
    
