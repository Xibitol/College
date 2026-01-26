-- 3
CREATE OR REPLACE FUNCTION setSpecialSeat()
    RETURNS trigger
    LANGUAGE plpgsql
AS $$
BEGIN
	UPDATE place SET categorie_place = 'S' WHERE numero_place = NEW.numero_place;
	RETURN NULL;
END;
$$;

CREATE TRIGGER specialSeat
    AFTER INSERT ON reservation
    FOR EACH ROW WHEN (NEW.numero_place = 1)
    EXECUTE FUNCTION setSpecialSeat();

DELETE FROM reservation WHERE numero_seance = 24 AND numero_place = 1;
UPDATE place SET categorie_place = 'A' WHERE numero_place = 1;
INSERT INTO reservation(numero_seance, numero_place, nom_spectateur) VALUES
	(24, 1, 'Moi');
SELECT * FROM place WHERE numero_place = 1;