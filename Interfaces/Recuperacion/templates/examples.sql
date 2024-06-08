-- Eliminar todas las tablas si existen
DROP TABLE IF EXISTS Detalle;
DROP TABLE IF EXISTS Factura;
DROP TABLE IF EXISTS Cliente;
DROP TABLE IF EXISTS Producto;

-- Crear la tabla Producto
CREATE TABLE Producto (
    id_producto INTEGER PRIMARY KEY NOT NULL,
    nombre TEXT,
    precio TEXT,
    stock INTEGER
);

-- Insertar ejemplos de datos en la tabla Producto (10 libros)
INSERT INTO Producto (nombre, precio, stock) VALUES
    ('Dune', '10.00', 50),
    ('Dune Messiah', '12.00', 30),
    ('Children of Dune', '15.00', 20),
    ('The Great Gatsby', '9.99', 40),
    ('To Kill a Mockingbird', '11.50', 35),
    ('1984', '8.95', 25),
    ('The Catcher in the Rye', '10.25', 30),
    ('The Lord of the Rings', '20.00', 50),
    ('Harry Potter and the Philosopher''s Stone', '12.99', 45),
    ('Pride and Prejudice', '7.50', 35);

-- Crear la tabla Cliente
CREATE TABLE Cliente (
    id_cliente INTEGER PRIMARY KEY NOT NULL,
    dni TEXT,
    nombre TEXT,
    apellido TEXT,
    direccion TEXT,
    fecha_nacimiento TEXT,
    telefono INTEGER,
    email TEXT,
    categoria TEXT CHECK (categoria IN ('Particular', 'Empresa')),
    alta INTEGER
);

-- Insertar ejemplos de datos en la tabla Cliente (10 clientes)
INSERT INTO Cliente (dni, nombre, apellido, direccion, fecha_nacimiento, telefono, email, categoria, alta) VALUES
    ('12345678A', 'Juan', 'Pérez', 'Calle Mayor 123', '1990-01-01', 123456789, 'juan@example.com', 'Particular', 1),
    ('87654321B', 'María', 'García', 'Avenida Principal 456', '1995-05-15', 987654321, 'maria@example.com', 'Empresa', 1),
    ('23456789C', 'Pedro', 'López', 'Plaza del Sol 789', '1985-09-20', 456789123, 'pedro@example.com', 'Particular', 0),
    ('98765432D', 'Laura', 'Martínez', 'Paseo de la Castellana 234', '2000-03-10', 789123456, 'laura@example.com', 'Empresa', 0),
    ('34567891E', 'Carlos', 'Sánchez', 'Calle Gran Vía 567', '1978-07-05', 654321987, 'carlos@example.com', 'Particular', 1),
    ('87654321F', 'Ana', 'Fernández', 'Avenida Diagonal 890', '1992-12-15', 321987654, 'ana@example.com', 'Empresa', 0),
    ('45678912G', 'David', 'Gómez', 'Calle Mayor 123', '1983-04-30', 987654321, 'david@example.com', 'Particular', 1),
    ('98765432H', 'Elena', 'Rodríguez', 'Plaza España 456', '1998-06-25', 654321987, 'elena@example.com', 'Empresa', 0),
    ('56789123I', 'Sara', 'Hernández', 'Paseo Marítimo 789', '1975-10-12', 321987654, 'sara@example.com', 'Particular', 0),
    ('12345678J', 'Manuel', 'Díaz', 'Avenida Libertad 234', '1991-02-18', 789123456, 'manuel@example.com', 'Empresa', 1);

-- Crear la tabla Factura
CREATE TABLE Factura (
    num_factura INTEGER PRIMARY KEY NOT NULL,
    id_cliente INTEGER,
    fecha TEXT,
    FOREIGN KEY (id_cliente) REFERENCES Cliente (id_cliente)
);

-- Crear la tabla Detalle
CREATE TABLE Detalle (
    id_detalle INTEGER PRIMARY KEY NOT NULL,
    id_factura INTEGER,
    id_producto INTEGER,
    cantidad INTEGER,
    precio TEXT,
    FOREIGN KEY (id_factura) REFERENCES Factura (num_factura),
    FOREIGN KEY (id_producto) REFERENCES Producto (id_producto)
);

-- Insertar ejemplos de datos en la tabla Factura
INSERT INTO Factura (num_factura, id_cliente, fecha) VALUES
    (1, 1, '2024-06-01'),
    (2, 2, '2024-06-02'),
    (3, 3, '2024-06-03');

-- Insertar ejemplos de datos en la tabla Detalle
INSERT INTO Detalle (id_factura, id_producto, cantidad, precio) VALUES
    (1, 1, 2, '20.00'),
    (1, 3, 3, '45.00'),
    (1, 5, 1, '18.00'),
    (2, 2, 1, '12.00'),
    (2, 4, 2, '40.00'),
    (2, 6, 4, '100.00'),
    (3, 3, 1, '15.00'),
    (3, 5, 2, '36.00'),
    (3, 7, 3, '90.00');
