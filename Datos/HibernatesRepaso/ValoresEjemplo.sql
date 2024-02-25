USE LibrosAutoresHibernate;

-- Insertar valores de ejemplo en la tabla Autores
INSERT INTO Autores (DniAutor, Nombre, Nacionalidad) VALUES
('123456789A', 'Juan Pérez', 'Español'),
('987654321B', 'María García', 'Mexicana'),
('456789123C', 'Luis Rodríguez', 'Argentino'),
('321654987D', 'Ana Martínez', 'Colombiana'),
('654987321E', 'Pedro López', 'Chileno'),
('789654123F', 'Laura Sánchez', 'Peruana'),
('987123654G', 'Carlos Fernández', 'Ecuatoriano'),
('147258369H', 'Sofía Ramírez', 'Venezolana'),
('369258147I', 'Miguel Torres', 'Boliviano'),
('258369147J', 'Lucía Gómez', 'Paraguaya');

-- Insertar valores de ejemplo en la tabla Libros
INSERT INTO Libros (Titulo, Precio) VALUES
('El libro de la selva', 20.99),
('Cien años de soledad', 25.50),
('Harry Potter y la piedra filosofal', 18.75),
('Don Quijote de la Mancha', 22.30),
('Orgullo y prejuicio', 19.95),
('Matar un ruiseñor', 21.20),
('1984', 23.45),
('El señor de los anillos', 27.80),
('Crimen y castigo', 24.60),
('La sombra del viento', 26.15);

-- Insertar valores de ejemplo en la tabla Libros_Autores
INSERT INTO Libros_Autores (IdLibro, DniAutor) VALUES
(1, '123456789A'),
(2, '987654321B'),
(3, '456789123C'),
(4, '321654987D'),
(5, '654987321E'),
(6, '789654123F'),
(7, '987123654G'),
(8, '147258369H'),
(9, '369258147I'),
(10, '258369147J');

-- Insertar valores de ejemplo en la tabla Teléfonos
INSERT INTO Telefonos (DniAutor, NumeroTf) VALUES
('123456789A', '123-456-789'),
('987654321B', '987-654-321'),
('456789123C', '456-789-123'),
('321654987D', '321-654-987'),
('654987321E', '654-987-321'),
('789654123F', '789-654-123'),
('987123654G', '987-123-654'),
('147258369H', '147-258-369'),
('369258147I', '369-258-147'),
('258369147J', '258-369-147');
