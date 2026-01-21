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