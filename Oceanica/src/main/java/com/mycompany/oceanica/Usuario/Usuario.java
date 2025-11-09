/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Usuario;

import com.mycompany.Interfaz.InterfazPrincipal;
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
    
    private final int PORT = 54321;
    private final String SERVER_IP = "localhost";
    private Socket socket;
    private ObjectInputStream objetoLector;
    private ObjectOutputStream objetoEscritor;
    private ThreadUsuario threadUsuario;
    private String nombre;

    private boolean haPerdido = false;
    private InterfazPrincipal interfazPrincipal;

    
    public Usuario(InterfazPrincipal interfazPrincipal, String nombre) {
        this.interfazPrincipal = interfazPrincipal;
        this.nombre = nombre;
        this.conectar();
    }
    
    public void conectar(){
        try {
            socket = new Socket(SERVER_IP, PORT);
            objetoEscritor = new ObjectOutputStream(socket.getOutputStream());
            objetoEscritor.flush();
            objetoLector = new ObjectInputStream(socket.getInputStream());
            
            threadUsuario = new ThreadUsuario(this, interfazPrincipal);
            threadUsuario.start();
             
            
            String args[] = {"NOMBRE",this.nombre};
            objetoEscritor.writeObject(ComandoFabrica.getComando(args, this.nombre));

            
            
        } catch (IOException ex) {
            System.getLogger(Usuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
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

    public String getNombre() {
        return nombre;
    }

    public boolean getHaPerdido() {
        return haPerdido;
    }

    public void setHaPerdido(boolean bool) {
        this.haPerdido = bool;
    }



    public InterfazPrincipal getInterfazPrincipal() {
        return interfazPrincipal;
    }

    public void setInterfazPrincipal(InterfazPrincipal interfazPrincipal) {
        this.interfazPrincipal = interfazPrincipal;
    }

}
    
