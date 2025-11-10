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

/**
 *
 * @author xsusk
 */
public class ComandosAtaquesFabrica {
    
    public static Comando getComandoAtaque(String[] args, String nombre, InterfazPrincipal interfaz){
        if (args.length < 1)
            return new ComandoError(args, nombre);
        String tipo = args[1].toUpperCase();
        String ataque = args[2].toUpperCase();
        Comando comando = null;

        switch (tipo) {
            case "POSEIDON_TRIDENT":
               PoseidonTrident poseidon = new PoseidonTrident(TipoPersonaje.THE_TRIDDENT);
                switch (ataque) {
                    case "THREE_LINES":
                        if (TiposAtaques.THREE_LINES.getParametrosRequeridos() < args.length){
                            return new ComandoError(args, nombre);
                        }
                        if (!ComandoAtaqueValidacion.fueraDeAlcanceXY(Integer.parseInt(args[4]), Integer.parseInt(args[5])) || !ComandoAtaqueValidacion.fueraDeAlcanceXY(Integer.parseInt(args[6]), Integer.parseInt(args[7])) || !ComandoAtaqueValidacion.fueraDeAlcanceXY(Integer.parseInt(args[8]), Integer.parseInt(args[9]))){
                                return new ComandoError(args, nombre);
                            }
                        comando = new ComandoAtaque(args, nombre);
                        poseidon.ataqueThreeLines(interfaz, (ComandoAtaque) comando);
                        return comando;
                    case "THREE_NUMBERS":
                        if (TiposAtaques.THREE_NUMBERS.getParametrosRequeridos() < args.length){
                            return new ComandoError(args, nombre);
                        }
                        if (!ComandoAtaqueValidacion.fueraDeAlcanceThreeNumbers(Integer.parseInt(args[4]),Integer.parseInt(args[5]),Integer.parseInt(args[6]))){
                                return new ComandoError(args, nombre);
                            }
                        comando = new ComandoAtaque(args, nombre);
                        poseidon.ataqueThreeNumbers(interfaz, (ComandoAtaque) comando);
                        return comando;
                    case "CONTROL_THE_KRAKEN":
                        if (TiposAtaques.CONTROL_THE_KRAKEN.getParametrosRequeridos() < args.length){
                            return new ComandoError(args, nombre);
                        }
                        comando = new ComandoAtaque(args, nombre);
                        poseidon.ataqueControlTheKraken(interfaz, (ComandoAtaque) comando);
                        return comando;
                    default:
                        return new ComandoError(args, nombre);
                }

            case "RELEASE_KRAKEN":
                ReleaseTheKraken kraken = new ReleaseTheKraken(TipoPersonaje.RELEASE_THE_KRAKEN);
                switch (ataque) {
                    case "TENTACULOS":
                        if (TiposAtaques.TENTACULOS.getParametrosRequeridos() < args.length){
                            return new ComandoError(args, nombre);
                        }
                        if (!ComandoAtaqueValidacion.fueraDeAlcanceXY(Integer.parseInt(args[4]), Integer.parseInt(args[5])) || !ComandoAtaqueValidacion.fueraDeAlcanceXY(Integer.parseInt(args[6]), Integer.parseInt(args[7])) || !ComandoAtaqueValidacion.fueraDeAlcanceXY(Integer.parseInt(args[8]), Integer.parseInt(args[9]))){
                                return new ComandoError(args, nombre);
                        }
                        comando = new ComandoAtaque(args, nombre);
                        kraken.ataqueTentaculos(interfaz, (ComandoAtaque) comando);
                        return comando;
                    case "KRAKEN_BREATH":
                        if (TiposAtaques.KRAKEN_BREATH.getParametrosRequeridos() < args.length)
                            return new ComandoError(args, nombre);
                        if (!ComandoAtaqueValidacion.fueraDeAlcanceXY(Integer.parseInt(args[4]), Integer.parseInt(args[5]))){
                            return new ComandoError(args, nombre);
                        }
                        comando = new ComandoAtaque(args, nombre);
                        kraken.ataqueKrakenBreath(interfaz, (ComandoAtaque) comando);
                        return comando;
                    case "RELEASE_THE_KRAKEN":
                        if (TiposAtaques.RELEASE_THE_KRAKEN.getParametrosRequeridos() < args.length)
                            return new ComandoError(args, nombre);
                        
                        comando = new ComandoAtaque(args, nombre);
                        kraken.ataqueReleaseTheKraken(interfaz, (ComandoAtaque) comando);
                        return comando;
                    default:
                        return new ComandoError(args, nombre);
                }

            case "FISH_TELEPATHY":
                FishTelepathy fish = new FishTelepathy(TipoPersonaje.FISH_TELEPATHY);
                switch (ataque) {
                    case "CARDUMEN":
                        if (TiposAtaques.CARDUMEN.getParametrosRequeridos() < args.length)
                            return new ComandoError(args, nombre);
                        
                        comando = new ComandoAtaque(args, nombre);
                        fish.ataqueCardumen(interfaz, (ComandoAtaque) comando);
                        return comando;
                    case "SHARK_ATTACK":
                        if (TiposAtaques.SHARK_ATTACK.getParametrosRequeridos() < args.length)
                            return new ComandoError(args, nombre);
                        
                        comando = new ComandoAtaque(args, nombre);
                        fish.ataqueSharkAttack(interfaz, (ComandoAtaque) comando);
                        return comando;
                    case "PULP":
                        if (TiposAtaques.PULP.getParametrosRequeridos() < args.length)
                            return new ComandoError(args, nombre);
                        
                        comando = new ComandoAtaque(args, nombre);
                        fish.ataquePulp(interfaz, (ComandoAtaque) comando);
                        return comando;
                    default:
                        return new ComandoError(args, nombre);
                }

            case "UNDERSEA_FIRE":
                UnderseaFire volcan = new UnderseaFire(TipoPersonaje.UNDERSEA_FIRE);
                switch (ataque) {
                    case "VOLCANO_RAISING":
                        if (TiposAtaques.VOLCANO_RAISING.getParametrosRequeridos() < args.length)
                            return new ComandoError(args, nombre);
                        
                        comando = new ComandoAtaque(args, nombre);
                        volcan.ataqueVolcanoRaising(interfaz, (ComandoAtaque) comando);
                        return comando;
                    case "VOLCANO_EXPLOSION":
                        if (TiposAtaques.VOLCANO_EXPLOSION.getParametrosRequeridos() < args.length)
                            return new ComandoError(args, nombre);
                        
                        comando = new ComandoAtaque(args, nombre);
                        volcan.ataqueVolcanoExplosion(interfaz, (ComandoAtaque) comando);
                        return comando;
                    case "TERMAL_RUSH":
                        if (TiposAtaques.TERMAL_RUSH.getParametrosRequeridos() < args.length)
                            return new ComandoError(args, nombre);
                        
                        comando = new ComandoAtaque(args, nombre);
                        volcan.ataqueTermalRush(interfaz, (ComandoAtaque) comando);
                        return comando;
                    default:
                        return new ComandoError(args, nombre);
                }

            case "THUNDERS_UNDER_THE_SEA":
                ThundersUnderTheSea torbellino = new ThundersUnderTheSea(TipoPersonaje.THUNDERS_UTS);
                switch (ataque) {
                    case "THUNDER_RAIN":
                        if (TiposAtaques.THUNDER_RAIN.getParametrosRequeridos() < args.length)
                            return new ComandoError(args, nombre);
                        
                        comando = new ComandoAtaque(args, nombre);
                        torbellino.ataqueThunderRain(interfaz, (ComandoAtaque) comando);
                        return comando;
                    case "POSEIDON_THUNDERS":
                        if (TiposAtaques.POSEIDON_THUNDERS.getParametrosRequeridos() < args.length)
                            return new ComandoError(args, nombre);
                        
                        comando = new ComandoAtaque(args, nombre);
                        torbellino.ataquePoseidonThunders(interfaz, (ComandoAtaque) comando);
                        return comando;
                    case "EEL_ATACK":
                        if (TiposAtaques.EEL_ATACK.getParametrosRequeridos() < args.length)
                            return new ComandoError(args, nombre);
                        
                        comando = new ComandoAtaque(args, nombre);
                        torbellino.ataqueEelAtack(interfaz, (ComandoAtaque) comando);
                        return comando;
                    default:
                        return new ComandoError(args, nombre);
                }

            case "WAVES_CONTROL":
                WavesControl waves = new WavesControl(TipoPersonaje.WAVES_CONTROL);
                switch (ataque) {
                    case "SWIRL_RAISING":
                        if (TiposAtaques.SWIRL_RAISING.getParametrosRequeridos() < args.length)
                            return new ComandoError(args, nombre);
                        
                        comando = new ComandoAtaque(args, nombre);
                        waves.ataqueSwirlRaising(interfaz, (ComandoAtaque) comando);
                        return comando;
                    case "SEND_HUMAN_GARBAGE":
                        if (TiposAtaques.SEND_HUMAN_GARBAGE.getParametrosRequeridos() < args.length)
                            return new ComandoError(args, nombre);
                        
                        comando = new ComandoAtaque(args, nombre);
                        waves.ataqueSendHumanGarbage(interfaz, (ComandoAtaque) comando);
                        return comando;
                    case "RADIOACTIVE_RUSH":
                        if (TiposAtaques.RADIOACTIVE_RUSH.getParametrosRequeridos() < args.length)
                            return new ComandoError(args, nombre);
                        
                        comando = new ComandoAtaque(args, nombre);
                        waves.ataqueRadioactiveRush(interfaz, (ComandoAtaque) comando);
                        return comando;
                    default:
                        return new ComandoError(args, nombre);
                }

            default:
                return new ComandoError(args, nombre);
        }
            }
    
}
