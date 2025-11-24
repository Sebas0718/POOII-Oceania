package com.mycompany.oceanica.Modelos;

import com.mycompany.oceanica.Threads.ThreadServer;
import com.mycompany.oceanica.Usuario.Usuario;

public class ComandoTurno extends Comando {

    public ComandoTurno(String nombreUsuario) {
        super(TiposComandos.TURNO,new String[0], nombreUsuario);
        
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setInfo(false);
        this.setIsBroadcast(true);
    }

    
     @Override
    public void procesoEnUsuario(Usuario usuario) {
        usuario.getInterfazPrincipal().getTxaBitacora()
                .append("Es el turno de " + this.getNombreUsuario() + "\n");
    }



}
