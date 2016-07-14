-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 14-07-2016 a las 21:59:13
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `restaurant`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuisines`
--

CREATE TABLE IF NOT EXISTS `cuisines` (
  `id_cuisin` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id_cuisin`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `cuisines`
--

INSERT INTO `cuisines` (`id_cuisin`, `name`) VALUES
(1, 'polish'),
(2, 'mexican'),
(3, 'italian'),
(4, 'spanish');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `drink`
--

CREATE TABLE IF NOT EXISTS `drink` (
  `id_drink` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`id_drink`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `drink`
--

INSERT INTO `drink` (`id_drink`, `name`, `price`) VALUES
(1, 'water', 5),
(2, 'fanta', 6.5),
(3, 'coke', 6.5),
(4, 'beer', 8),
(5, 'wine', 9),
(6, 'cofee', 5.2),
(7, 'sangria', 8.5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `food`
--

CREATE TABLE IF NOT EXISTS `food` (
  `id_food` int(11) NOT NULL AUTO_INCREMENT,
  `id_cuisin` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `description` varchar(500) NOT NULL,
  `type` enum('main','dessert') NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`id_food`),
  KEY `id_cuisin` (`id_cuisin`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Volcado de datos para la tabla `food`
--

INSERT INTO `food` (`id_food`, `id_cuisin`, `name`, `description`, `type`, `price`) VALUES
(1, 1, 'zurek', 'Tipical polish soup with potatoes, egg and sausage', 'main', 14),
(2, 1, 'pierogi', 'Patty with meat, cabbage and mushrooms', 'main', 9),
(3, 1, 'Kotlet schabowy', 'Breaded pork tenderloin with vegetables and french fries', 'main', 22.5),
(4, 1, 'Szarlotka', 'apple cake', 'dessert', 10),
(5, 1, 'Placek z truskawkami', 'cake with strawberries', 'dessert', 10),
(6, 3, 'Lasagna', 'Pasta with meat and cheese', 'main', 25),
(7, 3, 'Provolone Diabolo', 'Provolones with spicy souce, mozarella and tomatoes souce', 'main', 15.5),
(8, 3, 'Ice cream', 'chocolate, strawberry, vanilla', 'dessert', 8),
(9, 2, 'burrito', 'rolling tortilla with meat and vegetables', 'main', 15),
(10, 2, 'mexican crullers', 'These traditional cinnamon-sugar dusted, fried treats are great dipped in hot chocolate.', 'dessert', 10),
(11, 4, 'paella', 'yellow rice with meat', 'main', 17.5),
(12, 4, 'nougat cake', 'tipical spanish homemade nougat cake', 'dessert', 10.5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `order`
--

CREATE TABLE IF NOT EXISTS `order` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `total` float NOT NULL,
  PRIMARY KEY (`id_order`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Volcado de datos para la tabla `order`
--

INSERT INTO `order` (`id_order`, `total`) VALUES
(8, 62),
(9, 6.5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orderdrink`
--

CREATE TABLE IF NOT EXISTS `orderdrink` (
  `id_orderdrink` int(11) NOT NULL AUTO_INCREMENT,
  `id_order` int(11) NOT NULL,
  `id_drink` int(11) NOT NULL,
  PRIMARY KEY (`id_orderdrink`),
  KEY `id_order` (`id_order`,`id_drink`),
  KEY `id_drink` (`id_drink`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `orderdrink`
--

INSERT INTO `orderdrink` (`id_orderdrink`, `id_order`, `id_drink`) VALUES
(5, 8, 2),
(6, 8, 3),
(7, 9, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orderfood`
--

CREATE TABLE IF NOT EXISTS `orderfood` (
  `id_orderfood` int(11) NOT NULL AUTO_INCREMENT,
  `id_order` int(11) NOT NULL,
  `id_food` int(11) NOT NULL,
  PRIMARY KEY (`id_orderfood`),
  KEY `id_order` (`id_order`,`id_food`),
  KEY `id_food` (`id_food`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Volcado de datos para la tabla `orderfood`
--

INSERT INTO `orderfood` (`id_orderfood`, `id_order`, `id_food`) VALUES
(4, 8, 1),
(5, 8, 4),
(6, 8, 9),
(7, 8, 10),
(8, 9, 6),
(9, 9, 8);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `food`
--
ALTER TABLE `food`
  ADD CONSTRAINT `food_ibfk_1` FOREIGN KEY (`id_cuisin`) REFERENCES `cuisines` (`id_cuisin`) ON DELETE CASCADE;

--
-- Filtros para la tabla `orderdrink`
--
ALTER TABLE `orderdrink`
  ADD CONSTRAINT `orderdrink_ibfk_2` FOREIGN KEY (`id_drink`) REFERENCES `drink` (`id_drink`),
  ADD CONSTRAINT `orderdrink_ibfk_1` FOREIGN KEY (`id_order`) REFERENCES `order` (`id_order`) ON DELETE CASCADE;

--
-- Filtros para la tabla `orderfood`
--
ALTER TABLE `orderfood`
  ADD CONSTRAINT `orderfood_ibfk_2` FOREIGN KEY (`id_food`) REFERENCES `food` (`id_food`) ON DELETE CASCADE,
  ADD CONSTRAINT `orderfood_ibfk_1` FOREIGN KEY (`id_order`) REFERENCES `order` (`id_order`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
