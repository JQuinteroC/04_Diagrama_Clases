/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg04_diagrama_clases;

/**
 *
 * @author JQuintero
 */
public class clases {
    String nombre;    
    String[] atributos;
    String[] metodos;
    boolean creada;
    int tipo; //0 = clase, 1 = abstracta, 2 = interface

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
    
    
}
