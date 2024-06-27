-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 27-06-2024 a las 05:01:05
-- Versión del servidor: 8.3.0
-- Versión de PHP: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ventas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `nombre`, `correo`, `direccion`, `activo`) VALUES
(1, 'Juan Pérez', 'juan@email.com', 'Calle 123, Ciudad A', 1),
(2, 'María López', 'maria@email.com', 'Avenida 456, Ciudad B', 1),
(3, 'Carlos Rodríguez', 'carlos@email.com', 'Plaza 789, Ciudad C', 1),
(4, 'Ana Martínez', 'ana@email.com', 'Bulevar 321, Ciudad D', 1),
(5, 'Pedro Sánchez', 'pedro@email.com', 'Calle 654, Ciudad E', 1),
(6, 'Kevin Adonay', 'Kevin@Adonay.com', 'Calle Principal 325', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallefactura`
--

DROP TABLE IF EXISTS `detallefactura`;
CREATE TABLE IF NOT EXISTS `detallefactura` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `precio_unitario` double NOT NULL,
  `factura_id` bigint NOT NULL,
  `producto_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `factura_id` (`factura_id`),
  KEY `producto_id` (`producto_id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `detallefactura`
--

INSERT INTO `detallefactura` (`id`, `cantidad`, `precio_unitario`, `factura_id`, `producto_id`) VALUES
(1, 1, 999.99, 1, 1),
(2, 1, 599.99, 1, 2),
(3, 1, 399.99, 2, 3),
(4, 1, 149.99, 2, 4),
(5, 1, 999.99, 3, 1),
(6, 2, 349.99, 4, 5),
(7, 1, 149.99, 4, 4),
(21, 1, 599.99, 20, 2),
(20, 1, 999.99, 20, 1),
(22, 1, 999.99, 21, 1),
(23, 2, 1199.98, 21, 2),
(24, 1, 999.99, 22, 1),
(25, 2, 1199.98, 22, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

DROP TABLE IF EXISTS `factura`;
CREATE TABLE IF NOT EXISTS `factura` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cliente_id` bigint DEFAULT NULL,
  `fecha` date NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cliente_id` (`cliente_id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id`, `cliente_id`, `fecha`, `total`) VALUES
(1, 1, '2024-06-20', 1599.98),
(2, 2, '2024-06-21', 549.98),
(3, 3, '2024-06-22', 999.99),
(4, 4, '2024-06-23', 749.98),
(20, 4, '2024-06-26', 1599.98),
(21, 2, '2024-06-26', 1599.98),
(22, 6, '2024-06-26', 1599.98);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

DROP TABLE IF EXISTS `producto`;
CREATE TABLE IF NOT EXISTS `producto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` double NOT NULL,
  `stock` int NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `descripcion`, `precio`, `stock`, `activo`) VALUES
(1, 'Laptop', 'Laptop de última generación', 999.99, 50, 1),
(2, 'Smartphone', 'Teléfono inteligente con cámara de alta resolución', 599.99, 100, 1),
(3, 'Tablet', 'Tablet ligera y potente', 399.99, 75, 1),
(4, 'Auriculares Bluetooth', 'Auriculares inalámbricos con cancelación de ruido', 149.99, 200, 1),
(5, 'Monitor', 'Monitor 4K de 27 pulgadas', 349.99, 30, 1),
(6, 'Laptop Asus', 'Laptop de última generación', 999.99, 50, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promocion`
--

DROP TABLE IF EXISTS `promocion`;
CREATE TABLE IF NOT EXISTS `promocion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `descuento` double NOT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `activo` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `promocion`
--

INSERT INTO `promocion` (`id`, `nombre`, `descripcion`, `descuento`, `fecha_inicio`, `fecha_fin`, `activo`) VALUES
(1, 'Descuento de Verano', 'Descuento del 15% en productos seleccionados', 0.15, '2024-07-01', '2024-07-31', b'0'),
(2, '2x1 en Accesorios', 'Lleva dos accesorios y paga el de mayor valor', 0.5, '2024-08-01', '2024-08-15', b'0'),
(3, 'Cyber Monday', 'Descuentos de hasta 30% en electrónicos', 0.3, '2024-11-25', '2024-11-25', b'0'),
(4, 'Black Friday', 'Ofertas especiales en toda la tienda', 0.25, '2024-11-29', '2024-11-29', b'0'),
(6, 'Descuento de primavera', '10% de descuento en todos los productos', 0.1, '2024-06-01', '2024-08-31', b'0');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
