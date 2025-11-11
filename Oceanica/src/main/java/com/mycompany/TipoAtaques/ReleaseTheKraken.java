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

/**
 *
 * @author seb
 */
public class ReleaseTheKraken extends Ataque {
    
    private String[] ataques = new String[3];
    
    public ReleaseTheKraken(TipoPersonaje tipoPersonaje) {
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
    public void ataqueTentaculos(InterfazPrincipal interfazPrincipal, ComandoAtaque comando) {
            
        Celda[][] celdas = interfazPrincipal.getCeldas();
        String[] args = comando.getParametros();
        int tentaculox1 = Integer.parseInt(args[4]);
        int tentaculox2 = Integer.parseInt(args[6]);
        int tentaculox3 = Integer.parseInt(args[8]);
        int tentaculoy1 = Integer.parseInt(args[5]);
        int tentaculoy2 = Integer.parseInt(args[7]);
        int tentaculoy3 = Integer.parseInt(args[9]);
        
        aplicarAtaqueTentaculo(celdas, tentaculox1, tentaculoy1);
        aplicarAtaqueTentaculo(celdas, tentaculox2, tentaculoy2);
        aplicarAtaqueTentaculo(celdas, tentaculox3, tentaculoy3);
        
    }
        
        
    public void ataqueKrakenBreath(InterfazPrincipal interfazPrincipal,  ComandoAtaque comando){
        String[] args = comando.getParametros();
        int alientox = Integer.parseInt(args[4]);
        int alientoy = Integer.parseInt(args[5]);
        Random rand = new Random();
        int cantCasillasAtacadas = rand.nextInt(8) + 1;
        rand = new Random();
        int direccion = rand.nextInt(4) + 1;
        Celda[][] celdas = interfazPrincipal.getCeldas();
        switch (direccion){
            case 1: //Abajo
                for (int i = 0; i < cantCasillasAtacadas; i++){
                    if (ComandoAtaqueValidacion.fueraDeAlcanceXY(alientox + i, alientoy)) {
                        celdas[alientox + i][alientoy].recibirAtaqueDirecto(100);
                    }
                }
                break;
            case 2: //Arriba
                for (int i = 0; i < cantCasillasAtacadas; i++){
                    if (ComandoAtaqueValidacion.fueraDeAlcanceXY(alientox - i, alientoy)) {
                        celdas[alientox - i][alientoy].recibirAtaqueDirecto(100);
                    }
                }
                break;
            case 3: //Derecha
                for (int i = 0; i < cantCasillasAtacadas; i++){
                    if (ComandoAtaqueValidacion.fueraDeAlcanceXY(alientox, alientoy + i)) {
                        celdas[alientox][alientoy + i].recibirAtaqueDirecto(100);
                    }
                }
                break;
            case 4: //Izquierda
                for (int i = 0; i < cantCasillasAtacadas; i++){
                    if (ComandoAtaqueValidacion.fueraDeAlcanceXY(alientox, alientoy - i)) {
                        celdas[alientox][alientoy - i].recibirAtaqueDirecto(100);
                    }
                }
                break;
        }
    }
    
    public void ataqueReleaseTheKraken(InterfazPrincipal interfazPrincipal,  ComandoAtaque comando) {
    

        Random rand = new Random();

        Celda[][] celdas = interfazPrincipal.getCeldas();
        int F = celdas.length;
        int C = celdas[0].length;
        int rango = 9;
        
        int fila = rand.nextInt(20);
        int columna = rand.nextInt(20);
        
            // 1. Determinar los límites de búsqueda seguros
        int r_inicio = Math.max(0, fila - rango);
        int r_fin = Math.min(F - 1, fila + rango);
        int c_inicio = Math.max(0, columna - rango);
        int c_fin = Math.min(C - 1, columna + rango);

        for (int i = r_inicio; i <= r_fin; i++) {
            for (int j = c_inicio; j <= c_fin; j++) {            
                celdas[i][j].recibirAtaque(usuarioAtacante, 100);
            }
        }
        interfazPrincipal.actualizarInterfaz();

    }

    public String[] getAtaques() {
        return ataques;
    }

    public void setAtaques(String[] ataques) {
        this.ataques = ataques;
    }

    private void aplicarAtaqueTentaculo(Celda[][] celdas, int x, int y) {
    for (int dx = -1; dx <= 1; dx++) {
        for (int dy = -1; dy <= 1; dy++) {
            int nx = x + dx;
            int ny = y + dy;

            if (ComandoAtaqueValidacion.fueraDeAlcanceXY(nx, ny)) {
                celdas[nx][ny].recibirAtaqueDirecto(100);
            }
        }
    }
}
    

}
