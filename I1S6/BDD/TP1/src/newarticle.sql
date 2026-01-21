-- 4

DELETE FROM auteur;
DELETE FROM article;
DELETE FROM journal;
DELETE FROM chercheur;

DO $$
DECLARE
	current_id_journal journal.id_journal%type;
	current_id_article article.id_article%type;
	current_id_chercheur chercheur.id_chercheur%type;
BEGIN
	INSERT INTO journal(nom_journal, numero_journal, date_de_publication) VALUES
		('Computer Languages, Systems & Structures', 38, '01/01/2011')
		RETURNING id_journal INTO current_id_journal;

	INSERT INTO article(titre_article, id_journal, page_debut, page_fin) VALUES
		('Abstract Interpretation of Database Query Languages',
		 current_id_journal,
		 123,
		 157
		)
	RETURNING id_article INTO current_id_article;


	INSERT INTO chercheur(nom_chercheur, prenom, laboratoire) VALUES
		('Halder', 'Raju', 'Indian Institute of Technology Patna')
		RETURNING id_chercheur INTO current_id_chercheur;
	INSERT INTO auteur(id_article, id_chercheur) VALUES
		(current_id_article, current_id_chercheur);
	INSERT INTO chercheur(nom_chercheur, prenom, laboratoire) VALUES
		('Cortesi', 'Agostino', 'Universita Ca'' Foscari Venezia')
		RETURNING id_chercheur INTO current_id_chercheur;
	INSERT INTO auteur(id_article, id_chercheur) VALUES
		(current_id_article, current_id_chercheur);
END $$;