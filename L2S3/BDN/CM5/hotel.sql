-- 151 (PDF 3)
SELECT nomhotel FROM hotels WHERE ville='Niort'; -- Or better with postal code.

-- 152 (PDF 4)
SELECT DISTINCT c.numclient, c.nomclient FROM clients c
	NATURAL INNER JOIN reservations
	NATURAL INNER JOIN hotels h
	WHERE h.ville = 'Niort'
	ORDER BY c.numclient ASC;

-- 153 (PDF 5)
SELECT r.numhotel, r.numclient, r.numchambre FROM reservations r
	NATURAL INNER JOIN hotels h
	WHERE h.nomhotel = 'Hotel Ibis' AND h.ville <> 'La Rochelle';

-- 154 (PDF 6)
SELECT nomclient FROM clients
	WHERE pays IN ('Royaume Uni', 'Etats Unis')
	ORDER BY nomclient;

-- 155 (PDF 7)
SELECT nomclient FROM clients
	WHERE pays <> 'Royaume Uni'
		AND numclient IN (
		    SELECT numclient FROM reservations
		    NATURAL INNER JOIN hotels h
		    WHERE nomhotel = 'Hotel de la gare'
		)
	ORDER BY nomclient;

-- 156 (PDF 8)
SELECT c.numclient, c.nomclient	FROM clients c
	WHERE NOT EXISTS(
		SELECT 1 FROM reservations r WHERE r.numclient = c.numclient
	)
	ORDER BY c.numclient;

-- 157 (PDF 9)
SELECT h.numhotel, h.nomhotel FROM hotels h
	WHERE NOT EXISTS(
		SELECT 1 FROM clients c WHERE c.pays = 'Suisse' AND NOT EXISTS(
			SELECT 1 FROM reservations r
				WHERE r.numclient = c.numclient AND r.numhotel = h.numhotel
		)
	);

SELECT h.numhotel, h.nomhotel FROM hotels h
	WHERE NOT EXISTS(
		SELECT 1 FROM clients c
			LEFT OUTER JOIN reservations r
				ON r.numclient = c.numclient AND r.numhotel = h.numhotel
			WHERE c.pays = 'Suisse' AND r.numchambre IS NULL
	);

-- 158 (PDF 10)
SELECT COUNT(c.numclient) AS nb FROM clients c
	WHERE NOT EXISTS(
		SELECT 1 FROM reservations r WHERE r.numclient = c.numclient
	);

-- 159 (PDF 11)
SELECT h.nomhotel, COUNT(c.numchambre) FROM hotels h
	NATURAL INNER JOIN chambres c
	GROUP BY h.numhotel, h.nomhotel HAVING COUNT(c.numchambre) >= 9
	ORDER BY h.nomhotel;

-- 160 (PDF 12)
/*SELECT h.nomhotel FROM hotels h
	WHERE (SELECT MAX(prix) FROM chambres) = (
		SELECT MAX(c.prix) FROM chambres c WHERE c.numhotel = h.numhotel
	)
	ORDER BY h.nomhotel DESC;*/

SELECT h.nomhotel, MAX(c.prix) FROM hotels h
	NATURAL INNER JOIN chambres c
	GROUP BY h.numhotel, h.nomhotel
	ORDER BY h.nomhotel DESC;
