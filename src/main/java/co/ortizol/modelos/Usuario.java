/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ortizol.modelos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario {
    private int id;
    private String nombre;
    private String contraseghnia;
    private int idRol;
    
    public Usuario(){}

    public Usuario(int id) {
        this.id = id;
    }

    public Usuario(String nombre, String contraseghnia) {
        this.nombre = nombre;
        this.contraseghnia = contraseghnia;
    }

    public Usuario(String nombre, String contraseghnia, int idRol) {
        this.nombre = nombre;
        this.contraseghnia = contraseghnia;
        this.idRol = idRol;
    }

    public Usuario(int id, String nombre, String contraseghnia, int idRol) {
        this.id = id;
        this.nombre = nombre;
        this.contraseghnia = contraseghnia;
        this.idRol = idRol;
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

    public String getContraseghnia() {
        return contraseghnia;
    }

    public void setContraseghnia(String contraseghnia) {
        this.contraseghnia = contraseghnia;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
}
