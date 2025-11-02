/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Threads;

import com.mycompany.oceanica.Modelos.Comando;
import com.mycompany.oceanica.Usuario.Usuario;
import java.io.IOException;

/**
 *
 * @author xsusk
 */
public class ThreadUsuario extends Thread{
    private Usuario usuario;
    
    private boolean isRunning = true;

    public ThreadUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void run(){
        Comando comandoRecibido;
        
        
        while(isRunning){
            try{
                comandoRecibido = (Comando) usuario.getObjetoLector().readObject();
                comandoRecibido.procesoEnUsuario(usuario);
                
            }catch(IOException ex){
            
            } catch (ClassNotFoundException ex) {
                System.getLogger(ThreadUsuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
}
