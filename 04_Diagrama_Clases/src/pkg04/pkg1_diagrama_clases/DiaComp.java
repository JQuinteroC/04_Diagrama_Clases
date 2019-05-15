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
public class DiaComp implements composicion {

    Image pClase;
    Image sClase;

    @Override
    public void dibujarComp(int i, int j, ArrayList<Clases> clss, ArrayList<String> names) {
        int localizer = -1;

        for (int l = 0; l < names.size(); l++) {
            String name = names.get(l);
            String comp = clss.get(i).getPosComposicion(j);
            if (name.equals(comp)) {
                localizer = l;
                break;
            }
        }

        if (clss.get(i).creada && clss.get(localizer).creada) {

            BufferedImage bi = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
            // Lee el archivo
            try {
                bi = ImageIO.read(new File("imgs/class" + i + ".jpg"));
            } catch (IOException ex) {
                Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
            }
            pClase = bi;
            try {
                bi = ImageIO.read(new File("imgs/class" + localizer + ".jpg"));
            } catch (IOException ex) {
                Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
            }
            sClase = bi;

            int maxX;
            if (pClase.getWidth(null) > sClase.getWidth(null)) {
                maxX = pClase.getWidth(null);
                maxX += (maxX / 2) - (pClase.getWidth(null) / 2) + 30;
            } else {
                maxX = sClase.getWidth(null);
                maxX += (maxX / 2) - (sClase.getWidth(null) / 2) + 30;
            }
            if (maxX < 80) {
                maxX = 80;
            }
            maxX += 3;
            int maxY = pClase.getHeight(null) + sClase.getHeight(null) + a.getIconHeight() + 28;
            BufferedImage imagen = new BufferedImage(maxX, maxY, BufferedImage.TYPE_INT_RGB);
            Graphics k = imagen.getGraphics();

            k.setColor(Color.white);
            k.fillRect(0, 0, maxX, maxY);
            k.setColor(Color.blue);
            k.drawRect(0, 0, maxX - 2, maxY - 2);
            k.drawRect(1, 1, maxX - 2, maxY - 2);
            k.setColor(Color.black);
            k.drawString("Composición", 3, 15);
            k.drawImage(pClase, (maxX / 2) - (pClase.getWidth(null) / 2), 20, null);
            k.drawImage(sClase, (maxX / 2) - (sClase.getWidth(null) / 2), 20 + pClase.getHeight(null) + a.getIconHeight(), null);
            k.drawImage(a.getImage(), (maxX / 2) - (a.getImage().getWidth(null) / 2), 20 + pClase.getHeight(null), null);

            try {
                ImageIO.write(imagen, "jpg", new File("imgs/comp" + i + localizer + ".jpg"));
            } catch (IOException e) {
                System.out.println("Error de escritura");
            }
        }
    }

    public DiaComp() {
        pClase = null;
        sClase = null;
    }
}

interface composicion {

    ImageIcon a = new ImageIcon("src\\composicion.png");

    void dibujarComp(int i, int j, ArrayList<Clases> clases, ArrayList<String> nombres);
}
