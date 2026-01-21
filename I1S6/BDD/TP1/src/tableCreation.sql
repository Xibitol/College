-- 1

DROP SCHEMA IF EXISTS bdd_tp1 CASCADE;
CREATE SCHEMA bdd_tp1;

CREATE TABLE journal(
	id_journal serial PRIMARY KEY,
	nom_journal varchar(64) NOT NULL UNIQUE,
	numero_journal int NOT NULL UNIQUE,
	date_de_publication date NOT NULL
);

CREATE TABLE article(
	id_article serial PRIMARY KEY,
	titre_article varchar(256) NOT NULL UNIQUE,
	id_journal int NULL,
	page_debut smallint NOT NULL,
	page_fin smallint NOT NULL
);

CREATE TABLE auteur(
	id_article int,
	id_chercheur int,

	PRIMARY KEY (id_article, id_chercheur)
);

CREATE TABLE chercheur(
	id_chercheur serial PRIMARY KEY,
	nom_chercheur varchar(64) NOT NULL,
	prenom varchar(64) NOT NULL,
	laboratoire varchar(128) NOT NULL
);

