package com.mycompany.oceanica.Modelos;

import com.mycompany.oceanica.Threads.ThreadServer;
import com.mycompany.oceanica.Usuario.Usuario;

public class ComandoVictoria extends Comando{

    public ComandoVictoria(String nombreUsuario) {
        super(TiposComandos.VICTORIA,new String[0],nombreUsuario );
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        setInfo(true);
        setIsBroadcast(true);
    }

     @Override
    public void procesoEnUsuario(Usuario usuario) {
        usuario.getInterfazPrincipal().writeMessage("El jugador " + usuario.getNombre() + " ha ganado la partida",
                null);
        usuario.setIsGameOver(true);
        usuario.getInterfazPrincipal().limpiarInterfaz();
        usuario.getInterfazPrincipal().getTxaHistorial().setText("HAZ GANADO LA PARTIDA");
    }
}
