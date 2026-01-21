-- 18
SELECT COUNT(*) FROM realisateur;

-- 19
SELECT acteur.nom_acteur, COUNT(role.numero_film) FROM acteur
	NATURAL LEFT OUTER JOIN role
	GROUP BY acteur.nom_acteur;

-- 20
SELECT acteur.numero_acteur,
		MIN(film.duree),
		MAX(film.duree),
		MAX(film.duree) - MIN(film.duree),
		AVG(film.duree)
	FROM acteur NATURAL INNER JOIN role NATURAL INNER JOIN film
	GROUP BY acteur.numero_acteur;

-- 21
SELECT realisateur.numero_realisateur FROM realisateur
	INNER JOIN film on realisateur.numero_realisateur = film.numero_realisateur
    GROUP BY realisateur.numero_realisateur
	HAVING COUNT(film.numero_film) = 2;

-- 22
SELECT r.numero_realisateur, r.nom_realisateur, COUNT(f.numero_film) AS "Qte"
	FROM realisateur r
	NATURAL INNER JOIN film f
	GROUP BY r.numero_realisateur, r.nom_realisateur
	HAVING COUNT(f.numero_film) >= 3
	ORDER BY "Qte" DESC, r.nom_realisateur ASC;

-- 23
SELECT acteur.numero_acteur FROM acteur
	NATURAL INNER JOIN role
	NATURAL INNER JOIN film
	GROUP BY acteur.numero_acteur
	HAVING AVG(film.duree) > 3*60;

-- 24
SELECT acteur.numero_acteur FROM acteur
	NATURAL INNER JOIN role
	NATURAL INNER JOIN film
    WHERE acteur.nation_acteur = 'ANGLAISE'
	GROUP BY acteur.numero_acteur
	HAVING SUM(film.duree) < 5*60;

-- 25
SELECT acteur.nom_acteur, acteur.prenom_acteur, COUNT(role.numero_film)
	FROM acteur
	NATURAL LEFT OUTER JOIN role
	GROUP BY acteur.numero_acteur, acteur.nom_acteur
	ORDER BY COUNT(role.numero_film) DESC;