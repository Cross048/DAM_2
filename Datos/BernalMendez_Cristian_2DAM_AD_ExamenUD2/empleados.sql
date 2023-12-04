drop database if exists empleados;
CREATE DATABASE  IF NOT EXISTS `empleados` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `empleados`;
-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-11-2023 a las 23:15:20
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 7.3.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `empresa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `NIF` varchar(9) NOT NULL,
  `Nombre` varchar(100) DEFAULT NULL,
  `Apellidos` varchar(100) DEFAULT NULL,
  `Salario` float(6,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`NIF`, `Nombre`, `Apellidos`, `Salario`) VALUES
('11111111A', 'Álvaro', 'Rodríguez Fernández', 1200.00),
('11111111J', 'Carlos', 'Martín González', 1500.00),
('22222222B', 'Elena', 'Rodríguez García', 1800.00),
('33333333C', 'Claudia', 'Gómez López', 1250.00),
('44444444D', 'Javier', 'Fernández Torres', 1200.00),
('55555555E', 'María', 'Morales Ruiz', 1950.00),
('66666666F', 'Daniel', 'Sánchez Castro', 2100.00),
('77777777G', 'Ana', 'Jiménez Herrera', 3500.00),
('88888888H', 'Francisco', 'Ramírez Ortiz', 4000.00),
('99999999I', 'Laura', 'Torres Serrano', 3100.00);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`NIF`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
