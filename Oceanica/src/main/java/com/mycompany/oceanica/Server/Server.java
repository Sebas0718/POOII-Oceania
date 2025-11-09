/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Server;

import com.mycompany.oceanica.Modelos.Comando;
import com.mycompany.oceanica.Modelos.ComandoUsuarios;
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
import java.util.List;

/**
 *
 * @author xsusk
 */
public class Server {

    private final int PORT = 54321;
    ServerSocket server;
    Socket socketUsuarios; //El socket de los usuarios
    ArrayList<ThreadServer> usuariosConectados = new ArrayList<ThreadServer>();
    ArrayList<Usuario> arrayUsuarios = new ArrayList<Usuario>();
    private final int maxConexiones = 4;
    private PantallaServer refPantalla;
    private ThreadConexiones connexionesThread;

    private GestorTurnos gestorTurnos;

    public Server(PantallaServer refPantalla) {
        usuariosConectados = new ArrayList<ThreadServer>();
        this.refPantalla = refPantalla;
        this.gestorTurnos = new GestorTurnos(this);
        this.init();
        connexionesThread = new ThreadConexiones(this);
        connexionesThread.start();
    }

    private void init() {
        try {
            server = new ServerSocket(PORT);
            refPantalla.writeMessage("El servidor esta corriendo");
        } catch (IOException ex) {
            refPantalla.writeMessage("Error: " + ex.getMessage());
        }
    }

    public void conectarServer() {

        try {
            server = new ServerSocket(PORT);
        } catch (IOException ex) {
            System.getLogger(Server.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void ejecutarComando(Comando comando) {


        if (gestorTurnos.isJuegoActivo()) {
            ThreadServer jugadorActual = gestorTurnos.getJugadorActual();
            gestorTurnos.procesarComando(comando, jugadorActual);
        } else {
            if (comando.isInfo()){
            this.comandInfo(comando);
            }
            else if (comando.isIsBroadcast()) {
                this.broadcast(comando);
            } else {
                this.sendPrivate(comando);
            }
        }
    }

    public void broadcast(Comando comando) {
        for (ThreadServer usuario : usuariosConectados) {
            try {
                usuario.getObjetoEscritor().writeObject(comando);
            } catch (IOException ex) {

            }
        }

    }

    public void sendPrivate(Comando comando) {
        //asumo que el nombre del cliente viene en la posición 1 .  private_message Andres "Hola"
            } catch (IOException ex) {   
            }
        }

    }
    
    public void comandInfo(Comando comando){
        if (comando.getParametros().length < 1)
            return;
        String nombre = comando.getNombre();
        for (ThreadServer usuario : usuariosConectados) {
            if (usuario.getNombre().equals(nombre)){
                try {
                     if (comando.getTipo().equals(TiposComandos.USUARIOS)) {
                    // Construir una lista con los nombres de todos los conectados
                    List<String> nombres = new ArrayList<>();
                    for (ThreadServer usuarioActual : usuariosConectados) {
                        nombres.add(usuarioActual.getNombre());
                    }

                    // Convertir la lista a un arreglo para el comando
                    String[] parametros = new String[nombres.size()];
                    parametros = nombres.toArray(parametros);

                    // Crear un nuevo comando con esa información
                    ComandoUsuarios respuesta = new ComandoUsuarios(parametros, nombre);

                    // Enviar la respuesta al cliente
                    usuario.getObjetoEscritor().writeObject(respuesta);
                    usuario.getObjetoEscritor().flush();
                } else {
                    // Enviar cualquier otro comando normalmente
                    usuario.getObjetoEscritor().writeObject(comando);
                    usuario.getObjetoEscritor().flush();
                }
                break;
                } catch (IOException ex) {
                
                }
            }
        }
    }
    
    
    public void sendPrivate(Comando comando){
        if (comando.getParametros().length <= 1)
            return;

        String searchName = comando.getParametros()[1];

        for (ThreadServer usuario : usuariosConectados) {
            if (usuario.getNombre().equals(searchName)) {
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

    public void showAllNames() {
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

    public GestorTurnos getGestorTurnos() {
        return this.gestorTurnos;
    }

}
