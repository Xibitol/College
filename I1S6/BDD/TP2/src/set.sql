-- 12
SELECT acteur.nom_acteur AS nom FROM acteur
	UNION DISTINCT SELECT realisateur.nom_realisateur FROM realisateur;

-- 13
SELECT acteur.nom_acteur AS nom FROM acteur
	INTERSECT DISTINCT SELECT realisateur.nom_realisateur FROM realisateur;

-- 14
SELECT DISTINCT acteur.nom_acteur AS nom FROM acteur
	WHERE acteur.nom_acteur NOT IN (
		SELECT realisateur.nom_realisateur FROM realisateur
	);

-- 15
SELECT acteur.numero_acteur, acteur.nom_acteur FROM acteur
	WHERE acteur.nation_acteur IN ('ANGLAISE', 'CANADIENNE');

-- 16
SELECT * FROM realisateur WHERE realisateur.numero_realisateur NOT IN (
	SELECT film.numero_realisateur FROM film
);