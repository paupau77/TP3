package gestordeinventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

// Clase que representa un producto del inventario
public class Producto {
    private String nombre;
    private String categoria;
    private int cantidad;
    private String proveedor;
    private Date fechaAdquisicion;

    // Constructor de la clase Producto
    public Producto(String nombre, String categoria, int cantidad, String proveedor, Date fechaAdquisicion) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.fechaAdquisicion = fechaAdquisicion;
    }

    // Método para registrar un nuevo producto en la base de datos
    public void registrarProducto() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "INSERT INTO Productos (nombre, categoria, cantidad, proveedor, fechaAdquisicion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, categoria);
            stmt.setInt(3, cantidad);
            stmt.setString(4, proveedor);
            stmt.setDate(5, new java.sql.Date(fechaAdquisicion.getTime()));
            stmt.executeUpdate();
            System.out.println("Producto registrado exitosamente.");
        }
    }

    // Método para obtener todos los productos de la base de datos
    public static void obtenerProductos() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "SELECT * FROM Productos";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("idProducto") + ", Nombre: " + rs.getString("nombre") +
                        ", Categoría: " + rs.getString("categoria") + ", Cantidad: " + rs.getInt("cantidad"));
            }
        }
    }

    // Método para editar un producto existente
    public static void editarProducto(int idProducto, String nuevoNombre, int nuevaCantidad) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "UPDATE Productos SET nombre = ?, cantidad = ? WHERE idProducto = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nuevoNombre);
            stmt.setInt(2, nuevaCantidad);
            stmt.setInt(3, idProducto);
            stmt.executeUpdate();
            System.out.println("Producto editado exitosamente.");
        }
    }
}