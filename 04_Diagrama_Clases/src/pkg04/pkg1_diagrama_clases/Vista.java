package pkg04.pkg1_diagrama_clases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.ArrayList;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class Vista extends JFrame implements ActionListener {

    JLabel lblTitulo = new JLabel("Diagramas UML");
    JLabel lblEtiquetas[][];
    JLabel lblImgReferecia = new javax.swing.JLabel();
    // Botones
    JButton btnClase = new JButton("Crear clase");
    JButton btnHerencia = new JButton("Crear herencia");
    JButton btnGenerar = new JButton("Generar diagrama");
    JButton btnBorrar = new JButton("Borrar diagrama");
    // Campos de llenado
    ArrayList<JTextArea> txtClase;
    ArrayList<JScrollPane> scClase;
    ArrayList<JTextArea> txtMetodo;
    ArrayList<JScrollPane> scMetodo;
    ArrayList<JTextField> txtRelacion;
    // Secciones de clases y diagrama
    JScrollPane spClases = new JScrollPane();
    JScrollPane spDiagrama = new JScrollPane();
    Container panel = new Container();

    int nClases;
    int nRelaciones;
    int nComp;
    int nAgre;
    int nCar;
    int yC;
    Container cntClases = new Container();

    int posY[] = new int[5];

    ArrayList<Clases> clases = new ArrayList();

    Color fondo;

    void mostrar() {
        // Titulo
        lblTitulo.setFont(new java.awt.Font("Verdana", 1, 25));
        lblTitulo.setBounds(240, 5, 220, 45);
        // Imagen referencia
        lblImgReferecia.setIcon(new javax.swing.ImageIcon("src\\imgReferencia.png"));
        lblImgReferecia.setBounds(10, 490, 670, 175);
        // Botones
        btnClase.setBounds(30, 50, 140, 30);
        btnHerencia.setBounds(190, 50, 140, 30);
        btnGenerar.setBounds(350, 50, 140, 30);
        btnBorrar.setBounds(510, 50, 140, 30);
        // ScrollPane
        spClases.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clases", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11)));
        spClases.setBounds(10, 90, 675, 400);
        spDiagrama.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Diagrama", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11)));
        spDiagrama.setBounds(685, 10, 660, 660);
        // Contenedor de los dibujos
        spDiagrama.setViewportView(panel);
        spDiagrama.getViewport().setView(panel);

        setSize(1370, 710);     //Ajustar X según pantalla
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
        c.add(btnBorrar);
        c.add(spClases);
        c.add(spDiagrama);
        c.add(lblImgReferecia);

        btnClase.addActionListener(this);
        btnGenerar.addActionListener(this);
        btnHerencia.addActionListener(this);
        btnBorrar.addActionListener(this);
        Container frValores = new Container();
        frValores.setLayout(null);
        frValores.setPreferredSize(new Dimension(700, 45));

        txtClase = new ArrayList<>();
        scClase = new ArrayList<>();
        txtMetodo = new ArrayList<>();
        scMetodo = new ArrayList<>();
        txtRelacion = new ArrayList<>();
        lblEtiquetas = new JLabel[50][3];
        nClases = 0;
        nRelaciones = 0;
        yC = 0;
        cntClases.setLayout(null);
        fondo = spDiagrama.getBackground();
    }

    @Override
    public void actionPerformed(ActionEvent rr) {
        if (rr.getSource() == btnClase) {
            // <editor-fold defaultstate="collapsed" desc="Crear clase">
            // Declara los nuevos componentes
            JTextArea tc = new JTextArea();
            JScrollPane sc = new JScrollPane();
            JTextArea tm = new JTextArea();
            JScrollPane sm = new JScrollPane();
            lblEtiquetas[nClases][0] = new JLabel("Nombre y atributos de la clase " + (nClases + 1));
            lblEtiquetas[nClases][0].setFont(new Font("Verdana", 1, 10));
            lblEtiquetas[nClases][1] = new JLabel("Metodos de la clase " + (nClases + 1));
            lblEtiquetas[nClases][1].setFont(new Font("Verdana", 1, 10));
            // Ubica y dimenciona los componentes
            lblEtiquetas[nClases][0].setBounds(8, 0 + yC, 200, 25);
            lblEtiquetas[nClases][1].setBounds(325, 0 + yC, 150, 25);
            // Panel de texto de clase
            tc.setText("class ");
            sc.setBounds(10, 20 + yC, 307, 80);
            sc.setBorder(null);
            sc.setViewportView(tc);
            txtClase.add(tc);
            scClase.add(sc);
            // Panel de texto de metodo
            sm.setBounds(327, 20 + yC, 307, 80);
            sm.setBorder(null);
            sm.setViewportView(tm);
            txtMetodo.add(tm);
            scMetodo.add(sm);
            // Agrega los componentes al contenedor
            cntClases.add(lblEtiquetas[nClases][0]);
            cntClases.add(lblEtiquetas[nClases][1]);
            cntClases.add(scClase.get(nClases));
            cntClases.add(scMetodo.get(nClases));
            cntClases.setPreferredSize(new Dimension(645, 100 + yC));
            // Muestra el contenedor en el ScrollPane
            spClases.getViewport().add(cntClases);
            // Ubicar cursor en el area de texto
            txtClase.get(nClases).requestFocus();
            // Variables de control
            yC += 110;
            nClases++;

            // </editor-fold>
        } else if (rr.getSource() == btnHerencia) {
            // <editor-fold defaultstate="collapsed" desc="Crear herencia">
            if (nClases >= 2) {
                // Declaración de componentes
                JTextField tr = new JTextField();
                lblEtiquetas[nRelaciones][2] = new JLabel("Herencia " + (nRelaciones + 1) + " Ej: class A extends B o class A implements B ");
                lblEtiquetas[nRelaciones][2].setFont(new Font("Verdana", 1, 10));
                // Ubica y dimensiona los componentes
                tr.setBounds(10, 20 + yC, 625, 30);
                tr.setBorder(null);
                tr.setFont(new Font("Consolas", 0, 11));
                txtRelacion.add(tr);
                lblEtiquetas[nRelaciones][2].setBounds(10, yC, 400, 25);
                // Agrega los componentes al contenedor
                cntClases.add(txtRelacion.get(nRelaciones));
                cntClases.add(lblEtiquetas[nRelaciones][2]);
                cntClases.setPreferredSize(new Dimension(645, 50 + yC));
                // Muestra el contenedor en el ScrollPane
                spClases.getViewport().add(cntClases);
                // Ubicar cursor en el area de texto
                txtRelacion.get(nRelaciones).requestFocus();
                // Variables de control
                nRelaciones++;
                yC += 60;

            } else if (nClases < 2) {
                JOptionPane.showMessageDialog(null, "Ingrese al menos 2 clases para poder implementar herencia", "Error", JOptionPane.ERROR_MESSAGE);
            }
            // </editor-fold>
        } else if (rr.getSource() == btnGenerar) {
            // <editor-fold defaultstate="collapsed" desc="Crear diagrama">
            // Definie variables
            ArrayList<String> atrib = new ArrayList();
            ArrayList<String> met = new ArrayList();
            ArrayList<String> nombres = new ArrayList();
            int x = 0;
            int y = 0;
            int yMax = 0;
            int xMax = 0;
            clases.clear();
            // <editor-fold defaultstate="collapsed" desc="Borrar Diagrama">
            Graphics k = panel.getGraphics();
            k.setColor(fondo);
            k.fillRect(3, 10, spDiagrama.getWidth() - 6, spDiagrama.getHeight() - 14);
            k.setColor(Color.black);
            // </editor-fold>

            for (int i = 0; i < nClases; i++) {
                // Variables de control
                Clases temClass = new Clases();
                clases.add(temClass);
                String temporal[] = new String[0];
                int numSpaces[] = new int[0];
                // <editor-fold defaultstate="collapsed" desc="Nombre"> 
                temporal = txtClase.get(i).getText().split("\n"); // Toma el nombre y los parametros de la clase
                numSpaces = new int[temporal.length]; // Toma el numero de espacios por linea en el nombre y parametros
                for (int j = 0; j < 1; j++) {
                    numSpaces[j] = temporal[j].replaceAll("[^ ]", "").length(); // Cuenta el numero de espacios por linea
                    if (numSpaces[j] <= 2) {
                        temporal[j] = temporal[j].replace(" ", "");

                        // NOMBRE DE LA CLASE
                        if (temporal[j].contains("class") && temporal[j].contains("abstract")) {
                            temporal[j] = temporal[j].replace("class", "");
                            temporal[j] = temporal[j].replace("abstract", "");
                            // Comprueba que la clase inicie correctamente
                            if (!charAccept(temporal[j])) {
                                JOptionPane.showMessageDialog(null, "ERROR caracter invalido en nombre de clase abstracta", "Error clase", JOptionPane.ERROR_MESSAGE);
                                clases.get(i).setCreada(false);
                                break;
                            }
                            // Validad que la clase abstracta no exista anteriormente
                            if (!nombres.contains(temporal[j])) {
                                nombres.add(temporal[j]);
                                clases.get(i).setNombre("abstract class " + temporal[j]);
                                clases.get(i).setCreada(true);
                                clases.get(i).setTipo(1);
                            } else {
                                JOptionPane.showMessageDialog(null, "ERROR mismo nombre que otra clase", "Error clase", JOptionPane.ERROR_MESSAGE);
                                clases.get(i).setCreada(false);
                                clases.get(i).setCreada(false);
                                break;
                            }
                        } else if (temporal[j].contains("class")) {
                            temporal[j] = temporal[j].replace("class", "");
                            // Comprueba que la classe inicie correctamente
                            if (!charAccept(temporal[j])) {
                                JOptionPane.showMessageDialog(null, "ERROR caracter invalido en nombre de clase", "Error clase", JOptionPane.ERROR_MESSAGE);
                                clases.get(i).setCreada(false);
                                break;
                            }
                            // Validad que la clase no exista anteriormente
                            if (!nombres.contains(temporal[j])) {
                                nombres.add(temporal[j]);
                                clases.get(i).setNombre("class " + temporal[j]);
                                clases.get(i).setCreada(true);
                                clases.get(i).setTipo(0);
                            } else {
                                JOptionPane.showMessageDialog(null, "ERROR mismo nombre que otra clase", "Error clase", JOptionPane.ERROR_MESSAGE);
                                clases.get(i).setCreada(false);
                                break;
                            }
                        } else if (temporal[j].contains("interface")) {
                            temporal[j] = temporal[j].replace("interface", "");
                            // Comprueba que la classe inicie correctamente
                            if (!charAccept(temporal[j])) {
                                JOptionPane.showMessageDialog(null, "ERROR caracter invalido en nombre de interface", "Error clase", JOptionPane.ERROR_MESSAGE);
                                clases.get(i).setCreada(false);
                                break;
                            }
                            // Validad que la interface no exista anteriormente
                            if (!nombres.contains(temporal[j])) {
                                nombres.add(temporal[j]);
                                clases.get(i).setNombre("interface " + temporal[j]);
                                clases.get(i).setCreada(true);
                                clases.get(i).setTipo(2);
                            } else {
                                JOptionPane.showMessageDialog(null, "ERROR mismo nombre que otra clase", "Error calse", JOptionPane.ERROR_MESSAGE);
                                clases.get(i).setCreada(false);
                                break;
                            }
                        }
                    } else if (temporal[j] != null) {
                        JOptionPane.showMessageDialog(null, "ERROR más espacios", "Error clase", JOptionPane.ERROR_MESSAGE);
                        clases.get(i).setCreada(false);
                        break;
                    }
                }
                ingresarAtributos(atrib, clases, i);
                // </editor-fold>
            }

            for (int i = 0; i < nClases; i++) {
                DiaClase cl = new DiaClase();
                String temporal[] = new String[0];
                atrib.clear();
                met.clear();
                int numSpaces[] = new int[0];
                // <editor-fold defaultstate="collapsed" desc="Parametros"> 
                temporal = txtClase.get(i).getText().split("\n"); // Toma el nombre y los parametros de la clase
                numSpaces = new int[temporal.length]; // Toma el numero de espacios por linea en el nombre y parametros
                for (int j = 1; j < temporal.length; j++) {
                    numSpaces[j] = temporal[j].replaceAll("[^ ]", "").length(); // Cuenta el numero de espacios por linea
                    if (numSpaces[j] <= 2) {
                        temporal[j] = temporal[j].replace(" ", "");
                        // Atributos
                        if (clases.get(i).isCreada()) {
                            if (temporal[j].contains("public")) {
                                agregarAtrib(temporal, nombres, atrib, "public", i, j);
                            } else if (temporal[j].contains("private")) {
                                agregarAtrib(temporal, nombres, atrib, "private", i, j);
                            } else if (temporal[j].contains("protected")) {
                                agregarAtrib(temporal, nombres, atrib, "protected", i, j);
                            }
                        }
                    } else if (temporal[j] != null) {
                        JOptionPane.showMessageDialog(null, "ERROR más espacios", "Error clase", JOptionPane.ERROR_MESSAGE);
                        clases.get(i).setCreada(false);
                        break;
                    }
                }
                ingresarAtributos(atrib, clases, i);
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="Metodos">                                  
                if (clases.get(i).isCreada()) { // Metodos
                    temporal = txtMetodo.get(i).getText().split("\n"); // Toma los metodos de la clase clase
                    numSpaces = new int[temporal.length]; // Toma el numero de espacios por linea en los metodos
                    for (int j = 0; j < temporal.length; j++) { // Metodos
                        if (numSpaces[j] <= 1) {
                            temporal[j] = temporal[j].replace(" ", "");
                            temporal[j] = temporal[j].replace("()", "");
                            if (temporal[j].contains("public")) {
                                temporal[j] = temporal[j].replace("public", "");
                                if (!met.contains(temporal[j])) {
                                    // Comprueba que la clase inicie correctamente
                                    if (!charAccept(temporal[j].replace("()", ""))) {
                                        JOptionPane.showMessageDialog(null, "ERROR caracter invalido en nombre de un metodo", "Error clase", JOptionPane.ERROR_MESSAGE);
                                        clases.get(i).setCreada(false);
                                    } else {
                                        met.add("+ " + temporal[j] + "()");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "ERROR dos metodos o más con el mismo nombre", "Error metodos", JOptionPane.ERROR_MESSAGE);
                                    clases.get(i).setCreada(false);
                                    break;
                                }
                            } else if (temporal[j].contains("private")) {
                                temporal[j] = temporal[j].replace("private", "");
                                if (!met.contains(temporal[j])) {
                                    // Comprueba que la clase inicie correctamente
                                    if (!charAccept(temporal[j].replace("()", ""))) {
                                        JOptionPane.showMessageDialog(null, "ERROR caracter invalido en nombre de un metodo", "Error clase", JOptionPane.ERROR_MESSAGE);
                                        clases.get(i).setCreada(false);
                                    } else {
                                        met.add("- " + temporal[j] + "()");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "ERROR dos metodos o más con el mismo nombre", "Error metodos", JOptionPane.ERROR_MESSAGE);
                                    clases.get(i).setCreada(false);
                                    break;
                                }
                            } else if (temporal[j].contains("protected")) {
                                temporal[j] = temporal[j].replace("protected", "");
                                if (!met.contains(temporal[j])) {
                                    // Comprueba que la clase inicie correctamente
                                    if (!charAccept(temporal[j].replace("()", ""))) {
                                        JOptionPane.showMessageDialog(null, "ERROR caracter invalido en nombre de un metodo", "Error clase", JOptionPane.ERROR_MESSAGE);
                                        clases.get(i).setCreada(false);
                                    } else {
                                        met.add("# " + temporal[j] + "()");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "ERROR dos metodos o más con el mismo nombre", "Error metodos", JOptionPane.ERROR_MESSAGE);
                                    clases.get(i).setCreada(false);
                                    break;
                                }
                            } else if (!temporal[j].isEmpty()) {
                                JOptionPane.showMessageDialog(null, "ERROR en metodos sin definición de acceso", "Error metodos", JOptionPane.ERROR_MESSAGE);
                                clases.get(i).setCreada(false);
                                break;
                            }

                        } else if (temporal[j] != "") {
                            JOptionPane.showMessageDialog(null, "ERROR más espacios", "Error metodos", JOptionPane.ERROR_MESSAGE);
                            clases.get(i).setCreada(false);
                            break;
                        }
                    }
                }
                ingresarMetodos(met, clases, i);
                // </editor-fold>

                cl.d(i, this, clases);
                xMax = cl.x;
                yMax = cl.y;
            }

            // <editor-fold defaultstate="collapsed" desc="Relaciones">
            String re;
            DiaExt ext = new DiaExt();
            DiaImp imp = new DiaImp();
            for (int i = 0; i < nRelaciones; i++) {
                re = txtRelacion.get(i).getText();
                if (re.startsWith("class")) {
                    String temporal[] = re.split(" ");
                    if (temporal.length < 4) {
                        JOptionPane.showMessageDialog(null, "Error en formato de relación " + (i + 1), "Error texto de relación", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int padre = -1;
                        int hijo = -1;
                        //  Identificar clases
                        for (int j = 0; j < nombres.size(); j++) {
                            if (nombres.get(j).equals(temporal[1])) {
                                hijo = j;
                            }
                            if (nombres.get(j).equals(temporal[3])) {
                                padre = j;
                            }
                        }

                        if (null == temporal[2]) {
                            JOptionPane.showMessageDialog(null, "Error en formato de relación " + (i + 1), "Error tipo de relación", JOptionPane.ERROR_MESSAGE);
                        } else {
                            switch (temporal[2]) {
                                case "extends":
                                    //  Comprobar que no sea interface
                                    if (clases.get(hijo).tipo == 2 || clases.get(padre).tipo == 2) {
                                        JOptionPane.showMessageDialog(null, "No existe la clase " + clases.get(hijo).nombre, "Error en la relación " + (i + 1), JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        ext.dibujarHere(padre, hijo, clases, nombres);
                                    }
                                    break;
                                case "implements":
                                    if (clases.get(hijo).tipo == 2 || clases.get(padre).tipo != 2) {
                                        JOptionPane.showMessageDialog(null, "No existe la clase " + clases.get(hijo).nombre, "Error en la relación " + (i + 1), JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        imp.dibujarRea(padre, hijo, clases, nombres);
                                    }
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Error en formato de relación " + (i + 1), "Error tipo de relación", JOptionPane.ERROR_MESSAGE);
                                    break;
                            }
                        }
                    }
                }
            }
            // </editor-fold>

            // <editor-fold defaultstate="collapsed" desc="Crear imagenes de clases"> 
            // Composición
            for (int i = 0; i < nClases; i++) {
                DiaComp com = new DiaComp();
                if (clases.get(i).getComposicion().length != 0) {
                    int in = 0;
                    String temporal[] = clases.get(i).getComposicion();
                    for (int j = 0; j < clases.get(i).getComposicion().length; j++) {
                        if (temporal[in] == temporal[j] && j != in) {
                            in = j;
                        } else {
                            com.dibujarComp(i, j, clases, nombres);
                        }
                    }
                }
            }

            // Agregación
            for (int i = 0; i < nClases; i++) {
                DiaAgre agre = new DiaAgre();
                if (clases.get(i).getAgregacion().length != 0) {
                    int in = 0;
                    String temporal[] = clases.get(i).getAgregacion();
                    for (int j = 0; j < clases.get(i).getAgregacion().length; j++) {
                        if (temporal[in] == temporal[j] && j != in) {
                            in = j;
                        } else {
                            agre.dibujarAgre(i, j, clases, nombres);
                        }
                    }
                }
            }

            // Cardinalidad
            DiaCar car = new DiaCar();
            for (int i = 0; i < nClases; i++) {
                car.dibujarCar(i, clases, nombres);
            }
            nCar = car.listas.size();
            // </editor-fold>

            // <editor-fold defaultstate="collapsed" desc="Poner imagenes"> 
            // Buffer de la imagen
            BufferedImage bi = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
            panel.setPreferredSize(new Dimension(500, 500));

            // <editor-fold defaultstate="collapsed" desc="Dibujar Clases"> 
            posY[0] = 10;
            for (int i = 0; i < nClases; i++) {
                Graphics dia = panel.getGraphics();
                // Lee el archivo
                try {
                    bi = ImageIO.read(new File("imgs/class" + i + ".jpg"));
                } catch (IOException ex) {
                    Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                }
                Image as = bi;

                dia.drawImage(as, 10 + x, posY[0], null);

                x += as.getWidth(null) + 10;
                y = as.getHeight(null) + 10;
                if (y > yMax) {
                    yMax = y;
                }

                xMax = x;
            }
            //</editor-fold>

            // <editor-fold defaultstate="collapsed" desc="Dibujar Composicón"> 
            x = 0;
            posY[1] = yMax + 10;
            yMax = 0;
            for (int i = 0; i < nClases; i++) {
                int localizer = -1;
                int in = 0;
                String tempora[] = clases.get(i).getComposicion();
                for (int j = 0; j < clases.get(i).composicion.length; j++) {
                    if (tempora[in].equals(tempora[j]) && j != in) {
                        in = j;
                    } else {
                        String temporal = clases.get(i).getPosComposicion(j);
                        for (int l = 0; l < nombres.size(); l++) {
                            if (nombres.get(l).equals(temporal)) {
                                localizer = l;
                            }
                        }

                        Graphics dia = panel.getGraphics();
                        // Lee el archivo
                        try {
                            bi = ImageIO.read(new File("imgs/comp" + i + localizer + ".jpg"));
                        } catch (IOException ex) {
                            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Image as = bi;

                        dia.drawImage(as, 10 + x, posY[1], null);

                        x += as.getWidth(null) + 10;
                        y = as.getHeight(null) + posY[1];
                        if (y > yMax) {
                            yMax = y;
                        }
                        if (x > xMax) {
                            xMax = x;
                        }
                    }
                }
            }
            //</editor-fold>

            // <editor-fold defaultstate="collapsed" desc="Dibujar Agregación"> 
            x = 0;
            if (yMax == 0) {
                posY[2] = posY[1];
            } else {
                posY[2] = yMax + 10;
            }
            yMax = 0;
            for (int i = 0; i < nClases; i++) {
                int localizer = -1;
                int in = 0;
                String tempora[] = clases.get(i).getAgregacion();
                for (int j = 0; j < clases.get(i).agregacion.length; j++) {
                    if (tempora[in].equals(tempora[j]) && j != in) {
                        in = j;
                    } else {
                        String temporal = clases.get(i).getPosAgregacion(j);
                        for (int l = 0; l < nombres.size(); l++) {
                            if (nombres.get(l).equals(temporal)) {
                                localizer = l;
                            }
                        }

                        Graphics dia = panel.getGraphics();
                        // Lee el archivo
                        try {
                            bi = ImageIO.read(new File("imgs/agre" + i + localizer + ".jpg"));
                        } catch (IOException ex) {
                            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Image as = bi;

                        dia.drawImage(as, 10 + x, posY[2], null);

                        x += as.getWidth(null) + 10;
                        y = as.getHeight(null) + posY[2];

                        if (y > yMax) {
                            yMax = y;
                        }
                        if (x > xMax) {
                            xMax = x;
                        }
                    }
                }
            }
            //</editor-fold>

            // <editor-fold defaultstate="collapsed" desc="Dibujar Cardinalidad"> 
            x = 0;
            if (yMax == 0) {
                posY[3] = posY[2];
            } else {
                posY[3] = yMax + 10;
            }
            yMax = 0;
            for (int i = 0; i < nCar; i++) {
                Graphics dia = panel.getGraphics();
                // Lee el archivo
                try {
                    bi = ImageIO.read(new File("imgs/car" + car.listas.get(i)[0] + car.listas.get(i)[1] + ".jpg"));
                } catch (IOException ex) {
                    Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                }
                Image as = bi;

                dia.drawImage(as, 10 + x, posY[3], null);

                x += as.getWidth(null) + 10;
                y = as.getHeight(null) + posY[3];

                if (y > yMax) {
                    yMax = y;
                }
                if (x > xMax) {
                    xMax = x;
                }

            }
            //</editor-fold>

            // <editor-fold defaultstate="collapsed" desc="Dibujar Relaciones"> 
            x = 0;
            if (yMax == 0) {
                posY[4] = posY[3];
            } else {
                posY[4] = yMax + 10;
            }
            yMax = 0;
            for (int i = 0; i < ext.cnt; i++) {
                Graphics dia = panel.getGraphics();
                // Lee el archivo
                try {
                    bi = ImageIO.read(new File("imgs/her" + ext.lista.get(i)[0] + ext.lista.get(i)[1] + ".jpg"));
                } catch (IOException ex) {
                    Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                }
                Image as = bi;

                dia.drawImage(as, 10 + x, posY[4], null);

                x += as.getWidth(null) + 10;
                y = as.getHeight(null) + posY[4];

                if (y > yMax) {
                    yMax = y;
                }
                if (x > xMax) {
                    xMax = x;
                }
            }

            for (int i = 0; i < imp.cnt; i++) {
                Graphics dia = panel.getGraphics();
                // Lee el archivo
                try {
                    bi = ImageIO.read(new File("imgs/rea" + imp.lista.get(i)[0] + imp.lista.get(i)[1] + ".jpg"));
                } catch (IOException ex) {
                    Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
                }
                Image as = bi;

                dia.drawImage(as, 10 + x, posY[4], null);

                x += as.getWidth(null) + 10;
                y = as.getHeight(null) + posY[4];

                if (y > yMax) {
                    yMax = y;
                }
                if (x > xMax) {
                    xMax = x;
                }
            }
            //</editor-fold>
            // </editor-fold>

            // </editor-fold>
        } else if (rr.getSource() == btnBorrar) {
            // <editor-fold defaultstate="collapsed" desc="Borrar Diagrama">
            Graphics k = panel.getGraphics();
            k.setColor(fondo);
            k.fillRect(3, 10, spDiagrama.getWidth() - 6, spDiagrama.getHeight() - 14);
            k.setColor(Color.black);
            // </editor-fold>
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Funciones">
    public boolean charAccept(String palabra) {
        for (int i = 0; i < palabra.length(); i++) {
            if (!Character.isLetter(palabra.charAt(i))) {
                char dis = '_';
                if (i == 0 && Character.isDigit(palabra.charAt(i))) {
                    return false;
                } else if (dis != palabra.charAt(i) && !Character.isDigit(palabra.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    private void ingresarAtributos(ArrayList<String> arreglo, ArrayList<Clases> clases, int localizador) {
        String atributos[] = new String[arreglo.size()];
        for (int i = 0; i < arreglo.size(); i++) {
            atributos[i] = arreglo.get(i);
        }
        clases.get(localizador).setAtributos(atributos);
    }

    private void ingresarMetodos(ArrayList<String> arreglo, ArrayList<Clases> clases, int localizador) {
        String metodos[] = new String[arreglo.size()];
        for (int i = 0; i < arreglo.size(); i++) {
            metodos[i] = arreglo.get(i);
        }
        clases.get(localizador).setMetodos(metodos);
    }

    // Guarda los atributos en sus tres diferentes tipos
    private void agregarAtrib(String[] tem, ArrayList<String> nom, ArrayList<String> atributos, String discriminante, int posC, int pos) {
        String dis = "";

        if (null == discriminante && tem[pos] != null) {
            JOptionPane.showMessageDialog(null, "ERROR en atributo sin definición de acceso", "Error atributo", JOptionPane.ERROR_MESSAGE);
            clases.get(posC).setCreada(false);
        } else {
            switch (discriminante) {
                case "public":
                    dis = "+";
                    break;
                case "private":
                    dis = "-";
                    break;
                case "protected":
                    dis = "#";
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "ERROR en atributo sin definición de acceso", "Error atributo", JOptionPane.ERROR_MESSAGE);
                    clases.get(posC).setCreada(false);
                    break;
            }
        }

        tem[pos] = tem[pos].replace(discriminante, "");
        if (tem[pos].contains("new")) {
            tem[pos] = tem[pos].replace("new", "");
            String nombre = tem[pos].replace("[]", "");
            // Comprueba que la clase inicie correctamente
            if (!charAccept(nombre)) {
                JOptionPane.showMessageDialog(null, "ERROR caracter invalido en nombre de un atributo", "Error clase", JOptionPane.ERROR_MESSAGE);
                clases.get(posC).setCreada(false);
            } else {
                if (nom.contains(nombre)) {
                    atributos.add(dis + " new " + tem[pos]);
                    // Comprobar que la clase pueda crear objetos
                    int tipoC = -1;
                    for (int i = 0; i < nom.size(); i++) {
                        if (nom.get(i).equals(nombre)) {
                            tipoC = clases.get(i).tipo;
                            break;
                        }
                    }
                    if (tipoC != 0) {
                        JOptionPane.showMessageDialog(null, "ERROR no se encontro la clase " + nombre, "Error clase", JOptionPane.ERROR_MESSAGE);
                        clases.get(posC).setCreada(false);
                    } else if (tem[pos].contains("[]")) { // Agregación
                        // dimensión de las agregaciones
                        int dimension = clases.get(posC).getAgregacion().length + 1;
                        // declaración del arreglo de agregaciones
                        String temAgre[] = new String[dimension];
                        // Ingreso de las anteriores agregaciones
                        for (int i = 0; i < (dimension - 1); i++) {
                            temAgre[i] = clases.get(posC).getPosAgregacion(i);
                        }
                        // Ingreso de la ultima agregación
                        temAgre[dimension - 1] = nombre;
                        // Asignación de la nueva lista de agregación
                        clases.get(posC).setAgregacion(temAgre);
                    } else { // Composición
                        // dimensión de las composición
                        int dimension = clases.get(posC).getComposicion().length + 1;
                        // declaración del arreglo de composiciones
                        String temComp[] = new String[dimension];
                        // Ingreso de las anteriores composiciones
                        for (int i = 0; i < (dimension - 1); i++) {
                            temComp[i] = clases.get(posC).getPosComposicion(i);
                        }
                        // Ingreso de la ultima composición
                        temComp[dimension - 1] = tem[pos];
                        // Asignación de la nueva lista de composición
                        clases.get(posC).setComposicion(temComp);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR en composición o agregación", "Error atributo", JOptionPane.ERROR_MESSAGE);
                    clases.get(posC).setCreada(false);
                }
            }
        } else if (!atributos.contains(tem[pos])) {
            // Comprueba que la clase inicie correctamente
            if (!charAccept(tem[pos])) {
                JOptionPane.showMessageDialog(null, "ERROR caracter invalido en nombre de un atributo", "Error clase", JOptionPane.ERROR_MESSAGE);
                clases.get(posC).setCreada(false);
            } else {
                atributos.add(dis + " " + tem[pos]);
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR dos atributos o más con el mismo nombre", "Error atributo", JOptionPane.ERROR_MESSAGE);
            clases.get(posC).setCreada(false);
        }
    }
    // </editor-fold>
}
