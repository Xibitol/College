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
-- LID
--------------------------------------------------------------------------------
-- 1 ---------------------------------------------------------------------------
INSERT INTO factures VALUES (0, '2024-09-12', 'R');
INSERT INTO interventions VALUES (
	1, '2024-09-12', 'Xibitol', 'Xibitol', 1, 101, 0, 1
);
INSERT INTO clients VALUES(
	200000, 'Martin', 'Martin',
	'Rue des Marins', 'Martin Ville', '-1000',
	'06-06-06-06-06'
);

SELECT DISTINCT f.nofacture, c.nom FROM factures f
	NATURAL INNER JOIN interventions
	NATURAL INNER JOIN clients c
	ORDER BY f.nofacture ASC;

DELETE FROM interventions WHERE nointerv = 1;
DELETE FROM factures WHERE nofacture = 0;
DELETE FROM clients WHERE noclient = 200000;

-- 2 ---------------------------------------------------------------------------
INSERT INTO factures VALUES (0, '2024-09-12', 'C');
INSERT INTO interventions VALUES (
	1, '2024-09-12', 'Xibitol', 'Xibitol', 1, 101, 0, 1
);
INSERT INTO factures VALUES (2, '2024-09-12', 'C');

SELECT DISTINCT f.nofacture, c.nom FROM factures f
	NATURAL INNER JOIN interventions
	NATURAL INNER JOIN clients c
	WHERE f.etat = 'C'
	ORDER BY f.nofacture ASC;

DELETE FROM interventions WHERE nointerv = 1;
DELETE FROM factures WHERE nofacture = 0;
DELETE FROM factures WHERE nofacture = 2;

-- 3 ---------------------------------------------------------------------------
INSERT INTO factures VALUES (0, '2024-09-12', 'R');
INSERT INTO interventions VALUES (
	1, '2024-09-12', 'Xibitol', 'Xibitol', 1, 101, 0, 1
);
INSERT INTO factures VALUES (2, '2024-09-12', 'C');

SELECT f.nofacture, f.datefacture FROM factures f
	NATURAL INNER JOIN interventions
	NATURAL INNER JOIN clients c
	WHERE f.etat = 'R' AND c.nom = 'Rivoire'
		OR f.etat = 'C' and c.nom = 'Dallalon'
	ORDER BY f.nofacture ASC;

DELETE FROM interventions WHERE nointerv = 1;
DELETE FROM factures WHERE nofacture IN (0, 2);

-- 4 ---------------------------------------------------------------------------
INSERT INTO produits VALUES ('DT000', 'TEST', 10, 15, 5);
-- Unable to add an entry that doesn't alter the result.

SELECT p.reference, r.nointerv, r.qteremplacee FROM produits p
	NATURAL LEFT OUTER JOIN remplacements r
	ORDER BY r.qteremplacee DESC;

DELETE FROM produits WHERE reference = 'DT000';

-- 5 ---------------------------------------------------------------------------
INSERT INTO factures VALUES (0, '2024-09-12', 'R');
-- Unable to add an entry that doesn't alter the result.

SELECT f.nofacture, i.nointerv FROM factures f
	NATURAL LEFT OUTER JOIN interventions i
	ORDER BY f.nofacture ASC;

DELETE FROM factures WHERE nofacture = 0;

-- 6 ---------------------------------------------------------------------------
INSERT INTO factures VALUES (0, '2024-09-12', 'R');
INSERT INTO factures VALUES (1, '2024-09-12', 'R');
INSERT INTO interventions VALUES (
	2, '2024-09-12', 'Xibitol', 'Saultier', 1, 101, 0, 1
);
INSERT INTO interventions VALUES (
	3, '2024-09-12', 'Xibitol', 'Bonnaz', 1, 101, 0, 1
);
INSERT INTO interventions VALUES (
	4, '2024-09-12', 'Xibitol', 'Saultier', 1, 101, 1, 1
);

-- Could do better with just one subquery.
SELECT DISTINCT i.nofacture FROM interventions i
	INNER JOIN interventions j
		ON i.nointerv <> j.nointerv
			AND i.nofacture = j.nofacture
			-- Small optimization because of cartesian product.
			AND j.nominterv = 'Bonnaz'
	WHERE i.nominterv = 'Saultier';

DELETE FROM interventions WHERE nointerv IN(2, 3, 4);
DELETE FROM factures WHERE nofacture IN (0, 1);

-- 7 ---------------------------------------------------------------------------
INSERT INTO produits VALUES ('DT000', 'TEST1', 10, 15, 5);
INSERT INTO produits VALUES ('DT001', 'TEST2', 10, 15, 5);
INSERT INTO interventions VALUES (
	0, '2024-09-12', 'Xibitol', 'Xibitol', 1, 101, 1000, 1
);
INSERT INTO remplacements VALUES ('DT001', 0, 1);

SELECT p.designation FROM produits p WHERE p.reference NOT IN (
	SELECT r.reference FROM remplacements r
);

DELETE FROM remplacements WHERE nointerv = 0;
DELETE FROM interventions WHERE nointerv = 0;
DELETE FROM produits WHERE reference SIMILAR TO 'DT00(0|1)';

-- 8 ---------------------------------------------------------------------------
INSERT INTO clients VALUES(
	31000, 'Martin', 'Martin',
	'Rue des Marins', 'Martin Ville', '-1000',
	'06-06-06-06-06'
);
INSERT INTO clients VALUES(
	31001, 'Martin', 'Martine',
	'Rue des Marins', 'Martin Ville', '-1000',
	'06-06-06-06-06'
);
INSERT INTO interventions VALUES (
	0, '2024-09-12', 'Xibitol', 'Xibitol', 1, 31001, 1000, 1
);

SELECT c.noclient, c.nom FROM clients c WHERE c.noclient NOT IN (
	SELECT i.noclient FROM interventions i
);
SELECT c.noclient, c.nom FROM clients c WHERE NOT EXISTS(
	SELECT i.noclient FROM interventions i WHERE i.noclient = c.noclient
);
SELECT c.noclient, c.nom FROM clients c NATURAL LEFT OUTER JOIN interventions i
	WHERE i.nointerv IS NULL;

DELETE FROM interventions WHERE nointerv = 0;
DELETE FROM clients WHERE noclient IN (31000, 31001);

-- 9 ---------------------------------------------------------------------------
INSERT INTO produits VALUES ('DT000', 'TEST', 10, 15, 5);
INSERT INTO remplacements VALUES ('DT000', 1039, 1);
INSERT INTO remplacements VALUES ('DT000', 1040, 1);
INSERT INTO remplacements VALUES ('DT000', 1041, 1);
INSERT INTO remplacements VALUES ('DT000', 1042, 1);
INSERT INTO remplacements VALUES ('DT000', 1043, 1);
INSERT INTO remplacements VALUES ('DT000', 1044, 1);
INSERT INTO remplacements VALUES ('DT000', 1045, 1);
INSERT INTO remplacements VALUES ('DT000', 1046, 1);
INSERT INTO remplacements VALUES ('DT000', 1047, 1);
INSERT INTO remplacements VALUES ('DT000', 1048, 1);
INSERT INTO remplacements VALUES ('DT000', 1049, 1);
INSERT INTO remplacements VALUES ('DT000', 1050, 1);
INSERT INTO remplacements VALUES ('DT000', 1051, 1);
INSERT INTO remplacements VALUES ('DT000', 1052, 1);
-- No need for an entry that doesn't change the result.

/*SELECT p.reference, p.designation FROM produits p WHERE p.reference = ALL(
	SELECT r.reference FROM interventions i
	LEFT OUTER JOIN remplacements r
		ON r.nointerv = i.nointerv AND r.reference = p.reference
);*/

SELECT p.reference, p.designation FROM produits p WHERE NOT EXISTS(
	SELECT 1 FROM interventions i WHERE NOT EXISTS(
		SELECT 1 FROM remplacements r
		WHERE i.nointerv = r.nointerv AND r.reference = p.reference
	)
);

DELETE FROM remplacements WHERE reference = 'DT000';
DELETE FROM produits WHERE reference = 'DT000';

-- 10 --------------------------------------------------------------------------
INSERT INTO factures VALUES(0, '2024-09-12', 'R');
insert into tauxhoraire values(20, 11.67);
insert into interventions values(
	1, '2024-09-12', 'Xibitol', 'Xibitol', 1, 101, 0, 20
);
insert into interventions values(
	2, '2024-09-12', 'Xibitol', 'Xibitol', 1, 102, 0, 20
);
insert into interventions values(
	3, '2024-09-12', 'Xibitol', 'Xibitol', 1, 103, 0, 20
);
insert into interventions values(
	4, '2024-09-12', 'Xibitol', 'Xibitol', 1, 104, 0, 20
);
insert into interventions values(
	5, '2024-09-12', 'Xibitol', 'Xibitol', 1, 105, 0, 20
);
insert into interventions values(
	6, '2024-09-12', 'Xibitol', 'Xibitol', 1, 108, 0, 20
);
insert into interventions values(
	7, '2024-09-12', 'Xibitol', 'Xibitol', 1, 109, 0, 20
);

/*SELECT t.codetaux, t.txhoraire FROM tauxhoraire t WHERE t.codetaux = ALL(
	SELECT i.codetaux FROM clients c
	LEFT OUTER JOIN interventions i
		ON i.noclient = c.noclient AND i.codetaux = t.codetaux
);*/

SELECT t.txhoraire, t.codetaux FROM tauxhoraire t WHERE NOT EXISTS(
	SELECT 1 FROM clients c WHERE NOT EXISTS(
		SELECT 1 FROM interventions i
		WHERE i.noclient = c.noclient AND i.codetaux = t.codetaux
	)
);

DELETE FROM interventions WHERE codetaux = 20;
DELETE FROM factures WHERE nofacture = 0;
DELETE FROM tauxhoraire WHERE codetaux = 20;