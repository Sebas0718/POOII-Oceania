/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.Interfaz;

import com.mycompany.Personaje.Personaje;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

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
    
    private JLabel [][] tablero = new JLabel [20][20];
    private String[][] ocupados = new String[20][20];
    private ArrayList<String> historial = new ArrayList<>();
    
    
    
    
    /**
     * Creates new form InterfazPrincipal
     */
    public InterfazPrincipal() {
        initComponents();
        jPanelPersonajes.setLayout(new BoxLayout(jPanelPersonajes, BoxLayout.Y_AXIS));
        llenarStats();
        crearPersonajes();
        crearMatriz();
        
    }
    
    public void crearMatriz() {
        jPanelPrincipalTablero.setLayout(new GridLayout(20, 20, 2, 2)); // Distribuir 25x25
        jPanelPrincipalTablero.removeAll();


        for (int fila = 0; fila < 20; fila++) {
            for (int columna = 0; columna < 20; columna++) {

                final int filaCelda = fila;
                final int columnaCelda = columna;
                
                JLabel nuevoLabel = new JLabel();
                nuevoLabel.setBounds(fila * 30, columna * 30, 30, 30);
                nuevoLabel.setOpaque(true);
                nuevoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                nuevoLabel.setPreferredSize(new Dimension(20, 20)); // opcional
                nuevoLabel.setBackground(new Color(0, 100, 0)); // color base
                nuevoLabel.setHorizontalAlignment(SwingConstants.CENTER);
                nuevoLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel l = (JLabel) e.getSource();
                    Object entidad = l.getClientProperty("entidad");
                    if (entidad != null) {
                        JLabel jlabel = new JLabel(entidad.toString());
                        jPanelBitacora.add(jlabel);
                    }
                }
            });
               

                if (columna % 2 != 0 && fila % 2 == 0) {
                    nuevoLabel.setBackground(new Color(20, 153, 20));
                }
                nuevoLabel.addMouseListener(new MouseAdapter(){
                    Color colorOriginal = nuevoLabel.getBackground();
                     @Override
                    public void mouseEntered(MouseEvent e) {
                        // Simular relieve o iluminación
                        nuevoLabel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
                        nuevoLabel.setBackground(colorOriginal.brighter());
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        // Simular relieve o iluminación
                        nuevoLabel.setBorder(null);
                        nuevoLabel.setBackground(colorOriginal);
                    }

 
                    
                    
            });
                jPanelPrincipalTablero.add(nuevoLabel);
                tablero[fila][columna] = nuevoLabel;
            }   
        }
        jPanelPrincipalTablero.revalidate();
        jPanelPrincipalTablero.repaint();
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
    
    
    public void crearPersonajes(){
        
        jPanelPersonajes.setPreferredSize(new Dimension(300,200));
        
        for (int i = 0; i < 3; i++){
            Personaje personaje = new Personaje(this);
            Dimension dimension = new Dimension(60, 60);
            
            JPanel nuevoPersonaje = new JPanel();
            nuevoPersonaje.setPreferredSize(dimension);
           
            JLabel poder = new JLabel();
            JLabel resistencia = new JLabel();
            JLabel sanidad = new JLabel();
            JLabel nombre = new JLabel();
            JLabel tipo = new JLabel();
            JLabel porcentajeMapa = new JLabel();
            ImageIcon imagen = new ImageIcon(getClass().getResource("/Imagenes/bobEsponja.png"));
            Image imagenEscalada = imagen.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            ImageIcon imagenReescalada = new ImageIcon(imagenEscalada);
            
            JLabel image = new JLabel(imagenReescalada);
            
            
            
            nombre.setText(personaje.getNombre());
            tipo.setText("Tipo de Ataque: " + personaje.getTipoPersonaje().toString());
            poder.setText("Poder: " + String.valueOf(personaje.getPoder()));
            resistencia.setText("Resistencia: " + String.valueOf(personaje.getResistencia()));
            sanidad.setText("Sanidad: " + String.valueOf(personaje.getSanidad()));
            porcentajeMapa.setText("Mapa: " + String.valueOf(personaje.getPorcentajeMapa()));
            
            nuevoPersonaje.add(nombre);
            nuevoPersonaje.add(tipo);
            nuevoPersonaje.add(poder);
            nuevoPersonaje.add(resistencia);
            nuevoPersonaje.add(sanidad);
            nuevoPersonaje.add(porcentajeMapa);
            nuevoPersonaje.add(image);
            
            
            this.jPanelPersonajes.add(nuevoPersonaje);
            this.listaPersonajes.add(personaje);
            
        }

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
        jPanelPersonajes = new javax.swing.JPanel();
        jPanelBitacora = new javax.swing.JPanel();
        jPanelHistorial = new javax.swing.JPanel();
        jPanelConsola = new javax.swing.JPanel();
        jPanelConsolaSecundaria = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelPrincipal.setBackground(new java.awt.Color(204, 204, 204));

        jPanelTablero.setBackground(new java.awt.Color(255, 255, 204));
        jPanelTablero.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelStatsTablero.setBackground(new java.awt.Color(255, 204, 102));
        jPanelStatsTablero.setMaximumSize(new java.awt.Dimension(500, 140));
        jPanelStatsTablero.setMinimumSize(new java.awt.Dimension(500, 140));
        jPanelStatsTablero.setName(""); // NOI18N
        jPanelStatsTablero.setPreferredSize(new java.awt.Dimension(500, 140));

        javax.swing.GroupLayout jPanelStatsTableroLayout = new javax.swing.GroupLayout(jPanelStatsTablero);
        jPanelStatsTablero.setLayout(jPanelStatsTableroLayout);
        jPanelStatsTableroLayout.setHorizontalGroup(
            jPanelStatsTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        jPanelStatsTableroLayout.setVerticalGroup(
            jPanelStatsTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelPrincipalTableroLayout = new javax.swing.GroupLayout(jPanelPrincipalTablero);
        jPanelPrincipalTablero.setLayout(jPanelPrincipalTableroLayout);
        jPanelPrincipalTableroLayout.setHorizontalGroup(
            jPanelPrincipalTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTablero, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanelStatsTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanelPrincipalTableroLayout.setVerticalGroup(
            jPanelPrincipalTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalTableroLayout.createSequentialGroup()
                .addComponent(jPanelTablero, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelStatsTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelPersonajes.setBackground(new java.awt.Color(0, 51, 102));
        jPanelPersonajes.setLayout(new javax.swing.BoxLayout(jPanelPersonajes, javax.swing.BoxLayout.LINE_AXIS));

        jPanelBitacora.setBackground(new java.awt.Color(0, 102, 102));
        jPanelBitacora.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelHistorial.setBackground(new java.awt.Color(204, 255, 204));
        jPanelHistorial.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelConsola.setBackground(new java.awt.Color(153, 153, 153));
        jPanelConsola.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelConsolaSecundaria.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelBitacora, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jPanelPrincipalTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelPersonajes, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanelConsola, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanelConsolaSecundaria, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(jPanelBitacora, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanelHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelPrincipalTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelPersonajes, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jPanelConsola, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelConsolaSecundaria, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 1009, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JPanel jPanelBitacora;
    private javax.swing.JPanel jPanelConsola;
    private javax.swing.JPanel jPanelConsolaSecundaria;
    private javax.swing.JPanel jPanelHistorial;
    private javax.swing.JPanel jPanelPersonajes;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelPrincipalTablero;
    private javax.swing.JPanel jPanelStatsTablero;
    private javax.swing.JPanel jPanelTablero;
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
        this.porcentajeOcupadoMapa += porcentajeOcupadoMapa;
    }
    
    




}
