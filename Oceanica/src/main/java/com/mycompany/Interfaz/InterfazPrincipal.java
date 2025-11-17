/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.Interfaz;

import com.mycompany.Personaje.Personaje;
import com.mycompany.Personaje.TipoPersonaje;
import com.mycompany.Personaje.TipoPersonajeFabrica;
import com.mycompany.oceanica.Modelos.Comando;
import com.mycompany.oceanica.Modelos.ComandoAtaque;
import com.mycompany.oceanica.Modelos.ComandoCrearPersonaje;
import com.mycompany.oceanica.Modelos.ComandoCrearPersonajeAsignaciones;
import com.mycompany.oceanica.Modelos.ComandoCrearPersonajeErrores;
import com.mycompany.oceanica.Modelos.ComandoCrearPersonajeValidaciones;
import com.mycompany.oceanica.Modelos.ComandoError;
import com.mycompany.oceanica.Modelos.ComandoFabrica;
import com.mycompany.oceanica.Modelos.ComandoUtilidad;
import com.mycompany.oceanica.Modelos.ComandosAtaquesFabrica;
import com.mycompany.oceanica.Server.PantallaServer;
import com.mycompany.oceanica.Usuario.Usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.mycompany.oceanica.Modelos.ComandoSaltarTurno;

/**
 *
 * @author seb
 */
public class InterfazPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName());
    private ArrayList<Personaje> listaPersonajes = new ArrayList<Personaje>();
    
    private int[] statsPoder = new int[3];
    private int[] statsResistencia = new int[3];
    private int[] statsSanidad = new int[3];
    
    private int porcentajeOcupadoMapa = 0;
    private int contadorPersonaje1 = 0;
    private int contadorPersonaje2 = 0;
    private int contadorPersonaje3 = 0;
    
    private Celda [][] celdas  = new Celda[20][20];
    private JLabel [][] tablero = new JLabel [20][20];
    private String[][] ocupados = new String[20][20];

    private ArrayList<String> ataquesRecibidos = new ArrayList<>(); 
    private ArrayList<String> historial = new ArrayList<>();
    
    // private ArrayList<int> rangoVolcanesCreados = new ArrayList<int>();
    // private ArrayList<int> rangoRemolinosCreados = new ArrayList<int>();
    private int rangoUltimoVolcan = 0;
    private int rangoUltimoRemolino = 0;
    
   private int defensa = 1; 
    
    private PantallaServer servidor;
    
    private Usuario usuario;
    
    
    /**
     * Creates new form InterfazPrincipal
        */
    public InterfazPrincipal() {
        
        
       initComponents(); 
       llenarStats();
       jPanelPersonajes.setLayout(new BoxLayout(jPanelPersonajes, BoxLayout.Y_AXIS));

       this.revalidate();
       this.repaint();
       while (true) {
           String name = JOptionPane.showInputDialog(this, "Ingrese su nombre");
           if (name != null && name.length() > 0) {
               this.setTitle(name);
               usuario = new Usuario(this, name);
               break;
           }
       }
   }
    
    public void llenarPanelStats() {

        Personaje personaje1 = listaPersonajes.get(0);
        Personaje personaje2 = listaPersonajes.get(1);
        Personaje personaje3 = listaPersonajes.get(2);
        
        jLabelNombrePersonaje1.setText(personaje1.getNombre());
        jLabelNombrePersonaje2.setText(personaje2.getNombre());
        jLabelNombrePersonaje3.setText(personaje3.getNombre());



        int casillasVivasPersonaje1 = obtenerCasillasVivasPersonaje(personaje1) ;
        int casillasVivasPersonaje2 = obtenerCasillasVivasPersonaje(personaje2);
        int casillasVivasPersonaje3 =  obtenerCasillasVivasPersonaje(personaje3);
        
        int cantidadTotalCasillasP1 = (400 * personaje1.getPorcentajeMapa()) / 100;
        int cantidadTotalCasillasP2 = (400 * personaje2.getPorcentajeMapa()) / 100;
        int cantidadTotalCasillasP3 = (400 * personaje3.getPorcentajeMapa()) / 100;

        jLabelPorcentajeOcupadoPersonaje1.setText(((100*casillasVivasPersonaje1)/cantidadTotalCasillasP1) + "%");
        jLabelPorcentajeOcupadoPersonaje2.setText(((100*casillasVivasPersonaje2)/cantidadTotalCasillasP2) + "%");
        jLabelPorcentajeOcupadoPersonaje3.setText(((100*casillasVivasPersonaje3)/cantidadTotalCasillasP3) + "%");

        jLabelCasillasOcupadasPersonaje1
                .setText(casillasVivasPersonaje1 + " de " + cantidadTotalCasillasP1+ " casillas");
        jLabelCasillasOcupadasPersonaje2
                .setText(casillasVivasPersonaje2 + " de " + cantidadTotalCasillasP2+ " casillas");
        jLabelCasillasOcupadasPersonaje3
                .setText(casillasVivasPersonaje3 + " de " + cantidadTotalCasillasP3 + " casillas");
                
        jLabelNombrePersonaje1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelNombrePersonaje2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelNombrePersonaje3.setHorizontalAlignment(SwingConstants.CENTER);

        jLabelPorcentajeOcupadoPersonaje1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelPorcentajeOcupadoPersonaje2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelPorcentajeOcupadoPersonaje3.setHorizontalAlignment(SwingConstants.CENTER);

        jLabelCasillasOcupadasPersonaje1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelCasillasOcupadasPersonaje2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelCasillasOcupadasPersonaje3.setHorizontalAlignment(SwingConstants.CENTER);


    }

    public void limpiarInterfaz() {

        this.txfComando.setEditable(false);
        this.txfComando.setText("");
        for (Celda[] filaCeldas : celdas) {
            for (Celda celda : filaCeldas) {
                celda.getRefLabel().setBackground(new Color(120, 120, 120));
                celda.getRefLabel().repaint();
                celda.getRefLabel().revalidate();
            }
        }


        jPanelPersonajes.removeAll();
        jPanelPersonajes.repaint();
        txaBitacora.setText("");
        btnEnviar.setEnabled(false);

    }
    



    private void aumentarCasillasMuertas(){
        jLabelNumVida.setText("" + obtenerCasillasDestruidas());
        if (obtenerCasillasDestruidas() == 400) {
            this.limpiarInterfaz();
            this.getUsuario().derrota();

            
        }
    }
    
    public int obtenerCasillasVivasPersonaje(Personaje personaje) {
        int cantidad = 0;
        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[0].length; j++) {
                Celda celda = celdas[i][j];
                if (celda.getPersonajeDueño() == personaje && !celda.isIsCeldaDestruida()) {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }
    
    public int obtenerCasillasDestruidas(){
        int cantidad = 0;
        for (int i = 0; i < celdas.length;i++){
            for (int j = 0; j < celdas[0].length; j++){
                Celda celda = celdas[i][j];
                if (celda.isIsCeldaDestruida()){
                    cantidad++;
                }
            }
        }
        return cantidad;
    }
    
    public void curarCasillas(int cantidadCuracion) {

        for (Celda[] filaCelda : celdas) {
            for (Celda celda : filaCelda) {
                if (celda.getVida() > 0) {
                    float vida = celda.getVida();
                    float porcentajeAumentar = (cantidadCuracion * vida) / 100;
                    celda.setVida(vida + porcentajeAumentar);
                }
            }
        }
        txaBitacora.append("Se han curado las casillas");
        ComandoSaltarTurno comando = new ComandoSaltarTurno(null, usuario.getNombre());

        if (comando != null) {
            try {
                this.usuario.getObjetoEscritor().writeObject(comando);
            } catch (Exception e) {
            }
        }
    }
    

    public void aumentarDefensa(int defensa) {
        
        switch (defensa) {
            case 100:
                this.usuario.setDefensa(2);
                break;
            case 75:
                this.usuario.setDefensa(1.5f);
                break;
            case 50:
                this.usuario.setDefensa(1.25f);
                break;
        }
        this.txaBitacora.append("Se ha aumentado la defensa en " + defensa + "%");
    }


    public void actualizarInterfaz(){

        for (int i = 0; i < celdas.length; i++){
            for (int j = 0; j < celdas[0].length; j++){
                if (celdas[i][j].getVida() <= 0 && !celdas[i][j].isTieneVolcan() && !celdas[i][j].isTieneRemolino()){
                    celdas[i][j].getRefLabel().setBackground(new Color(0,0,0));
                }
            }
        }
        llenarPanelStats();
        actualizarAtaquesRecibidos();
        aumentarCasillasMuertas();
    }

    public void actualizarAtaquesRecibidos() {
        
        this.txaComandoActual.setText("");
        for (int i = 0; i < ataquesRecibidos.size(); i++) {
            this.txaComandoActual.append(ataquesRecibidos.get(i));
        }
    }


    
    public void crearMatriz() {
        jPanelTablero.setLayout(new GridLayout(20, 20, 2, 2)); // Distribuir 20x20
        jPanelTablero.setPreferredSize(new Dimension(500,330));
        jPanelTablero.removeAll();


        for (int fila = 0; fila < 20; fila++) {
            for (int columna = 0; columna < 20; columna++) {

                
                final int  finalFila = fila;
                final int finalColumna = columna;
                
                JLabel nuevoLabel = new JLabel();
                nuevoLabel.setBounds(fila * 30, columna * 30, 30, 30);
                nuevoLabel.setOpaque(true);
                nuevoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                nuevoLabel.setPreferredSize(new Dimension(20, 20)); 
                nuevoLabel.setBackground(new Color(0, 100, 0)); 
                nuevoLabel.setHorizontalAlignment(SwingConstants.CENTER);
                boolean isPintado = false;
                while (!isPintado) {
                    Random rand = new Random();
                    int colorCelda = rand.nextInt(3);
                    switch(colorCelda) {
                        case 0:
                            if (this.contadorPersonaje1 < (400*listaPersonajes.get(0).getPorcentajeMapa())/100){
                                contadorPersonaje1++;
                                celdas[fila][columna] = new Celda(nuevoLabel,listaPersonajes.get(0), fila, columna);
                                nuevoLabel.setBackground(new Color(20,20,200));
                                isPintado = true;
                            }
                            break;
                        case 1:
                            if (this.contadorPersonaje2 < (400*listaPersonajes.get(1).getPorcentajeMapa())/100){
                                contadorPersonaje2++;
                                celdas[fila][columna] = new Celda(nuevoLabel,listaPersonajes.get(1), fila, columna);
                                nuevoLabel.setBackground(new Color(200,20,20));
                                isPintado = true;
                            }
                            break;
                     case 2:
                            if (this.contadorPersonaje3 < (400*listaPersonajes.get(2).getPorcentajeMapa())/100){
                                contadorPersonaje3++;
                                celdas[fila][columna] = new Celda(nuevoLabel,listaPersonajes.get(2), fila, columna);
                                nuevoLabel.setBackground(new Color(20,200,20));
                                isPintado = true;
                            }
                            break;
                    }
                }
                nuevoLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {


                    Celda celdaSeleccionada = celdas[finalFila][finalColumna];
                    txaBitacora.setText(celdaSeleccionada.toString());

                }
            });
                nuevoLabel.addMouseListener(new MouseAdapter(){
                    Color colorOriginal = nuevoLabel.getBackground();
                    Celda celda = celdas[finalFila][finalColumna];
                     @Override
                    public void mouseEntered(MouseEvent e) {
                        nuevoLabel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
                        if (celda.getVida() >0 && !usuario.isIsGameOver()){
                            nuevoLabel.setBackground(colorOriginal.brighter());
                        }
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        nuevoLabel.setBorder(null);
                        if (celda.getVida() > 0 && !usuario.isIsGameOver()){
                            nuevoLabel.setBackground(colorOriginal);
                        }
                    }

 
                    
                    
            });
                jPanelTablero.add(nuevoLabel);
                tablero[fila][columna] = nuevoLabel;
            }   
        }
        jPanelTablero.revalidate();
        jPanelTablero.repaint();
    }
    
    
    
    
    public void llenarStats(){
        
        this.statsPoder[0] = 50;
        this.statsPoder[1] = 75;
        this.statsPoder[2] = 100;
        this.statsResistencia[0] = 50;
        this.statsResistencia[1] = 75;
        this.statsResistencia[2] = 100;
        this.statsSanidad[0] = 50;
        this.statsSanidad[1] = 75;
        this.statsSanidad[2] = 100;
    }
    
    
    public void crearPersonajes(ComandoCrearPersonaje comando) {

        if (this.listaPersonajes.size() == 3) {
            ComandoCrearPersonajeErrores.error("!!!ERROR!!! Ya se crearon los 3 personajes posibles", this.usuario,
                    comando);
        }
        jPanelPersonajes.setPreferredSize(new Dimension(320, 150));
        for (int i = 0; i < 8; i++) {
        }
        
        Personaje personaje = TipoPersonajeFabrica.getTipoPersonaje(comando.getParametros()[1]);

        if (personaje == null || personaje.getTipoPersonaje().equals(null))

    {
            ComandoCrearPersonajeErrores.error("!!!ERROR!!! Tipo de personaje no existente", this.getUsuario(),
                    comando);
                return ;
            }
        if (!ComandoCrearPersonajeAsignaciones.asignarValoresPersonaje(comando, personaje, this)) {
            return;
        }
        pintarPersonajes(personaje, comando);

    }

    public void pintarPersonajes(Personaje personaje, Comando comando) {

        
        Dimension dimension = new Dimension(Integer.MAX_VALUE, jPanelPersonajes.getHeight()/3);
        Dimension maxDimension = new Dimension(Integer.MAX_VALUE,jPanelPersonajes.getHeight()/3);
        JPanel nuevoPersonaje = new JPanel();

        JPanel contenedorImagen = new JPanel();
        JPanel contenedorTexto = new JPanel();
        JPanel contenedorTitulos = new JPanel();
        JPanel contenedorStats = new JPanel();

        nuevoPersonaje.setPreferredSize(dimension);
        nuevoPersonaje.setMaximumSize(maxDimension);

        contenedorImagen.setMaximumSize(new Dimension(100, 200));
        contenedorTexto.setMaximumSize(new Dimension(jPanelPersonajes.getWidth()-100,200));
        contenedorTitulos.setMaximumSize(new Dimension(jPanelPersonajes.getWidth()-100, 100));
        contenedorStats.setMaximumSize(new Dimension(jPanelPersonajes.getWidth()-100, 100));

        nuevoPersonaje.setLayout(new BoxLayout(nuevoPersonaje, BoxLayout.X_AXIS));

        contenedorTexto.setLayout(new BoxLayout(contenedorTexto, BoxLayout.Y_AXIS));
        contenedorTitulos.setLayout(new BoxLayout(contenedorTitulos,BoxLayout.Y_AXIS));
        contenedorStats.setLayout(new BoxLayout(contenedorStats,BoxLayout.Y_AXIS));


        


        JLabel poder = new JLabel();
        JLabel resistencia = new JLabel();
        JLabel sanidad = new JLabel();
        JLabel nombre = new JLabel();
        JLabel tipo = new JLabel();
        JLabel porcentajeMapa = new JLabel();
        ImageIcon imagen = new ImageIcon(getClass().getResource(comando.getParametros()[3]));
        Image imagenEscalada = imagen.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagenReescalada = new ImageIcon(imagenEscalada);
        JLabel image = new JLabel(imagenReescalada);
        

        nombre.setText(personaje.getNombre());
        tipo.setText("Tipo: " + personaje.getTipoPersonaje().toString());
        poder.setText("Poder: " + String.valueOf(personaje.getPoder()) + "%");
        resistencia.setText("Resistencia: " + String.valueOf(personaje.getResistencia()) + "%");
        sanidad.setText("Sanidad: " + String.valueOf(personaje.getSanidad())+ "%");
        porcentajeMapa.setText("Mapa: " + String.valueOf(personaje.getPorcentajeMapa())+ " %");
        
        nombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        tipo.setAlignmentX(Component.CENTER_ALIGNMENT);
        poder.setAlignmentX(Component.CENTER_ALIGNMENT);
        resistencia.setAlignmentX(Component.CENTER_ALIGNMENT);
        sanidad.setAlignmentX(Component.CENTER_ALIGNMENT);
        porcentajeMapa.setAlignmentX(Component.CENTER_ALIGNMENT);
        image.setAlignmentX(Component.CENTER_ALIGNMENT);


        contenedorImagen.add(image);

        contenedorTitulos.add(porcentajeMapa);
        contenedorTitulos.add(nombre);
        contenedorTitulos.add(tipo);
        
        contenedorStats.add(poder);
        contenedorStats.add(resistencia);
        contenedorStats.add(sanidad);

        contenedorTexto.add(contenedorTitulos);
        contenedorTexto.add(contenedorStats);
        nuevoPersonaje.add(contenedorImagen);
        nuevoPersonaje.add(contenedorTexto);


        this.jPanelPersonajes.add(nuevoPersonaje);
        this.listaPersonajes.add(personaje);
        this.usuario.getInterfazPrincipal().writeMessage("Personaje creado con exito\n", comando);

        this.jPanelPersonajes.revalidate();
        this.jPanelPersonajes.repaint();
        this.jPanelPersonajes.updateUI();

        if (this.listaPersonajes.size() == 3) {
            this.crearMatriz();
            llenarPanelStats();
        }

    }


    public Personaje buscarPersonaje(String nombre) {
        
        for (Personaje personaje : listaPersonajes){
            if (personaje.getNombre().equals(nombre)){
                return personaje;
            }
        }
        return null;
    }
    
    public void borrarMensajes(){
        txaBitacora.setText("");
    }
    
    public void writeResultadoAtaque (String string){
        txaBitacora.append(string + "\n");
    }


   public void writeError(String string){
       txaHistorial.append(string);
       txaBitacora.setText(string);
   }
    
    public void writeMessage(String string, Comando comando){
        txaHistorial.append(comando + "\n");
        txaBitacora.setText(string);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPrincipal = new javax.swing.JPanel();
        jPanelPrincipalTablero = new javax.swing.JPanel();
        jPanelTablero = new javax.swing.JPanel();
        jPanelStatsTablero = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelNumVida = new javax.swing.JLabel();
        jPanelStatsPersonaje1 = new javax.swing.JPanel();
        jLabelNombrePersonaje1 = new javax.swing.JLabel();
        jLabelPorcentajeOcupadoPersonaje1 = new javax.swing.JLabel();
        jLabelCasillasOcupadasPersonaje1 = new javax.swing.JLabel();
        jPanelStatsPersonaje2 = new javax.swing.JPanel();
        jLabelPorcentajeOcupadoPersonaje2 = new javax.swing.JLabel();
        jLabelNombrePersonaje2 = new javax.swing.JLabel();
        jLabelCasillasOcupadasPersonaje2 = new javax.swing.JLabel();
        jPanelStatsPersonaje3 = new javax.swing.JPanel();
        jLabelPorcentajeOcupadoPersonaje3 = new javax.swing.JLabel();
        jLabelNombrePersonaje3 = new javax.swing.JLabel();
        jLabelCasillasOcupadasPersonaje3 = new javax.swing.JLabel();
        jLabelVidaTablero = new javax.swing.JLabel();
        jLabelNumVidaTablero = new javax.swing.JLabel();
        jPanelPersonajes = new javax.swing.JPanel();
        jLabelBitacora = new javax.swing.JPanel();
        jLabelBitaco = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaBitacora = new javax.swing.JTextArea();
        jPanelHistorial = new javax.swing.JPanel();
        jLabelHistorial = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaHistorial = new javax.swing.JTextArea();
        jPanelConsola = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaComandoActual = new javax.swing.JTextArea();
        jPanelConsolaSecundaria = new javax.swing.JPanel();
        txfComando = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelPrincipal.setBackground(new java.awt.Color(204, 204, 204));
        jPanelPrincipal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jPanelPrincipalTablero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jPanelTablero.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout jPanelTableroLayout = new javax.swing.GroupLayout(jPanelTablero);
        jPanelTablero.setLayout(jPanelTableroLayout);
        jPanelTableroLayout.setHorizontalGroup(
            jPanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        jPanelTableroLayout.setVerticalGroup(
            jPanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );

        jPanelStatsTablero.setBackground(new java.awt.Color(255, 204, 102));
        jPanelStatsTablero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanelStatsTablero.setName(""); // NOI18N
        jPanelStatsTablero.setPreferredSize(new java.awt.Dimension(500, 140));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel1.setText("Casillas Destruidas: ");

        jLabelNumVida.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabelNumVida.setText("0");

        jPanelStatsPersonaje1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanelStatsPersonaje1.setOpaque(false);
        jPanelStatsPersonaje1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNombrePersonaje1.setBackground(new java.awt.Color(254, 254, 254));
        jLabelNombrePersonaje1.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabelNombrePersonaje1.setFocusable(false);
        jPanelStatsPersonaje1.add(jLabelNombrePersonaje1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 128, 32));

        jLabelPorcentajeOcupadoPersonaje1.setBackground(new java.awt.Color(254, 254, 254));
        jLabelPorcentajeOcupadoPersonaje1.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabelPorcentajeOcupadoPersonaje1.setFocusable(false);
        jPanelStatsPersonaje1.add(jLabelPorcentajeOcupadoPersonaje1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 44, 88, 16));

        jLabelCasillasOcupadasPersonaje1.setBackground(new java.awt.Color(254, 254, 254));
        jLabelCasillasOcupadasPersonaje1.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabelCasillasOcupadasPersonaje1.setFocusable(false);
        jPanelStatsPersonaje1.add(jLabelCasillasOcupadasPersonaje1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 66, 134, 16));

        jPanelStatsPersonaje2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanelStatsPersonaje2.setOpaque(false);

        jLabelPorcentajeOcupadoPersonaje2.setBackground(new java.awt.Color(254, 254, 254));
        jLabelPorcentajeOcupadoPersonaje2.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabelPorcentajeOcupadoPersonaje2.setFocusable(false);

        jLabelNombrePersonaje2.setBackground(new java.awt.Color(254, 254, 254));
        jLabelNombrePersonaje2.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabelNombrePersonaje2.setFocusable(false);

        jLabelCasillasOcupadasPersonaje2.setBackground(new java.awt.Color(0, 204, 204));
        jLabelCasillasOcupadasPersonaje2.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabelCasillasOcupadasPersonaje2.setToolTipText("");
        jLabelCasillasOcupadasPersonaje2.setFocusable(false);

        javax.swing.GroupLayout jPanelStatsPersonaje2Layout = new javax.swing.GroupLayout(jPanelStatsPersonaje2);
        jPanelStatsPersonaje2.setLayout(jPanelStatsPersonaje2Layout);
        jPanelStatsPersonaje2Layout.setHorizontalGroup(
            jPanelStatsPersonaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatsPersonaje2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabelPorcentajeOcupadoPersonaje2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelStatsPersonaje2Layout.createSequentialGroup()
                .addGroup(jPanelStatsPersonaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelStatsPersonaje2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelNombrePersonaje2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelStatsPersonaje2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelCasillasOcupadasPersonaje2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelStatsPersonaje2Layout.setVerticalGroup(
            jPanelStatsPersonaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatsPersonaje2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNombrePersonaje2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPorcentajeOcupadoPersonaje2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCasillasOcupadasPersonaje2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanelStatsPersonaje3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanelStatsPersonaje3.setOpaque(false);

        jLabelPorcentajeOcupadoPersonaje3.setBackground(new java.awt.Color(153, 255, 255));
        jLabelPorcentajeOcupadoPersonaje3.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabelPorcentajeOcupadoPersonaje3.setFocusable(false);

        jLabelNombrePersonaje3.setBackground(new java.awt.Color(0, 102, 102));
        jLabelNombrePersonaje3.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabelNombrePersonaje3.setFocusable(false);

        jLabelCasillasOcupadasPersonaje3.setBackground(new java.awt.Color(0, 204, 204));
        jLabelCasillasOcupadasPersonaje3.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabelCasillasOcupadasPersonaje3.setFocusable(false);

        javax.swing.GroupLayout jPanelStatsPersonaje3Layout = new javax.swing.GroupLayout(jPanelStatsPersonaje3);
        jPanelStatsPersonaje3.setLayout(jPanelStatsPersonaje3Layout);
        jPanelStatsPersonaje3Layout.setHorizontalGroup(
            jPanelStatsPersonaje3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatsPersonaje3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabelNombrePersonaje3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanelStatsPersonaje3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabelPorcentajeOcupadoPersonaje3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelStatsPersonaje3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelCasillasOcupadasPersonaje3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelStatsPersonaje3Layout.setVerticalGroup(
            jPanelStatsPersonaje3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelStatsPersonaje3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelNombrePersonaje3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPorcentajeOcupadoPersonaje3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCasillasOcupadasPersonaje3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabelVidaTablero.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabelVidaTablero.setText("VIDA: ");

        jLabelNumVidaTablero.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N

        javax.swing.GroupLayout jPanelStatsTableroLayout = new javax.swing.GroupLayout(jPanelStatsTablero);
        jPanelStatsTablero.setLayout(jPanelStatsTableroLayout);
        jPanelStatsTableroLayout.setHorizontalGroup(
            jPanelStatsTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatsTableroLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelStatsTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelVidaTablero)
                    .addGroup(jPanelStatsTableroLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabelNumVidaTablero)))
                .addGap(93, 93, 93)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(jLabelNumVida))
            .addGroup(jPanelStatsTableroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelStatsPersonaje1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jPanelStatsPersonaje2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanelStatsPersonaje3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelStatsTableroLayout.setVerticalGroup(
            jPanelStatsTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatsTableroLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelStatsTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelVidaTablero)
                    .addComponent(jLabelNumVidaTablero)
                    .addComponent(jLabel1)
                    .addComponent(jLabelNumVida))
                .addGap(2, 2, 2)
                .addGroup(jPanelStatsTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelStatsPersonaje1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelStatsPersonaje3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelStatsTableroLayout.createSequentialGroup()
                        .addComponent(jPanelStatsPersonaje2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanelPrincipalTableroLayout = new javax.swing.GroupLayout(jPanelPrincipalTablero);
        jPanelPrincipalTablero.setLayout(jPanelPrincipalTableroLayout);
        jPanelPrincipalTableroLayout.setHorizontalGroup(
            jPanelPrincipalTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalTableroLayout.createSequentialGroup()
                .addGroup(jPanelPrincipalTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelStatsTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelPrincipalTableroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelPrincipalTableroLayout.setVerticalGroup(
            jPanelPrincipalTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalTableroLayout.createSequentialGroup()
                .addComponent(jPanelTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelStatsTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelPersonajes.setBackground(new java.awt.Color(204, 204, 204));
        jPanelPersonajes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanelPersonajes.setFocusable(false);
        jPanelPersonajes.setLayout(new javax.swing.BoxLayout(jPanelPersonajes, javax.swing.BoxLayout.LINE_AXIS));

        jLabelBitacora.setBackground(new java.awt.Color(0, 102, 102));
        jLabelBitacora.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLabelBitacora.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBitaco.setFont(new java.awt.Font("Liberation Sans", 1, 30)); // NOI18N
        jLabelBitaco.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBitaco.setText("Bitácora");
        jLabelBitacora.add(jLabelBitaco, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txaBitacora.setColumns(20);
        txaBitacora.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        txaBitacora.setRows(5);
        jScrollPane1.setViewportView(txaBitacora);

        jLabelBitacora.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 220, 160));

        jPanelHistorial.setBackground(new java.awt.Color(204, 255, 204));
        jPanelHistorial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanelHistorial.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHistorial.setFont(new java.awt.Font("Liberation Sans", 1, 30)); // NOI18N
        jLabelHistorial.setText("Historial");
        jPanelHistorial.add(jLabelHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txaHistorial.setColumns(20);
        txaHistorial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txaHistorial.setRows(5);
        jScrollPane2.setViewportView(txaHistorial);

        jPanelHistorial.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 220, 190));

        jPanelConsola.setBackground(new java.awt.Color(153, 153, 153));
        jPanelConsola.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanelConsola.setForeground(new java.awt.Color(255, 255, 255));
        jPanelConsola.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setOpaque(false);

        txaComandoActual.setColumns(20);
        txaComandoActual.setRows(5);
        txaComandoActual.setOpaque(false);
        jScrollPane3.setViewportView(txaComandoActual);

        jPanelConsola.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 990, 110));

        txfComando.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txfComando.setOpaque(true);
        txfComando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfComandoActionPerformed(evt);
            }
        });

        btnEnviar.setText("Enviar");
        btnEnviar.setOpaque(true);
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelConsolaSecundariaLayout = new javax.swing.GroupLayout(jPanelConsolaSecundaria);
        jPanelConsolaSecundaria.setLayout(jPanelConsolaSecundariaLayout);
        jPanelConsolaSecundariaLayout.setHorizontalGroup(
            jPanelConsolaSecundariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsolaSecundariaLayout.createSequentialGroup()
                .addComponent(txfComando, javax.swing.GroupLayout.PREFERRED_SIZE, 943, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnEnviar))
        );
        jPanelConsolaSecundariaLayout.setVerticalGroup(
            jPanelConsolaSecundariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsolaSecundariaLayout.createSequentialGroup()
                .addComponent(btnEnviar)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(txfComando, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                    .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanelHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                        .addComponent(jLabelBitacora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, 0)
                    .addComponent(jPanelPrincipalTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanelPersonajes, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jPanelConsola, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsolaSecundaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabelBitacora, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanelHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelPrincipalTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelPersonajes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelConsola, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelConsolaSecundaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    //#########################################################################################3

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        String msg = txfComando.getText().trim();
        Personaje personajeActual = null;
        Comando comando = null;
        if (msg.length() > 0){
            String args[] = ComandoUtilidad.tokenizerArgs(msg);
            if(args.length > 0){
                if (args[0].toUpperCase().equals("ATAQUE")){
                    if (this.listaPersonajes.size() == 0){
                        comando = ComandoFabrica.getComando(args, this.usuario.getNombre());
                    }
                    for (Personaje personaje : this.listaPersonajes){
                        if (args[2].equals(personaje.getNombre())){
                            personajeActual = personaje;
                            break;
                        }
                    }
                    if (personajeActual == null){
                        comando = ComandoFabrica.getComando(args,this.usuario.getNombre());
                    } else  {
                        comando = ComandosAtaquesFabrica.getComandoAtaque(args, this.usuario.getNombre(), personajeActual);
                        this.usuario.getResultadoAtaqueEnviado().add("Se envio el ataque [" + args[3] + "] al usuario " + args[1]);
                        this.usuario.getResultadosHistorialAtaques().add("Se envio el ataque [" + args[3] + "] al usuario " + args[1]);
                    }
                }
                else {
                    if (args[0].equals("SANAR") || args[0].equals("DEFENSA")) {
                        Personaje personaje = buscarPersonaje(args[1]);
                        if (personaje != null) {
                            
                            if (args[0].equals("SANAR")) {
                                int cantidadCuracion = personaje.getSanidad();
                                curarCasillas(cantidadCuracion);
                            }
                            else if (args[0].equals("DEFENSA")) {
                                int defensa = personaje.getResistencia();
                                aumentarDefensa(defensa);
                            }
                            try {
                                this.usuario.getObjetoEscritor()
                                        .writeObject(new ComandoSaltarTurno(new String[0], this.usuario.getNombre()));
                                        this.getUsuario().setDefensa(1);
                                return;
                            } catch (Exception e) {
                            }
                        }
                    }    
                comando = ComandoFabrica.getComando(args, this.usuario.getNombre());
                    System.out.println("ts5");
                }
                if (comando != null){
                    try {
                        this.usuario.getObjetoEscritor().writeObject(comando);
                        System.out.println("ts6");
                        System.out.println(comando);
                    } catch (IOException ex){
                    
                    }
                } else {
                    this.txaHistorial.append("Error: comando desconocido\n");
                }
            }
        } 
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void txfComandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfComandoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfComandoActionPerformed
    
    public boolean ataqueThreeNumbers(int[] numeros){
        int contador = 0;
        
        while (contador < 3) {
           String name = JOptionPane.showInputDialog(this, "Ingrese un numero entre 1 a 9");
           try{
               int valorActual = Integer.parseInt(name);
               if (valorActual >= 10){
                   this.writeError("ERROR!!! ELIJE UN NUMERO ENTRE 1 A 9");
                   continue;
               }
               for (int num : numeros){
                    if (num == valorActual){
                        return true;
                    }
           }
               contador++;
           } catch(NumberFormatException e){
               this.writeError("ERROR!!! FORMATO NO VALIDO, ELIJE UN NUMERO");
           }
       }
        return false;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new InterfazPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelBitaco;
    private javax.swing.JPanel jLabelBitacora;
    private javax.swing.JLabel jLabelCasillasOcupadasPersonaje1;
    private javax.swing.JLabel jLabelCasillasOcupadasPersonaje2;
    private javax.swing.JLabel jLabelCasillasOcupadasPersonaje3;
    private javax.swing.JLabel jLabelHistorial;
    private javax.swing.JLabel jLabelNombrePersonaje1;
    private javax.swing.JLabel jLabelNombrePersonaje2;
    private javax.swing.JLabel jLabelNombrePersonaje3;
    private javax.swing.JLabel jLabelNumVida;
    private javax.swing.JLabel jLabelNumVidaTablero;
    private javax.swing.JLabel jLabelPorcentajeOcupadoPersonaje1;
    private javax.swing.JLabel jLabelPorcentajeOcupadoPersonaje2;
    private javax.swing.JLabel jLabelPorcentajeOcupadoPersonaje3;
    private javax.swing.JLabel jLabelVidaTablero;
    private javax.swing.JPanel jPanelConsola;
    private javax.swing.JPanel jPanelConsolaSecundaria;
    private javax.swing.JPanel jPanelHistorial;
    private javax.swing.JPanel jPanelPersonajes;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelPrincipalTablero;
    private javax.swing.JPanel jPanelStatsPersonaje1;
    private javax.swing.JPanel jPanelStatsPersonaje2;
    private javax.swing.JPanel jPanelStatsPersonaje3;
    private javax.swing.JPanel jPanelStatsTablero;
    private javax.swing.JPanel jPanelTablero;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea txaBitacora;
    private javax.swing.JTextArea txaComandoActual;
    private javax.swing.JTextArea txaHistorial;
    private javax.swing.JTextField txfComando;
    // End of variables declaration//GEN-END:variables


    public ArrayList<Personaje> getListaPersonajes() {
        return listaPersonajes;
    }

    public int[] getStatsPoder() {
        return statsPoder;
    }
    
    public int[] getStatsResistencia() {
        return statsResistencia;
    }
    public int[] getStatsSanidad() {
        return statsSanidad;
    }

    public int getPorcentajeOcupadoMapa() {
        return porcentajeOcupadoMapa;
    }

    public void setPorcentajeOcupadoMapa(int porcentajeOcupadoMapa) {
        this.porcentajeOcupadoMapa = porcentajeOcupadoMapa;
    }

    public int getContadorPersonaje1() {
        return contadorPersonaje1;
    }

    public void setContadorPersonaje1(int contadorPersonaje1) {
        this.contadorPersonaje1 = contadorPersonaje1;
    }

    public int getContadorPersonaje2() {
        return contadorPersonaje2;
    }

    public void setContadorPersonaje2(int contadorPersonaje2) {
        this.contadorPersonaje2 = contadorPersonaje2;
    }

    public int getContadorPersonaje3() {
        return contadorPersonaje3;
    }

    public void setContadorPersonaje3(int contadorPersonaje3) {
        this.contadorPersonaje3 = contadorPersonaje3;
    }

    public Celda[][] getCeldas() {
        return celdas;
    }

    public void setCeldas(Celda[][] celdas) {
        this.celdas = celdas;
    }

    public JLabel[][] getTablero() {
        return tablero;
    }

    public void setTablero(JLabel[][] tablero) {
        this.tablero = tablero;
    }

    public String[][] getOcupados() {
        return ocupados;
    }

    public void setOcupados(String[][] ocupados) {
        this.ocupados = ocupados;
    }

    public ArrayList<String> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<String> historial) {
        this.historial = historial;
    }

    public PantallaServer getServidor() {
        return servidor;
    }

    public void setServidor(PantallaServer servidor) {
        this.servidor = servidor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getRangoUltimoRemolino() {
        return this.rangoUltimoRemolino;
    }
    
    public void setRangoUltimoRemolino(int rango) {
        this.rangoUltimoRemolino = rango;
    }

    public int getRangoUltimoVolcan() {
        return this.rangoUltimoVolcan;
    }

    public JButton getBtnEnviar() {
        return btnEnviar;
    }

    public void setBtnEnviar(JButton btnEnviar) {
        this.btnEnviar = btnEnviar;
    }
    
    
    public void setRangoUltimoVolcan(int rango) {
        this.rangoUltimoVolcan= rango;
    }

    public JTextArea getTxaBitacora() {
        return txaBitacora;
    }

    public void setTxaBitacora(JTextArea txaBitacora) {
        this.txaBitacora = txaBitacora;
    }

    public JTextArea getTxaComandoActual() {
        return txaComandoActual;
    }

    public void setTxaComandoActual(JTextArea txaComandoActual) {
        this.txaComandoActual = txaComandoActual;
    }

    public JTextArea getTxaHistorial() {
        return txaHistorial;
    }

    public void setTxaHistorial(JTextArea txaHistorial) {
        this.txaHistorial = txaHistorial;
    }

    public JTextField getTxfComando() {
        return txfComando;
    }

    public void setTxfComando(JTextField txfComando) {
        this.txfComando = txfComando;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
    
    




}
