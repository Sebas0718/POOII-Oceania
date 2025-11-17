package com.mycompany.oceanica.Modelos;

import com.mycompany.oceanica.Threads.ThreadServer;
import com.mycompany.oceanica.Usuario.Usuario;

public class ComandoDerrota extends Comando {

    public ComandoDerrota(String nombreUsuario) {
        super(TiposComandos.DERROTA, new String[0], nombreUsuario);
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setInfo(false);
        this.setIsBroadcast(true);
        

    }

    @Override
    public void procesoEnUsuario(Usuario usuario) {
        usuario.getInterfazPrincipal().writeMessage("El usuario " + usuario.getNombre() + " ha perdido", this);
    }

    




}
