/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oceanica.Modelos;

import com.mycompany.oceanica.Usuario.Usuario;

/**
 *
 * @author xsusk
 */
public class ComandoCrearPersonajeErrores {
    public static void error(String string, Usuario usuario, Comando comando){
        usuario.getInterfazPrincipal().writeMessage(string, comando);
    }
}
