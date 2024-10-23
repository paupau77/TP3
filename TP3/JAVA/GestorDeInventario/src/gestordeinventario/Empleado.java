package gestordeinventario;

public class Empleado extends Usuario {

    public Empleado(String nombre, String email, String clave) {
        super(nombre, email, clave);
    }

    @Override
    public void mostrarOpciones() {
        System.out.println("Opciones de Empleado:");
        System.out.println("1. Registrar Entrada");
        System.out.println("2. Registrar Salida");
        System.out.println("3. Visualizar Inventario");
    }

    // Métodos específicos del Empleado

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
}