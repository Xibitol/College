-- 2
CREATE OR REPLACE PROCEDURE Supprime_Acteur(
		nom acteur.nom_acteur%type, prenom acteur.prenom_acteur%type
	)
	LANGUAGE plpgsql
AS $$
DECLARE
	noActeur acteur.numero_acteur%type;
BEGIN
	SELECT acteur.numero_acteur INTO noActeur FROM acteur
		WHERE acteur.nom_acteur = nom AND acteur.prenom_acteur = prenom;

	DELETE FROM role WHERE role.numero_acteur = noActeur;
	DELETE FROM acteur WHERE acteur.numero_acteur = noActeur;
end;
$$;

CALL Supprime_Acteur('PITT', 'Brad');