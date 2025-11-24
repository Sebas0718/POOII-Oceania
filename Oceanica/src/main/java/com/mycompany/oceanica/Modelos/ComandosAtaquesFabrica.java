/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Modelos;

import com.mycompany.Interfaz.InterfazPrincipal;
import com.mycompany.Personaje.Personaje;
import com.mycompany.Personaje.TipoPersonaje;
import com.mycompany.TipoAtaques.FishTelepathy;
import com.mycompany.TipoAtaques.PoseidonTrident;
import com.mycompany.TipoAtaques.ReleaseTheKraken;
import com.mycompany.TipoAtaques.ThundersUnderTheSea;
import com.mycompany.TipoAtaques.UnderseaFire;
import com.mycompany.TipoAtaques.WavesControl;
import com.mycompany.oceanica.Usuario.Usuario;

/**
 *
 * @author xsusk
 */
public class ComandosAtaquesFabrica {
    
    public static Comando getComandoAtaque(String[] args, String nombre, Personaje personaje, Usuario usuario){
        TipoPersonaje tipo = personaje.getTipoPersonaje();
        String ataque = args[3].toUpperCase();
        Comando comando = null;

        
        if (tipo.equals(TipoPersonaje.THE_TRIDDENT)){
            switch (ataque) {
                case "THREE_LINES":
                    if (TiposAtaques.THREE_LINES.getParametrosRequeridos() < args.length){
                        return new ComandoError(args, nombre);
                    }
                    if (!ComandoAtaqueValidacion.fueraDeAlcanceXY(Integer.parseInt(args[4]), Integer.parseInt(args[5])) || !ComandoAtaqueValidacion.fueraDeAlcanceXY(Integer.parseInt(args[6]), Integer.parseInt(args[7])) || !ComandoAtaqueValidacion.fueraDeAlcanceXY(Integer.parseInt(args[8]), Integer.parseInt(args[9]))){
                            return new ComandoError(args, nombre);
                        }
                    
                    comando = new ComandoAtaque(args, nombre, personaje);
                    return comando;
                case "THREE_NUMBERS":
                    if (TiposAtaques.THREE_NUMBERS.getParametrosRequeridos() < args.length){
                        return new ComandoError(args, nombre);
                    }
                    if (!ComandoAtaqueValidacion.fueraDeAlcanceThreeNumbers(Integer.parseInt(args[4]),Integer.parseInt(args[5]),Integer.parseInt(args[6]))){
                            return new ComandoError(args, nombre);
                        }
                    
                    comando = new ComandoAtaque(args, nombre, personaje);
                    return comando;
                case "CONTROL_THE_KRAKEN":
                    if (!usuario.isReleaseTheKraken())
                        return new ComandoError(args, nombre);
                    else if (TiposAtaques.CONTROL_THE_KRAKEN.getParametrosRequeridos() < args.length){
                        return new ComandoError(args, nombre);
                    }
                    
                    return new ComandoAtaque(args, nombre, personaje);
                default:
                    return new ComandoError(args, nombre);
            }
        }
        else if (tipo.equals(TipoPersonaje.RELEASE_THE_KRAKEN)){

            switch (ataque) {
                case "TENTACULOS":
                    if (TiposAtaques.TENTACULOS.getParametrosRequeridos() < args.length){
                        return new ComandoError(args, nombre);
                    }
                    if (!ComandoAtaqueValidacion.fueraDeAlcanceXY(Integer.parseInt(args[4]), Integer.parseInt(args[5])) || !ComandoAtaqueValidacion.fueraDeAlcanceXY(Integer.parseInt(args[6]), Integer.parseInt(args[7])) || !ComandoAtaqueValidacion.fueraDeAlcanceXY(Integer.parseInt(args[8]), Integer.parseInt(args[9]))){
                            return new ComandoError(args, nombre);
                    }
                    
                    return new ComandoAtaque(args, nombre, personaje);
                case "KRAKEN_BREATH":
                    if (TiposAtaques.KRAKEN_BREATH.getParametrosRequeridos() < args.length)
                        return new ComandoError(args, nombre);
                    if (!ComandoAtaqueValidacion.fueraDeAlcanceXY(Integer.parseInt(args[4]), Integer.parseInt(args[5]))){
                        return new ComandoError(args, nombre);
                    }
                    
                    return new ComandoAtaque(args, nombre, personaje);
                case "RELEASE_THE_KRAKEN":
                    if (TiposAtaques.RELEASE_THE_KRAKEN.getParametrosRequeridos() < args.length)
                        return new ComandoError(args, nombre);
                    
                    return new ComandoAtaque(args, nombre, personaje);
                default:
                    return new ComandoError(args, nombre);
            }
        }
        else if (tipo.equals(TipoPersonaje.FISH_TELEPATHY)){
            switch (ataque) {
                case "CARDUMEN":
                    if (TiposAtaques.CARDUMEN.getParametrosRequeridos() < args.length)
                        return new ComandoError(args, nombre);
                    
                    return new ComandoAtaque(args, nombre, personaje);
                    
                case "SHARK_ATTACK":
                    if (TiposAtaques.SHARK_ATTACK.getParametrosRequeridos() < args.length)
                        return new ComandoError(args, nombre);
                    
                    return new ComandoAtaque(args, nombre, personaje);
                    
                case "PULP":
                    if (TiposAtaques.PULP.getParametrosRequeridos() < args.length)
                        return new ComandoError(args, nombre);
                    
                    return new ComandoAtaque(args, nombre, personaje);
                default:
                    return new ComandoError(args, nombre);
            }
        }
        else if (tipo.equals(TipoPersonaje.UNDERSEA_FIRE)){
            switch (ataque) {
                case "VOLCANO_RAISING":
                    if (TiposAtaques.VOLCANO_RAISING.getParametrosRequeridos() < args.length)
                        return new ComandoError(args, nombre);
                    
                    return new ComandoAtaque(args, nombre, personaje);

                case "VOLCANO_EXPLOSION":
                    if (TiposAtaques.VOLCANO_EXPLOSION.getParametrosRequeridos() < args.length)
                        return new ComandoError(args, nombre);
                    
                    return new ComandoAtaque(args, nombre, personaje);

                case "TERMAL_RUSH":
                    if (TiposAtaques.TERMAL_RUSH.getParametrosRequeridos() < args.length)
                        return new ComandoError(args, nombre);
                    
                    return new ComandoAtaque(args, nombre, personaje);
                default:
                    return new ComandoError(args, nombre);
            }
        }

        else if (tipo.equals(TipoPersonaje.THUNDERS_UTS)){
            switch (ataque) {
                case "THUNDER_RAIN":
                    if (TiposAtaques.THUNDER_RAIN.getParametrosRequeridos() < args.length)
                        return new ComandoError(args, nombre);

                    
                    return new ComandoAtaque(args, nombre, personaje);
                    
                case "POSEIDON_THUNDERS":
                    if (TiposAtaques.POSEIDON_THUNDERS.getParametrosRequeridos() < args.length)
                        return new ComandoError(args, nombre);

                    
                    return new ComandoAtaque(args, nombre, personaje);
                    
                case "EEL_ATTACK":
                    System.out.println("ts1");
                    if (TiposAtaques.EEL_ATTACK.getParametrosRequeridos() < args.length)
                        return new ComandoError(args, nombre);
                    System.out.println("ts2");
                    
                    return new ComandoAtaque(args, nombre, personaje);
                    
                default:
                    System.out.println("ts3");
                    return new ComandoError(args, nombre);
            }
        }

        else if (tipo.equals(TipoPersonaje.WAVES_CONTROL)){
            switch (ataque) {
                case "SWIRL_RAISING":
                    if (TiposAtaques.SWIRL_RAISING.getParametrosRequeridos() < args.length)
                        return new ComandoError(args, nombre);
                    
                    return new ComandoAtaque(args, nombre, personaje);

                case "SEND_HUMAN_GARBAGE":
                    if (TiposAtaques.SEND_HUMAN_GARBAGE.getParametrosRequeridos() < args.length)
                        return new ComandoError(args, nombre);
                    
                    return new ComandoAtaque(args, nombre, personaje);

                case "RADIOACTIVE_RUSH":
                    if (TiposAtaques.RADIOACTIVE_RUSH.getParametrosRequeridos() < args.length){
                        System.out.println("ts8");
                        return new ComandoError(args, nombre);
                    }
                    System.out.println("ts9");
                    return new ComandoAtaque(args, nombre, personaje);

                default:
                    System.out.println("tsError");
                    return new ComandoError(args, nombre);
            }
        }

        return new ComandoError(args, nombre);
    }
    

}
    

