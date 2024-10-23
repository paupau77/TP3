package gestordeinventario;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        // Conectar a la base de datos al iniciar el programa y crear las tablas si no existen
        DatabaseConnection.conectar();
        DatabaseConnection.crearTablas();  // Crear las tablas al iniciar si no existen

        Usuario usuarioActual = null;

        while (!salir) {
            System.out.println("Menú Principal:");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Salir");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    // Iniciar sesión
                    System.out.print("Ingrese su email: ");
                    String email = scanner.nextLine();
                    System.out.print("Ingrese su contraseña: ");
                    String password = scanner.nextLine();

                    try {
                        usuarioActual = Usuario.iniciarSesion(email, password);
                        if (usuarioActual != null) {
                            // Identificar el tipo de usuario y mostrar su menú correspondiente
                            if (usuarioActual instanceof Administrador) {
                                mostrarMenuAdministrador((Administrador) usuarioActual, scanner);
                            } else if (usuarioActual instanceof Empleado) {
                                mostrarMenuEmpleado((Empleado) usuarioActual, scanner);
                            } else if (usuarioActual instanceof Gerente) {
                                mostrarMenuGerente((Gerente) usuarioActual, scanner);
                            }
                        } else {
                            System.out.println("Usuario o contraseña incorrectos.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error al iniciar sesión.");
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    salir = true;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }

        // Cerrar la conexión al terminar el programa
        DatabaseConnection.cerrar();
        scanner.close();
    }

    // Menú para el Administrador
    public static void mostrarMenuAdministrador(Administrador admin, Scanner scanner) {
        int opcion;
        do {
            System.out.println("\nMenú Administrador:");
            System.out.println("1. Registrar Producto");
            System.out.println("2. Ver Productos");
            System.out.println("3. Eliminar Producto");
            System.out.println("4. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    // Registrar producto
                    try {
                        System.out.print("Ingrese el nombre del producto: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese la categoría: ");
                        String categoria = scanner.nextLine();
                        System.out.print("Ingrese la cantidad: ");
                        int cantidad = scanner.nextInt();
                        scanner.nextLine(); // Consumir la nueva línea
                        System.out.print("Ingrese el proveedor: ");
                        String proveedor = scanner.nextLine();
                        System.out.print("Ingrese la fecha de adquisición (YYYY-MM-DD): ");
                        String fecha = scanner.nextLine();

                        admin.registrarProducto(nombre, categoria, cantidad, proveedor, java.sql.Date.valueOf(fecha));
                    } catch (SQLException e) {
                        System.out.println("Error al registrar el producto.");
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    // Ver productos
                    try {
                        Producto.obtenerProductos();
                    } catch (SQLException e) {
                        System.out.println("Error al obtener los productos.");
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    // Eliminar producto
                    try {
                        System.out.print("Ingrese el ID del producto a eliminar: ");
                        int idProducto = scanner.nextInt();
                        scanner.nextLine(); // Consumir la nueva línea
                        Producto.eliminarProducto(idProducto);
                    } catch (SQLException e) {
                        System.out.println("Error al eliminar el producto.");
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.println("Cerrando sesión...");
                    return;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (true);
    }

    // Menú para el Empleado
    public static void mostrarMenuEmpleado(Empleado empleado, Scanner scanner) {
        int opcion;
        do {
            System.out.println("\nMenú Empleado:");
            System.out.println("1. Registrar Entrada");
            System.out.println("2. Registrar Salida");
            System.out.println("3. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    // Registrar entrada
                    try {
                        System.out.print("Ingrese el ID del producto: ");
                        int idProducto = scanner.nextInt();
                        System.out.print("Ingrese la cantidad de entrada: ");
                        int cantidad = scanner.nextInt();
                        empleado.registrarEntrada(idProducto, cantidad);
                    } catch (SQLException e) {
                        System.out.println("Error al registrar la entrada.");
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    // Registrar salida
                    try {
                        System.out.print("Ingrese el ID del producto: ");
                        int idProducto = scanner.nextInt();
                        System.out.print("Ingrese la cantidad de salida: ");
                        int cantidad = scanner.nextInt();
                        empleado.registrarSalida(idProducto, cantidad);
                    } catch (SQLException e) {
                        System.out.println("Error al registrar la salida.");
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("Cerrando sesión...");
                    return;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (true);
    }

    // Menú para el Gerente
    public static void mostrarMenuGerente(Gerente gerente, Scanner scanner) {
        int opcion;
        do {
            System.out.println("\nMenú Gerente:");
            System.out.println("1. Generar Reporte");
            System.out.println("2. Ver Inventario");
            System.out.println("3. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    // Generar reporte
                    System.out.println("Generando reporte...");
                    // Lógica para generar reporte
                    break;
                case 2:
                    // Ver inventario
                    try {
                        Producto.obtenerProductos();
                    } catch (SQLException e) {
                        System.out.println("Error al obtener los productos.");
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("Cerrando sesión...");
                    return;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (true);
    }
}