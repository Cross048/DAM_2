-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS LIBRERIA;

-- Seleccionar la base de datos
USE LIBRERIA;

-- Crear la tabla
CREATE TABLE IF NOT EXISTS Libros (
    IdLibro INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(50),
    Autor VARCHAR(35),
    Editorial VARCHAR(25),
    ISBN VARCHAR(30),
    Precio DECIMAL(10, 2)
);

-- Insertar registros
INSERT INTO Libros (Nombre, Autor, Editorial, ISBN, Precio) VALUES
    ('El señor de los anillos', 'J.R.R. Tolkien', 'Minotauro', '9788445072814', 29.99),
    ('Cien años de soledad', 'Gabriel García Márquez', 'Diana', '9789584101840', 25.50),
    ('Harry Potter y la piedra filosofal', 'J.K. Rowling', 'Salamandra', '9788478884459', 19.95),
    ('1984', 'George Orwell', 'Debolsillo', '9788499890953', 15.75),
    ('Orgullo y prejuicio', 'Jane Austen', 'Alba Editorial', '9788484287275', 18.25),
    ('Crimen y castigo', 'Fyodor Dostoevsky', 'Penguin Clásicos', '9788491051650', 22.00),
    ('La sombra del viento', 'Carlos Ruiz Zafón', 'Penguin Random House', '9788408043643', 27.50),
    ('To Kill a Mockingbird', 'Harper Lee', 'J.B. Lippincott & Co.', '9780061120084', 21.99),
    ('Matar a un ruiseñor', 'Harper Lee', 'J.B. Lippincott & Co.', '9788478888563', 20.45),
    ('Don Quijote de la Mancha', 'Miguel de Cervantes', 'Real Academia Española', '9788424111498', 24.99);
