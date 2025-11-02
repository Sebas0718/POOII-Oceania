/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Threads;

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
        String receivedMessage = "";
        
        while(isRunning){
            try{
                receivedMessage = usuario.getLector().readUTF();
                usuario.receivedMesagge(receivedMessage);
                
                
                
            }catch(IOException ex){
            
            }
        }
    }
}
