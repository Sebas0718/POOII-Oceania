/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Threads;

import com.mycompany.Interfaz.InterfazPrincipal;
import com.mycompany.oceanica.Modelos.Comando;
import com.mycompany.oceanica.Modelos.TiposComandos;
import com.mycompany.oceanica.Usuario.Usuario;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author xsusk
 */
public class ThreadUsuario extends Thread {
    
    private Usuario usuario;
    private InterfazPrincipal interfazPrincipal;
    
    private boolean isRunning = true;
    
    



    public ThreadUsuario(Usuario usuario, InterfazPrincipal interfazPrincipal) {
        this.usuario = usuario;
        this.interfazPrincipal = interfazPrincipal;
    }
    
    public void run() {
        
        Comando comandoRecibido;
        
        while(isRunning){
            try {
                System.out.println("ts1");
                comandoRecibido = (Comando) usuario.getObjetoLector().readObject();
                String[] args = comandoRecibido.getParametros();
                
                if (comandoRecibido.getTipo().equals(TiposComandos.ATAQUE) && this.usuario.getNombre().equals(args[1])){
                    usuario.recibirAtaque(comandoRecibido);
                }
                if (comandoRecibido.getTipo().equals(TiposComandos.CONSULTAR_ENEMIGO) && comandoRecibido.getParametros()[1].equals(this.usuario.getNombre())){ 
                    System.out.println(comandoRecibido.getParametros()[1]);
                    System.out.println(this.usuario.getNombre());
                    System.out.println("ts2");
                    usuario.enviarInfo(comandoRecibido);
                }
                System.out.println("tsERROR");
                comandoRecibido.procesoEnUsuario(usuario);
            } catch(IOException ex){
            
            } catch (ClassNotFoundException ex) {
                System.getLogger(ThreadUsuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
}
