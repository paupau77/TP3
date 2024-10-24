package gestordeinventario;

import java.sql.SQLException;

// Subclase de Usuario que representa al Administrador
public class Administrador extends Usuario {

    // Constructor de la clase Administrador
    public Administrador(String nombre, String email, String clave) {
        super(nombre, email, clave);
    }

    // Mostrar las opciones del menú para el Administrador
    @Override
    public void mostrarOpciones() {
        System.out.println("\nMenú Administrador:");
        System.out.println("1. Registrar Producto");
        System.out.println("2. Editar Producto");
        System.out.println("3. Registrar Entrada");
        System.out.println("4. Registrar Salida");
        System.out.println("5. Visualizar Inventario");
        System.out.println("6. Generar Reporte");
        System.out.println("7. Cerrar Sesión");
    }

    // Implementación para registrar un producto
    public void registrarProducto(Producto producto) throws SQLException {
        producto.registrarProducto();
    }

    // Implementación para editar un producto
    public void editarProducto(int idProducto, String nuevoNombre, int nuevaCantidad) throws SQLException {
        Producto.editarProducto(idProducto, nuevoNombre, nuevaCantidad);
    }

    // Implementación para registrar entrada o salida
    public void registrarEntrada(int idProducto, int cantidad) throws SQLException {
        Inventario inventario = new Inventario();
        inventario.registrarEntrada(idProducto, cantidad);
    }

    public void registrarSalida(int idProducto, int cantidad) throws SQLException {
        Inventario inventario = new Inventario();
        inventario.registrarSalida(idProducto, cantidad);
    }

    // Implementación para visualizar el inventario
    @Override
    public void visualizarInventario() throws SQLException {
        Producto.obtenerProductos();  // Llamamos al método que obtiene todos los productos
    }

    // Implementación para generar reporte
    @Override
    public void generarReporte() throws SQLException {
        System.out.println("Generando reporte para Administrador...");
        // Lógica básica de reporte (puedes expandirla según lo necesites)
    }
}