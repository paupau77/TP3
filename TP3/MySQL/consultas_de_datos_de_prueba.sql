-- Consulta de todos los usuarios
SELECT * FROM Usuarios;

-- Consulta de todos los productos
SELECT * FROM Productos;

-- Consulta de entradas para un producto
SELECT * FROM Entradas WHERE idProducto = 1;

-- Consulta de salidas para un producto
SELECT * FROM Salidas WHERE idProducto = 2;

-- Consulta de alertas por baja de stock
SELECT * FROM Alertas WHERE tipoAlerta = 'stock bajo';

-- Consulta de reportes
SELECT * FROM Reportes;