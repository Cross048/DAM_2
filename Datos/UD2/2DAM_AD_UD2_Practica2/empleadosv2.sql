drop database if exists empleados;
CREATE DATABASE  IF NOT EXISTS `empleados` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `empleados`;
-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-11-2023 a las 10:52:32
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `empleados`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamento`
--

CREATE TABLE `departamento` (
  `Numdep` tinyint(3) UNSIGNED NOT NULL,
  `Nombredep` varchar(15) DEFAULT NULL,
  `Numempdep` int(10) DEFAULT NULL,
  `NSSgerente` varchar(50) DEFAULT NULL,
  `fechainicgerente` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `departamento`
--

INSERT INTO `departamento` (`Numdep`, `Nombredep`, `Numempdep`, `NSSgerente`, `fechainicgerente`) VALUES
(1, 'Investigacion', 16, '11111', '1989-03-12 00:00:00'),
(2, 'Administracion', 14, '22222', '1989-02-13 00:00:00'),
(3, 'Dirección', 2, '88888', '1999-08-14 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamento-ubi`
--

CREATE TABLE `departamento-ubi` (
  `Numdep` tinyint(3) UNSIGNED NOT NULL,
  `Lugardep` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `departamento-ubi`
--

INSERT INTO `departamento-ubi` (`Numdep`, `Lugardep`) VALUES
(1, 'Coruña'),
(1, 'Santiago'),
(1, 'Vigo'),
(2, 'Lugo'),
(2, 'Vigo'),
(3, 'Vigo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `NSS` varchar(6) NOT NULL,
  `Nombre` varchar(15) DEFAULT NULL,
  `Apel1` varchar(15) DEFAULT NULL,
  `Apel2` varchar(15) DEFAULT NULL,
  `Sexo` varchar(2) DEFAULT NULL,
  `Dirección` varchar(30) DEFAULT NULL,
  `Fechanac` datetime DEFAULT NULL,
  `Salario` int(10) DEFAULT NULL,
  `Numdept` tinyint(3) UNSIGNED DEFAULT NULL,
  `NSSsup` varchar(6) DEFAULT NULL,
  `NIF` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`NSS`, `Nombre`, `Apel1`, `Apel2`, `Sexo`, `Dirección`, `Fechanac`, `Salario`, `Numdept`, `NSSsup`, `NIF`) VALUES
('11111', 'José', 'Nieto', 'Silva', 'M', 'C/Sol,4', '1963-03-12 00:00:00', 420000, 1, NULL, '11222333A'),
('11122', 'Federico', 'Fuertes', 'Serantes', 'M', 'C/Espiga,7', '1970-10-07 00:00:00', 140000, 2, '88888', '11222333B'),
('11133', 'Carmen', 'Prieto', 'Gonzalez', 'F', 'C/Fresnos,7', '1970-10-07 00:00:00', 175000, 1, '11111', '11222333C'),
('22222', 'Federico', 'García', 'López', 'M', 'C/Luna,8', '1970-04-12 00:00:00', 150000, 3, '11111', '11222333D'),
('33322', 'Maria', 'Ramos', 'Perez', 'F', 'C/Sol,22', '1965-03-03 00:00:00', 120000, 1, '88888', '11222333E'),
('33333', 'Ana', 'Sanchez', 'Valdés', 'F', 'C/Bravo,8', '1959-03-17 00:00:00', 120000, 1, '22222', '11222333F'),
('44444', 'Jaime', 'Silva', 'Gonzalez', 'M', 'C/Dalias,7', '1970-04-12 00:00:00', 200000, 1, '22222', '11222333G'),
('55555', 'Luis', 'Gonzalez', 'Mouro', 'M', 'C/Peru, 9', '1958-08-19 00:00:00', 150000, 2, '11111', '11222333H'),
('66666', 'José', 'Vilan', 'Silva', 'M', 'C/Panama, 8', '1959-12-17 00:00:00', 200000, 1, '11111', '11222333I'),
('77777', 'Ana', 'López', 'Villa', 'F', 'C/Rosas,7', '1971-04-11 00:00:00', 200000, 2, '22222', '11222333J'),
('88888', 'Pedro', 'Gonzalez', 'Jimenez', 'M', 'C/Valle, 6', '1953-03-17 00:00:00', 300000, 3, NULL, '11222333K'),
('99999', 'Ana', 'Silva', 'López', 'F', 'C/Bravo,8', '1970-10-07 00:00:00', 150000, 1, '88888', '11222333L');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleadoproyecto`
--

CREATE TABLE `empleadoproyecto` (
  `NSS` varchar(6) NOT NULL,
  `NUMPROY` int(10) NOT NULL,
  `NUMHORAS` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `empleadoproyecto`
--

INSERT INTO `empleadoproyecto` (`NSS`, `NUMPROY`, `NUMHORAS`) VALUES
('11111', 1, 30),
('11111', 2, 24),
('11111', 4, 13),
('22222', 1, 25),
('22222', 3, 30),
('22222', 6, 14),
('44444', 1, 25),
('44444', 5, 17),
('44444', 7, 35),
('66666', 2, 25),
('77777', 7, 35),
('88888', 7, 40),
('99999', 1, 20),
('99999', 2, 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propuestas`
--

CREATE TABLE `propuestas` (
  `IDpropuesta` int(10) NOT NULL,
  `Nssempleado` varchar(6) DEFAULT NULL,
  `Descripcion` longtext DEFAULT NULL,
  `FechaPropuesta` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `propuestas`
--

INSERT INTO `propuestas` (`IDpropuesta`, `Nssempleado`, `Descripcion`, `FechaPropuesta`) VALUES
(1, '11111', 'Mejora parking', '2009-01-12 00:00:00'),
(2, '22222', 'Servicio guarderia 12 horas', '2009-01-12 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyecto`
--

CREATE TABLE `proyecto` (
  `Numproy` int(10) NOT NULL,
  `Nombreproy` varchar(15) DEFAULT NULL,
  `Lugarproy` varchar(20) DEFAULT NULL,
  `departamento_Numdep` tinyint(3) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `proyecto`
--

INSERT INTO `proyecto` (`Numproy`, `Nombreproy`, `Lugarproy`, `departamento_Numdep`) VALUES
(1, 'ProductoX', 'Vigo', 1),
(2, 'ProductoY', 'Santiago', 1),
(3, 'ProductoZ', 'Vigo', 1),
(4, 'Reorganización', 'Vigo', 3),
(5, 'Automatización', 'Vigo', 2),
(6, 'Nuevaspresta', 'Lugo', 2),
(7, 'ProductoW', 'Coruña', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salarios`
--

CREATE TABLE `salarios` (
  `codigo` varchar(3) NOT NULL,
  `categoria` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `salarios`
--

INSERT INTO `salarios` (`codigo`, `categoria`) VALUES
('001', 'Programador Junior'),
('002', 'Programador Senior'),
('003', 'Analista Junior'),
('004', 'Analista Senior'),
('005', 'Analista Junior');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `departamento`
--
ALTER TABLE `departamento`
  ADD PRIMARY KEY (`Numdep`);

--
-- Indices de la tabla `departamento-ubi`
--
ALTER TABLE `departamento-ubi`
  ADD PRIMARY KEY (`Numdep`,`Lugardep`),
  ADD KEY `DEPARTAMENTODEPARTAMENTO-UBI` (`Numdep`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`NSS`),
  ADD KEY `DEPARTAMENTOEMPLEADO` (`Numdept`),
  ADD KEY `EMPLEADOEMPLEADO` (`NSSsup`);

--
-- Indices de la tabla `empleadoproyecto`
--
ALTER TABLE `empleadoproyecto`
  ADD PRIMARY KEY (`NSS`,`NUMPROY`),
  ADD KEY `EMPLEADOEMPLEADO-PROYECTO` (`NSS`),
  ADD KEY `PROYECTOEMPLEADO-PROYECTO` (`NUMPROY`);

--
-- Indices de la tabla `propuestas`
--
ALTER TABLE `propuestas`
  ADD PRIMARY KEY (`IDpropuesta`),
  ADD KEY `EMPLEADOPropuestas` (`Nssempleado`),
  ADD KEY `IDpropuesta` (`IDpropuesta`);

--
-- Indices de la tabla `proyecto`
--
ALTER TABLE `proyecto`
  ADD PRIMARY KEY (`Numproy`),
  ADD KEY `fk_proyecto_departamento1_idx` (`departamento_Numdep`);

--
-- Indices de la tabla `salarios`
--
ALTER TABLE `salarios`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `propuestas`
--
ALTER TABLE `propuestas`
  MODIFY `IDpropuesta` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `departamento-ubi`
--
ALTER TABLE `departamento-ubi`
  ADD CONSTRAINT `departamento-ubi_ibfk_1` FOREIGN KEY (`Numdep`) REFERENCES `departamento` (`Numdep`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`Numdept`) REFERENCES `departamento` (`Numdep`),
  ADD CONSTRAINT `empleado_ibfk_2` FOREIGN KEY (`NSSsup`) REFERENCES `empleado` (`NSS`);

--
-- Filtros para la tabla `empleadoproyecto`
--
ALTER TABLE `empleadoproyecto`
  ADD CONSTRAINT `empleadoproyecto_ibfk_1` FOREIGN KEY (`NUMPROY`) REFERENCES `proyecto` (`Numproy`),
  ADD CONSTRAINT `empleadoproyecto_ibfk_2` FOREIGN KEY (`NSS`) REFERENCES `empleado` (`NSS`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `propuestas`
--
ALTER TABLE `propuestas`
  ADD CONSTRAINT `propuestas_ibfk_1` FOREIGN KEY (`Nssempleado`) REFERENCES `empleado` (`NSS`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `proyecto`
--
ALTER TABLE `proyecto`
  ADD CONSTRAINT `fk_proyecto_departamento1` FOREIGN KEY (`departamento_Numdep`) REFERENCES `departamento` (`Numdep`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

CREATE TABLE departamentos( 
ID Integer(2) PRIMARY KEY, 
nombre varchar(50) );

ALTER TABLE empleado ADD dpto Integer(2) NOT NULL;
ALTER TABLE empleado ADD CONSTRAINT fk_dpto
FOREIGN KEY (dpto) REFERENCES departamentos(ID);

