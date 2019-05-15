package pkg04.pkg1_diagrama_clases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author JQuintero
 */
public class DiaCar {

    ArrayList<int[]> listas;

    public DiaCar() {
        listas = new ArrayList<>();
    }

    void dibujarCar(int i, ArrayList<Clases> clss, ArrayList<String> names) {
        if (clss.get(i).creada) {
            // Arreglo con las diferentes cardinalidades por clase
            ArrayList<int[]> lista = new ArrayList<>();
            ArrayList<Integer> borrar = new ArrayList();

            int[] temporal = new int[4];

            // <editor-fold defaultstate="collapsed" desc="Agregaciones"> 
            for (int j = 0; j < clss.get(i).agregacion.length; j++) {
                lista.add(temporal);
                listas.add(temporal);
                temporal = new int[4];
                temporal[0] = i;

                // Buscar posición de la clase relacionada
                for (int k = 0; k < names.size(); k++) {
                    if (names.get(k).equals(clss.get(i).getPosAgregacion(j))) {
                        temporal[1] = k;
                        break;
                    }
                }

                // Buscar cantidad de relaciones con una clase
                temporal[3] = relacionClases(i, temporal[1], clss, names);
                temporal[2] = relacionClases(temporal[1], i, clss, names);
                // Guardar información recolectada en temporal[]
                boolean a = true;
                for (int k = 0; k < lista.size(); k++) {
                    if (lista.get(k)[1] == temporal[1]) {
                        lista.set(k, temporal);
                        if (k != (lista.size() - 1)) {
                            //lista.remove(lista.size() - 1);
                            borrar.add(listas.size() - 1);
                            //listas.remove(listas.size() - 1);
                        }
                        a = false;
                        break;
                    }
                }
                if (a) {
                    lista.set(j, temporal);
                    listas.set(j, temporal);
                }
            }
            // </editor-fold>

            // <editor-fold defaultstate="collapsed" desc="Composiciones"> 
            for (int j = 0; j < clss.get(i).composicion.length; j++) {
                lista.add(temporal);
                listas.add(temporal);
                temporal = new int[4];
                temporal[0] = i;

                // Buscar posición de la clase relacionada
                for (int k = 0; k < names.size(); k++) {
                    if (names.get(k).equals(clss.get(i).getPosComposicion(j))) {
                        temporal[1] = k;
                        break;
                    }
                }

                // Buscar cantidad de relaciones con una clase
                temporal[3] = relacionClases(i, temporal[1], clss, names);
                temporal[2] = relacionClases(temporal[1], i, clss, names);
                // Guardar información recolectada en temporal[]
                boolean a = true;
                for (int k = 0; k < lista.size(); k++) {
                    if (lista.get(k)[1] == temporal[1]) {
                        lista.set(k, temporal);
                        listas.set(k, temporal);
                        if (k != (lista.size() - 1)) {
                            borrar.add(listas.size() - 1);
                            //lista.remove(lista.size() - 1);
                            //listas.remove(listas.size() - 1);
                        }
                        a = false;
                        break;
                    }
                }
                if (a) {
                    lista.set(j, temporal);
                    listas.set(j, temporal);
                }
            }
            // </editor-fold>

            for (int j = 0; j < borrar.size(); j++) {
                int index = borrar.get(j);
                lista.remove(index);
                listas.remove(index);
            }

            // <editor-fold defaultstate="collapsed" desc="Dibujar"> 
            for (int j = 0; j < lista.size(); j++) {
                Image pClase;
                Image sClase;
                BufferedImage bi = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
                // Lee el archivo
                try {
                    bi = ImageIO.read(new File("imgs/class" + lista.get(j)[0] + ".jpg"));
                } catch (IOException ex) {
                    Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                }
                pClase = bi;
                try {
                    bi = ImageIO.read(new File("imgs/class" + lista.get(j)[1] + ".jpg"));
                } catch (IOException ex) {
                    Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                }
                sClase = bi;

                // Tamaño de la imagen
                int maxX = pClase.getWidth(null) + sClase.getWidth(null) + 100;
                maxX += 10;
                int maxY = 0;
                int yLinea = 0;
                if (pClase.getHeight(null) > sClase.getHeight(null)) {
                    maxY = pClase.getHeight(null);
                } else {
                    maxY = sClase.getHeight(null);
                }
                maxY += 20;
                yLinea = maxY / 2;
                yLinea += 5;
                // Dimensiona la imagen
                BufferedImage imagen = new BufferedImage(maxX, maxY, BufferedImage.TYPE_INT_RGB);
                Graphics k = imagen.getGraphics();

                // Dibujar en la imagen
                // Fondo
                k.setColor(Color.white);
                k.fillRect(0, 0, maxX, maxY);
                // Clases
                k.setColor(Color.black);
                k.drawString("Cardinalidades entre clases", 5, 10);
                k.drawImage(pClase, 5, (maxY / 2) - (pClase.getHeight(null) / 2) + 5, null);
                k.drawImage(sClase, 5 + pClase.getWidth(null) + 100, (maxY / 2) - (sClase.getHeight(null) / 2) + 5, null);
                // Discriminación de la cardinalidad

                String t1, t2;
                if (lista.get(j)[2] == -2) {
                    t1 = "*";
                } else {
                    t1 = Integer.toString(lista.get(j)[2]);
                }
                if (lista.get(j)[3] == -2) {
                    t2 = "*";
                } else {
                    t2 = Integer.toString(lista.get(j)[3]);
                }
                // Cardinalidad
                k.drawString(t1, 5 + pClase.getWidth(null) + 2, yLinea - 2);
                k.drawString(t2, 5 + pClase.getWidth(null) + 100 - (t2.length() * 10), yLinea - 2);
                // Linea de relación
                k.setColor(Color.red);
                k.drawLine(5 + pClase.getWidth(null), yLinea, 5 + pClase.getWidth(null) + 100, yLinea);

                // Guardar imagen en un archivo
                try {
                    ImageIO.write(imagen, "jpg", new File("imgs/car" + lista.get(j)[0] + lista.get(j)[1] + ".jpg"));
                } catch (IOException e) {
                    System.out.println("Error de escritura");
                }
            }
            // </editor-fold>
        }
    }

    int relacionClases(int i, int j, ArrayList<Clases> clss, ArrayList<String> names) {
        // Agregaciones
        for (int k = 0; k < clss.get(i).agregacion.length; k++) {
            if (names.get(j).equals(clss.get(i).getPosAgregacion(k))) {
                return -2;
            }
        }

        // Composición
        int n = 0;
        for (int k = 0; k < clss.get(i).composicion.length; k++) {
            if (names.get(j).equals(clss.get(i).getPosComposicion(k))) {
                n++;
            }
        }

        return n;
    }
}
