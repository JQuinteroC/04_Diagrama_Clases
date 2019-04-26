/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg04_diagrama_clases;

import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author JQuintero
 */
public class Diagrama  {

}


interface Agregacion {

    JLabel titulo = new JLabel("Agregación");
    ImageIcon a = new ImageIcon("src\\agregacion.png");
    JLabel img = new JLabel(a);
    Container con = new Container();

    void dibujarAgreg();

    void dibujar(clases cl, int x, int y, Container c);
}

abstract class Cardinalidad {

    JLabel titulo = new JLabel("Composión");
    ImageIcon a;
    JLabel img;
    Container con = new Container();

    public Cardinalidad(int izq, int der) {
        a = new ImageIcon("src\\c" + url(izq) + url(der) + ".png");
        img = new JLabel(a);
    }

    String url(int num) {
        switch (num) {
            case 0:
                return "0";
            case 1:
                return "1";
            default:
                return "n";
        }
    }

    abstract void dibujar(clases cl, int x, int y, Container c);

    abstract void dibujarCar();
}

interface Herecia {

    JLabel titulo = new JLabel("Herencia");
    ImageIcon a = new ImageIcon("src\\herencia.png");
    JLabel img = new JLabel(a);
    Container con = new Container();

    void dibujarHer();

    void dibujar(clases cl, int x, int y, Container c);
}

interface Realizacion {

    JLabel titulo = new JLabel("Realización");
    ImageIcon a = new ImageIcon("src\\realizacion.png");
    JLabel img = new JLabel(a);
    Container con = new Container();

    void dibujarReal();

    void dibujar(clases cl, int x, int y, Container c);
}
