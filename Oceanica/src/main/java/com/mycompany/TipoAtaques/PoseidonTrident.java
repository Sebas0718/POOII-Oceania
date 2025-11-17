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
public class PoseidonTrident extends Personaje{

    /* 
     * Three lines: selecciona 3 puntos en el mapa. En cada punto destruye lo que esté de 1 a 4
     * casillas a la derecha, izquierda, arriba, abajo (aleatorio)
    */
    
    private String[] ataques = new String[3];
    public PoseidonTrident() {
        super(TipoPersonaje.THE_TRIDDENT);
        this.ataques[0] = "THREE_LINES";
        this.ataques[1] = "THREE_NUMBERS";
        this.ataques[2] = "CONTROL_THE_KRAKEN";
    }
    
    public ComandoResultadoAtaque ataqueThreeLines(InterfazPrincipal interfaz, ComandoAtaque comando){
        Celda[][] celdas = interfaz.getCeldas();
        String[] args = comando.getParametros();
        List<String> mensajes = new ArrayList<>();
        int tentaculox1 = Integer.parseInt(args[4]);
        int tentaculox2 = Integer.parseInt(args[6]);
        int tentaculox3 = Integer.parseInt(args[8]);
        int tentaculoy1 = Integer.parseInt(args[5]);
        int tentaculoy2 = Integer.parseInt(args[7]);
        int tentaculoy3 = Integer.parseInt(args[9]);
        interfaz.borrarMensajes();
        interfaz.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");
        
        
        celdas[tentaculox1][tentaculoy1].recibirAtaque(comando, 100, interfaz);
        String msg = "[Three_Lines] Celda (" + tentaculox1 + "," + tentaculoy1 +
                ") quedó con " + celdas[tentaculox1][tentaculoy1].getVida() + " de vida.";
               mensajes.add(msg);
                
        celdas[tentaculox2][tentaculoy2].recibirAtaque(comando, 100, interfaz);
        msg = "[Three_Lines] Celda (" + tentaculox2 + "," + tentaculoy2 +
                ") quedó con " + celdas[tentaculox2][tentaculoy2].getVida() + " de vida.";
               mensajes.add(msg);
        celdas[tentaculox3][tentaculoy3].recibirAtaque(comando, 100, interfaz);
        msg = "[Three_Lines] Celda (" + tentaculox3 + "," + tentaculoy3 +
                ") quedó con " + celdas[tentaculox3][tentaculoy3].getVida() + " de vida.";
               mensajes.add(msg);
        this.aplicarThreeLines(celdas, tentaculox1, tentaculoy1, comando, interfaz, mensajes);
        this.aplicarThreeLines(celdas, tentaculox2, tentaculoy2, comando, interfaz, mensajes);
        this.aplicarThreeLines(celdas, tentaculox3, tentaculoy3, comando, interfaz, mensajes);
        
        String[] resultadoArray = new String[mensajes.size() + 2];

        resultadoArray[0] = "RESULTADO_ATAQUE";
        resultadoArray[1] = comando.getNombreUsuario();  

        for (int i = 0; i < mensajes.size(); i++) {
            resultadoArray[i + 2] = mensajes.get(i);
        }

        // Entregamos el comando directamente
        return new ComandoResultadoAtaque(resultadoArray, interfaz.getUsuario().getNombre(),true);
    }
    
    public ComandoResultadoAtaque ataqueThreeNumbers(InterfazPrincipal interfaz, ComandoAtaque comando){
        Celda[][] celdas = interfaz.getCeldas();
        List<String> mensajes = new ArrayList<>();
        String[] args = comando.getParametros();
        int[] numeros = {Integer.parseInt(args[4]),Integer.parseInt(args[5]),Integer.parseInt(args[6])};
        if (interfaz.ataqueThreeNumbers(numeros)){
            int celdasAtacar = numeros[0] * numeros[1] * numeros[2];
            interfaz.borrarMensajes();
            interfaz.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");
            
            this.realizarThreeNumbers(celdasAtacar, celdas, comando, interfaz, mensajes);
            String[] resultadoArray = new String[mensajes.size() + 2];

            resultadoArray[0] = "RESULTADO_ATAQUE";
            resultadoArray[1] = comando.getNombreUsuario();  

            for (int i = 0; i < mensajes.size(); i++) {
                resultadoArray[i + 2] = mensajes.get(i);
            }

            // Entregamos el comando directamente
            return new ComandoResultadoAtaque(resultadoArray, interfaz.getUsuario().getNombre(),true);
        }
        
        String[] resultadoArray = new String[mensajes.size() + 2];

        resultadoArray[0] = "RESULTADO_ATAQUE";
        resultadoArray[1] = comando.getNombreUsuario();  
        mensajes.add("El ataque Three Numbers falló. No acertaste ningún número.");
        for (int i = 0; i < mensajes.size(); i++) {
            resultadoArray[i + 2] = mensajes.get(i);
        }

        // Entregamos el comando directamente
        return new ComandoResultadoAtaque(resultadoArray, interfaz.getUsuario().getNombre(),false);
    }
    
    public ComandoResultadoAtaque ataqueControlTheKraken(InterfazPrincipal interfaz, ComandoAtaque comando){
        Random rand = new Random();
        List<String> mensajes = new ArrayList<>();
        Celda[][] celdas = interfaz.getCeldas();
        int rango = rand.nextInt(9) + 1;
        
        int fila = rand.nextInt(20);
        int columna = rand.nextInt(20);
        interfaz.borrarMensajes();
        interfaz.writeResultadoAtaque("SE RECIBIO UN ATAQUE Y SU RESULTADO FUE: ");
        
        for (int i = fila - rango; i <= fila + rango; i++) {
            for (int j = columna - rango; j <= columna + rango; j++) {
                if (ComandoAtaqueValidacion.fueraDeAlcanceXY(i, j)) {
                    celdas[i][j].recibirAtaque(comando ,100, interfaz);
                    String msg = "[Control_The_Kraken] Celda (" + i + "," + j +
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
        return new ComandoResultadoAtaque(resultadoArray, interfaz.getUsuario().getNombre(),true);
    }
    
    public void realizarThreeNumbers(int celdasAtacar, Celda[][] celdas, ComandoAtaque comando, InterfazPrincipal interfaz, List<String> mensajes){
         Random rand = new Random();
        int atacadas = 0;
        int seguridad = 0;
        int limiteSeguridad = 20 * 20; // 400 intentos máximos
        while (atacadas < celdasAtacar && seguridad < limiteSeguridad) {
            seguridad++;

            int x = rand.nextInt(20);
            int y = rand.nextInt(20);

            // Atacar solo si la celda no está muerta
            if (celdas[x][y].getVida() > 0.0) {
                celdas[x][y].recibirAtaque(comando, 100, interfaz);
                String msg = "[Three_Numbers] Celda (" + x + "," + y +
                ") quedó con " + celdas[x][y].getVida() + " de vida.";
                mensajes.add(msg);
                atacadas++;
            }
        }
    }
    
    public String[] getAtaques() {
        return ataques;
    }

    public void setAtaques(String[] ataques) {
        this.ataques = ataques;
    }
    
    public void aplicarThreeLines(Celda[][] celdas, int x, int y, ComandoAtaque comando, InterfazPrincipal interfaz, List<String> mensajes){
        Random rand = new Random();
        
        int rango = rand.nextInt(4) + 1;
        
        //Arriba
        for (int i = x - rango; i < x; i++ ){
            if (ComandoAtaqueValidacion.fueraDeAlcanceXY(i, y)) {
                celdas[i][y].recibirAtaque(comando, 100, interfaz);
                String msg = "[Three_Lines] Celda (" + i + "," + y +
                ") quedó con " + celdas[i][y].getVida() + " de vida.";
               mensajes.add(msg);
                }
        }
        
        //Abajo
        for (int i = x + 1; i <= x + rango; i++){
            if (ComandoAtaqueValidacion.fueraDeAlcanceXY(i, y)) {
                celdas[i][y].recibirAtaque(comando, 100, interfaz);
                String msg = "[Three_Lines] Celda (" + i + "," + y +
                ") quedó con " + celdas[i][y].getVida() + " de vida.";
               mensajes.add(msg);
                }
        }
        
        //Izquierda
        for (int j = y - rango; j < y; j++ ){
            if (ComandoAtaqueValidacion.fueraDeAlcanceXY(x, j)) {
                celdas[x][j].recibirAtaque(comando, 100, interfaz);
                String msg = "[Three_Lines] Celda (" + x + "," + j +
                ") quedó con " + celdas[x][j].getVida() + " de vida.";
               mensajes.add(msg);
                }
        }
        
        //Derecha
        for (int j = y + 1; j <= y + rango; j++){
            if (ComandoAtaqueValidacion.fueraDeAlcanceXY(x, j)) {
                celdas[x][j].recibirAtaque(comando, 100, interfaz);
                String msg = "[Three_Lines] Celda (" + x + "," + j +
                ") quedó con " + celdas[x][j].getVida() + " de vida.";
               mensajes.add(msg);
                }
        }
    }
    
    
    @Override
    public ComandoResultadoAtaque realizarAtaque(ComandoAtaque comando, InterfazPrincipal interfaz) {
        ComandoResultadoAtaque result = null;
        String[] args = comando.getParametros();
        for (String ataque : this.ataques){
            if (ataque.equals(args[3].toUpperCase())){
                switch(ataque){
                    case "THREE_LINES":
                        result = ataqueThreeLines(interfaz,comando);
                        return result;
                    case "THREE_NUMBERS":
                        result = ataqueThreeNumbers(interfaz, comando);
                        return result;
                    case "CONTROL_THE_KRAKEN":
                        result = ataqueControlTheKraken(interfaz, comando);
                        return result;
                }
            }
        }
        return result;
    }
}
