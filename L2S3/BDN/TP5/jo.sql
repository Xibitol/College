-- DROPPING
DROP TABLE IF EXISTS participe;
DROP TABLE IF EXISTS athlete, discipline;
DROP TABLE IF EXISTS pays, sport;

-- TABLES
CREATE TABLE pays(
	paysID VARCHAR(3) PRIMARY KEY NOT NULL,
	paysNom VARCHAR(32) NOT NULL
);
CREATE TABLE athlete(
	athleteID INTEGER PRIMARY KEY NOT NULL,
	prenom VARCHAR(32) NOT NULL,
	nom VARCHAR(32) NOT NULL,
	dateDeNaissance DATE NOT NULL,
	pays VARCHAR(3) NOT NULL REFERENCES pays(paysID)
);

CREATE TABLE sport(
	sportID INTEGER PRIMARY KEY NOT NULL,
	sportNom VARCHAR(32) NOT NULL
);
CREATE TABLE discipline(
	disciplineID INTEGER PRIMARY KEY NOT NULL,
	disciplineNom VARCHAR(32) NOT NULL,
	sport INTEGER NOT NULL REFERENCES sport(sportID)
);

CREATE TABLE participe(
	athlete INTEGER NOT NULL REFERENCES athlete(athleteID),
	discipline INTEGER NOT NULL REFERENCES discipline(disciplineID),
	rang INTEGER NOT NULL,
	PRIMARY KEY (athlete, discipline)
);

--------------------------------------------------------------------------------
-- LID
--------------------------------------------------------------------------------
-- 1
SELECT prenom, nom FROM athlete WHERE pays = 'FRA' ORDER BY nom ASC;

-- 2
SELECT a.prenom, a.nom FROM athlete a
	INNER JOIN participe p ON p.athlete = a.athleteID
	WHERE p.rang = 1
	ORDER BY a.nom ASC;

-- 3
SELECT a.prenom, a.nom FROM athlete a
	INNER JOIN participe p ON p.athlete = a.athleteID
	WHERE p.rang <= 3 and a.pays = 'FRA' 
	ORDER BY a.nom ASC;

-- 4
SELECT prenom, nom, dateDeNaissance, pays FROM athlete
	ORDER BY dateDeNaissance ASC
	LIMIT 1;

SELECT prenom, nom, dateDeNaissance, pays FROM athlete
	WHERE dateDeNaissance <= ALL(
		SELECT dateDeNaissance FROM athlete
	);

SELECT prenom, nom, dateDeNaissance, pays FROM athlete
	WHERE dateDeNaissance = (SELECT MIN(dateDeNaissance) FROM athlete);

-- 5
DROP VIEW IF EXISTS athleteAges;
CREATE VIEW athleteAges AS
	SELECT athleteID, FLOOR((CURRENT_DATE - dateDeNaissance)/365) AS age
		FROM athlete
		GROUP BY athleteID;

/* For SQLite
DROP VIEW IF EXISTS athleteAges;
CREATE VIEW athleteAges AS
	SELECT athleteID,
		FLOOR((julianday('now') - julianday(dateDeNaissance))/365) AS age
	FROM athlete
	GROUP BY athleteID;
*/

SELECT prenom, nom, pays, age FROM athlete
	NATURAL INNER JOIN athleteAges
	WHERE age = (SELECT MIN(age) FROM athleteAges);

-- 6
SELECT a.prenom, a.nom FROM athlete a
	INNER JOIN participe p ON p.athlete = a.athleteID
	GROUP BY a.prenom, a.nom HAVING MIN(p.rang) > 3
	ORDER BY a.nom;

-- 7
SELECT a.prenom, a.nom, a.pays FROM athlete a
	INNER JOIN participe p ON p.athlete = a.athleteID
	WHERE p.rang <= 3
	GROUP BY a.prenom, a.nom, a.pays HAVING COUNT(p.rang) > 1
	ORDER BY a.nom;

-- 8
SELECT a.prenom, a.nom, a.pays FROM athlete a
	INNER JOIN participe p ON p.athlete = a.athleteID
	WHERE p.rang = 1
	GROUP BY a.prenom, a.nom, a.pays HAVING COUNT(p.rang) > 1
	ORDER BY a.nom;

-- 9
SELECT ps.paysNom, COUNT(p.rang) AS NombreDeMedailles FROM pays ps
	INNER JOIN athlete a ON a.pays = ps.paysID
	INNER JOIN participe p ON p.athlete = a.athleteID
	WHERE p.rang = 1
	GROUP BY ps.paysNom
	ORDER BY NombreDeMedailles DESC;

-- 10
SELECT ps.paysNom FROM pays ps
	INNER JOIN athlete a ON a.pays = ps.paysID
	INNER JOIN participe p ON p.athlete = a.athleteID
	INNER JOIN discipline d ON d.disciplineID = p.discipline
	INNER JOIN sport s ON s.sportID = d.sport
	WHERE p.rang <= 3
	GROUP BY ps.paysnom HAVING COUNT(DISTINCT s.sportID) = (
		SELECT COUNT(*) FROM sport
	);

-- 11
SELECT a.prenom, a.nom,
		COUNT(*) FILTER (WHERE p.rang <= 3) AS NombreDeMedailles
	FROM athlete a
	LEFT OUTER JOIN participe p ON a.athleteID = p.athlete
	GROUP BY a.prenom, a.nom
	ORDER BY a.nom ASC;