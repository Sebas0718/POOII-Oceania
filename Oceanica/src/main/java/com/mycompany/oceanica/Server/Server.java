/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Server;

import com.mycompany.oceanica.Modelos.Comando;
import com.mycompany.oceanica.Threads.ThreadServer;
import com.mycompany.oceanica.Usuario.Usuario;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author xsusk
 */
public class Server {
    private final int PORT = 12345;
    ServerSocket server;
    Socket socketUsuarios; //El socket de los usuarios
    DataOutputStream escritorAlUsuario;
    DataInputStream lectorDelUsuario;
    ArrayList<ThreadServer> usuariosConectados = new ArrayList<ThreadServer>();
    private final int maxConexiones = 4;
    private PantallaServer refPantalla;
    public Server() {
        
    }
    
    public void conectarServer(){
        
        try {
            server = new ServerSocket(PORT);
        } catch (IOException ex) {
            System.getLogger(Server.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public void recibirConexion(){
        System.out.println("Esperando conexion ...");
        try {
            socketUsuarios = server.accept();
            System.out.println("Se conecto alguien");
            escritorAlUsuario = new DataOutputStream(socketUsuarios.getOutputStream());
            lectorDelUsuario = new DataInputStream(socketUsuarios.getInputStream());
            escritorAlUsuario.writeUTF("Crotolamo");
            escritorAlUsuario.writeInt(506);
            System.out.println("Funciono");
            
            
            
            //Esto es para pruebas se debe borrar mas adelante
            int i = 0;
            while (maxConexiones > i){
                System.out.println("Esperando mensaje ...");
                String recibido = lectorDelUsuario.readUTF();
                System.out.println("Mensaje Recibido :    " + recibido);
                i++;
            }
            
            System.out.println("Terminado el server");
            
        } catch (IOException ex) {
            System.getLogger(Server.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public void ejecutarComando(Comando comando) {
        if (comando.isIsBroadcast())
            this.broadcast(comando);
        else
            this.sendPrivate(comando);

    }
    
    public void broadcast(Comando comando){
        for (ThreadServer usuario : usuariosConectados) {
            try {
                usuario.getObjetoEscritor().writeObject(comando);
            } catch (IOException ex) {
                
            }
        }

    }
    
    public void sendPrivate(Comando comando){
        //asumo que el nombre del cliente viene en la posición 1 .  private_message Andres "Hola"
        if (comando.getParametros().length <= 1)
            return;
        
        String searchName =  comando.getParametros()[1];
        
        for (ThreadServer usuario : usuariosConectados) {
            if (usuario.name.equals(searchName)){
                try {
                //simulo enviar solo al primero, pero debe buscarse por nombre
                    usuario.getObjetoEscritor().writeObject(comando);
                    break;
                } catch (IOException ex) {
                
                }
            }
        }
    }
    
    public void writeMessage(String msg){
        this.refPantalla.getTxaMensajes().append(msg);
    }
    
    
    public static void main(String[] args) {
        Server s = new Server();
        s.conectarServer();
        s.recibirConexion();
    }

    public PantallaServer getRefPantalla() {
        return refPantalla;
    }

    public void setRefPantalla(PantallaServer refPantalla) {
        this.refPantalla = refPantalla;
    }

    public int getPORT() {
        return PORT;
    }

    public ServerSocket getServer() {
        return server;
    }

    public Socket getSocketUsuarios() {
        return socketUsuarios;
    }

    public DataOutputStream getEscritorAlUsuario() {
        return escritorAlUsuario;
    }

    public DataInputStream getLectorDelUsuario() {
        return lectorDelUsuario;
    }

    public ArrayList<ThreadServer> getUsuariosConectados() {
        return usuariosConectados;
    }

    public int getMaxConexiones() {
        return maxConexiones;
    }
    
    
}
