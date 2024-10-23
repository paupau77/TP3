package gestordeinventario;

public class Gerente extends Usuario {

    public Gerente(String nombre, String email, String clave) {
        super(nombre, email, clave);
    }

    @Override
    public void mostrarOpciones() {
        System.out.println("Opciones de Gerente:");
        System.out.println("1. Visualizar Inventario");
        System.out.println("2. Generar Reporte");
    }

    // Métodos específicos del Gerente

    public void visualizarInventario() {
        // Implementación para visualizar el inventario
        System.out.println("Inventario visualizado.");
    }

    public void generarReporte() {
        // Implementación para generar reportes
        System.out.println("Reporte generado.");
    }
}

