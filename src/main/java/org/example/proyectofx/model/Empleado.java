package org.example.proyectofx.model;

//Clase padre Empleado
public class Empleado {
    private String nombre,identificacion;

    //Constructor de la clase Empleado
    public Empleado(String nombre, String identificacion){
        this.nombre = nombre;
        this.identificacion = identificacion;
    }

    //Gets y Sets de la clase Empleado
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion){
        this.identificacion = identificacion;
    }

    //Metodo toString de la clase Empleado
    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", identificacion='" + identificacion + '\'' +
                '}';
    }
}
