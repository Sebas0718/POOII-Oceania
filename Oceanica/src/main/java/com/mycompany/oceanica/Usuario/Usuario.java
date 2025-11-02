/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Usuario;

import com.mycompany.oceanica.Modelos.ComandoFabrica;
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
    private final int PORT = 12345;
    private final String SERVER_IP = "localhost";
    private Socket socket;
    
    
    private ObjectInputStream objetoLector;
    private ObjectOutputStream objetoEscritor;
    
    private int contador = 5; //Esta es para pruebas borrar luego
    private ThreadUsuario threadUsuario;
    
    private String name;

    public Usuario(PantallaUsuario refPantalla, String name) {
        this.refPantalla = refPantalla;
        this.name = name;
        this.conectar();
    }
    
    

    public void conectar(){
        try {
            socket = new Socket(SERVER_IP, PORT);
            objetoEscritor = new ObjectOutputStream(socket.getOutputStream());
            objetoEscritor.flush();
            objetoLector = new ObjectInputStream(socket.getInputStream());
            
            threadUsuario = new ThreadUsuario(this);
            threadUsuario.start();
             
            String args[] = {"NAME",this.name};
            objetoEscritor.writeObject(ComandoFabrica.getComando(args));
        } catch (IOException ex) {
            System.getLogger(Usuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
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
    
