
package gestordeinventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Producto {
    private String nombre;
    private String categoria;
    private int cantidad;
    private String proveedor;
    private Date fechaAdquisicion;

    // Constructor
    public Producto(String nombre, String categoria, int cantidad, String proveedor, Date fechaAdquisicion) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.fechaAdquisicion = fechaAdquisicion;
    }

    // Métodos CRUD
    public void registrarProducto() throws SQLException {
        DatabaseConnection.insertarProducto(nombre, categoria, cantidad, proveedor, fechaAdquisicion);
    }

    public static void obtenerProductos() throws SQLException {
        DatabaseConnection.obtenerProductos();
    }

    public static void eliminarProducto(int idProducto) throws SQLException {
        DatabaseConnection.eliminarProducto(idProducto);
    }
}