package gestordeinventario;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Clase que gestiona el inventario
public class Inventario {

    // Método para registrar la entrada de productos
    public void registrarEntrada(int idProducto, int cantidad) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "UPDATE Productos SET cantidad = cantidad + ? WHERE idProducto = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cantidad);
            stmt.setInt(2, idProducto);
            stmt.executeUpdate();
            System.out.println("Entrada registrada exitosamente.");
        }
    }

    // Método para registrar la salida de productos
    public void registrarSalida(int idProducto, int cantidad) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "UPDATE Productos SET cantidad = cantidad - ? WHERE idProducto = ? AND cantidad >= ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cantidad);
            stmt.setInt(2, idProducto);
            stmt.setInt(3, cantidad);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Salida registrada exitosamente.");
            } else {
                System.out.println("No hay suficiente stock para registrar la salida.");
            }
        }
    }
}