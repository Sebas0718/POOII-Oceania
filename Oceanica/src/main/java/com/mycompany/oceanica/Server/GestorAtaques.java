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
    private ArrayList<Usuario> usuarios;
    private ArrayList<ThreadServer> threadServers; 

    public GestorAtaques(Server server){
        this.server = server;
        this.threadServers = server.getThreadsConectados();
        this.usuarios = server.getUsuarios();
    }

    public void atacarUsuario(ComandoAtaque comando, Usuario usuarioAtacante, Usuario usuarioVictima){
        
        InterfazPrincipal interfazVictima = usuarioVictima.getInterfazPrincipal(); 

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
                System.out.println("TS10: LLEGO HASTA ATACAR CON EL KRAKEN");
                ReleaseTheKraken.ataqueReleaseTheKraken(usuarioAtacante, interfazVictima);

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
        usuarioAtacante.getInterfazPrincipal().writeMessage("Se ha atacado con exito", comando);

    }

    





    public Usuario buscarUsuario(String nombre){
        for (Usuario usuario: usuarios){
            if (usuario.getNombre().equals(nombre)){
                return usuario;
            }
        }
        return null;
    }


    public void actualizarPantalla(Usuario usuario) {
        usuario.getInterfazPrincipal().validate();
        usuario.getInterfazPrincipal().repaint();
    }




}
