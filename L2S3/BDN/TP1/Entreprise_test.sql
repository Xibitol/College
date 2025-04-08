DELETE FROM materiel;
DELETE FROM categorie;
DELETE FROM contrat;
DELETE FROM societe;

-- -----------------------------------------------------------------------------
-- 2.3.1 Contraintes d'existence et unicité
-- -----------------------------------------------------------------------------
INSERT INTO societe(no_societe, raison_sociale, ville, code_postal)
	VALUES(1, 'AIRBUS', 'BLAGNAC', '31700');
INSERT INTO societe(no_societe, raison_sociale, ville, code_postal)
	VALUES(2, 'ALSTOM', 'XXX', '93400');
-- 'ville' is missing
/*INSERT INTO societe(no_societe, raison_sociale, code_postal)
	VALUES(3, 'BNP PARIBAS', '75009');*/
-- 'raison_sociale' is missing
/*INSERT INTO societe(no_societe, ville, code_postal)
	VALUES(4, 'PARIS', '75017');*/
-- 'date_souscription' is missing
/*INSERT INTO contrat(no_contrat, duree, montant, societe)
	VALUES(1, '5Y', 12000.00, 1);*/
INSERT INTO contrat(no_contrat, date_souscription, duree, montant, societe)
	VALUES(2, '2018-02-01', '3Y 6M', 22000.00, 1);
INSERT INTO materiel (no_materiel, no_serie, designation, contrat, categorie)
	VALUES (1, 'A123 45B', 'rocking chair', 13, 1);
-- 'designation' is missing
/*INSERT INTO materiel (no_materiel, no_serie, contrat, categorie)
	VALUES (2, 'A123 46B', 11, 1);*/
INSERT INTO materiel (no_materiel, no_serie, designation, contrat)
	VALUES (3, 'A123 47B', 'rocking chair', 9);
INSERT INTO materiel (no_materiel, no_serie, designation, categorie)
	VALUES (4, 'A123 48B', 'rocking chair', 1);
INSERT INTO categorie(no_categorie, designation, caracteristiques)
	VALUES (1, 'Mobilier', 'chaise, bureau, étagère, etc.');

-- -----------------------------------------------------------------------------
-- 2.3.2 Contraintes de domaine simples
-- -----------------------------------------------------------------------------
INSERT INTO contrat(no_contrat, date_souscription, duree, montant, societe)
	VALUES(9, '2014-03-01', '1Y', 12000.00, 3);
-- 'date_souscription' too old
/*INSERT INTO contrat(no_contrat, date_souscription, duree, montant, societe)
	VALUES(10, '1916-03-01', '1Y', 11000.00, 3);*/
-- 'date_souscription' too old
/*INSERT INTO contrat(no_contrat, date_souscription, duree, montant, societe)
	VALUES(15, '1916-03-01', '100Y', 11000.00, 3);*/
-- 'duree' too long
/*INSERT INTO contrat(no_contrat, date_souscription, duree, montant, societe)
	VALUES(11, '2016-03-01', '12Y 6M', 13000.00, 3);*/
-- 'montant' negative
/*INSERT INTO contrat(no_contrat, date_souscription, duree, montant, societe)
	VALUES(24, '2017-03-01', '12Y 6M', -1400.00, 3);*/
-- 'montant' negative
/*INSERT INTO contrat(no_contrat, date_souscription, duree, montant, societe)
	VALUES(24, '2017-03-01', '1Y 6M', -1400.00, 3);*/
	
-- -----------------------------------------------------------------------------
-- 2.3.3 Utilisation de LIKE
-- -----------------------------------------------------------------------------
-- 'no_serie' not unique
/*INSERT INTO materiel (no_materiel, no_serie, designation)
	VALUES (1, 'A123 48B', 'TEST NUMERO SERIE');*/
-- 'no_serie' invalid
/*INSERT INTO materiel (no_materiel, no_serie, designation)
	VALUES (1, 'AAAA BBB', 'TEST NUMERO SERIE');*/
-- 'no_serie' invalid
/*INSERT INTO materiel (no_materiel, no_serie, designation)
	VALUES (1, '12345678', 'TEST NUMERO SERIE');*/
-- 'no_serie' invalid
/*INSERT INTO materiel (no_materiel, no_serie, designation)
	VALUES (1, '1111XXXX', 'TEST NUMERO SERIE');*/
-- 'no_serie' invalid
/*INSERT INTO materiel (no_materiel, no_serie, designation)
	VALUES (1, '1111 111', 'TEST NUMERO SERIE');*/
-- 'no_serie' invalid
/*INSERT INTO materiel (no_materiel, no_serie, designation)
	VALUES (1, 'XXXXXXXX', 'TEST NUMERO SERIE');*/
-- 'no_serie' invalid
/*INSERT INTO materiel (no_materiel, no_serie, designation)
	VALUES (1, 'XXXX XXX', 'TEST NUMERO SERIE');*/

-- -----------------------------------------------------------------------------
-- 2.3.4 Création de domaines avancée
-- -----------------------------------------------------------------------------
-- Code Postal
-- -----------------------------------------------------------------------------
INSERT INTO societe(no_societe, raison_sociale, ville, code_postal)
	VALUES(1, 'NOM', 'VILLE', '17440');
/*INSERT INTO societe(no_societe, raison_sociale, ville, code_postal)
	VALUES(1, 'NOM', 'VILLE', 'XXXXX');*/
/*INSERT INTO societe(no_societe, raison_sociale, ville, code_postal)
	VALUES(1, 'NOM', 'VILLE', 'XXX');*/
/*INSERT INTO societe(no_societe, raison_sociale, ville, code_postal)
	VALUES(1, 'NOM', 'VILLE', '11X11');*/
/*INSERT INTO societe(no_societe, raison_sociale, ville, code_postal)
	VALUES(1, 'NOM', 'VILLE', '1000');*/
/*INSERT INTO societe(no_societe, raison_sociale, ville, code_postal)
	VALUES(1, 'NOM', 'VILLE', '99999');*/
INSERT INTO societe(no_societe, raison_sociale, ville, code_postal)
	VALUES(1, 'NOM', 'VILLE', '95000');
INSERT INTO societe(no_societe, raison_sociale, ville, code_postal)
	VALUES(1, 'NOM', 'VILLE', '01000');