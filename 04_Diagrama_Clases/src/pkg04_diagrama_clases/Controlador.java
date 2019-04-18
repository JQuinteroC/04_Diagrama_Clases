package pkg04_diagrama_clases;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class Controlador implements ActionListener {

    Vista d;
    Modelo t;
    int nClases;
    int nRelaciones;
    int yC;
    int yD;
    Container cntClases;
    Container cntDiagrama;

    public Controlador(Vista d, Modelo t) {
        this.d = d;
        this.t = t;
        // Estable como maximo 20 clases y relaciones por diagrama
        d.txtClase = new JTextArea[20];
        d.txtMetodo = new JTextArea[20];
        d.txtRelacion = new JTextField[20];
        d.lblEtiquetas = new JLabel[20][3];
        // Iniciacializa varibales
        nClases = 0;
        nRelaciones = 0;
        yC = 0;
        yD = 0;
        // Definir container
        cntClases = new Container();
        cntClases.setLayout(null);
        cntClases.setPreferredSize(new Dimension(845, 250));

        cntDiagrama = new Container();
        cntDiagrama.setLayout(null);
        cntDiagrama.setPreferredSize(new Dimension(845, 300));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (null != e.getActionCommand()) {
            switch (e.getActionCommand()) {
                // <editor-fold defaultstate="collapsed" desc="Crear clase">
                case "Crear clase":
                    if (nClases < 20) {
                        // Declara los nuevos componentes
                        d.txtClase[nClases] = new JTextArea();
                        d.txtMetodo[nClases] = new JTextArea();
                        d.lblEtiquetas[nClases][0] = new JLabel("Nombre y atributos de la clase " + (nClases + 1));
                        d.lblEtiquetas[nClases][0].setFont(new Font("Verdana", 1, 10));
                        d.lblEtiquetas[nClases][1] = new JLabel("Metodos de la clase " + (nClases + 1));
                        d.lblEtiquetas[nClases][1].setFont(new Font("Verdana", 1, 10));
                        // Ubica y dimenciona los componentes
                        d.lblEtiquetas[nClases][0].setBounds(8, 0 + yC, 200, 25);
                        d.lblEtiquetas[nClases][1].setBounds(408, 0 + yC, 180, 25);
                        d.txtClase[nClases].setBounds(10, 20 + yC, 390, 80);
                        d.txtClase[nClases].setText("class ");
                        d.txtMetodo[nClases].setBounds(410, 20 + yC, 390, 80);
                        // Agrega los componentes al contenedor
                        cntClases.add(d.lblEtiquetas[nClases][0]);
                        cntClases.add(d.lblEtiquetas[nClases][1]);
                        cntClases.add(d.txtClase[nClases]);
                        cntClases.add(d.txtMetodo[nClases]);
                        cntClases.setPreferredSize(new Dimension(800, 100 + yC));
                        // Muestra el contenedor en el ScrollPane
                        d.spClases.getViewport().add(cntClases);
                        // Ubicar cursor en el area de texto
                        d.txtClase[nClases].requestFocus();
                        // Variables de control
                        nClases++;
                        yC += 110;
                    } else {
                        JOptionPane.showMessageDialog(null, "No puede ingresar más de 20 clases", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    // </editor-fold>
                    break;
                // <editor-fold defaultstate="collapsed" desc="Crear herencia">    
                case "Crear herencia":
                    if (nRelaciones < 20 && nClases >= 2) {
                        // Declaración de componentes
                        d.txtRelacion[nRelaciones] = new JTextField();
                        d.lblEtiquetas[nClases][2] = new JLabel("Herencia " + (nRelaciones + 1) + " Ej: class A extends B o class A implements B ");
                        d.lblEtiquetas[nClases][2].setFont(new Font("Verdana", 1, 10));
                        // Ubica y dimensiona los componentes
                        d.txtRelacion[nRelaciones].setBounds(10, 20 + yC, 800, 30);
                        d.lblEtiquetas[nClases][2].setBounds(10, yC, 400, 25);
                        // Agrega los componentes al contenedor
                        cntClases.add(d.txtRelacion[nRelaciones]);
                        cntClases.add(d.lblEtiquetas[nClases][2]);
                        cntClases.setPreferredSize(new Dimension(800, 50 + yC));
                        // Muestra el contenedor en el ScrollPane
                        d.spClases.getViewport().add(cntClases);
                        // Ubicar cursor en el area de texto
                        d.txtRelacion[nRelaciones].requestFocus();
                        // Variables de control
                        nRelaciones++;
                        yC += 60;

                    } else if (nClases < 2) {
                        JOptionPane.showMessageDialog(null, "Ingrese al menos 2 clases para poder implementar herencia", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No puede ingresar más de 20 relaciones diferentes", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    // </editor-fold>
                    break;
                // <editor-fold defaultstate="collapsed" desc="Generar diagrama">    
                case "Generar diagrama":
                    if (nClases != 0){
                        
                    }else {
                        JOptionPane.showMessageDialog(null, "Debe ingresar almenos una clase para poder generar el diagrama", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    // </editor-fold>
                    break;
                default:
                    break;
            }
        }
    }
}
