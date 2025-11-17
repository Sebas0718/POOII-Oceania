/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.TipoAtaques;


import java.util.Random;

import com.mycompany.Interfaz.Celda;
import com.mycompany.Interfaz.InterfazPrincipal;
import com.mycompany.Personaje.Personaje;
import com.mycompany.Personaje.TipoPersonaje;
import com.mycompany.oceanica.Modelos.Comando;
import com.mycompany.oceanica.Modelos.ComandoAtaque;
import com.mycompany.oceanica.Modelos.ComandoAtaqueValidacion;
import com.mycompany.oceanica.Modelos.ComandoResultadoAtaque;
import com.mycompany.oceanica.Usuario.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author seb
 */
public class ReleaseTheKraken extends Personaje {
    
    private String[] ataques = new String[3];
    
    public ReleaseTheKraken() {
        super(TipoPersonaje.RELEASE_THE_KRAKEN);
        this.ataques[0] = "TENTACULOS";
        this.ataques[1] = "KRAKEN_BREATH";
        this.ataques[2] = "RELEASE_THE_KRAKEN";
    }


        /*
         * Tentáculos: permite colocar en el
            tablero la aparción de 3 tentáculos
            que destruyen lo que esté en el
            radio de 1 casilla alrededor
         * 
         */
    public ComandoResultadoAtaque ataqueTentaculos(InterfazPrincipal interfazPrincipal, ComandoAtaque comando) {
            
        Celda[][] celdas = interfazPrincipal.getCeldas();
        List<String> mensajes = new ArrayList<>();
        String[] args = comando.getParametros();
        int tentaculox1 = Integer.parseInt(args[4]);
        int tentaculox2 = Integer.parseInt(args[6]);
        int tentaculox3 = Integer.parseInt(args[8]);
        int tentaculoy1 = Integer.parseInt(args[5]);
        int tentaculoy2 = Integer.parseInt(args[7]);
        int tentaculoy3 = Integer.parseInt(args[9]);
        interfazPrincipal.borrarMensajes();
        interfazPrincipal.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");
        interfazPrincipal.getUsuario().getResultadoAtaqueRecibido().add("Se recibio el ataque [TENTACULOS] del usuario " + comando.getNombreUsuario());
        
        aplicarAtaqueTentaculo(celdas, tentaculox1, tentaculoy1,interfazPrincipal, comando, mensajes);
        aplicarAtaqueTentaculo(celdas, tentaculox2, tentaculoy2,interfazPrincipal, comando, mensajes);
        aplicarAtaqueTentaculo(celdas, tentaculox3, tentaculoy3,interfazPrincipal, comando, mensajes);
        
        String[] resultadoArray = new String[mensajes.size() + 2];

        resultadoArray[0] = "RESULTADO_ATAQUE";
        resultadoArray[1] = comando.getNombreUsuario();  

        for (int i = 0; i < mensajes.size(); i++) {
            resultadoArray[i + 2] = mensajes.get(i);
        }

        // Entregamos el comando directamente
        return new ComandoResultadoAtaque(resultadoArray, interfazPrincipal.getUsuario().getNombre(), true);
    }
        
        
    public ComandoResultadoAtaque ataqueKrakenBreath(InterfazPrincipal interfaz,  ComandoAtaque comando){
        String[] args = comando.getParametros();
        int alientox = Integer.parseInt(args[4]);
        int alientoy = Integer.parseInt(args[5]);
        Random rand = new Random();
        int cantCasillasAtacadas = rand.nextInt(8) + 1;
        rand = new Random();
        int direccion = rand.nextInt(4) + 1;
        Celda[][] celdas = interfaz.getCeldas();
        List<String> mensajes = new ArrayList<>();
        interfaz.borrarMensajes();
        interfaz.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");
        interfaz.getUsuario().getResultadoAtaqueRecibido().add("Se recibio el ataque [KRAKEN BREATH] del usuario " + comando.getNombreUsuario());
        
        switch (direccion){
            case 1: //Abajo
                for (int i = 0; i < cantCasillasAtacadas; i++){
                    if (ComandoAtaqueValidacion.fueraDeAlcanceXY(alientox + i, alientoy)) {
                        celdas[alientox + i][alientoy].recibirAtaque(comando, 100, interfaz);
                        String msg = "[KRAKEN BREATH] Celda (" + (alientox + i) + "," + alientoy +
                        ") quedó con " + celdas[alientox + i][alientoy].getVida() + " de vida.";
                        mensajes.add(msg);
                    }
                }
                break;
            case 2: //Arriba
                for (int i = 0; i < cantCasillasAtacadas; i++){
                    if (ComandoAtaqueValidacion.fueraDeAlcanceXY(alientox - i, alientoy)) {
                        celdas[alientox - i][alientoy].recibirAtaque(comando, 100, interfaz);
                        String msg = "[KRAKEN BREATH] Celda (" + (alientox - i) + "," + alientoy +
                        ") quedó con " + celdas[alientox - i][alientoy].getVida() + " de vida.";
                        mensajes.add(msg);
                    }
                }
                break;
            case 3: //Derecha
                for (int i = 0; i < cantCasillasAtacadas; i++){
                    if (ComandoAtaqueValidacion.fueraDeAlcanceXY(alientox, alientoy + i)) {
                        celdas[alientox][alientoy + i].recibirAtaque(comando, 100, interfaz);
                        String msg = "[KRAKEN BREATH] Celda (" + alientox + "," + (alientoy + i) +
                        ") quedó con " + celdas[alientox][alientoy + i].getVida() + " de vida.";
                        mensajes.add(msg);
                    }
                }
                break;
            case 4: //Izquierda
                for (int i = 0; i < cantCasillasAtacadas; i++){
                    if (ComandoAtaqueValidacion.fueraDeAlcanceXY(alientox, alientoy - i)) {
                        celdas[alientox][alientoy - i].recibirAtaque(comando, 100, interfaz);
                        String msg = "[KRAKEN BREATH] Celda (" + alientox + "," + (alientoy - i) +
                        ") quedó con " + celdas[alientox][alientoy - i].getVida() + " de vida.";
                        mensajes.add(msg);
                    }
                }
                break;
        }
        String[] resultadoArray = new String[mensajes.size() + 2];

        resultadoArray[0] = "RESULTADO_ATAQUE";
        resultadoArray[1] = comando.getNombreUsuario();  

        for (int i = 0; i < mensajes.size(); i++) {
            resultadoArray[i + 2] = mensajes.get(i);
        }

        // Entregamos el comando directamente
        return new ComandoResultadoAtaque(resultadoArray, interfaz.getUsuario().getNombre(), true);
    }
    
    public ComandoResultadoAtaque ataqueReleaseTheKraken(InterfazPrincipal interfaz,  ComandoAtaque comando) {
    

        Random rand = new Random();
        
        Celda[][] celdas = interfaz.getCeldas();
        List<String> mensajes = new ArrayList<>();
        int rango = rand.nextInt(19) + 1;
        
        int fila = rand.nextInt(20);
        int columna = rand.nextInt(20);
        interfaz.borrarMensajes();
        interfaz.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");
        interfaz.getUsuario().getResultadoAtaqueRecibido().add("Se recibio el ataque [REALEASE THE KRAKEN] del usuario " + comando.getNombreUsuario());
        
        for (int i = fila - rango; i <= fila + rango; i++) {
            for (int j = columna - rango; j <= columna + rango; j++) {
                if (ComandoAtaqueValidacion.fueraDeAlcanceXY(i, j)) {
                celdas[i][j].recibirAtaque(comando,100, interfaz);
                String msg = "[REALEASE THE KRAKEN] Celda (" + i + "," + j +
                ") quedó con " + celdas[i][j].getVida() + " de vida.";
                mensajes.add(msg);
                }
            }
        }
        String[] resultadoArray = new String[mensajes.size() + 2];

        resultadoArray[0] = "RESULTADO_ATAQUE";
        resultadoArray[1] = comando.getNombreUsuario();  

        for (int i = 0; i < mensajes.size(); i++) {
            resultadoArray[i + 2] = mensajes.get(i);
        }

        // Entregamos el comando directamente
        return new ComandoResultadoAtaque(resultadoArray, interfaz.getUsuario().getNombre(), true);
            
    }

    public String[] getAtaques() {
        return ataques;
    }

    public void setAtaques(String[] ataques) {
        this.ataques = ataques;
    }

    private void aplicarAtaqueTentaculo(Celda[][] celdas, int x, int y, InterfazPrincipal interfaz, ComandoAtaque comando, List<String> mensajes) {
    for (int dx = -1; dx <= 1; dx++) {
        for (int dy = -1; dy <= 1; dy++) {
            int nx = x + dx;
            int ny = y + dy;

            if (ComandoAtaqueValidacion.fueraDeAlcanceXY(nx, ny)) {
                celdas[nx][ny].recibirAtaque(comando,100, interfaz);
                String msg = "[TENTACULOS] Celda (" + nx + "," + ny +
                ") quedó con " + celdas[nx][ny].getVida() + " de vida.";
               mensajes.add(msg);
            }
        }
    }
}

    @Override
    public ComandoResultadoAtaque realizarAtaque(ComandoAtaque comando, InterfazPrincipal interfaz) {
        String[] args = comando.getParametros();
        ComandoResultadoAtaque result = null;
        for (String ataque : this.ataques){
            if (ataque.equals(args[3].toUpperCase())){
                switch(ataque){
                    case "TENTACULOS":
                        result = ataqueTentaculos(interfaz,comando);
                        return result;
                    case "KRAKEN_BREATH":
                        result = ataqueKrakenBreath(interfaz, comando);
                        return result;
                    case "RELEASE_THE_KRAKEN":
                        result = ataqueReleaseTheKraken(interfaz, comando);
                        return result;
                }
            }
        }
        return result;
    }
    

}
