-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS LibrosAutoresHibernate;

-- Usar la base de datos creada
USE LibrosAutoresHibernate;

-- Tabla Libros
CREATE TABLE Libros (
    IdLibro INT AUTO_INCREMENT PRIMARY KEY,
    Titulo VARCHAR(255),
    Precio DECIMAL(10, 2)
);

-- Tabla Autores
CREATE TABLE Autores (
    DniAutor VARCHAR(20) PRIMARY KEY,
    Nombre VARCHAR(100),
    Nacionalidad VARCHAR(100)
);

-- Tabla Libros_Autores (para relación muchos a muchos)
CREATE TABLE Libros_Autores (
    IdLibro INT,
    DniAutor VARCHAR(20),
    PRIMARY KEY (IdLibro, DniAutor),
    FOREIGN KEY (IdLibro) REFERENCES Libros(IdLibro),
    FOREIGN KEY (DniAutor) REFERENCES Autores(DniAutor)
);

-- Tabla Teléfonos (relación uno a uno con Autores)
CREATE TABLE Telefonos (
    DniAutor VARCHAR(20) PRIMARY KEY,
    NumeroTf VARCHAR(15),
    FOREIGN KEY (DniAutor) REFERENCES Autores(DniAutor)
);
