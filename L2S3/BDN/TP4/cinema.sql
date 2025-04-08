-- DROPPING
DROP TABLE projection;
DROP TABLE jouer;
DROP TABLE voir;
DROP TABLE aimer;
DROP TABLE films;
DROP TABLE individus;

--------------------------------------------------------------------------------
-- LID
--------------------------------------------------------------------------------
-- 1
SELECT i.nompers, COUNT(v.nospect) AS nbre_films_vus FROM individus i
	INNER JOIN voir v ON v.nospect = i.nopers
	GROUP BY i.nopers, i.nompers
	ORDER BY i.nompers ASC;

-- 2
SELECT i.nopers, i.nompers FROM individus i
	INNER JOIN jouer j ON j.noacteur = i.nopers
	GROUP BY i.nopers, i.nompers HAVING COUNT(j.nofilm) > 3
	ORDER BY i.nopers ASC;

-- 3
SELECT f.nofilm, f.titre FROM films f
	INNER JOIN aimer a ON a.nofilm = f.nofilm
	GROUP BY f.nofilm, f.titre HAVING COUNT(a.nospect) > 10
	ORDER BY f.nofilm ASC;

-- 4
SELECT f.titre, COUNT(v.nospect) AS nbre_spectateurs FROM films f
	INNER JOIN voir v ON v.nofilm = f.nofilm
	GROUP BY f.nofilm, f.titre HAVING COUNT(v.nospect) < 5
	ORDER BY f.titre ASC;

-- 5
SELECT COUNT(DISTINCT a.nospect) AS nbre_films_de_matthieu_aimes FROM aimer a
	INNER JOIN films f ON f.nofilm = a.nofilm
	WHERE f.norealisateur = 'P36';

SELECT COUNT(DISTINCT nospect) AS nbre_films_de_matthieu_aimes FROM aimer
	WHERE nofilm IN (
		SELECT nofilm FROM films WHERE norealisateur = 'P36'
	);

-- 6
SELECT COUNT(DISTINCT j.noacteur) AS nbre FROM jouer j
	INNER JOIN films f ON f.noproducteur = j.noacteur;

SELECT COUNT(DISTINCT noacteur) AS nbre FROM jouer WHERE noacteur IN (
	SELECT noproducteur FROM films
);

-- 7
DROP VIEW IF EXISTS likeCounts;
CREATE VIEW likeCounts AS SELECT nofilm, COUNT(nospect) AS likes FROM aimer
	GROUP BY nofilm;

SELECT f.titre FROM likeCounts lc
	INNER JOIN films f ON f.nofilm = lc.nofilm
	WHERE lc.likes = (SELECT MAX(likes) FROM likeCounts)
	ORDER BY f.titre ASC;

-- 8
SELECT DISTINCT f.titre FROM films f
	INNER JOIN projection p ON p.nofilm = f.nofilm
	WHERE p.date_proj BETWEEN '2012-07-01' AND '2012-07-31'
	ORDER BY f.titre ASC;

-- 9
SELECT i.nopers, i.nompers FROM individus i
	INNER JOIN jouer j ON j.noacteur = i.nopers
	INNER JOIN films f ON f.nofilm = j.nofilm AND f.noproducteur = j.noacteur
	ORDER BY i.nopers ASC;

-- 10
SELECT nompers FROM individus
	WHERE nopers NOT IN (
		SELECT DISTINCT nospect FROM aimer
	)
	ORDER BY nompers ASC;

-- 11
SELECT nopers, nompers FROM individus
	WHERE nopers IN (SELECT DISTINCT nospect FROM voir)
		AND nopers NOT IN (SELECT DISTINCT nospect FROM aimer)
	ORDER BY nopers ASC;

SELECT i.nopers, i.nompers FROM individus i
	WHERE NOT EXISTS(
		SELECT 1 FROM voir v
			LEFT OUTER JOIN aimer a ON a.nospect = v.nospect
				AND a.nofilm = v.nofilm
			WHERE v.nospect = i.nopers AND a.nofilm IS NOT NULL
	)
	ORDER BY i.nopers ASC;

-- 12
SELECT nofilm FROM films
	WHERE nofilm NOT IN (SELECT DISTINCT nofilm FROM aimer)
	ORDER BY nofilm ASC;

SELECT f.nofilm FROM films f
	WHERE NOT EXISTS (SELECT 1 FROM aimer a WHERE a.nofilm = f.nofilm)
	ORDER BY nofilm ASC;

-- 13
SELECT i.nopers, i.nompers FROM individus i WHERE i.nopers NOT IN (
		SELECT v.nospect FROM voir v NATURAL INNER JOIN films f
			WHERE f.noproducteur <> i.nopers
	);

SELECT i.nopers, i.nompers FROM individus i WHERE NOT EXISTS (
		SELECT 1 FROM voir v NATURAL INNER JOIN films f
			WHERE f.noproducteur <> i.nopers AND v.nospect = i.nopers
	);

-- 14
SELECT nompers FROM individus WHERE nopers IN (
	SELECT j.noacteur FROM jouer j
	INNER JOIN films f1 ON f1.noproducteur = j.noacteur
	INNER JOIN films f2 ON f2.norealisateur = j.noacteur
);

-- 15
SELECT i.nompers FROM individus i
	INNER JOIN voir v ON v.nospect = i.nopers
	GROUP BY i.nompers HAVING COUNT(v.nofilm) = (SELECT COUNT(*) FROM films)
	ORDER BY i.nompers;