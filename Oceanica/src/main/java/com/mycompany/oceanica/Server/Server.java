/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Server;

import com.mycompany.oceanica.Modelos.Comando;
import com.mycompany.oceanica.Modelos.TiposComandos;
import com.mycompany.oceanica.Threads.ThreadConexiones;
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
    
    private final int PORT = 54321;
    ServerSocket server;
    Socket socketUsuarios; //El socket de los usuarios
    ArrayList<ThreadServer> usuariosConectados = new ArrayList<ThreadServer>();
    private final int maxConexiones = 4;
    private PantallaServer refPantalla;
    private ThreadConexiones connexionesThread;
    
    public Server(PantallaServer refPantalla) {
        usuariosConectados = new ArrayList<ThreadServer>();
        this.refPantalla = refPantalla;
        this.init();
        connexionesThread = new ThreadConexiones(this);
        connexionesThread.start();
    }
    
    private void init(){
        try{
            server = new ServerSocket(PORT);
            refPantalla.writeMessage("El servidor esta corriendo");
        } catch(IOException ex){
            refPantalla.writeMessage("Error: " + ex.getMessage());
        }
    }
    
    public void conectarServer(){
        
        try {
            server = new ServerSocket(PORT);
        } catch (IOException ex) {
            System.getLogger(Server.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public void ejecutarComando(Comando comando) {
        if (comando.isInfo())
            this.comandInfo(comando);
        
        else if (comando.isIsBroadcast())
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
    
    public void comandInfo(Comando comando){
        if (comando.getParametros().length <= 1)
            return;
        String nombre = comando.getNombre();
        for (ThreadServer usuario : usuariosConectados) {
            if (usuario.getNombre().equals(nombre)){
                try {
                //simulo enviar solo al primero, pero debe buscarse por nombre
                    usuario.getObjetoEscritor().writeObject(comando);
                    break;
                } catch (IOException ex) {
                
                }
            }
        }
    }
    
    
    public void sendPrivate(Comando comando){
        if (comando.getParametros().length <= 1)
            return;
        
        String searchName =  comando.getParametros()[1];
        
        for (ThreadServer usuario : usuariosConectados) {
            if (usuario.getNombre().equals(searchName)){
                try {
                    usuario.getObjetoEscritor().writeObject(comando);
                    if (comando.getTipo().equals(TiposComandos.RENDIRSE)){
                        usuario.setIsActive(false);
                        usuariosConectados.remove(usuario);
                    }
                    break;
                } catch (IOException ex) {
                
                }
            }
        }
    }
    
    public void showAllNames(){
        this.refPantalla.writeMessage("Usuarios conectados");
        for (ThreadServer client : usuariosConectados) {
            this.refPantalla.writeMessage(client.getNombre());
        }
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



    public ArrayList<ThreadServer> getUsuariosConectados() {
        return usuariosConectados;
    }

    public int getMaxConexiones() {
        return maxConexiones;
    }
    
    
}
