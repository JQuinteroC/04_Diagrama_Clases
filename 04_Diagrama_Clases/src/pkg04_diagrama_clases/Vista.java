package pkg04_diagrama_clases;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
    JLabel lblEtiquetas [][];
    // Imagenes de relaciones
    JLabel lblRelaciones [];
    // Botones
    JButton btnClase = new JButton("Crear clase");
    JButton btnHerencia = new JButton("Crear herencia");
    JButton btnGenerar = new JButton("Generar diagrama");
    // Campos de llenado
    JTextArea txtClase [];
    JTextArea txtMetodo [];
    JTextField txtRelacion[];
    // Estructura de la imagen
    JEditorPane EdtClase [];
    // Secciones de clases y diagrama
    JScrollPane spClases = new JScrollPane();
    JScrollPane spDiagrama = new JScrollPane();
    
    void mostrar() {
        // Titulo
        lblTitulo.setFont(new java.awt.Font("Verdana", 1, 25));
        lblTitulo.setBounds(330, 5, 220, 45);
        // Botones
        btnClase.setBounds(150, 50, 150, 30);
        btnHerencia.setBounds(350, 50, 150, 30);
        btnGenerar.setBounds(550, 50, 150, 30);
        // ScrollPane
        spClases.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clases", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11)));
        spClases.setBounds(20, 90, 845, 250);
        spDiagrama.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Diagrama", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11)));
        spDiagrama.setBounds(20, 350, 845, 300);
        
        setSize(900, 700);
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
        // Crear segmento Scroll de Variables
//        c.add(spValores);
        Container frValores = new Container();
        frValores.setLayout(null);
        frValores.setPreferredSize(new Dimension(700, 45));
//        spValores.getViewport().add(frValores);
        
        // Ingresar componentes a Scroll de Variables
//        frValores.add(txtNumVar);
       // frValores.add(btnCrearCampos);

    }

    void asignaOyentes(Controlador c) {
        btnClase.addActionListener(c);
        btnGenerar.addActionListener(c);
        btnHerencia.addActionListener(c);
     //   btnCrearCampos.addActionListener(c);
    }
}
