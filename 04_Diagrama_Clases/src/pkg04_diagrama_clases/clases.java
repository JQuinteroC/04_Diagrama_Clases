/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg04_diagrama_clases;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class clases {

    String nombre;
    String[] atributos;
    String[] metodos;
    String[] composicion;
    String[] agregacion;
    boolean creada;
    int tipo; //0 = clase, 1 = abstracta, 2 = interface

    public clases() {
        nombre = "";
        atributos = new String[0];
        metodos = new String[0];
        composicion = new String[0];
        agregacion = new String[0];
        creada = false;
        tipo = -1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAtributos(int i) {
        return atributos[i];
    }

    public void setAtributos(String[] atributos) {
        this.atributos = atributos;
    }

    public String getMetodos(int i) {
        return metodos[i];
    }

    public void setMetodos(String[] metodos) {
        this.metodos = metodos;
    }

    public boolean isCreada() {
        return creada;
    }

    public void setCreada(boolean creada) {
        this.creada = creada;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String[] getComposicion() {
        return composicion;
    }

    public String getPosComposicion(int i) {
        return composicion[i];
    }

    public void setComposicion(String[] composición) {
        this.composicion = composición;
    }

    public String[] getAgregacion() {
        return agregacion;
    }

    public String getPosAgregacion(int i) {
        return agregacion[i];
    }

    public void setAgregacion(String[] agregación) {
        this.agregacion = agregación;
    }

}
