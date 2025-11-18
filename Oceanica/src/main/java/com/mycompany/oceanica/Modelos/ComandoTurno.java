package com.mycompany.oceanica.Modelos;

import com.mycompany.oceanica.Threads.ThreadServer;
import com.mycompany.oceanica.Usuario.Usuario;

public class ComandoTurno extends Comando {

    public ComandoTurno(String nombreUsuario) {
        super(TiposComandos.TURNO,new String[0], nombreUsuario);
        
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {

    }

    
     @Override
    public void procesoEnUsuario(Usuario usuario) {
        usuario.getInterfazPrincipal().getTxaBitacora()
                .append("El jugador " + usuario.getNombre() + " ha ganado la partida");
    }



}
