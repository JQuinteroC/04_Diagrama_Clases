package pkg04_diagrama_clases;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class Controlador extends Exception implements ActionListener {

    Vista d;
    Modelo t;
    int nClases;
    int nRelaciones;
    int nComAgre;
    int yC;
    int yD;
    int xD;
    Container cntClases;
    Container cntDiagrama;
    ArrayList<clases> clases = new ArrayList();
    ArrayList<String> nombres = new ArrayList();

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
        nComAgre = 0;
        yC = 0;
        yD = 0;
        // Definir container
        cntClases = new Container();
        cntClases.setLayout(null);
        cntClases.setPreferredSize(new Dimension(845, 250));

        cntDiagrama = new Container();
        cntDiagrama.setLayout(null);
        cntDiagrama.setPreferredSize(new Dimension(660, 660));
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
                        d.lblEtiquetas[nClases][1].setBounds(325, 0 + yC, 150, 25);
                        d.txtClase[nClases].setBounds(10, 20 + yC, 307, 80);
                        d.txtClase[nClases].setText("class ");
                        d.txtMetodo[nClases].setBounds(327, 20 + yC, 307, 80);
                        // Agrega los componentes al contenedor
                        cntClases.add(d.lblEtiquetas[nClases][0]);
                        cntClases.add(d.lblEtiquetas[nClases][1]);
                        cntClases.add(d.txtClase[nClases]);
                        cntClases.add(d.txtMetodo[nClases]);
                        cntClases.setPreferredSize(new Dimension(645, 100 + yC));
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
                        d.txtRelacion[nRelaciones].setBounds(10, 20 + yC, 625, 30);
                        d.txtRelacion[nRelaciones].setBorder(null);
                        d.txtRelacion[nRelaciones].setFont(new Font("Consolas", 0, 11));
                        d.lblEtiquetas[nClases][2].setBounds(10, yC, 400, 25);
                        // Agrega los componentes al contenedor
                        cntClases.add(d.txtRelacion[nRelaciones]);
                        cntClases.add(d.lblEtiquetas[nClases][2]);
                        cntClases.setPreferredSize(new Dimension(645, 50 + yC));
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
                    // Inicializa las variables y controladores
                    Modelo md = new Modelo();
                    
                    cntDiagrama.removeAll();
                    cntDiagrama.repaint();
                    xD = 0;
                    yD = 0;
                    nombres.clear();
                    clases.clear();
                    // Definie variables
                    ArrayList<String> atrib = new ArrayList();
                    ArrayList<String> met = new ArrayList();
                    d.EdtClase = new ArrayList<>();
                    d.pnlClase = new ArrayList<>();
                    // Genera diagrama
                    if (nClases != 0) {
                        cntDiagrama.removeAll();
                        d.spDiagrama.getViewport().add(cntDiagrama);
                        for (int i = 0; i < nClases; i++) {
                            // Variables de control
                            clases temClass = new clases();
                            clases.add(temClass);
                            String temporal[] = new String[0];
                            atrib.clear();
                            met.clear();
                            int numSpaces[] = new int[0];
                            try { // Intenta hacer la clase
                                // <editor-fold defaultstate="collapsed" desc="Nombre y parametros"> 
                                temporal = d.txtClase[i].getText().split("\n"); // Toma el nombre y los parametros de la clase
                                numSpaces = new int[temporal.length]; // Toma el numero de espacios por linea en el nombre y parametros
                                for (int j = 0; j < temporal.length; j++) {
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
                                        } else if (clases.get(i).isCreada()) { // Atributos
                                            if (temporal[j].contains("public")) {
                                                agregarAtrib(temporal, nombres, atrib, "public", i, j);
                                            } else if (temporal[j].contains("private")) {
                                                agregarAtrib(temporal, nombres, atrib, "private", i, j);
                                            } else if (temporal[j].contains("protected")) {
                                                agregarAtrib(temporal, nombres, atrib, "protected", i, j);
                                            } else if (temporal[j] != null) {
                                                JOptionPane.showMessageDialog(null, "ERROR en atributo sin definición de acceso", "Error atributo", JOptionPane.ERROR_MESSAGE);
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
                                // <editor-fold defaultstate="collapsed" desc="Metodos">                                  
                                if (clases.get(i).isCreada()) { // Metodos
                                    temporal = d.txtMetodo[i].getText().split("\n"); // Toma los metodos de la clase clase
                                    numSpaces = new int[temporal.length]; // Toma el numero de espacios por linea en los metodos
                                    for (int j = 0; j < temporal.length; j++) { // Metodos
                                        if (numSpaces[j] <= 1) {
                                            temporal[j] = temporal[j].replace(" ", "");

                                            if (temporal[j].contains("public")) {
                                                temporal[j] = temporal[j].replace("public", "");
                                                if (!met.contains(temporal[j])) {
                                                    // Comprueba que la clase inicie correctamente
                                                    if (!charAccept(temporal[j].replace("()", ""))) {
                                                        JOptionPane.showMessageDialog(null, "ERROR caracter invalido en nombre de un atributo", "Error clase", JOptionPane.ERROR_MESSAGE);
                                                        clases.get(i).setCreada(false);
                                                    } else {
                                                        met.add("+ " + temporal[j]);
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
                                                        JOptionPane.showMessageDialog(null, "ERROR caracter invalido en nombre de un atributo", "Error clase", JOptionPane.ERROR_MESSAGE);
                                                        clases.get(i).setCreada(false);
                                                    } else {
                                                        met.add("- " + temporal[j]);
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
                                                        JOptionPane.showMessageDialog(null, "ERROR caracter invalido en nombre de un atributo", "Error clase", JOptionPane.ERROR_MESSAGE);
                                                        clases.get(i).setCreada(false);
                                                    } else {
                                                        met.add("# " + temporal[j]);
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

                                // <editor-fold defaultstate="collapsed" desc="Diagrama"> 
                                JTextPane txt = new JTextPane();

                                txt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                                txt.setMargin(new java.awt.Insets(5, 5, 5, 5));

                                JPanel pnl = new JPanel();
                                pnl.add(txt);
                                
                                d.EdtClase.add(txt);
                                d.pnlClase.add(pnl);
                                if (clases.get(i).isCreada()) {
                                    // <editor-fold defaultstate="collapsed" desc="Clases"> 
                                    // Modelo md = new Modelo();
                                    md.dibujarClases(i, d, clases, cntDiagrama, xD, yD);
                                    xD = md.xD;
                                    yD = md.yD;
                                    // </editor-fold>

                                    // <editor-fold defaultstate="collapsed" desc="Composición"> 
                                    md.dibujarComposicion(i, d, clases, nombres, cntDiagrama, xD, yD, nClases);
                                    // </editor-fold>
                                    // <editor-fold defaultstate="collapsed" desc="Agregación"> 
                                    // </editor-fold>
                                    // <editor-fold defaultstate="collapsed" desc="Carinalidad"> 
                                    // </editor-fold>
                                    // <editor-fold defaultstate="collapsed" desc="Herencia"> 
                                    // </editor-fold>
                                    // <editor-fold defaultstate="collapsed" desc="Realización"> 
                                    // </editor-fold>
                                } else {
                                    JLabel lblError = new JLabel("No se pudo crear la clase #" + (i + 1));
                                    cntDiagrama.add(lblError);
                                    lblError.setBounds(15 + xD, 25, 150, 25);
                                    cntDiagrama.setPreferredSize(new Dimension(xD + 200, 40 + yD));

                                    d.spDiagrama.getViewport().add(cntDiagrama);

                                    i = nClases;
                                }
                                // </editor-fold>
                            } catch (HeadlessException ex) {
                                clases.get(i).setCreada(false);
                                break;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Debe ingresar almenos una clase para poder generar el diagrama", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    for (int i = 0; i < nRelaciones; i++) {
                        // Crear relaciones ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    }
                    // </editor-fold>
                    break;
                default:
                    break;
            }
        }
    }

    private void ingresarAtributos(ArrayList<String> arreglo, ArrayList<clases> clases, int localizador) {
        String atributos[] = new String[arreglo.size()];
        for (int i = 0; i < arreglo.size(); i++) {
            atributos[i] = arreglo.get(i);
        }
        clases.get(localizador).setAtributos(atributos);
    }

    private void ingresarMetodos(ArrayList<String> arreglo, ArrayList<clases> clases, int localizador) {
        String metodos[] = new String[arreglo.size()];
        for (int i = 0; i < arreglo.size(); i++) {
            metodos[i] = arreglo.get(i);
        }
        clases.get(localizador).setMetodos(metodos);
    }

    /**
     * Guarda los atributos en sus tres diferentes tipos
     *
     * @param tem
     * @param nom
     * @param atributos
     * @param discriminante
     * @param posC
     * @param pos
     */
    private void agregarAtrib(String[] tem, ArrayList<String> nom, ArrayList<String> atributos, String discriminante, int posC, int pos) {
        String dis = "";

        if (null == discriminante) {
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
                    if (tem[pos].contains("[]")) { // Agregación
                        // dimensión de las agregaciones
                        int dimension = clases.get(posC).getAgregacion().length + 1;
                        // declaración del arreglo de agregaciones
                        String temAgre[] = new String[dimension];
                        // Ingreso de las anteriores agregaciones
                        for (int i = 0; i < (dimension - 1); i++) {
                            temAgre[i] = clases.get(posC).getPosAgregacion(i);
                        }
                        // Ingreso de la ultima agregación
                        temAgre[dimension - 1] = tem[pos];
                        // Asignación de la nueva lista de agregación
                        clases.get(posC).setAgregacion(temAgre);

                        nComAgre += 2;
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

                        nComAgre += 2;
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

        if (cNombre > cAtrib && cNombre > cMet) {
            return cNombre;
        } else if (cAtrib > cNombre && cAtrib > cMet) {
            return cAtrib;
        }
        return cMet;
    }

    /**
     * Valida si el nombre tiene caracteres aceptados
     *
     * @param palabra
     * @return
     */
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
}
