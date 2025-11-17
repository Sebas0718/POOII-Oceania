/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.TipoAtaques;

import com.mycompany.Interfaz.Celda;
import com.mycompany.Interfaz.InterfazPrincipal;
import com.mycompany.Personaje.Personaje;
import com.mycompany.Personaje.TipoPersonaje;
import com.mycompany.oceanica.Modelos.ComandoAtaque;
import com.mycompany.oceanica.Modelos.ComandoAtaqueValidacion;
import com.mycompany.oceanica.Modelos.ComandoResultadoAtaque;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author seb
 */
public class ThundersUnderTheSea extends Personaje {
    
    private String[] ataques = new String[3];
    
    public ThundersUnderTheSea() {
        super(TipoPersonaje.THUNDERS_UTS);
        this.ataques[0] = "THUNDER_RAIN";
        this.ataques[1] = "POSEIDON_THUNDERS";
        this.ataques[2] = "EEL_ATTACK";
    }
    
    public ComandoResultadoAtaque ataqueThunderRain(InterfazPrincipal interfaz, ComandoAtaque comando){
        Random rand = new Random();
        Celda[][] celdas = interfaz.getCeldas();
        List<String> mensajes = new ArrayList<>();
        int cantidadRayos = 100;
        interfaz.borrarMensajes();
        interfaz.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");
        interfaz.getUsuario().getResultadoAtaqueRecibido().add("Se recibio el ataque [THUNDER RAIN] del usuario " + comando.getNombreUsuario());
        
        
        for (int i = 0; i < cantidadRayos; i++) {

            // Coordenadas aleatorias
            int x = rand.nextInt(20);
            int y = rand.nextInt(20);

            // Daño entre 10% y 20%
            int daño = rand.nextInt(11) + 10;  // 10 a 20
            
            celdas[x][y].recibirAtaque(comando, daño, interfaz);
            String msg = "[THUNDER RAIN] Celda (" + x + "," + y +
                ") quedó con " + celdas[x][y].getVida() + " de vida.";
               mensajes.add(msg);
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
    
    public ComandoResultadoAtaque ataquePoseidonThunders(InterfazPrincipal interfaz, ComandoAtaque comando){
        Random rand = new Random();
        Celda[][] celdas = interfaz.getCeldas();
        List<String> mensajes = new ArrayList<>();
        // Cantidad de rayos: entre 5 y 10
        int cantidadRayos = rand.nextInt(6) + 5;
        interfaz.borrarMensajes();
        interfaz.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");
        interfaz.getUsuario().getResultadoAtaqueRecibido().add("Se recibio el ataque [POSEIDON THUNDERS] del usuario " + comando.getNombreUsuario());
        
        for (int r = 0; r < cantidadRayos; r++) {

            // Punto donde cae el rayo
            int x = rand.nextInt(20);
            int y = rand.nextInt(20);

            // Radio de destrucción entre 2 y 10
            int radio = rand.nextInt(9) + 2;

            // Aplicar daño en área (onda expansiva)
            for (int i = x - radio; i <= x + radio; i++) {
                for (int j = y - radio; j <= y + radio; j++) {

                    // Validar casilla
                    if (ComandoAtaqueValidacion.fueraDeAlcanceXY(i, j)) {

                        // Daño estándar de 100%
                        celdas[i][j].recibirAtaque(comando, 100, interfaz);
                        String msg = "[POSEIDON THUNDERS] Celda (" + i + "," + j +
                        ") quedó con " + celdas[i][j].getVida() + " de vida.";
                       mensajes.add(msg);
                    }
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
    
    public ComandoResultadoAtaque ataqueEelAtack(InterfazPrincipal interfaz, ComandoAtaque comando){
        System.out.println("ts7");
        Random rand = new Random();
        Celda[][] celdas = interfaz.getCeldas();
        List<String> mensajes = new ArrayList<>();
        // Cantidad de anguilas entre 25 y 100
        int cantidadAnguilas = rand.nextInt(76) + 25;
        interfaz.borrarMensajes();
        interfaz.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");
        interfaz.getUsuario().getResultadoAtaqueRecibido().add("Se recibio el ataque [EEL ATTACK] del usuario " + comando.getNombreUsuario());
        
        
        for (int k = 0; k < cantidadAnguilas; k++) {
            System.out.println("ts7");
            // Cada anguila ataca una casilla aleatoria
            int x = rand.nextInt(20);
            int y = rand.nextInt(20);

            // Descargas: entre 1 y 10 → cada una vale 10% de daño
            int descargas = rand.nextInt(10) + 1;
            int dannoTotal = descargas * 10; // 10% por descarga
            System.out.println("ts7");
            // Aplicar daño
            celdas[x][y].recibirAtaque(comando, dannoTotal, interfaz);
            String msg = "[EEL ATTACK] Celda (" + x + "," + y +
                ") quedó con " + celdas[x][y].getVida() + " de vida.";
               mensajes.add(msg);
        }
        String[] resultadoArray = new String[mensajes.size() + 2];
        System.out.println("ts7");
        resultadoArray[0] = "RESULTADO_ATAQUE";
        resultadoArray[1] = comando.getNombreUsuario();  

        for (int i = 0; i < mensajes.size(); i++) {
            resultadoArray[i + 2] = mensajes.get(i);
        }
        System.out.println("ts7");
        // Entregamos el comando directamente
        return new ComandoResultadoAtaque(resultadoArray, interfaz.getUsuario().getNombre(), true);
            
    }
    
    public String[] getAtaques() {
        return ataques;
    }

    

    @Override
    public ComandoResultadoAtaque realizarAtaque(ComandoAtaque comando, InterfazPrincipal interfaz) {
        String[] args = comando.getParametros();
        ComandoResultadoAtaque result = null;
        for (String ataque : this.ataques){
            if (ataque.equals(args[3].toUpperCase())){
                switch(ataque){
                    case "THUNDER_RAIN":
                        result = ataqueThunderRain(interfaz,comando);
                        return result;
                    case "POSEIDON_THUNDERS":
                        result = ataquePoseidonThunders(interfaz, comando);
                        return result;
                    case "EEL_ATTACK":
                        result = ataqueEelAtack(interfaz, comando);
                        System.out.println(result);
                        return result;
                }
            }
        }
        return result;
    }
    
}
