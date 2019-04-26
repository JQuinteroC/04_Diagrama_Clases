package pkg04_diagrama_clases;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.ImageIcon;
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
public class DiaComp implements composicion {

    int xAux;
    int x;
    int y;

    @Override
    public void dibujarComp(int pos, Vista win, ArrayList<clases> clss, ArrayList<String> names, Container cnt, int x, int y, int nClss) {
        con.removeAll();
        con.repaint();
        this.x = x;
        this.y = y;

        int localizer = -1;

        for (String composicion : clss.get(pos).composicion) {
            for (int j = 0; j < names.size(); j++) {
                String name = names.get(j);
                String comp = composicion;
                if (name.equals(comp)) {
                    localizer = j;
                    break;
                }
            }
            dibujar(pos, win, clss, cnt, xAux, this.y, true, "Composición");
            dibujar(localizer, win, clss, cnt, xAux, this.y, false, null);
        }
    }

    @Override
    public void dibujar(int pos, Vista win, ArrayList<clases> clss, Container cnt, int x, int y, boolean first, String ttl) {
        this.x = x;

        int h = 3 + clss.get(pos).atributos.length + clss.get(pos).metodos.length;
        int w = mayorCaracter(clss.get(pos)) + 2;

        JPanel pnl = new JPanel();

        if (first) {
            hacerCuadro(x, y);
            JLabel lblTit = new JLabel(ttl);
            lblTit.setFont(new Font("Verdana", 1, 12));
            lblTit.setBounds(5, 0, 150, 25);
            con.add(lblTit);
            this.y = 0;

            img.setBounds((w * 4) - 15, (20 * h) + 15, 40, 45);
            win.lblRelaciones.add(img);
            //d.lblRelaciones.add(img);
            con.add(img);

            pnl.setBounds(10, 25 + this.y, (8 * w), (20 * h));
        } else {
            int index = win.lblRelaciones.size() - 1;
            pnl.setBounds(win.lblRelaciones.get(index).getLocation().x - 16, 25 + this.y, (8 * w), (20 * h));
        }

        JTextPane txt = new JTextPane();
        // Nombre
        appendToPane(txt, clss.get(pos).getNombre() + "\n", Color.RED);
        // Linea
        for (int i = 0; i < w; i++) {
            appendToPane(txt, "_", Color.BLACK);
        }
        appendToPane(txt, "\n", Color.BLACK);
        // Atributos
        for (int i = 0; i < clss.get(pos).atributos.length; i++) {
            appendToPane(txt, clss.get(pos).getAtributos(i) + "\n", Color.RED);
        }
        // Linea
        for (int i = 0; i < w; i++) {
            appendToPane(txt, "_", Color.BLACK);
        }
        appendToPane(txt, "\n", Color.BLACK);
        // Metodos
        for (int i = 0; i < clss.get(pos).metodos.length; i++) {
            appendToPane(txt, clss.get(pos).getMetodos(i) + "\n", Color.BLUE);
        }
        // Agrega componente y define tamaño de vista
        pnl.add(txt);
        con.setLayout(null);
        con.add(pnl);
        if (con.getWidth() < ((8 * w) + 20)) {
            con.setSize((8 * w) + 20, (20 * h) + con.getHeight());
        } else {
            con.setSize(con.getWidth(), y + con.getHeight());
        }
        con.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102), 2));
        con.setPreferredSize(new Dimension((8 * w) + x + 20, (20 * h) + con.getHeight()));
        // Muestra el contenedor y define el tamaño
        txt.setEditable(false);
        txt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt.setMargin(new java.awt.Insets(5, 5, 5, 5));
        win.pnlClase.add(pnl);
        win.EdtClase.add(txt);

        cnt.add(con);
        cnt.setPreferredSize(new Dimension((8 * w) + x + 20, (20 * h) + con.getHeight()));
        win.spDiagrama.getViewport().add(cnt);
        if (this.y < (20 * h)) {
            this.y += (20 * h);
        }
        xAux += con.getWidth() + 25;
        if (first) {
            this.y += 30;
        }
    }

    private void appendToPane(JTextPane tp, String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }

    private int mayorCaracter(clases cl) {
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

    private void hacerCuadro(int x, int y) {
        con.setLocation(x, y + 25);
        con.setSize(0, 25);
    }
}

interface composicion {

    JLabel titulo = new JLabel("Composión");
    ImageIcon a = new ImageIcon("src\\composicion.png");
    JLabel img = new JLabel(a);
    JPanel con = new JPanel();

    void dibujarComp(int i, Vista d, ArrayList<clases> clases, ArrayList<String> nombres, Container cntDiagrama, int x, int y, int nClases);

    void dibujar(int i, Vista d, ArrayList<clases> clases, Container cntDiagrama, int xD, int yD, boolean primera, String titulo);
}
