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
    public void ataqueTentaculos(InterfazPrincipal interfazPrincipal, ComandoAtaque comando) {
            
        Celda[][] celdas = interfazPrincipal.getCeldas();
        String[] args = comando.getParametros();
        int tentaculox1 = Integer.parseInt(args[4]);
        int tentaculox2 = Integer.parseInt(args[6]);
        int tentaculox3 = Integer.parseInt(args[8]);
        int tentaculoy1 = Integer.parseInt(args[5]);
        int tentaculoy2 = Integer.parseInt(args[7]);
        int tentaculoy3 = Integer.parseInt(args[9]);
        
        aplicarAtaqueTentaculo(celdas, tentaculox1, tentaculoy1,interfazPrincipal, comando.getNombre());
        aplicarAtaqueTentaculo(celdas, tentaculox2, tentaculoy2,interfazPrincipal, comando.getNombre());
        aplicarAtaqueTentaculo(celdas, tentaculox3, tentaculoy3,interfazPrincipal, comando.getNombre());
        
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
                        celdas[alientox + i][alientoy].recibirAtaque(comando.getNombre(), 100);
                    }
                }
                break;
            case 2: //Arriba
                for (int i = 0; i < cantCasillasAtacadas; i++){
                    if (ComandoAtaqueValidacion.fueraDeAlcanceXY(alientox - i, alientoy)) {
                        celdas[alientox - i][alientoy].recibirAtaque(comando.getNombre(), 100);
                    }
                }
                break;
            case 3: //Derecha
                for (int i = 0; i < cantCasillasAtacadas; i++){
                    if (ComandoAtaqueValidacion.fueraDeAlcanceXY(alientox, alientoy + i)) {
                        celdas[alientox][alientoy + i].recibirAtaque(comando.getNombre(), 100);
                    }
                }
                break;
            case 4: //Izquierda
                for (int i = 0; i < cantCasillasAtacadas; i++){
                    if (ComandoAtaqueValidacion.fueraDeAlcanceXY(alientox, alientoy - i)) {
                        celdas[alientox][alientoy - i].recibirAtaque(comando.getNombre(), 100);
                    }
                }
                break;
        }
    }
    
    public void ataqueReleaseTheKraken(InterfazPrincipal interfazPrincipal,  ComandoAtaque comando) {
    

        Random rand = new Random();

        Celda[][] celdas = interfazPrincipal.getCeldas();
        int rango = rand.nextInt(9) + 1;
        
        int fila = rand.nextInt(20);
        int columna = rand.nextInt(20);
        
        for (int i = fila - rango; i <= fila + rango; i++) {
            for (int j = columna - rango; j <= columna + rango; j++) {
                if (ComandoAtaqueValidacion.fueraDeAlcanceXY(i, j)) {
                celdas[i][j].recibirAtaque(comando.getNombre(),100);
                }
            }
        }
    }

    public String[] getAtaques() {
        return ataques;
    }

    public void setAtaques(String[] ataques) {
        this.ataques = ataques;
    }

    private void aplicarAtaqueTentaculo(Celda[][] celdas, int x, int y, InterfazPrincipal interfaz, String nombre) {
    for (int dx = -1; dx <= 1; dx++) {
        for (int dy = -1; dy <= 1; dy++) {
            int nx = x + dx;
            int ny = y + dy;

            if (ComandoAtaqueValidacion.fueraDeAlcanceXY(nx, ny)) {
                celdas[nx][ny].recibirAtaque(nombre,100);
            }
        }
    }
}

    @Override
    public void realizarAtaque(ComandoAtaque comando, InterfazPrincipal interfaz) {
        String[] args = comando.getParametros();
        for (String ataque : this.ataques){
            if (ataque.equals(args[3].toUpperCase())){
                switch(ataque){
                    case "TENTACULOS":
                        ataqueTentaculos(interfaz,comando);
                        return;
                    case "KRAKEN_BREATH":
                        ataqueKrakenBreath(interfaz, comando);
                        return;
                    case "RELEASE_THE_KRAKEN":
                        ataqueReleaseTheKraken(interfaz, comando);
                        return;
                }
            }
        }
    }
    

}
