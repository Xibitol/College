-- CREATE DATABASE TP01_ENTERPRISE;

DROP TABLE IF EXISTS materiel;
DROP TABLE IF EXISTS categorie;
DROP TABLE IF EXISTS contrat;
DROP TABLE IF EXISTS societe;
DROP DOMAIN IF EXISTS type_cp;
DROP DOMAIN IF EXISTS type_serie;

-- DOMAINS
CREATE DOMAIN type_cp AS CHAR(5) CHECK(
	value SIMILAR TO '(0[1-9]|[1-8]\d|9[0-5])\d{3}'
);
CREATE DOMAIN type_serie AS CHAR(8) CHECK(
	value SIMILAR TO '[A-Z]\d{3} \d{2}[A-Z]'
);

-- TABLES
CREATE TABLE societe(
	no_societe INTEGER PRIMARY KEY,
	raison_sociale VARCHAR(30) NOT NULL UNIQUE,
	ville VARCHAR(20) NOT NULL,
	code_postal type_cp NOT NULL
);
CREATE TABLE contrat(
	no_contrat INTEGER PRIMARY KEY,
	date_souscription DATE NOT NULL CHECK(date_souscription > '2000-01-01'),
	duree INTERVAL NOT NULL CHECK(duree <= '5Y'),
	montant MONEY NOT NULL CHECK(montant >= 0.0::MONEY),
	societe INTEGER NOT NULL,
	FOREIGN KEY (societe) REFERENCES societe(no_societe)
);
CREATE TABLE categorie(
	no_categorie INTEGER PRIMARY KEY,
	designation VARCHAR(20) NOT NULL,
	caracteristiques VARCHAR(80)
);
CREATE TABLE materiel(
	no_materiel INTEGER PRIMARY KEY,
	no_serie type_serie NOT NULL UNIQUE,
	designation VARCHAR(50) NOT NULL,
	contrat INTEGER,
	categorie INTEGER,
	FOREIGN KEY (contrat) REFERENCES contrat(no_contrat),
	FOREIGN KEY (categorie) REFERENCES categorie(no_categorie)
);

-- I FORGOT
ALTER TABLE materiel ADD CHECK(materiel.no_serie LIKE '____ %');