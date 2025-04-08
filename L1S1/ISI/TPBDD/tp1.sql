-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 27 oct. 2023 à 13:01
-- Version du serveur :  5.7.17
-- Version de PHP :  5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `tp1`
--

-- --------------------------------------------------------

--
-- Structure de la table `students`
--

CREATE TABLE `students` (
  `number` bigint(8) UNSIGNED NOT NULL,
  `lastname` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `firstname` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `students`
--

INSERT INTO `students` (`number`, `lastname`, `firstname`) VALUES
(1001, 'Dupont', 'Jean'),
(1002, 'Durand', 'Sophie'),
(1003, 'Martin', 'Emilie');

-- --------------------------------------------------------

--
-- Structure de la table `studentsue`
--

CREATE TABLE `studentsue` (
  `student` bigint(8) UNSIGNED NOT NULL,
  `ue` varchar(16) CHARACTER SET ascii NOT NULL,
  `noteCC` decimal(4,2) UNSIGNED NOT NULL,
  `noteExam` tinyint(1) UNSIGNED NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `studentsue`
--

INSERT INTO `studentsue` (`student`, `ue`, `noteCC`, `noteExam`) VALUES
(1001, 'L1BDD', '10.00', 11),
(1001, 'L1WEB', '8.00', 10),
(1002, 'L1BDD', '10.00', 11),
(1002, 'L1ISI', '8.00', 10),
(1003, 'L1BDD', '10.00', 11),
(1003, 'L1WEB', '13.00', 15),
(1003, 'L1ISI', '12.00', 13);

-- --------------------------------------------------------

--
-- Structure de la table `teachers`
--

CREATE TABLE `teachers` (
  `number` tinyint(1) UNSIGNED ZEROFILL NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `city` varchar(32) NOT NULL,
  `ue` varchar(16) CHARACTER SET ascii DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `teachers`
--

INSERT INTO `teachers` (`number`, `lastname`, `firstname`, `city`, `ue`) VALUES
(10, 'Sidere', 'Nicolas', 'Surgeres', 'L1BDD'),
(11, 'Coustaty', 'Mickael', 'Perigny', 'L1ISI'),
(12, 'Rodriguez', 'Pierre', 'Aytre', 'L1WEB');

-- --------------------------------------------------------

--
-- Structure de la table `ue`
--

CREATE TABLE `ue` (
  `code` varchar(16) CHARACTER SET ascii NOT NULL,
  `label` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'L[Number][Acronym]',
  `hours` tinyint(1) UNSIGNED NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ue`
--

INSERT INTO `ue` (`code`, `label`, `hours`) VALUES
('L1BDD', 'Introduction aux Base de Données', 24),
('L1ISI', 'Introduction aux Systèmes Informatiques', 36),
('L1WEB', 'Introduction à la programmation WEB', 24);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`number`);

--
-- Index pour la table `studentsue`
--
ALTER TABLE `studentsue`
  ADD PRIMARY KEY (`student`,`ue`);

--
-- Index pour la table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`number`);

--
-- Index pour la table `ue`
--
ALTER TABLE `ue`
  ADD PRIMARY KEY (`code`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `students`
--
ALTER TABLE `students`
  MODIFY `number` bigint(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1004;
--
-- AUTO_INCREMENT pour la table `teachers`
--
ALTER TABLE `teachers`
  MODIFY `number` tinyint(1) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
