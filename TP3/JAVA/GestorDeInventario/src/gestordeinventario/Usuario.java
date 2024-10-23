package gestordeinventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Usuario {
    protected int id;
    protected String nombre;
    protected String email;
    protected String clave;

    // Constructor
    public Usuario(String nombre, String email, String clave) {
        this.id = (int) (Math.random() * 9000) + 1000;  // Generar ID aleatorio
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    // Método abstracto para mostrar las opciones del menú según el rol
    public abstract void mostrarOpciones();
    
    public static Usuario iniciarSesion(String email, String password) throws SQLException {
    // Verificar las credenciales en la base de datos usando DatabaseConnection
    Connection conn = DatabaseConnection.getConnection();
    String query = "SELECT * FROM Usuarios WHERE email = ? AND clave = ?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // Si las credenciales son correctas, devolver el tipo de usuario correspondiente
            String rol = rs.getString("rol");
            if (rol.equals("Administrador")) {
                return new Administrador(rs.getString("nombre"), rs.getString("email"), rs.getString("clave"));
            } else if (rol.equals("Empleado")) {
                return new Empleado(rs.getString("nombre"), rs.getString("email"), rs.getString("clave"));
            } else if (rol.equals("Gerente")) {
                return new Gerente(rs.getString("nombre"), rs.getString("email"), rs.getString("clave"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    
}
