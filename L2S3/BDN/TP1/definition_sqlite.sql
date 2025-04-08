-- TABLES
CREATE TABLE societe(
	no_societe INTEGER PRIMARY KEY,
	raison_sociale TEXT NOT NULL UNIQUE,
	ville TEXT NOT NULL,
	code_postal TEXT NOT NULL
);
CREATE TABLE contrat(
	no_contrat INTEGER PRIMARY KEY,
	date_souscription TEXT,
	duree TEXT,
	montant REAL,
	societe INTEGER,
	FOREIGN KEY (societe) REFERENCES societe(no_societe)
);
CREATE TABLE categorie(
	no_categorie INTEGER PRIMARY KEY,
	designation TEXT NOT NULL,
	caracteristiques TEXT
);
CREATE TABLE materiel(
	no_materiel INTEGER PRIMARY KEY,
	no_serie TEXT NOT NULL UNIQUE,
	designation TEXT NOT NULL,
	contrat INTEGER,
	categorie INTEGER,
	FOREIGN KEY (contrat) REFERENCES contrat(no_contrat),
	FOREIGN KEY (categorie) REFERENCES categorie(no_categorie)
);