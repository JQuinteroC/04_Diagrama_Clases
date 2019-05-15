package pkg04.pkg1_diagrama_clases;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author JQuintero
 */
public class DiaClase {

    int x;
    int y;

    void d(int i, Vista d, ArrayList<Clases> clases) {
        // Dimensiones
        int ancho = mayorCaracter(clases.get(i)) * 7;
        int altoN = 20;
        int altoA = clases.get(i).atributos.length * 20;
        int altoF = clases.get(i).metodos.length * 20;
        if (altoA == 0) {
            altoA = 5;
        }
        if (altoF == 0) {
            altoF = 5;
        }
        int alto = altoA + altoF + altoN;
        // Imagen
        BufferedImage imagen = new BufferedImage(ancho + 1, alto + 1, BufferedImage.TYPE_INT_RGB);
        Graphics k = imagen.getGraphics();

        // Nombre
        k.setColor(Color.white);
        k.fillRect(0, 0, 500, 500);
        k.setColor(Color.black);
        k.drawRect(0, 0, ancho, altoN);
        k.setColor(Color.red);
        ///////////////////////////////////////////////////////////////////////////////////////
        if (clases.get(i).tipo == 1) {
            Font s = new Font("Serif", Font.ITALIC | Font.BOLD, 12);
            k.setFont(s);
        }
        ///////////////////////////////////////////////////////////////////////////////////////
        k.drawString(clases.get(i).getNombre(), 2, 15);
        // Atributos
        k.setColor(Color.black);
        if (clases.get(i).atributos.length != 0) {
            k.drawRect(0, altoN, ancho, altoA);
        } else {
            k.drawRect(0, altoN, ancho, 5);
        }
        k.setColor(Color.red);

        for (int j = 0; j < clases.get(i).atributos.length; j++) {
            int js = altoA / clases.get(i).atributos.length * j;
            k.drawString(clases.get(i).getAtributos(j), 3, 15 + altoA - js);
        }

        // Metodos
        k.setColor(Color.black);
        if (clases.get(i).metodos.length != 0) {
            k.drawRect(0, altoA + altoN, ancho, altoF);
        } else {
            k.drawRect(0, altoA + altoN, ancho, 5);
        }
        k.setColor(Color.blue);
        for (int j = 0; j < clases.get(i).metodos.length; j++) {
            int js = altoF / clases.get(i).metodos.length * j;
            k.drawString(clases.get(i).getMetodos(j), 3, 15 + altoF + altoA - js);
        }

        try {
            ImageIO.write(imagen, "jpg", new File("imgs/class" + i + ".jpg"));
        } catch (IOException e) {
            System.out.println("Error de escritura");
        }

        if (this.y < alto) {
            this.y += alto;
        }
        this.x += ancho + 10;

    }

    public int mayorCaracter(Clases cl) {
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
