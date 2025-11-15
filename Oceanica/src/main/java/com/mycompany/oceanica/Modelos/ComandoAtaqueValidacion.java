/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Modelos;

/**
 *
 * @author xsusk
 */
public class ComandoAtaqueValidacion {
    
    public static boolean fueraDeAlcanceXY(int x, int y){
        return (x >= 0 && x < 20 && y >= 0 && y < 20);

    }
    
    public static boolean fueraDeAlcanceThreeNumbers(int num1, int num2, int num3){
        return (num1 > 0 || num1 <= 9) && (num2 > 0 || num2 <= 9) && (num3 > 0 || num3 <= 9);
        
    }
    
}
