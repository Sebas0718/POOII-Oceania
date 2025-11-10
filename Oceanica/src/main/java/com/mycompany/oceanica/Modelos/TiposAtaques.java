/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.oceanica.Modelos;

/**
 *
 * @author xsusk
 */
public enum TiposAtaques {
    
    TENTACULOS(10),
    KRAKEN_BREATH(6),
    RELEASE_THE_KRAKEN(4),
    THREE_LINES(10),
    THREE_NUMBERS(7),
    CONTROL_THE_KRAKEN(4),
    CARDUMEN(4),
    SHARK_ATTACK(4),
    PULP(4),
    VOLCANO_RAISING(4),
    VOLCANO_EXPLOSION(5),
    TERMAL_RUSH(5),
    THUNDER_RAIN(4),
    POSEIDON_THUNDERS(4),
    EEL_ATACK(4),
    SWIRL_RAISING(4),
    SEND_HUMAN_GARBAGE(5),
    RADIOACTIVE_RUSH(4);
    
    
    
    
    
    
    
    private int ParametrosRequeridos;

    private TiposAtaques(int ParametrosRequeridos) {
        this.ParametrosRequeridos = ParametrosRequeridos;
    }

    public int getParametrosRequeridos() {
        return ParametrosRequeridos;
    }
}
