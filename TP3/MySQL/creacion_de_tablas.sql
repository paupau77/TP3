-- Crear la tabla Usuarios
CREATE TABLE Usuarios (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    contraseña VARCHAR(100) NOT NULL,
    rol ENUM('admin', 'gerente', 'empleado') NOT NULL
);

-- Crear la tabla Productos
CREATE TABLE Productos (
    idProducto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoría VARCHAR(50) NOT NULL,
    cantidad INT NOT NULL,
    proveedor VARCHAR(100) NOT NULL,
    fechaAdquisicion DATE NOT NULL
);

-- Crear la tabla Entradas
CREATE TABLE Entradas (
    idEntrada INT AUTO_INCREMENT PRIMARY KEY,
    idProducto INT NOT NULL,
    cantidad INT NOT NULL,
    fechaEntrada DATE NOT NULL,
    idUsuario INT NOT NULL,
    FOREIGN KEY (idProducto) REFERENCES Productos(idProducto) ON DELETE CASCADE,
    FOREIGN KEY (idUsuario) REFERENCES Usuarios(idUsuario) ON DELETE CASCADE
);

-- Crear la tabla Salidas
CREATE TABLE Salidas (
    idSalida INT AUTO_INCREMENT PRIMARY KEY,
    idProducto INT NOT NULL,
    cantidad INT NOT NULL,
    fechaSalida DATE NOT NULL,
    idUsuario INT NOT NULL,
    FOREIGN KEY (idProducto) REFERENCES Productos(idProducto) ON DELETE CASCADE,
    FOREIGN KEY (idUsuario) REFERENCES Usuarios(idUsuario) ON DELETE CASCADE
);

-- Crear la tabla Alertas
CREATE TABLE Alertas (
    idAlerta INT AUTO_INCREMENT PRIMARY KEY,
    idProducto INT NOT NULL,
    fechaAlerta DATE NOT NULL,
    tipoAlerta ENUM('stock bajo', 'fuera de stock') NOT NULL,
    FOREIGN KEY (idProducto) REFERENCES Productos(idProducto) ON DELETE CASCADE
);

-- Crear la tabla Reportes
CREATE TABLE Reportes (
    idReporte INT AUTO_INCREMENT PRIMARY KEY,
    fechaGeneracion DATE NOT NULL,
    tipoReporte ENUM('mensual', 'anual') NOT NULL,
    idUsuario INT NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES Usuarios(idUsuario) ON DELETE CASCADE
);
