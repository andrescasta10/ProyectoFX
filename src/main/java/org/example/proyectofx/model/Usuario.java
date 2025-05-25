package org.example.proyectofx.model;

public class Usuario {
    private String nombre, correo, contrasenia;
    private int ID;
    private Boolean deuda;


    public Usuario(String nombre, int ID, String correo, String contrasenia, Boolean deuda){
        this.nombre = nombre;
        this.ID = ID;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.deuda = deuda;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public int getID(){
        return ID;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Boolean getDeuda() {
        return deuda;
    }

    public void setDeuda(Boolean deuda) {
        this.deuda = deuda;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", ID=" + ID +
                ", deuda=" + deuda +
                '}';
    }
}
