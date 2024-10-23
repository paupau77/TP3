
package gestordeinventario;

public class Administrador extends Usuario {

    public Administrador(String nombre, String email, String clave) {
        super(nombre, email, clave);
    }

    @Override
    public void mostrarOpciones() {
        System.out.println("Opciones de Administrador:");
        System.out.println("1. Registrar Producto");
        System.out.println("2. Editar Producto");
        System.out.println("3. Registrar Entrada");
        System.out.println("4. Registrar Salida");
        System.out.println("5. Visualizar Inventario");
        System.out.println("6. Generar Reporte");
    }

    // Métodos específicos del Administrador

    public void registrarProducto(String nombre, String categoria, int cantidad, String proveedor, java.util.Date fechaAdquisicion) {
        // Implementación para registrar un producto
        System.out.println("Producto registrado exitosamente.");
    }

    public void editarProducto(int idProducto, String nuevoNombre, int nuevaCantidad) {
        // Implementación para editar un producto
        System.out.println("Producto editado exitosamente.");
    }

    public void registrarEntrada(int idProducto, int cantidad) {
        // Implementación para registrar una entrada
        System.out.println("Entrada registrada exitosamente.");
    }

    public void registrarSalida(int idProducto, int cantidad) {
        // Implementación para registrar una salida
        System.out.println("Salida registrada exitosamente.");
    }

    public void visualizarInventario() {
        // Implementación para visualizar el inventario
        System.out.println("Inventario visualizado.");
    }

    public void generarReporte() {
        // Implementación para generar reportes
        System.out.println("Reporte generado.");
    }
}
