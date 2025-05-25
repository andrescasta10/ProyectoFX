package org.example.proyectofx.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {

    //Atributos de la clase Biblioteca
    private String nombre;
    private List<Empleado> listEmpleado;
    private List<Libro> listLibros;
    private List<Usuario> listUsuarios;

    //Constructor de la clase Biblioteca
    public Biblioteca(String nombre){
        this.nombre = nombre;
        this.listEmpleado = new ArrayList<>();
        this.listLibros = new ArrayList<>();
        this.listUsuarios = new ArrayList<>();
    }

    public static Usuario verificarCredenciales(String correo, String contrasenia) throws Exception{
       if (correo == null || correo.isEmpty()) throw new Exception("Verifique el correo ingresado");
       if (contrasenia == null || contrasenia.isEmpty()) throw new Exception("Verifique la contraseña ingresada");

       Usuario usuario = buscarUsuarioporCorreo(correo);

       if(usuario == null) throw new Exception("El correo no existe");
       if (!usuario.getContrasenia().equals(contrasenia)) throw new Exception("La contraseña es incorrecta");
       return usuario;
    }

    public Usuario buscarUsuarioporCorreo(String correo){
        for (Usuario usuario : listUsuarios){
            if (usuario.getCorreo().equals(correo)){
                return usuario;
            }
        }
        return null;
    }

    //Gets y Sets de la clase Biblioteca
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public List<Empleado> getListEmpleado() {
        return listEmpleado;
    }

    public void setListEmpleado(List<Empleado> listEmpleado){
        this.listEmpleado = listEmpleado;
    }

    public List<Libro> getListLibros() {
        return listLibros;
    }

    public void setListLibros(List<Libro> listLibros) {
        this.listLibros = listLibros;
    }

    public List<Usuario> getListUsuarios (){
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuario> listUsuarios){
        this.listUsuarios = listUsuarios;
    }

    //Metodo toString de la clase Biblioteca
    @Override
    public String toString() {
        return "Biblioteca{" +
                "nombre='" + nombre + '\'' +
                ", listEmpleado=" + listEmpleado +
                ", listLibros=" + listLibros +
                ", listUsuarios=" + listUsuarios +
                '}';
    }
}
