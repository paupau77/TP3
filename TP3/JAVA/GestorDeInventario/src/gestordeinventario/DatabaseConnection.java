package gestordeinventario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Clase que maneja la conexión a la base de datos MySQL
public class DatabaseConnection {
    private static Connection conexion;

    // URL de la base de datos, usuario y contraseña
    private static final String URL = "jdbc:mysql://localhost:3306/inventario_db";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "root";

    // Método para obtener la conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try {
                // Intentar conectar con la base de datos MySQL
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
                System.out.println("Conexión establecida.");
            } catch (SQLException e) {
                System.out.println("Error al conectar con la base de datos.");
                e.printStackTrace();
                throw e;  // Lanza una excepción si ocurre un error en la conexión
            }
        }
        return conexion;
    }

    // Método para cerrar la conexión con la base de datos
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión.");
            e.printStackTrace();
        }
    }

    // Método para crear las tablas necesarias y usuarios predeterminados
    public static void crearTablasYUsuarios() throws SQLException {
        Connection conn = getConnection();

        // Crear la tabla de productos si no existe
        String sqlProductos = "CREATE TABLE IF NOT EXISTS Productos (" +
                "idProducto INT AUTO_INCREMENT PRIMARY KEY," +
                "nombre VARCHAR(255)," +
                "categoria VARCHAR(255)," +
                "cantidad INT," +
                "proveedor VARCHAR(255)," +
                "fechaAdquisicion DATE)";
        
        // Crear la tabla de usuarios si no existe
        String sqlUsuarios = "CREATE TABLE IF NOT EXISTS Usuarios (" +
                "idUsuario INT AUTO_INCREMENT PRIMARY KEY," +
                "nombre VARCHAR(255)," +
                "email VARCHAR(255) UNIQUE," +
                "clave VARCHAR(255)," +
                "rol ENUM('Administrador', 'Empleado', 'Gerente'))";

        // Ejecutar las consultas para crear tablas
        try (PreparedStatement stmtProductos = conn.prepareStatement(sqlProductos);
             PreparedStatement stmtUsuarios = conn.prepareStatement(sqlUsuarios)) {
            stmtProductos.executeUpdate();
            stmtUsuarios.executeUpdate();
            System.out.println("Tablas creadas correctamente.");
        }

        // Insertar usuarios predeterminados para pruebas
        String sqlInsertarUsuario = "INSERT IGNORE INTO Usuarios (nombre, email, clave, rol) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmtInsertar = conn.prepareStatement(sqlInsertarUsuario)) {
            // Administrador predeterminado
            stmtInsertar.setString(1, "Admin");
            stmtInsertar.setString(2, "admin@admin.com");
            stmtInsertar.setString(3, "admin123");
            stmtInsertar.setString(4, "Administrador");
            stmtInsertar.executeUpdate();

            // Empleado predeterminado
            stmtInsertar.setString(1, "Empleado1");
            stmtInsertar.setString(2, "empleado1@empresa.com");
            stmtInsertar.setString(3, "empleado123");
            stmtInsertar.setString(4, "Empleado");
            stmtInsertar.executeUpdate();

            // Gerente predeterminado
            stmtInsertar.setString(1, "Gerente1");
            stmtInsertar.setString(2, "gerente1@empresa.com");
            stmtInsertar.setString(3, "gerente123");
            stmtInsertar.setString(4, "Gerente");
            stmtInsertar.executeUpdate();

            System.out.println("Usuarios predeterminados agregados.");
        }
    }
}