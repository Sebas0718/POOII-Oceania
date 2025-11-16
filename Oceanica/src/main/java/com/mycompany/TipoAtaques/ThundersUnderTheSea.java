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
    
    public void ataqueThunderRain(InterfazPrincipal interfaz, ComandoAtaque comando){
        Random rand = new Random();
        Celda[][] celdas = interfaz.getCeldas();

        int cantidadRayos = 100;

        for (int i = 0; i < cantidadRayos; i++) {

            // Coordenadas aleatorias
            int x = rand.nextInt(20);
            int y = rand.nextInt(20);

            // Daño entre 10% y 20%
            int daño = rand.nextInt(11) + 10;  // 10 a 20

            celdas[x][y].recibirAtaque(comando.getUsuario(), daño);
        }
    }
    
    public void ataquePoseidonThunders(InterfazPrincipal interfaz, ComandoAtaque comando){
        Random rand = new Random();
        Celda[][] celdas = interfaz.getCeldas();

        // Cantidad de rayos: entre 5 y 10
        int cantidadRayos = rand.nextInt(6) + 5;

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
                        celdas[i][j].recibirAtaque(comando.getUsuario(), 100);
                    }
                }
            }
        }
    }
    
    public void ataqueEelAtack(InterfazPrincipal interfaz, ComandoAtaque comando){
        Random rand = new Random();
        Celda[][] celdas = interfaz.getCeldas();
        
        // Cantidad de anguilas entre 25 y 100
        int cantidadAnguilas = rand.nextInt(76) + 25;

        for (int k = 0; k < cantidadAnguilas; k++) {

            // Cada anguila ataca una casilla aleatoria
            int x = rand.nextInt(20);
            int y = rand.nextInt(20);

            // Descargas: entre 1 y 10 → cada una vale 10% de daño
            int descargas = rand.nextInt(10) + 1;
            int danoTotal = descargas * 10; // 10% por descarga

            // Aplicar daño
            celdas[x][y].recibirAtaque(comando.getUsuario(), danoTotal);
        }
    }
    
    public String[] getAtaques() {
        return ataques;
    }

    

    @Override
    public void realizarAtaque(ComandoAtaque comando, InterfazPrincipal interfaz) {
        String[] args = comando.getParametros();
        for (String ataque : this.ataques){
            if (ataque.equals(args[3].toUpperCase())){
                switch(ataque){
                    case "THUNDER_RAIN":
                        ataqueThunderRain(interfaz,comando);
                        return;
                    case "POSEIDON_THUNDERS":
                        ataquePoseidonThunders(interfaz, comando);
                        return;
                    case "EEL_ATTACK":
                        ataqueEelAtack(interfaz, comando);
                        return;
                }
            }
        }
    }
    
}
