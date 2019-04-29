/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg04_diagrama_clases;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author JQuintero
 */
public class DiaClss {
    int x;
    int y;
    
    void d(int i, Vista d, ArrayList<clases> clases, Container cntDiagrama, int xD, int yD){
        if (i == 0) {
            JLabel lblTit = new JLabel("Diagrama de clases");
            lblTit.setFont(new Font("Verdana", 1, 12));
            lblTit.setBounds(5, 5, 150, 25);
            cntDiagrama.add(lblTit);
        }

        int alto = 3 + clases.get(i).atributos.length + clases.get(i).metodos.length;
        int cN = mayorCaracter(clases.get(i)) + 2;

        JPanel pnl = new JPanel();
        pnl.setBounds(10 + xD, 25, (8 * cN), (20 * alto));

        JTextPane txt = new JTextPane();
        // Nombre
        appendToPane(txt, clases.get(i).getNombre() + "\n", Color.RED);
        // Linea
        for (int j = 0; j < cN; j++) {
            appendToPane(txt, "_", Color.BLACK);
        }
        appendToPane(txt, "\n", Color.BLACK);
        // Atributos
        for (int j = 0; j < clases.get(i).atributos.length; j++) {
            appendToPane(txt, clases.get(i).getAtributos(j) + "\n", Color.RED);
        }
        // Linea
        for (int j = 0; j < cN; j++) {
            appendToPane(txt, "_", Color.BLACK);
        }
        appendToPane(txt, "\n", Color.BLACK);
        // Metodos
        for (int j = 0; j < clases.get(i).metodos.length; j++) {
            appendToPane(txt, clases.get(i).getMetodos(j) + "\n", Color.BLUE);
        }
        // Agrega componente y define tamaño de vista
        // Muestra el contenedor y define el tamaño
        txt.setEditable(false);
        txt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt.setMargin(new java.awt.Insets(5, 5, 5, 5));
        d.pnlClase.add(pnl);
        d.EdtClase.add(txt);
        pnl.add(txt);
        cntDiagrama.add(pnl);
        cntDiagrama.setPreferredSize(new Dimension((8 * cN) + xD + 15, (20 * alto) + 30));
        d.spDiagrama.getViewport().add(cntDiagrama);
        if (this.y < (20 * alto)) {
            this.y += (20 * alto);
        }
        this.x += (8 * cN);
    }
    
     public void appendToPane(JTextPane tp, String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }

    public int mayorCaracter(clases cl) {
        int cNombre = cl.getNombre().length();
        int cAtrib = -1;
        int cMet = -1;

        for (int j = 0; j < cl.atributos.length; j++) {
            if (cl.getAtributos(j).length() > cAtrib) {
                cAtrib = cl.getAtributos(j).length();
            }
        }

        for (int j = 0; j < cl.metodos.length; j++) {
            if (cl.getMetodos(j).length() > cMet) {
                cMet = cl.getMetodos(j).length();
            }
        }

        if (cNombre >= cAtrib && cNombre >= cMet) {
            return cNombre;
        } else if (cAtrib >= cNombre && cAtrib >= cMet) {
            return cAtrib;
        }
        return cMet;
    }
}