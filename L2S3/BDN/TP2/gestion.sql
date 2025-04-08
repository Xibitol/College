-- DROPPING
DROP TABLE remplacements;
DROP TABLE produits;
DROP TABLE interventions;
DROP TABLE tauxhoraire;
DROP TABLE factures;
DROP TABLE clients;

-- PRIMARY AND FOREIGN KEYS
ALTER TABLE clients ADD PRIMARY KEY (noclient);
ALTER TABLE factures ADD PRIMARY KEY (nofacture);
ALTER TABLE tauxhoraire ADD PRIMARY KEY (codetaux);
ALTER TABLE produits ADD PRIMARY KEY (reference);

ALTER TABLE interventions ADD PRIMARY KEY (nointerv);
ALTER TABLE interventions ADD
	FOREIGN KEY (noclient) REFERENCES clients(noclient);
ALTER TABLE interventions ADD
	FOREIGN KEY (nofacture) REFERENCES factures(nofacture);
ALTER TABLE interventions ADD
	FOREIGN KEY (codetaux) REFERENCES tauxhoraire(codetaux);

ALTER TABLE remplacements ADD PRIMARY KEY (reference, nointerv);
ALTER TABLE remplacements ADD
	FOREIGN KEY (reference) REFERENCES produits(reference);
ALTER TABLE remplacements ADD
	FOREIGN KEY (nointerv) REFERENCES interventions(nointerv);

--------------------------------------------------------------------------------
-- LMD
--------------------------------------------------------------------------------
INSERT INTO clients(noclient, nom, prenom, adresse, ville, cp, tel) VALUES
	(131,
		'Itol',
		'Xib',
		'Avenue Jean Monnaie',
		'La Rochelle',
		'17000',
		'06-07-31-28-41'
	),
	(132,
		'Itol',
		NULL,
		'36 Rue de la Seudre',
		'La Tremblade',
		'17390',
		'06-06-06-06-06'
	);

-- Duplicate keys
/*INSERT INTO clients(noclient, nom, adresse, ville, cp, tel) VALUES
	(103, '', '', '', '', '00-00-00-00-00'),
	(103, '', '', '', '', '00-00-00-00-00');*/

INSERT INTO produits(reference, designation, prixht) VALUES
	('DT000', 'Radiateur Blanc Acier 120x80', 200);

-- Invalid qtestock and qtesecurite
/*INSERT INTO produits(reference, designation, prixht, qtestock, qtesecurite)
	VALUES ('', '', 0, 0, 1);*/

-- (Run gestion_lmd.sql) -------------------------------------------------------

DELETE FROM remplacements r USING interventions
	WHERE r.nointerv = i.nointerv AND i.nofacture = 1010;
DELETE FROM interventions WHERE nofacture = 1010;
DELETE FROM factures WHERE nofacture = 1010;

DELETE FROM remplacements r USING interventions i, factures f
	WHERE r.nointerv = i.nointerv AND i.nofacture = f.nofacture
		AND f.etat = 'R';
DELETE FROM interventions i USING factures f
	WHERE i.nofacture = f.nofacture AND f.etat = 'R';
DELETE FROM factures WHERE etat = 'R';

INSERT INTO factures(nofacture, datefacture, etat)
	VALUES (1014, '2020-01-30', 'C');
INSERT INTO interventions(
	nointerv, dateinterv, nomresponsable, nominterv, temps,
	noclient, nofacture, codetaux
) VALUES
	(0,
		'2020-01-31', 'M. Itol', 'Réparation fuite radiateur', 0.5,
		131, 1014, 2
	);

-- temps cannot be 0
/*INSERT INTO interventions(
	nointerv, dateinterv, nomresponsable, nominterv, temps,
	noclient, nofacture, codetaux
) VALUES
	(1,
		'2020-01-31', 'M. Itol', 'Réparation fuite radiateur', 0,
		131, 1014, 2
	);*/

INSERT INTO interventions(
	nointerv, dateinterv, nomresponsable, nominterv, temps,
	noclient, nofacture, codetaux
) VALUES
	(1,
		'2020-01-31', 'M. Itol', 'Réparation fuite radiateur', 2.5,
		131, 1014, 2
	);

-- temps cannot be greater than 8
/*INSERT INTO interventions(
	nointerv, dateinterv, nomresponsable, nominterv, temps,
	noclient, nofacture, codetaux
) VALUES
	(1,
		'2020-01-31', 'M. Itol', 'Réparation fuite radiateur', 12,
		131, 1014, 2
	);*/

DELETE FROM remplacements;
DELETE FROM produits;
DELETE FROM interventions;
DELETE FROM tauxhoraire;
DELETE FROM factures;
DELETE FROM clients;

--------------------------------------------------------------------------------
-- LID
--------------------------------------------------------------------------------
-- 1
SELECT reference, designation FROM produits WHERE prixht > 15;

-- 2
SELECT nointerv, dateinterv, temps FROM interventions
	WHERE nominterv = 'Crespin';

-- 3
SELECT designation FROM produits WHERE qtestock - qtesecurite BETWEEN 1 AND 10;

-- 4
SELECT reference, designation FROM produits WHERE reference LIKE '%1';

-- 5
SELECT i.nointerv, i.dateinterv FROM interventions i
	NATURAL INNER JOIN factures f
	WHERE f.etat = 'R' AND f.datefacture = '2019-07-15';

-- 6
SELECT f.nofacture FROM factures f
	NATURAL INNER JOIN interventions i NATURAL INNER JOIN clients c
	WHERE c.nom = 'Rivoire';

-- 7
SELECT DISTINCT c.nom FROM clients c
	NATURAL INNER JOIN interventions i
	WHERE i.dateinterv < '2019-07-15'
	ORDER BY c.nom ASC;

-- 8
SELECT c.noclient FROM clients c
	NATURAL INNER JOIN interventions i
	WHERE i.nominterv = 'Bonnaz';

-- 9
SELECT DISTINCT i.nomresponsable FROM interventions i
	NATURAL INNER JOIN factures f
	WHERE f.etat = 'C';

-- 10
SELECT i.nointerv FROM interventions i
	NATURAL INNER JOIN remplacements
	NATURAL INNER JOIN produits p
	WHERE p.prixht > 50
	ORDER BY i.nointerv ASC;

-- 11
SELECT p.designation FROM produits p
	NATURAL INNER JOIN remplacements
	NATURAL INNER JOIN interventions i
	WHERE i.dateinterv = '2019-07-03';

-- 12
SELECT r.reference FROM remplacements r
	NATURAL INNER JOIN interventions
	NATURAL INNER JOIN factures f
	WHERE f.etat = 'R'
	ORDER BY r.reference ASC;

-- 13
SELECT c.nom FROM clients c
	NATURAL INNER JOIN interventions
	NATURAL INNER JOIN remplacements r
	WHERE r.reference = 'DT180';

-- 14
SELECT DISTINCT r.reference FROM remplacements r
	NATURAL INNER JOIN interventions
	NATURAL INNER JOIN factures f
	WHERE f.datefacture BETWEEN '2019-07-01' AND '2019-07-31'
	ORDER BY r.reference ASC;

-- 15
SELECT p.designation FROM produits p
	NATURAL INNER JOIN remplacements
	NATURAL INNER JOIN interventions i
	WHERE i.nominterv = 'Crespin';

-- 16
SELECT f.datefacture FROM factures f
	NATURAL INNER JOIN interventions i
	NATURAL INNER JOIN remplacements
	NATURAL INNER JOIN produits p
	WHERE i.nominterv = 'Saultier' AND lower(p.designation) LIKE '%br_leur_%';

-- 17
SELECT p.designation FROM produits p
	NATURAL INNER JOIN remplacements
	NATURAL INNER JOIN interventions
	NATURAL INNER JOIN clients c
	WHERE c.nom = 'Provent';