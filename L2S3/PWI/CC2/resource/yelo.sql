-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mar. 11 déc. 2018 à 15:17
-- Version du serveur :  5.7.17
-- Version de PHP :  7.1.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `yelo`
--

-- --------------------------------------------------------

--
-- Structure de la table `stationvelo`
--

CREATE TABLE `stationvelo` (
  `id` int(11) NOT NULL,
  `nom` varchar(256) NOT NULL,
  `nbVeloMax` int(11) NOT NULL,
  `nbVeloPresent` int(11) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
    `image` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `stationvelo`
--

INSERT INTO `stationvelo` (`id`, `nom`, `nbVeloMax`, `nbVeloPresent`, `latitude`, `longitude`,  `image`) VALUES
(1, 'universite sciences', 12, 5, 46.147079, -1.155300000000011,'img/sciences.jpg'),
(2, 'flash', 15, 2, 46.152048, -1.153215,'img/flash.jpg'),
(3, 'iut', 18, 10, 46.142653, -1.151852,'img/iut.jpeg'),
(4, 'eglise saint sauveur', 20, 15, 46.159027, -1.150371,'img/saint-sauveur.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `velo`
--

CREATE TABLE `velo` (
  `id` int(11) NOT NULL,
  `dateMiseService` date NOT NULL,
  `dateRevision` date NOT NULL,
  `stationId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `velo`
--

INSERT INTO `velo` (`id`, `dateMiseService`, `dateRevision`, `stationId`) VALUES
(1, '2017-10-16', '2018-01-01', 1),
(2, '2017-10-16', '2018-01-12', 2),
(3, '2017-10-16', '2018-01-01', 1),
(4, '2017-10-16', '2018-01-12', 3),
(5, '2017-12-12', '2018-01-01', 4),
(6, '2017-12-12', '2018-01-12', 1),
(7, '2017-12-12', '2018-01-01', 1),
(8, '2018-01-10', '2018-06-12', 2),
(9, '2018-01-10', '2018-06-12', 2),
(10, '0201-01-10', '2018-06-12', 3);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `stationvelo`
--
ALTER TABLE `stationvelo`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `velo`
--
ALTER TABLE `velo`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `stationvelo`
--
ALTER TABLE `stationvelo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `velo`
--
ALTER TABLE `velo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
