package pkg04_diagrama_clases;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class Vista extends JFrame {

    JLabel lblTitulo = new JLabel("Diagramas UML");
    JLabel lblEtiquetas[][];
    JLabel lblImgReferecia = new javax.swing.JLabel();
    // Imagenes de relaciones
    ArrayList<JLabel> lblRelaciones = new ArrayList<>();
    // Botones
    JButton btnClase = new JButton("Crear clase");
    JButton btnHerencia = new JButton("Crear herencia");
    JButton btnGenerar = new JButton("Generar diagrama");
    // Campos de llenado
    JTextArea txtClase[];
    JTextArea txtMetodo[];
    JTextField txtRelacion[];
    // Estructura de la imagen
    ArrayList<JTextPane> EdtClase;
    ArrayList<JPanel> pnlClase;
    // Secciones de clases y diagrama
    JScrollPane spClases = new JScrollPane();
    JScrollPane spDiagrama = new JScrollPane();

    void mostrar() {
// Titulo
        lblTitulo.setFont(new java.awt.Font("Verdana", 1, 25));
        lblTitulo.setBounds(240, 5, 220, 45);
        // Imagen referencia
        lblImgReferecia.setIcon(new javax.swing.ImageIcon("src\\imgReferencia.png"));
        lblImgReferecia.setBounds(10, 490, 670, 175);
        // Botones
        btnClase.setBounds(75, 50, 150, 30);
        btnHerencia.setBounds(275, 50, 150, 30);
        btnGenerar.setBounds(475, 50, 150, 30);
        // ScrollPane
        spClases.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clases", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11)));
        spClases.setBounds(10, 90, 675, 400);
        spDiagrama.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Diagrama", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11)));
        spDiagrama.setBounds(685, 10, 660, 660);

        setSize(1350, 710);     //Ajustar X según pantalla
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Diagrama UML");
        }
        
    public Vista() throws HeadlessException {
        Container c = getContentPane();

        c.setLayout(null);
        c.add(lblTitulo);
        c.add(btnClase);
        c.add(btnHerencia);
        c.add(btnGenerar);
        c.add(spClases);
        c.add(spDiagrama);
        c.add(lblImgReferecia);

        Container frValores = new Container();
        frValores.setLayout(null);
        frValores.setPreferredSize(new Dimension(700, 45));
    }

    void asignaOyentes(Controlador c) {
        btnClase.addActionListener(c);
        btnGenerar.addActionListener(c);
        btnHerencia.addActionListener(c);
        //   btnCrearCampos.addActionListener(c);
    }
}
