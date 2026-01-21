-- 2

ALTER TABLE article
	ADD CONSTRAINT fk_id_journal FOREIGN KEY (id_journal)
		REFERENCES journal(id_journal);

ALTER TABLE auteur
	ADD CONSTRAINT fk_id_article FOREIGN KEY (id_article)
		REFERENCES article(id_article),
	ADD CONSTRAINT fk_id_chercheur FOREIGN KEY (id_chercheur)
		REFERENCES chercheur(id_chercheur);