/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Usuario;

import com.mycompany.Interfaz.InterfazPrincipal;
import com.mycompany.Personaje.Personaje;
import com.mycompany.oceanica.Modelos.Comando;
import com.mycompany.oceanica.Modelos.ComandoAtaque;
import com.mycompany.oceanica.Modelos.ComandoFabrica;
import com.mycompany.oceanica.Modelos.ComandoResultadoAtaque;
import com.mycompany.oceanica.Threads.ThreadUsuario;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.oceanica.Modelos.ComandoDerrota;

/**
 *
 * @author xsusk
 */
public class Usuario{
    
    private final int PORT = 54321;
    private final String SERVER_IP = "localhost";
    private Socket socket;
    private ObjectInputStream objetoLector;
    private ObjectOutputStream objetoEscritor;
    private ThreadUsuario threadUsuario;
    private String nombre;

    
    private boolean haPerdido = false;
    private boolean isGameOver = false;
    private  InterfazPrincipal interfazPrincipal;
    
    private List<String> resultadoAtaqueRecibido = new ArrayList<>();
    private List<String> resultadoAtaqueEnviado = new ArrayList<>();
    private List<String> resultadosHistorialAtaques = new ArrayList<>();
    
    private int ataquesEnviados = 0;
    private int ataquesAtinados = 0;
    private int ataquesfallados = 0;
    
    private float defensa = 1;
    
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
            objetoEscritor.writeObject(ComandoFabrica.getComando(args, this.getNombre()));
            
            
            
        } catch (IOException ex) {
            System.getLogger(Usuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void recibirAtaque(Comando comando){
        ComandoAtaque comandoAtaque = (ComandoAtaque) comando;
        Personaje personaje = comandoAtaque.getPersonaje();
        ComandoResultadoAtaque comandoRecibido = personaje.realizarAtaque(comandoAtaque,  this.interfazPrincipal);
        enviarComando(comandoRecibido);
    }


    public void derrota() {
        this.isGameOver = true;
        this.haPerdido = true;
        enviarComando(new ComandoDerrota(this.nombre));
    }
    
    public void enviarComando(Comando c) {
        try {
            objetoEscritor.writeObject(c);
            objetoEscritor.flush();
        } catch (IOException e) { }
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

    public float getDefensa() {
        return defensa;
    }

    public void setDefensa(float defensa) {
        this.defensa = defensa;
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

    public List<String> getResultadoAtaqueRecibido() {
        return resultadoAtaqueRecibido;
    }

    public void setResultadoAtaqueRecibido(List<String> resultadoAtaqueRecibido) {
        this.resultadoAtaqueRecibido = resultadoAtaqueRecibido;
    }

    public List<String> getResultadoAtaqueEnviado() {
        return resultadoAtaqueEnviado;
    }

    public void setResultadoAtaqueEnviado(List<String> resultadoAtaqueEnviado) {
        this.resultadoAtaqueEnviado = resultadoAtaqueEnviado;
    }

    public List<String> getResultadosHistorialAtaques() {
        return resultadosHistorialAtaques;
    }

    public void setResultadosHistorialAtaques(List<String> resultadosHistorialAtaques) {
        this.resultadosHistorialAtaques = resultadosHistorialAtaques;
    }

    public int getAtaquesEnviados() {
        return ataquesEnviados;
    }

    public void setAtaquesEnviados(int ataquesEnviados) {
        this.ataquesEnviados = ataquesEnviados;
    }

    public int getAtaquesAtinados() {
        return ataquesAtinados;
    }

    public void setAtaquesAtinados(int ataquesAtinados) {
        this.ataquesAtinados = ataquesAtinados;
    }

    public int getAtaquesfallados() {
        return ataquesfallados;
    }

    public void setAtaquesfallados(int ataquesfallados) {
        this.ataquesfallados = ataquesfallados;
    }

    public ThreadUsuario getThreadUsuario() {
        return threadUsuario;
    }

    public void setThreadUsuario(ThreadUsuario threadUsuario) {
        this.threadUsuario = threadUsuario;
    }

    public boolean isIsGameOver() {
        return isGameOver;
    }

    public void setIsGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }
    
    

}
    
