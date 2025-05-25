// Administrador.java
package org.example.proyectofx.model;

public class Administrador extends Empleado {

    public Administrador(int id, String nombre) {
        super(id, nombre);
    }

    /** Agrega empleado al sistema */
    public void agregarEmpleadoSistema(Biblioteca biblioteca, Empleado empleado) throws Exception {
        biblioteca.registrarEmpleado(empleado);
    }

    /** Elimina empleado del sistema */
    public void eliminarEmpleado(Biblioteca biblioteca, int empleadoId) throws Exception {
        biblioteca.eliminarEmpleado(empleadoId);
    }

    /** Modifica un empleado existente */
    public void modificarEmpleado(Biblioteca biblioteca, Empleado empleadoModificado) throws Exception {
        biblioteca.modificarEmpleado(empleadoModificado);
    }
}
