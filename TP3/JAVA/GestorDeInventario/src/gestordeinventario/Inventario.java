package gestordeinventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Inventario {

    public void registrarEntrada(int idProducto, int cantidad) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "UPDATE Productos SET cantidad = cantidad + ? WHERE idProducto = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, cantidad);
        stmt.setInt(2, idProducto);
        stmt.executeUpdate();
        System.out.println("Entrada registrada exitosamente.");
        stmt.close();
        conn.close();
    }

    public void registrarSalida(int idProducto, int cantidad) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "UPDATE Productos SET cantidad = cantidad - ? WHERE idProducto = ? AND cantidad >= ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, cantidad);
        stmt.setInt(2, idProducto);
        stmt.setInt(3, cantidad);
        int rowsUpdated = stmt.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Salida registrada exitosamente.");
        } else {
            System.out.println("Error: No hay suficiente stock para registrar la salida.");
        }
        stmt.close();
        conn.close();
    }
}
