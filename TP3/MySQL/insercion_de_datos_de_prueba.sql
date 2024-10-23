-- Insertar Usuarios
INSERT INTO Usuarios (nombre, email, contraseña, rol)
VALUES 
('Juan Pérez', 'jperez@email.com', 'hashed_password', 'admin'),
('Ana López', 'alopez@email.com', 'hashed_password', 'empleado');

-- Insertar Productos
INSERT INTO Productos (nombre, categoría, cantidad, proveedor, fechaAdquisicion)
VALUES 
('Producto A', 'Electrónica', 100, 'Proveedor 1', '2024-01-01'),
('Producto B', 'Alimentación', 50, 'Proveedor 2', '2024-01-05');

-- Insertar Entradas
INSERT INTO Entradas (idProducto, cantidad, fechaEntrada, idUsuario)
VALUES 
(1, 50, '2024-01-01', 1),
(2, 30, '2024-01-05', 2);

-- Insertar Salidas
INSERT INTO Salidas (idProducto, cantidad, fechaSalida, idUsuario)
VALUES 
(1, 20, '2024-01-10', 1),
(2, 15, '2024-01-12', 2);

-- Insertar Alertas
INSERT INTO Alertas (idProducto, fechaAlerta, tipoAlerta)
VALUES 
(1, '2024-01-10', 'stock bajo'),
(2, '2024-01-12', 'fuera de stock');

-- Insertar Reportes
INSERT INTO Reportes (fechaGeneracion, tipoReporte, idUsuario)
VALUES 
('2024-02-01', 'mensual', 1),
('2024-02-15', 'anual', 2);