package com.mycompany.oceanica.Modelos;

import com.mycompany.oceanica.Threads.ThreadServer;
import com.mycompany.oceanica.Usuario.Usuario;

public class ComandoDerrota extends Comando {

    public ComandoDerrota(String nombreUsuario) {
        super(TiposComandos.DERROTA, new String[0], nombreUsuario);
    }

    @Override
    public void procesoPorServer(ThreadServer threadServidor) {
        this.setInfo(true);
        this.setIsBroadcast(false);
        threadServidor.getServer().getRefPantalla()
                .writeMessage("El jugador " + threadServidor.getNombre() + " ha perdido");
    }
    
    @Override
    public void procesoEnUsuario(Usuario usuario) {
        usuario.getInterfazPrincipal().limpiarInterfaz();
        usuario.getInterfazPrincipal().getTxaHistorial().setText("El usuario " + usuario.getNombre() + " ha perdido\n");
    }

    




}
