package gestordeinventario;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static Connection conexion;

        // URL de la base de datos, usuario y contraseña
    private static final String URL = "jdbc:mysql://localhost:3306/inventario_db";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "password";
    
    // Método para conectar a la base de datos
    public static void conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario_db", "root", "password");
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos.");
        }
    }

        // Método para obtener la conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        // Verificar si la conexión es nula o está cerrada
        if (conexion == null || conexion.isClosed()) {
            try {
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
                System.out.println("Conexión establecida.");
            } catch (SQLException e) {
                System.out.println("Error al conectar con la base de datos.");
                throw e;  // Propagar la excepción si ocurre un error
            }
        }
        return conexion;
    }
    
    
    // Método para cerrar la conexión de forma segura
    public static void cerrar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión.");
        }
    }

    // Método para crear las tablas en la base de datos
    public static void crearTablas() {
        try (Statement stmt = conexion.createStatement()) {
            String sqlProductos = "CREATE TABLE IF NOT EXISTS Productos (" +
                    "idProducto INT AUTO_INCREMENT PRIMARY KEY," +
                    "nombre VARCHAR(255)," +
                    "categoria VARCHAR(255)," +
                    "cantidad INT," +
                    "proveedor VARCHAR(255)," +
                    "fechaAdquisicion DATE)";

            String sqlUsuarios = "CREATE TABLE IF NOT EXISTS Usuarios (" +
                    "idUsuario INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(255)," +
                    "email VARCHAR(255) UNIQUE," +
                    "password VARCHAR(255)," +
                    "rol ENUM('admin', 'gerente', 'empleado'))";

            stmt.executeUpdate(sqlProductos);
            stmt.executeUpdate(sqlUsuarios);

            System.out.println("Tablas creadas exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al crear las tablas.");
        }
    }

    // Método para insertar un nuevo producto en la base de datos
    public static void insertarProducto(String nombre, String categoria, int cantidad, String proveedor, java.util.Date fechaAdquisicion) {
        String query = "INSERT INTO Productos (nombre, categoria, cantidad, proveedor, fechaAdquisicion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, categoria);
            pstmt.setInt(3, cantidad);
            pstmt.setString(4, proveedor);
            pstmt.setDate(5, new java.sql.Date(fechaAdquisicion.getTime()));
            pstmt.executeUpdate();
            System.out.println("Producto insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar el producto.");
        }
    }

    // Método para eliminar un producto
    public static void eliminarProducto(int idProducto) {
        String query = "DELETE FROM Productos WHERE idProducto = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, idProducto);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("No se encontró el producto con el ID especificado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto.");
        }
    }

    // Método para verificar si un usuario existe en la base de datos
    public static boolean existeUsuario(String email, String password) {
        String query = "SELECT * FROM Usuarios WHERE email = ? AND password = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            return pstmt.executeQuery().next(); // Retorna true si encuentra un resultado
        } catch (SQLException e) {
            System.out.println("Error al verificar el usuario.");
        }
        return false;
    }
    
    public static void obtenerProductos() throws SQLException {
    Connection conn = getConnection();  // Asegúrate de que el método getConnection esté correctamente implementado
    String query = "SELECT * FROM Productos";

    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("idProducto") + 
                               ", Nombre: " + rs.getString("nombre") +
                               ", Categoría: " + rs.getString("categoria") +
                               ", Cantidad: " + rs.getInt("cantidad"));
        }
    } catch (SQLException e) {
    }
}
    
}