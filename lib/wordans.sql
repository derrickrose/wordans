-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Dim 14 Août 2016 à 12:23
-- Version du serveur :  10.1.13-MariaDB
-- Version de PHP :  5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `crawl`
--

-- --------------------------------------------------------

--
-- Structure de la table `wordans`
--

CREATE TABLE `wordans` (
  `productId` varchar(75) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `link` varchar(600) NOT NULL,
  `image` varchar(600) NOT NULL,
  `description` varchar(2500) NOT NULL,
  `motclef` varchar(600) NOT NULL,
  `price` float NOT NULL,
  `shippingCost` float NOT NULL,
  `shippingDealy` int(3) NOT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `category` varchar(150) DEFAULT NULL,
  `quantity` int(10) DEFAULT NULL,
  `update_time` varchar(50) DEFAULT NULL,
  `parent_id` varchar(50) NOT NULL,
  `color_name` varchar(50) NOT NULL,
  `size_name` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `wordans`
--
ALTER TABLE `wordans`
  ADD PRIMARY KEY (`productId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;