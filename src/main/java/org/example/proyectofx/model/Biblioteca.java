// Biblioteca.java
package org.example.proyectofx.model;

import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Biblioteca {

    private String nombre;
    private List<Empleado> listaEmpleados;
    private List<Libro> listaLibros;
    private List<Usuario> listaUsuarios;
    private List<PrestamoLibro> listaPrestamos; // lista de todos los préstamos

    // Constructor de la clase Biblioteca
    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.listaEmpleados = new ArrayList<>();
        this.listaLibros = new ArrayList<>();
        this.listaUsuarios = new ArrayList<>();
        this.listaPrestamos = new ArrayList<>();
    }

    public Usuario verificarCredenciales(String correo, String contrasenia) throws Exception{
        if (correo == null || correo.isEmpty()) throw new Exception("Verifique el correo ingresado");
        if (contrasenia == null || contrasenia.isEmpty()) throw new Exception("Verifique la contraseña ingresada");

        Usuario usuario = buscarUsuarioporCorreo(correo);

        if(usuario == null) throw new Exception("El correo no existe");
        if (!usuario.getContrasenia().equals(contrasenia)) throw new Exception("La contraseña es incorrecta");
        return usuario;
    }

    public Usuario buscarUsuarioporCorreo(String correo){
        for (Usuario usuario : listaUsuarios){
            if (usuario.getCorreo().equals(correo)){
                return usuario;
            }
        }
        return null;
    }

    // ----- Gestión de Empleados -----

    public void registrarEmpleado(Empleado emp) throws Exception {
        for (Empleado e : listaEmpleados) {
            if (e.getId() == emp.getId()) {
                throw new Exception("Ya existe un empleado con ID: " + emp.getId());
            }
        }
        listaEmpleados.add(emp);
    }

    public void modificarEmpleado(int idOriginal, Empleado empModificado) throws Exception {
        boolean encontrado = false;


        for (Empleado e : listaEmpleados) {
            if (e.getId() == empModificado.getId() && e.getId() != idOriginal) {
                throw new Exception("Ya existe un empleado con el nuevo ID: " + empModificado.getId());
            }
        }

        for (int i = 0; i < listaEmpleados.size(); i++) {
            Empleado actual = listaEmpleados.get(i);

            if (actual.getId() == idOriginal) {
                listaEmpleados.set(i, empModificado);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new Exception("No se encontró empleado con ID original: " + idOriginal);
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

    // ----- Gestión de Libros -----

    public void registrarLibro(Libro libro) throws Exception {
        for (Libro l : listaLibros) {
            if (l.getID() == libro.getID()) {
                throw new Exception("Ya existe un libro con ID: " + libro.getID());
            }
        }
        listaLibros.add(libro);
    }

    public boolean buscarLibro(Libro libro) {
        for (Libro libroB : listaLibros) {
            if (libro.getID() == libroB.getID()) {
                return true;
            }
        }
        return false;
    }

    // ----- Gestión de Usuarios -----

    public void registrarUsuario(Usuario usuario) throws Exception {
        for (Usuario u : listaUsuarios) {
            if (u.getId() == usuario.getId()) {
                throw new Exception("Ya existe un usuario con ID: " + usuario.getId());
            }
        }
        listaUsuarios.add(usuario);
    }

    // ----- Gestión de Préstamos -----

    /**
     * Crea un préstamo si:
     * 1) El libro existe y está DISPONIBLE.
     * 2) El usuario existe, no tiene deudas y no supera su límite.
     */
    public boolean registrarPrestamo(Usuario usuario, Libro libro) throws Exception {
        // 1) Verificar que libro exista
        Libro libEncontrado = null;
        for (Libro l : listaLibros) {
            if (l.getID() == libro.getID()) {
                libEncontrado = l;
                break;
            }
        }
        if (libEncontrado == null) {
            throw new Exception("Libro con ID " + libro.getID() + " no encontrado.");
        }
        // No prestar libro de referencia
        if (libEncontrado.getEstado() == Estado.REFERENCIA) {
            throw new Exception("Este libro es de referencia y no puede ser prestado.");
        }
        // Verificar disponibilidad
        if (libEncontrado.getEstado() != Estado.DISPONIBLE) {
            throw new Exception("El libro no está disponible para préstamo.");
        }

        // 2) Verificar que usuario exista
        Usuario usrEncontrado = null;
        for (Usuario u : listaUsuarios) {
            if (u.getId() == usuario.getId()) {
                usrEncontrado = u;
                break;
            }
        }
        if (usrEncontrado == null) {
            throw new Exception("Usuario con ID " + usuario.getId() + " no encontrado.");
        }
        // Verificar deudas
        if (usrEncontrado.tieneDeuda()) {
            throw new Exception("El usuario tiene deudas pendientes.");
        }
        // Contar préstamos activos de este usuario
        int prestamosActivos = 0;
        for (PrestamoLibro p : listaPrestamos) {
            if (p.getUsuario().getId() == usuario.getId() && !p.isDevuelto()) {
                prestamosActivos++;
            }
        }
        if (prestamosActivos >= usrEncontrado.getMaximoLibrosPermitidos()) {
            throw new Exception("El usuario ha alcanzado el límite de préstamos activos.");
        }

        // 3) Crear el préstamo
        LocalDate hoy = LocalDate.now();
        LocalDate vencimiento = hoy.plusDays(usrEncontrado.getDiasMaximosPrestamo());
        String idTransaccion = usuario.getId() + "-" + libro.getID() + "-" + System.currentTimeMillis();
        PrestamoLibro nuevoPrestamo = new PrestamoLibro(idTransaccion, hoy.toString(), vencimiento.toString(), libEncontrado, usrEncontrado);
        listaPrestamos.add(nuevoPrestamo);

        // 4) Actualizar estado del libro
        libEncontrado.setEstado(Estado.PRESTADO);

        return true;
    }

    /**
     * Registra la devolución:
     * 1) Busca el préstamo activo (mismo libro y usuario, y devuelto == false).
     * 2) Marca el préstamo como devuelto y actualiza fecha de devolución.
     * 3) Cambia el estado del libro a DISPONIBLE.
     * 4) Si hay atraso, marca deuda en el usuario.
     */
    public void registrarDevolucion(int usuarioId, int libroId) throws Exception {
        PrestamoLibro prestamoEncontrado = null;
        for (PrestamoLibro p : listaPrestamos) {
            if (p.getUsuario().getId() == usuarioId
                    && p.getLibro().getID() == libroId
                    && !p.isDevuelto()) {
                prestamoEncontrado = p;
                break;
            }
        }
        if (prestamoEncontrado == null) {
            throw new Exception("No existe préstamo activo para ese usuario y libro.");
        }

        // 1) Actualizar fecha de devolución y marcar devuelto
        LocalDate hoy = LocalDate.now();
        prestamoEncontrado.setFechaDevolucion(hoy.toString());
        prestamoEncontrado.setDevuelto(true);

        // 2) Actualizar estado del libro
        Libro libroDev = prestamoEncontrado.getLibro();
        libroDev.setEstado(Estado.DISPONIBLE);

        // 3) Verificar atraso
        LocalDate fechaVenc = LocalDate.parse(prestamoEncontrado.getFechaVencimiento());
        if (hoy.isAfter(fechaVenc)) {
            prestamoEncontrado.getUsuario().setDeuda(true);
        }
    }

    // ----- Reportes mínimos -----

    public void reporteLibrosPrestados() {
        System.out.println("=== Libros Prestados Actualmente ===");
        for (PrestamoLibro p : listaPrestamos) {
            if (!p.isDevuelto()) {
                System.out.println(p.getLibro());
            }
        }
    }

    public void reporteLibrosDisponibles() {
        System.out.println("=== Libros Disponibles ===");
        for (Libro l : listaLibros) {
            if (l.getEstado() == Estado.DISPONIBLE) {
                System.out.println(l);
            }
        }
    }

    public void reporteUsuariosConDeuda() {
        System.out.println("=== Usuarios con Deudas ===");
        for (Usuario u : listaUsuarios) {
            if (u.tieneDeuda()) {
                System.out.println(u);
            }
        }
    }

    public Empleado buscarEmpleado(int id){
        for(Empleado empleado : listaEmpleados){
            if(empleado.getId() == (id)){
                return  empleado;
            }
        }
        return null;
    }

    public void editarEmpleado( Empleado empleado, int id, String nombre) throws Exception {


        if (empleado.getId() == id) {
            empleado.setNombre(nombre);
            editar(empleado);
        } else if (buscarEmpleado(id) != null) {
            throw new Exception("Ya existe un empleado con el mismo id");
        } else {
            empleado.setId(id);
            empleado.setNombre(nombre);
            editar(empleado);
        }
    }

    public void editar(Empleado empleado){
        listaEmpleados.set(listaEmpleados.indexOf(empleado), empleado);
    }


    // ----- Getters y Setters -----

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public List<Libro> getListaLibros() {
        return listaLibros;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public List<PrestamoLibro> getListaPrestamos() {
        return listaPrestamos;
    }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "nombre='" + nombre + '\'' +
                ", empleados=" + listaEmpleados +
                ", libros=" + listaLibros +
                ", usuarios=" + listaUsuarios +
                ", prestamos=" + listaPrestamos +
                '}';
    }
}
