// Administrador.java
package org.example.proyectofx.model;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Empleado {
    private List<Empleado> listaEmpleados;


    public Administrador(int id, String nombre) {
        super(id, nombre);
        this.listaEmpleados = new ArrayList<>();
    }

    public List<Empleado> getListaEmpleados() {

        return listaEmpleados;
    }

    public void registrarEmpleado(Empleado emp) throws Exception {
        for (Empleado e : listaEmpleados) {
            if (e.getId() == emp.getId()) {
                throw new Exception("Ya existe un empleado con ID: " + emp.getId());
            }
        }
        listaEmpleados.add(emp);
    }

    public void modificarEmpleado(Empleado empModificado) throws Exception {
        boolean encontrado = false;
        for (int i = 0; i < listaEmpleados.size(); i++) {
            Empleado e = listaEmpleados.get(i);
            if (e.getId() == empModificado.getId()) {
                listaEmpleados.set(i, empModificado);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new Exception("No existe empleado con ID: " + empModificado.getId());
        }
    }


    public void eliminarEmpleado(int empId) throws Exception {
        boolean eliminado = false;
        for (int i = 0; i < listaEmpleados.size(); i++) {
            Empleado e = listaEmpleados.get(i);
            if (e.getId() == empId) {
                listaEmpleados.remove(i);
                eliminado = true;
                break;
            }
        }
        if (!eliminado) {
            throw new Exception("No existe empleado con ID: " + empId);
        }
    }


}
