/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ortizol.modelos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tarea {

    private int id;
    private String nombre;
    private double nota;
    private int idAsignatura;
    private int idUsuarioProfesor;
    private int idUsuarioEstudiante;

    public Tarea() {
    }

    public Tarea(String nombre, double nota, int idAsignatura, int idUsuario) {
        this.nombre = nombre;
        this.nota = nota;
        this.idAsignatura = idAsignatura;
        this.idUsuarioProfesor = idUsuario;
    }

    public Tarea(int id, String nombre, double nota, int idAsignatura, int idUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.nota = nota;
        this.idAsignatura = idAsignatura;
        this.idUsuarioProfesor = idUsuario;
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

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public int getIdUsuarioProfesor() {
        return idUsuarioProfesor;
    }

    public void setIdUsuarioProfesor(int idUsuarioProfesor) {
        this.idUsuarioProfesor = idUsuarioProfesor;
    }

    public int getIdUsuarioEstudiante() {
        return idUsuarioEstudiante;
    }

    public void setIdUsuarioEstudiante(int idUsuarioEstudiante) {
        this.idUsuarioEstudiante = idUsuarioEstudiante;
    }
}
