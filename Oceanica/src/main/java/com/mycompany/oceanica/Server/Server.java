/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Server;

import com.mycompany.oceanica.Modelos.Comando;
import com.mycompany.oceanica.Modelos.ComandoUsuarios;
import com.mycompany.oceanica.Modelos.TiposComandos;
import com.mycompany.oceanica.Server.GestorTurnos;
import com.mycompany.oceanica.Server.PantallaServer;
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

import com.mycompany.oceanica.Modelos.ComandoVictoria;

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
    private GestorAtaques gestorAtaques;

    public Server(PantallaServer refPantalla) {
        this.usuariosConectados = new ArrayList<ThreadServer>();
        this.refPantalla = refPantalla;
        this.gestorTurnos = new GestorTurnos(this);
        

        // Inicializar el servidor directamente aquí
        try {
            server = new ServerSocket(PORT);
            refPantalla.writeMessage("El servidor esta corriendo en puerto " + PORT);

            // Iniciar el thread de conexiones
            connexionesThread = new ThreadConexiones(this);
            connexionesThread.start();
        } catch (IOException ex) {
            refPantalla.writeMessage("Error iniciando servidor: " + ex.getMessage());
        }
        this.gestorAtaques = new GestorAtaques(this);
    }

    //#######################################################################################

    
    public void conectarServer() {
        try {
            if (server != null && !server.isClosed()) {
                server.close();
            }
            server = new ServerSocket(PORT);
            refPantalla.writeMessage("Servidor reconectado en puerto " + PORT);
        } catch (IOException ex) {
            refPantalla.writeMessage("Error reconectando servidor: " + ex.getMessage());
        }
    }


    //#######################################################################################

    public void ejecutarComando(Comando comando) {
        // Solo procesar NOMBRE si el usuario no tiene nombre
        if (comando.getTipo().equals(TiposComandos.NOMBRE)) {
            broadcast(comando);
            return;
        }

        // Para otros comandos, el usuario debe tener nombre
        ThreadServer jugador = buscarJugador(comando.getNombreUsuario());
        if (jugador == null) {
            refPantalla.writeMessage("Error: comando de usuario desconocido");
            return;
        }

        if (comando.getTipo().equals(TiposComandos.DERROTA)) {
            gestorTurnos.derrota(jugador);
            return;
        }
        if (comando.getTipo().equals(TiposComandos.VICTORIA)) {
            comandVictoria(comando);
            return;
        }

        // Comandos de juego
        if (gestorTurnos.isJuegoActivo()) {
            ThreadServer jugadorActual = gestorTurnos.getJugadorActual();

            // Validar turno para comandos que lo requieren
            if (requiresTurno(comando.getTipo()) && jugador != jugadorActual) {
                refPantalla.writeMessage("Error: no es el turno de " + jugador.getNombre());
                return;
            }

            // Procesar comando
            if (comando.isInfo()) {
                comandInfo(comando);
                return;
            } else if (comando.isIsBroadcast()) {
                broadcast(comando);
                return;
            } else {
                sendPrivate(comando);
                if (requiresTurno(comando.getTipo())) {
                    gestorTurnos.siguienteTurno();
                }
            }
        }
        // Si el juego no está activo, solo permitir comandos básicos
        else if (comando.getTipo() == TiposComandos.INICIAR) {
            gestorTurnos.iniciarJuego();
        } else if (comando.isIsBroadcast()) {
            broadcast(comando);
        } else if (comando.isInfo()) {
            comandInfo(comando);
        }
    }
    
    //#######################################################################################

    private ThreadServer buscarJugador(String nombre) {
        for (ThreadServer jugador : usuariosConectados) {
            if (jugador.getNombre() != null && jugador.getNombre().equals(nombre)) {
                return jugador;
            }
        }
        return null;
    }
//#######################################################################################

    private boolean requiresTurno(TiposComandos tipo) {
        return tipo == TiposComandos.ATAQUE ||
                tipo == TiposComandos.SALTAR;
    }

//#######################################################################################

    public void broadcast(Comando comando) {
        for (ThreadServer usuario : usuariosConectados) {
            try {
                usuario.getObjetoEscritor().writeObject(comando);
            } catch (IOException ex) {

            }
        }

    }
    //#######################################################################################
    
    public void comandInfo(Comando comando) {
        if (comando.getParametros().length < 1)
            return;
        String nombre = comando.getNombreUsuario();
        for (ThreadServer usuario : usuariosConectados) {
            if (usuario.getNombre().equals(nombre)) {
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
                        ComandoUsuarios respuesta = new ComandoUsuarios(parametros, comando.getNombreUsuario());

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


    public void comandVictoria(Comando comando) {
        try {
            comando = (ComandoVictoria) comando;
            gestorTurnos.getJugadores().getFirst().getObjetoEscritor().writeObject(comando);
        } catch (Exception e) {
        }

    }
    
    //#######################################################################################
    
    
    public void sendPrivate(Comando comando) {
        if (comando.getParametros().length <= 1)
            return;

        String searchName = comando.getParametros()[1];

        for (ThreadServer usuario : usuariosConectados) {
            if (usuario.getNombre().equals(searchName)) {
                try {
                    usuario.getObjetoEscritor().writeObject(comando);
                    if (comando.getTipo().equals(TiposComandos.RENDIRSE)) {
                        usuario.setIsActive(false);
                        usuariosConectados.remove(usuario);
                        gestorTurnos.getJugadores().remove(usuario);
                    }
                    break;
                } catch (IOException ex) {

                }
            }
        }
    }
    //#######################################################################################


    // public void anunciarGanador(ThreadServer ganador) { 
    
    //     ee
    
    // }


    
    
    //#######################################################################################

    public void showAllNames() {
        this.refPantalla.writeMessage("Usuarios conectados");
        for (ThreadServer client : usuariosConectados) {
            this.refPantalla.writeMessage(client.getNombre());
        }
    }


    //#######################################################################################

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

    public ArrayList<ThreadServer> getThreadsConectados() {
        return usuariosConectados;
    }

    public int getMaxConexiones() {
        return maxConexiones;
    }

    public GestorTurnos getGestorTurnos() {
        return this.gestorTurnos;
    }

    public ArrayList<Usuario> getUsuarios(){
        return this.arrayUsuarios;
    }
    public GestorAtaques getGestorAtaques(){
        return this.gestorAtaques;
    }
    


}
