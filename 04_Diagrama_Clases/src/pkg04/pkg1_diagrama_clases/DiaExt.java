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
import javax.swing.ImageIcon;

/**
 *
 * @author JQuintero
 */
public class DiaExt implements extendss {

    Image clPadre;
    Image clHijo;
    int cnt;
    ArrayList<int[]> lista;

    public DiaExt() {
        cnt = 0;
        lista = new ArrayList<>();
    }

    @Override
    public void dibujarHere(int padre, int hijo, ArrayList<Clases> clss, ArrayList<String> nombres) {
        if (clss.get(padre).creada && clss.get(hijo).creada) {
            int tem[] = new int[2];
            tem[0] = padre;
            tem[1] = hijo;
            
            BufferedImage bi = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
            // Lee el archivo
            try {
                bi = ImageIO.read(new File("imgs/class" + padre + ".jpg"));
            } catch (IOException ex) {
                Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
            }
            clPadre = bi;
            try {
                bi = ImageIO.read(new File("imgs/class" + hijo + ".jpg"));
            } catch (IOException ex) {
                Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
            }
            clHijo = bi;

            int maxX;
            if (clPadre.getWidth(null) > clHijo.getWidth(null)) {
                maxX = clPadre.getWidth(null);
                maxX += (maxX / 2) - (clPadre.getWidth(null) / 2) + 30;
            } else {
                maxX = clHijo.getWidth(null);
                maxX += (maxX / 2) - (clHijo.getWidth(null) / 2) + 30;
            }
            if (maxX < 80) {
                maxX = 80;
            }
            maxX += 3;
            int maxY = clPadre.getHeight(null) + clHijo.getHeight(null) + a.getIconHeight() + 28;
            BufferedImage imagen = new BufferedImage(maxX, maxY, BufferedImage.TYPE_INT_RGB);
            Graphics k = imagen.getGraphics();

            k.setColor(Color.white);
            k.fillRect(0, 0, maxX, maxY);
            k.setColor(Color.blue);
            k.drawRect(0, 0, maxX - 2, maxY - 2);
            k.drawRect(1, 1, maxX - 2, maxY - 2);
            k.setColor(Color.black);
            k.drawString("Herencia", 5, 15);
            k.drawImage(clPadre, (maxX / 2) - (clPadre.getWidth(null) / 2), 20, null);
            k.drawImage(clHijo, (maxX / 2) - (clHijo.getWidth(null) / 2), 20 + clPadre.getHeight(null) + a.getIconHeight(), null);
            k.drawImage(a.getImage(), (maxX / 2) - (a.getImage().getWidth(null) / 2), 20 + clPadre.getHeight(null), null);

            try {
                ImageIO.write(imagen, "jpg", new File("imgs/her" + padre + hijo + ".jpg"));
                cnt++;
                lista.add(tem);
            } catch (IOException e) {
                System.out.println("Error de escritura");
            }
        }
    }
}

interface extendss {

    ImageIcon a = new ImageIcon("src\\herencia.png");

    void dibujarHere(int padre, int hijo, ArrayList<Clases> clss, ArrayList<String> nombres);
}
