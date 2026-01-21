-- 26
SELECT acteur.nom_acteur FROM acteur
	WHERE acteur.nom_acteur IN (
		SELECT realisateur.nom_realisateur FROM realisateur
	);

-- 27
SELECT realisateur.numero_realisateur FROM realisateur
	NATURAL INNER JOIN film
	WHERE film.duree = (
		SELECT MAX(film.duree) FROM film
	);

-- 28
WITH freq AS (
		SELECT acteur.nation_acteur AS "nation",
			COUNT(acteur.numero_acteur) AS "count"
			FROM acteur
			GROUP BY acteur.nation_acteur
	)
SELECT acteur.numero_acteur, acteur.nom_acteur FROM acteur
	WHERE acteur.nation_acteur IN (
		SELECT freq.nation FROM freq WHERE freq.count = (
			SELECT MAX(freq.count) FROM freq
		)
	);

-- 28 BIS (17:32 to 18:39 -> 1 heure et 7 minutes)
SELECT acteur.numero_acteur, acteur.nom_acteur
	FROM acteur
	INNER JOIN acteur AS aBis ON acteur.nation_acteur = aBis.nation_acteur
	GROUP BY acteur.numero_acteur, acteur.nom_acteur
	HAVING COUNT(aBis.numero_acteur) = (
		SELECT COUNT(a2.numero_acteur) AS "count"
			FROM acteur AS a1
			INNER JOIN acteur AS a2 ON a1.nation_acteur = a2.nation_acteur
			GROUP BY a1.numero_acteur
			ORDER BY "count" DESC
			LIMIT 1
	)
	ORDER BY acteur.numero_acteur ASC;

-- 29
WITH actors AS (
		SELECT * FROM role
			NATURAL INNER JOIN film
		    WHERE film.numero_realisateur != 2
	)
SELECT acteur.nom_acteur FROM acteur
	WHERE acteur.numero_acteur IN (SELECT actors.numero_acteur FROM actors);

-- 30
SELECT * FROM realisateur WHERE realisateur.numero_realisateur NOT IN (
	SELECT film.numero_realisateur FROM film
);