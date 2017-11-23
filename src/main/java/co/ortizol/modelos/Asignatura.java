/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ortizol.modelos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Asignatura {
    private int id;
    private String nombre;
    private int creditos;
    private String descripcion;

    public Asignatura() {
    }

    public Asignatura(String nombre, int creditos, String descripcion) {
        this.nombre = nombre;
        this.creditos = creditos;
        this.descripcion = descripcion;
    }

    public Asignatura(int id, String nombre, int creditos, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
