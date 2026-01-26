-- 1
CREATE OR REPLACE VIEW acteurSeance(
		numero_acteur, nom_acteur, prenom_acteur,
		nation_acteur, date_de_naissance,
		count
	) AS
		SELECT acteur.numero_acteur,
				acteur.nom_acteur,
				acteur.prenom_acteur,
				acteur.nation_acteur,
				acteur.date_de_naissance,
				COUNT(seance.numero_seance)
			FROM acteur
			NATURAL LEFT OUTER JOIN role
			NATURAL LEFT OUTER JOIN film
			NATURAL LEFT OUTER JOIN seance
			GROUP BY acteur.numero_acteur,
				acteur.nom_acteur,
				acteur.prenom_acteur,
				acteur.nation_acteur,
				acteur.date_de_naissance;

SELECT "as".nom_acteur, "as".prenom_acteur, "as".count AS "seances"
	FROM acteurSeance AS "as";