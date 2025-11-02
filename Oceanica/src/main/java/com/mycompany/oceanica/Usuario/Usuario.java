/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Usuario;

import com.mycompany.oceanica.Threads.ThreadUsuario;
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
public class Usuario {
    
    private PantallaUsuario refPantalla;
    private final String SERVER_IP = "localhost";
    private Socket socket;
    
    
    private ObjectInputStream objetoLector;
    private ObjectOutputStream objetoEscritor;
    
    
    private final int PORT = 12345;
    private DataInputStream lector;
    private DataOutputStream escritor;
    private int contador = 5; //Esta es para pruebas borrar luego
    private ThreadUsuario threadUsuario;
    
    public Usuario() {
    }

    public void conectar(){
        try {
            socket = new Socket(SERVER_IP, PORT);
            objetoEscritor = new ObjectOutputStream(socket.getOutputStream());
            objetoLector = new ObjectInputStream(socket.getInputStream());
            
            threadUsuario = new ThreadUsuario(this);
            threadUsuario.start();
            
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
    
    
}
    
