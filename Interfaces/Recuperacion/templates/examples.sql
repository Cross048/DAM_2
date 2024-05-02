-- Eliminar las tablas si existen
DROP TABLE IF EXISTS Detalle;
DROP TABLE IF EXISTS Factura;
DROP TABLE IF EXISTS Producto;
DROP TABLE IF EXISTS Cliente;

-- Crear la tabla Cliente
CREATE TABLE "Cliente" (
    "id_cliente"    INTEGER NOT NULL UNIQUE,
    "dni"   TEXT,
    "nombre"    TEXT,
    "apellido"  TEXT,
    "direccion" TEXT,
    "fecha_nacimiento"  TEXT,
    "telefono"  INTEGER,
    "email" TEXT,
    "categoria" TEXT CHECK (categoria IN ('Particular', 'Empresa')), -- Restricción de categoría
    "alta" INTEGER, -- Cambiar a tipo INTEGER (0 o 1)
    PRIMARY KEY("id_cliente" AUTOINCREMENT)
);

-- Insertar ejemplos en la tabla Cliente
INSERT INTO Cliente (dni, nombre, apellido, direccion, fecha_nacimiento, telefono, email, categoria, alta) VALUES
('12345678A', 'Juan', 'García', 'Calle Mayor 123', '15/05/1990', 666555444, 'juan@example.com', 'Particular', 1),
('98765432B', 'María', 'López', 'Avenida Libertad 45', '20/08/1985', 677888999, 'maria@example.com', 'Empresa', 0),
('11111111C', 'Pedro', 'Martínez', 'Plaza España 7', '10/01/2000', 622333444, 'pedro@example.com', 'Particular', 1),
('22222222D', 'Ana', 'Sánchez', 'Calle Sol 20', '03/12/1978', 611222333, 'ana@example.com', 'Empresa', 0),
('33333333E', 'Laura', 'Gómez', 'Avenida Principal 10', '25/09/1995', 688999000, 'laura@example.com', 'Particular', 1),
('44444444F', 'Carlos', 'Fernández', 'Calle Granada 5', '18/07/1983', 655444333, 'carlos@example.com', 'Empresa', 0),
('55555555G', 'Sofía', 'Díaz', 'Avenida del Parque 30', '12/04/1976', 644888222, 'sofia@example.com', 'Particular', 1),
('66666666H', 'David', 'Jiménez', 'Calle Reyes Católicos 15', '30/11/1992', 633111000, 'david@example.com', 'Empresa', 0),
('77777777I', 'Elena', 'Ruiz', 'Plaza del Carmen 25', '05/06/1988', 699222111, 'elena@example.com', 'Particular', 1),
('88888888J', 'Alberto', 'López', 'Calle Mayor 50', '08/03/1970', 677333444, 'alberto@example.com', 'Empresa', 0);

-- Crear la tabla Producto
CREATE TABLE "Producto" (
    "id_producto"   INTEGER NOT NULL,
    "nombre"    TEXT,
    "precio"    TEXT,
    "stock" INTEGER,
    PRIMARY KEY("id_producto")
);

-- Insertar ejemplos en la tabla Producto
INSERT INTO Producto (nombre, precio, stock) VALUES
('Producto A', '10.00', 100),
('Producto B', '20.00', 50),
('Producto C', '15.00', 75),
('Producto D', '25.00', 30),
('Producto E', '12.50', 90),
('Producto F', '18.00', 60),
('Producto G', '30.00', 25),
('Producto H', '22.00', 40),
('Producto I', '17.50', 80),
('Producto J', '28.00', 35);

-- Crear la tabla Factura
CREATE TABLE "Factura" (
    "num_factura"   INTEGER NOT NULL,
    "id_cliente"    INTEGER NOT NULL,
    "fecha" TEXT,
    PRIMARY KEY("num_factura"),
    FOREIGN KEY("id_cliente") REFERENCES Cliente("id_cliente")
);

-- Insertar ejemplos en la tabla Factura
INSERT INTO Factura (id_cliente, fecha) VALUES
(1, '01/04/2024'),
(2, '02/04/2024'),
(3, '03/04/2024'),
(4, '04/04/2024'),
(5, '05/04/2024'),
(6, '06/04/2024'),
(7, '07/04/2024'),
(8, '08/04/2024'),
(9, '09/04/2024'),
(10, '10/04/2024');

-- Crear la tabla Detalle
CREATE TABLE "Detalle" (
    "num_detalle"   INTEGER NOT NULL,
    "id_factura"    INTEGER NOT NULL,
    "id_producto"   INTEGER,
    "cantidad"  INTEGER,
    "precio"    TEXT,
    FOREIGN KEY("id_producto") REFERENCES "Producto"("id_producto"),
    FOREIGN KEY("id_factura") REFERENCES Factura("num_factura"),
    PRIMARY KEY("num_detalle","id_factura")
);

-- Insertar ejemplos en la tabla Detalle
INSERT INTO Detalle (num_detalle, id_factura, id_producto, cantidad, precio) VALUES
(1, 1, 1, 2, '20.00'),
(2, 2, 3, 1, '15.00'),
(3, 3, 5, 3, '37.50'),
(4, 4, 7, 2, '60.00'),
(5, 5, 9, 1, '17.50'),
(6, 6, 2, 4, '80.00'),
(7, 7, 4, 2, '50.00'),
(8, 8, 6, 3, '54.00'),
(9, 9, 8, 1, '22.00'),
(10, 10, 10, 2, '56.00');
