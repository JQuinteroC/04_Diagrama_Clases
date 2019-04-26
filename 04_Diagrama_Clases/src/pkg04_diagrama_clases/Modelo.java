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
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class Modelo {

    JPanel con;
    int xD;
    int yD;
    int xComp;
    int xAgreg;

    Modelo() {
        xComp = 0;
    }

    public void dibujarClases(int i, Vista d, ArrayList<clases> clases, Container cntDiagrama, int xD, int yD) {
        this.xD = xD;
        this.yD = yD;

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
        if (this.yD < (20 * alto)) {
            this.yD += (20 * alto);
        }
        this.xD += (8 * cN);

    }

    public void dibujarComposicion(int i, Vista d, ArrayList<clases> clases, ArrayList<String> nombres, Container cntDiagrama, int xD, int yD, int nClases) {
        this.xD = xD;
        this.yD = yD;
        DiaComp com = new DiaComp();
        com.dibujarComp(i, d, clases, nombres, cntDiagrama, xD, yD, nClases);
    }

    /**
     * Ingresa el texto al panel de texto
     *
     * @param tp
     * @param msg
     * @param c
     */
    public void appendToPane(JTextPane tp, String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }

    /**
     * Regresa la mayor cantidad de caracteres de una clase, del nombre,
     * atributos o metodos
     *
     * @param cl
     * @return
     */
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
