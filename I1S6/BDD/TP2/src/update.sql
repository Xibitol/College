-- 38
INSERT INTO realisateur(nom_realisateur, prenom_realisateur, nation_realisateur)
	VALUES ('Pelleray-Guilhem', 'Axel', 'FRANÇAISE');

-- 39
DELETE FROM role WHERE role.numero_acteur IN (
	SELECT acteur.numero_acteur FROM acteur
	    WHERE acteur.nation_acteur = 'AMERICAINE'
);
DELETE FROM acteur WHERE acteur.nation_acteur = 'AMERICAINE';

-- 40
UPDATE film SET duree = duree + 60;