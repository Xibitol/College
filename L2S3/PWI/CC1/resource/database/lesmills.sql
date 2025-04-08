-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 18 Octobre 2018 à 12:51
-- Version du serveur :  5.7.11
-- Version de PHP :  5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `lesmills`
--

-- --------------------------------------------------------

--
-- Structure de la table `typecours`
--

create table typecours
(
    id_typecours int auto_increment
        primary key,
    nom          varchar(255) not null,
    photo        varchar(255) not null,
    description  text         not null
)
    charset = utf8mb3;

--
-- Contenu de la table `typecours`
--

INSERT INTO `typecours` (`id_typecours`, `nom`, `photo`, `description`) VALUES
(1, 'bodypump', 'img/bodypump.jpg', 'BODYPUMP est pour toutes les personnes qui souhaitent s’affiner, se tonifier et se remettre en forme – rapidement.'),
(2, 'bodycombat', 'img/bodycombat.jpg', 'Le cours à haute intensité inspiré des arts martiaux, le tout sans contact. Un combat vers la remise en forme'),
(3, 'rpm', 'img/rpm.jpg', 'Le cours de vélo indoor, au rythme d’une musique motivante. Brûlez des calories et augmentez vos performances rapidement.'),
(4, 'cardio', 'img/cardio.jpg', 'Le cours à haute intensité de 30 minutes qui améliore la forme cardio, augmente la vitesse et maximise la dépense énergétique.');




--
-- Structure de la table `cours`
--

create table cours
(
    id_cours     int auto_increment
        primary key,
    heure        time not null,
    date         date not null,
    id_typecours int  not null,
    constraint cours_typecours_null_fk
        foreign key (id_typecours) references typecours (id_typecours)
)
    charset = utf8mb3;

--
-- Contenu de la table `cours`
--

INSERT INTO `cours` (`id_cours`, `heure`, `date`, `id_typecours`) VALUES
(1,'09:00:00','2023-11-16',1),(2,'09:00:00','2023-11-23',2),(3,'10:00:00','2023-12-22',3),(4,'18:00:00','2023-12-22',2),(5,'19:00:00','2023-12-22',3),(6,'16:00:00','2023-12-22',4),(7,'11:00:00','2023-12-23',1),(8,'17:00:00','2023-12-23',4),(9,'18:00:00','2023-12-23',3),(10,'19:00:00','2023-12-23',2),(11,'10:00:00','2023-12-24',1),(12,'11:00:00','2023-12-24',3),(13,'17:00:00','2023-12-26',4),(14,'18:00:00','2023-12-26',4),(15,'09:00:00','2023-12-27',2),(16,'11:00:00','2023-12-27',3),(17,'18:00:00','2023-12-27',1),(18,'19:00:00','2023-12-27',3);

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

create table personne
(
    id_personne int auto_increment
        primary key,
    nom         varchar(255)   not null,
    prenom      varchar(255)   not null,
    nbSeance    int default 10 not null
)
    charset = utf8mb3;

--
-- Contenu de la table `personne`
--

INSERT INTO `personne` (`id_personne`, `nom`, `prenom`, `nbSeance`) VALUES
(1, 'dupont', 'georges', 6),
(2, 'durant', 'amanda', 9),
(3, 'lalanne', 'jean', 10);






--
-- Structure de la table `participe`
--

create table participe
(
    id          int auto_increment
        primary key,
    id_cours    int not null,
    id_personne int not null,
    constraint participe_cours_null_fk
        foreign key (id_cours) references cours (id_cours),
    constraint participe_personne_null_fk
        foreign key (id_personne) references personne (id_personne)
)
    charset = utf8mb3;

--
-- Contenu de la table `participe`
--

INSERT INTO `participe` (`id`, `id_cours`, `id_personne`) VALUES
(1, 3, 1),
(2, 11, 2),
(3, 2, 1);

-- --------------------------------------------------------


