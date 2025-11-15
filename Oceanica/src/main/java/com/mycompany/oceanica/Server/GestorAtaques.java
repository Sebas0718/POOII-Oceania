package com.mycompany.oceanica.Server;

import java.util.ArrayList;

import com.mycompany.Interfaz.InterfazPrincipal;
import com.mycompany.Personaje.TipoPersonaje;
import com.mycompany.TipoAtaques.ReleaseTheKraken;
import com.mycompany.TipoAtaques.TipoAtaque;
import com.mycompany.oceanica.Modelos.ComandoAtaque;
import com.mycompany.oceanica.Threads.ThreadServer;
import com.mycompany.oceanica.Usuario.Usuario;



public class GestorAtaques {

    private Server server;   


    public GestorAtaques(Server server){
        this.server = server;

    }

    public void atacarUsuario(ComandoAtaque comando, ThreadServer threadAtacante, ThreadServer threadVictima){


        try {
            
            String nombreAtaque = comando.getParametros()[2].toUpperCase();
            System.out.println(nombreAtaque);          
              
        switch (nombreAtaque){
            case "SWIRL_RAISING":
                
                break;

            case "SEND_HUMAN_GARBAGE":
                break;
                
            case "RADIOACTIVE_RUSH":
                break;    

            case "CARDUMEN":
                break;    

            case "SHARK_ATTACK":
                break;    
            case "PULP":
                break;    

            case "THREE_LINES":
                break;
            case "THREE_NUMBERS": 
                break;
            case "CONTROL_THE_KRAKEN":
                break;
            case "TENTACULOS":
                break;
            case "KRAKEN_BREATH":
                break;
            case "RELEASE_THE_KRAKEN":
                // ReleaseTheKraken.ataqueReleaseTheKraken(threadAtacante);

            case "VOLCANO_RAISING":
                break;
            case "VOLCANO_EXPLOSION":
                break;
            case "TERMAL_RUSH":
                break;
        }

        } catch (Exception e) {
            System.out.println("E150: No se ha podido atacar al usuario");
        }
        threadAtacante.getServer().getRefPantalla().writeMessage("Usuario: " + threadAtacante.getNombre() + " ha atacado a " + threadVictima.getNombre());

    }

    
    public ThreadServer buscarUsuario(String nombre){
        
        for (ThreadServer thread: server.getThreadsConectados()){
            System.out.println(thread.getNombre());
            if (thread.getNombre().equals(nombre)){
                return thread;
            }
        }
        return null;
    }


    public void actualizarPantalla(Usuario usuario) {
        usuario.getInterfazPrincipal().validate();
        usuario.getInterfazPrincipal().repaint();
    }




}
