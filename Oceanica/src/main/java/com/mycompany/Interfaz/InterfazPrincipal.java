/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.Interfaz;

import com.mycompany.Personaje.Personaje;
import com.mycompany.Personaje.TipoPersonaje;
import com.mycompany.Personaje.TipoPersonajeFabrica;
import com.mycompany.oceanica.Modelos.Comando;
import com.mycompany.oceanica.Modelos.ComandoCrearPersonaje;
import com.mycompany.oceanica.Modelos.ComandoCrearPersonajeErrores;
import com.mycompany.oceanica.Modelos.ComandoError;
import com.mycompany.oceanica.Modelos.ComandoFabrica;
import com.mycompany.oceanica.Modelos.ComandoUtilidad;
import com.mycompany.oceanica.Server.PantallaServer;
import com.mycompany.oceanica.Usuario.Usuario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

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
    private ArrayList<String> historial = new ArrayList<>();
    
    
   
    private PantallaServer servidor;
    
    private Usuario usuario;
    
    
    /**
     * Creates new form InterfazPrincipal
     */
    public InterfazPrincipal() {
        initComponents();
        jPanelPersonajes.setLayout(new BoxLayout(jPanelPersonajes, BoxLayout.Y_AXIS));
        while(true){
            String name = JOptionPane.showInputDialog(this, "Ingrese su nombre");
            if (name.length()>0){
                this.setTitle(name);
                usuario = new Usuario(this, name);
                break;
            }
        }
        llenarStats();
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
                            if (this.contadorPersonaje1 < 400*0.5){
                                contadorPersonaje1++;
                                celdas[fila][columna] = new Celda(nuevoLabel,listaPersonajes.get(0), fila, columna);
                                nuevoLabel.setBackground(new Color(20,20,200));
                                isPintado = true;
                            }
                            break;
                        case 1:
                            if (this.contadorPersonaje2 < 400*0.3){
                                contadorPersonaje2++;
                                celdas[fila][columna] = new Celda(nuevoLabel,listaPersonajes.get(1), fila, columna);
                                nuevoLabel.setBackground(new Color(200,20,20));
                                isPintado = true;
                            }
                            break;
                     case 2:
                            if (this.contadorPersonaje3 < 400*0.2){
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
                     @Override
                    public void mouseEntered(MouseEvent e) {
                        nuevoLabel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
                        nuevoLabel.setBackground(colorOriginal.brighter());
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        nuevoLabel.setBorder(null);
                        nuevoLabel.setBackground(colorOriginal);
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
    
    
    public void crearPersonajes(ComandoCrearPersonaje comando){
        if (this.listaPersonajes.size() == 3){
                ComandoCrearPersonajeErrores.error("!!!ERROR!!! Ya se crearon los 3 personajes posibles", this.usuario, comando);
        }
        jPanelPersonajes.setPreferredSize(new Dimension(300,200));
        
        for (int i = 0; i < 3; i++){
            Personaje personaje = new Personaje(this);
            if (!this.asignarValoresPersonaje(comando, personaje)){
                return;
            }
            Dimension dimension = new Dimension(60, 60);
            
            JPanel nuevoPersonaje = new JPanel();
            nuevoPersonaje.setPreferredSize(dimension);
           
            JLabel poder = new JLabel();
            JLabel resistencia = new JLabel();
            JLabel sanidad = new JLabel();
            JLabel nombre = new JLabel();
            JLabel tipo = new JLabel();
            JLabel porcentajeMapa = new JLabel();
            ImageIcon imagen = new ImageIcon(getClass().getResource(comando.getParametros()[3]));
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
            if (this.listaPersonajes.size() == 3){
                this.crearMatriz();
            }
        }

    }
    
    
    public boolean asignarValoresPersonaje(ComandoCrearPersonaje comando, Personaje personaje){
        try{
            personaje.setNombre((String) comando.getParametros()[7]);
            personaje.setPoder(Integer.parseInt(comando.getParametros()[4]));
            personaje.setResistencia(Integer.parseInt(comando.getParametros()[5]));
            personaje.setSanidad(Integer.parseInt(comando.getParametros()[6]));
            if (!this.validarEstadisticas(personaje, comando)){
                return false;
            }
            String tipo = comando.getParametros()[1].toUpperCase();
            TipoPersonajeFabrica.getTipoPersonaje(tipo, personaje);
            if (personaje.getTipoPersonaje().equals(null)){
                ComandoCrearPersonajeErrores.error("!!!ERROR!!! Tipo de personaje no existente", this.usuario, comando);
                return false;
            }
        } catch(NumberFormatException ex){
                ComandoCrearPersonajeErrores.error("!!!ERROR!!! Tipo de formato no valido", this.usuario, comando);
                return false;
        }
        return true;
    }
    
    public boolean validarEstadisticas(Personaje personaje, ComandoCrearPersonaje comando){
        if (listaPersonajes.size() > 1){
            for (Personaje personajeActual : this.getListaPersonajes()){
                if (personaje.getPoder() == personajeActual.getPoder() || personaje.getResistencia() == personajeActual.getResistencia() || personaje.getSanidad() == personajeActual.getSanidad())
                    ComandoCrearPersonajeErrores.error("!!!ERROR!!! Estadistica usada en otro personaje", this.usuario, comando);
                    return false;
                    }
        }
        if (!this.validarPoder(personaje)){
            ComandoCrearPersonajeErrores.error("!!!ERROR!!! La estadistica poder no es valida", this.usuario, comando);
            return false;
        }
        
        if (!this.validarResistencia(personaje)){
            ComandoCrearPersonajeErrores.error("!!!ERROR!!! La estadistica poder no es valida", this.usuario, comando);
            return false;
        }
        
        if (!this.validarSanidad(personaje)){
            ComandoCrearPersonajeErrores.error("!!!ERROR!!! La estadistica poder no es valida", this.usuario, comando);
            return false;
        }
        
        return true;
    }
    
    
    public boolean validarPoder(Personaje personaje){
        if (this.statsPoder[0] == personaje.getPoder() || this.statsPoder[1] == personaje.getPoder() || this.statsPoder[2] == personaje.getPoder()){
            return true;
        }
        return false;
}
    public boolean validarResistencia(Personaje personaje){
        if (this.statsResistencia[0] == personaje.getResistencia() || this.statsResistencia[1] == personaje.getResistencia() || this.statsResistencia[2] == personaje.getResistencia()){
            return true;
        }
        return false;
}
    public boolean validarSanidad(Personaje personaje){
        if (this.statsSanidad[0] == personaje.getSanidad() || this.statsSanidad[1] == personaje.getSanidad() || this.statsSanidad[2] == personaje.getSanidad()){
            return true;
        }
        return false;
}
    
    
    public void atacarCelda(int ataque, Celda celda){
        celda.recibirAtaqueDirecto(ataque);
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
        jPanelStatsTablero.setName(""); // NOI18N
        jPanelStatsTablero.setPreferredSize(new java.awt.Dimension(500, 140));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel1.setText("Casillas Destruidas: ");

        jLabelNumVida.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabelNumVida.setText("0");

        jPanelStatsPersonaje1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNombrePersonaje1.setBackground(new java.awt.Color(0, 102, 102));
        jLabelNombrePersonaje1.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabelNombrePersonaje1.setOpaque(true);
        jPanelStatsPersonaje1.add(jLabelNombrePersonaje1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 128, 32));

        jLabelPorcentajeOcupadoPersonaje1.setBackground(new java.awt.Color(153, 255, 255));
        jLabelPorcentajeOcupadoPersonaje1.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabelPorcentajeOcupadoPersonaje1.setOpaque(true);
        jPanelStatsPersonaje1.add(jLabelPorcentajeOcupadoPersonaje1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 44, 88, 16));

        jLabelCasillasOcupadasPersonaje1.setBackground(new java.awt.Color(0, 204, 204));
        jLabelCasillasOcupadasPersonaje1.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabelCasillasOcupadasPersonaje1.setOpaque(true);
        jPanelStatsPersonaje1.add(jLabelCasillasOcupadasPersonaje1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 66, 134, 16));

        jPanelStatsPersonaje2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPorcentajeOcupadoPersonaje2.setBackground(new java.awt.Color(153, 255, 255));
        jLabelPorcentajeOcupadoPersonaje2.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabelPorcentajeOcupadoPersonaje2.setOpaque(true);
        jPanelStatsPersonaje2.add(jLabelPorcentajeOcupadoPersonaje2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 88, 16));

        jLabelNombrePersonaje2.setBackground(new java.awt.Color(0, 102, 102));
        jLabelNombrePersonaje2.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabelNombrePersonaje2.setOpaque(true);
        jPanelStatsPersonaje2.add(jLabelNombrePersonaje2, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 10, 130, 32));

        jLabelCasillasOcupadasPersonaje2.setBackground(new java.awt.Color(0, 204, 204));
        jLabelCasillasOcupadasPersonaje2.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabelCasillasOcupadasPersonaje2.setOpaque(true);
        jPanelStatsPersonaje2.add(jLabelCasillasOcupadasPersonaje2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 134, 16));

        jLabelPorcentajeOcupadoPersonaje3.setBackground(new java.awt.Color(153, 255, 255));
        jLabelPorcentajeOcupadoPersonaje3.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabelPorcentajeOcupadoPersonaje3.setOpaque(true);

        jLabelNombrePersonaje3.setBackground(new java.awt.Color(0, 102, 102));
        jLabelNombrePersonaje3.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabelNombrePersonaje3.setOpaque(true);

        jLabelCasillasOcupadasPersonaje3.setBackground(new java.awt.Color(0, 204, 204));
        jLabelCasillasOcupadasPersonaje3.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabelCasillasOcupadasPersonaje3.setOpaque(true);

        javax.swing.GroupLayout jPanelStatsPersonaje3Layout = new javax.swing.GroupLayout(jPanelStatsPersonaje3);
        jPanelStatsPersonaje3.setLayout(jPanelStatsPersonaje3Layout);
        jPanelStatsPersonaje3Layout.setHorizontalGroup(
            jPanelStatsPersonaje3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatsPersonaje3Layout.createSequentialGroup()
                .addGroup(jPanelStatsPersonaje3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelStatsPersonaje3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelNombrePersonaje3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelStatsPersonaje3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabelPorcentajeOcupadoPersonaje3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelCasillasOcupadasPersonaje3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        jPanelStatsPersonaje3Layout.setVerticalGroup(
            jPanelStatsPersonaje3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelStatsPersonaje3Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jLabelNombrePersonaje3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabelPorcentajeOcupadoPersonaje3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabelCasillasOcupadasPersonaje3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabelVidaTablero.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabelVidaTablero.setText("VIDA: ");

        jLabelNumVidaTablero.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabelNumVidaTablero.setText("100%");

        javax.swing.GroupLayout jPanelStatsTableroLayout = new javax.swing.GroupLayout(jPanelStatsTablero);
        jPanelStatsTablero.setLayout(jPanelStatsTableroLayout);
        jPanelStatsTableroLayout.setHorizontalGroup(
            jPanelStatsTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatsTableroLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelStatsTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelStatsTableroLayout.createSequentialGroup()
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
                        .addComponent(jPanelStatsPersonaje1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jPanelStatsPersonaje2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jPanelStatsPersonaje3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(jPanelStatsPersonaje2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelStatsPersonaje3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanelPrincipalTableroLayout = new javax.swing.GroupLayout(jPanelPrincipalTablero);
        jPanelPrincipalTablero.setLayout(jPanelPrincipalTableroLayout);
        jPanelPrincipalTableroLayout.setHorizontalGroup(
            jPanelPrincipalTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanelStatsTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanelPrincipalTableroLayout.setVerticalGroup(
            jPanelPrincipalTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalTableroLayout.createSequentialGroup()
                .addComponent(jPanelTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelStatsTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelPersonajes.setBackground(new java.awt.Color(0, 51, 102));
        jPanelPersonajes.setLayout(new javax.swing.BoxLayout(jPanelPersonajes, javax.swing.BoxLayout.LINE_AXIS));

        jLabelBitacora.setBackground(new java.awt.Color(0, 102, 102));
        jLabelBitacora.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBitaco.setFont(new java.awt.Font("Liberation Sans", 1, 30)); // NOI18N
        jLabelBitaco.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBitaco.setText("Bitácora");
        jLabelBitacora.add(jLabelBitaco, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txaBitacora.setColumns(20);
        txaBitacora.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        txaBitacora.setRows(5);
        jScrollPane1.setViewportView(txaBitacora);

        jLabelBitacora.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 260, 160));

        jPanelHistorial.setBackground(new java.awt.Color(204, 255, 204));
        jPanelHistorial.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHistorial.setFont(new java.awt.Font("Liberation Sans", 1, 30)); // NOI18N
        jLabelHistorial.setText("Historial");
        jPanelHistorial.add(jLabelHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txaHistorial.setColumns(20);
        txaHistorial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txaHistorial.setRows(5);
        jScrollPane2.setViewportView(txaHistorial);

        jPanelHistorial.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 260, 190));

        jPanelConsola.setBackground(new java.awt.Color(153, 153, 153));
        jPanelConsola.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txaComandoActual.setColumns(20);
        txaComandoActual.setRows(5);
        jScrollPane3.setViewportView(txaComandoActual);

        jPanelConsola.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 990, 110));

        jPanelConsolaSecundaria.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPrincipalLayout.createSequentialGroup()
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelBitacora, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jPanelPrincipalTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelPersonajes, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanelConsola, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanelConsolaSecundaria, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jPanelPersonajes, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelConsola, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelConsolaSecundaria, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txfComando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfComandoActionPerformed(evt);
            }
        });

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 1009, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txfComando, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnEnviar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfComando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnviar))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        String msg = txfComando.getText().trim();
        if (msg.length() > 0){
            String args[] = ComandoUtilidad.tokenizerArgs(msg);
            if(args.length > 0){
                Comando comando = ComandoFabrica.getComando(args,usuario.getNombre());
                if (comando != null){
                    try{
                        usuario.getObjetoEscritor().writeObject(comando);
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
        this.porcentajeOcupadoMapa += porcentajeOcupadoMapa;
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
    
    




}
