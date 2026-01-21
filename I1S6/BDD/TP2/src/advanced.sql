-- 31
SELECT DISTINCT nom_acteur
	FROM realisateur, acteur
	WHERE nom_acteur = nom_realisateur;
-- Noms associés à la fois à des acteurs et à des réalisateurs.

-- 32
SELECT a.nom_acteur
	FROM acteur a, acteur copie
	WHERE a.nom_acteur = copie.nom_acteur
	GROUP BY a.nom_acteur
	HAVING COUNT(*) = 1;
-- Nom associés à un seul et unique acteur.