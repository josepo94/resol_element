/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectorbase;

/**
 *
 * @author Bryan Beltrán
 */
public class Palabra {
    String nombre;
    String elementos;

    public Palabra(String nombre, String elementos) {
        this.nombre = nombre;
        this.elementos = elementos;
    }
   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getElementos() {
        return elementos;
    }

    public void setElementos(String elementos) {
        this.elementos = elementos;
    }
    
}
