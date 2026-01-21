-- 1
SELECT acteur.nom_acteur FROM acteur;

-- 2
SELECT DISTINCT acteur.nom_acteur FROM acteur;

-- 3
SELECT * FROM acteur WHERE acteur.nation_acteur = 'ANGLAISE';

-- 4
SELECT acteur.nom_acteur FROM acteur WHERE
	acteur.date_de_naissance >= '01/01/1950'::date
	AND acteur.date_de_naissance <= '12/31/1979'::date;

-- 5
SELECT role.nom_du_role FROM role
	WHERE role.numero_acteur = 2
	ORDER BY role.nom_du_role ASC;

-- 6
SELECT realisateur.nom_realisateur, realisateur.prenom_realisateur
	FROM realisateur
		NATURAL INNER JOIN film
		NATURAL INNER JOIN role
	WHERE role.numero_acteur = 2;

-- 7
SELECT realisateur.nom_realisateur, realisateur.prenom_realisateur
	FROM realisateur
		NATURAL INNER JOIN film
		NATURAL INNER JOIN role
		NATURAL INNER JOIN acteur
	WHERE acteur.nom_acteur = 'DE NIRO'
	ORDER BY realisateur.nom_realisateur ASC;

-- 8
SELECT * FROM acteur
	NATURAL INNER JOIN role
	NATURAL INNER JOIN film
	WHERE film.numero_realisateur = 10;

-- 9
SELECT acteur.numero_acteur, acteur.nom_acteur FROM acteur
	WHERE acteur.nation_acteur IS NOT NULL;

-- 10
SELECT realisateur.nom_realisateur FROM realisateur
	WHERE realisateur.numero_realisateur IN (
		SELECT numero_realisateur FROM film
	);

-- 11
SELECT acteur.numero_acteur, acteur.nom_acteur, role.nom_du_role FROM acteur
	NATURAL LEFT OUTER JOIN role;

-- 12
SELECT DISTINCT acteur.nom_acteur, acteur.prenom_acteur,
       pair.nom_acteur, pair.prenom_acteur
	FROM acteur
		NATURAL INNER JOIN role
		NATURAL INNER JOIN film
		INNER JOIN role AS pairRole ON pairRole.numero_film = film.numero_film
		INNER JOIN acteur AS pair ON pair.numero_acteur = pairRole.numero_acteur
	WHERE acteur.numero_acteur < pair.numero_acteur;