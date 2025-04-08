-- Simple requests
SELECT nom, ville FROM Clients;
SELECT * FROM Clients;

-- Double Elimination
SELECT ville FROM Clients;
SELECT DISTINCT ville FROM Clients;

-- Selection with WHERE clause
SELECT * FROM Clients WHERE ville = 'Aytré';
SELECT * FROM Films WHERE prix > 4.90;
SELECT * FROM Locations WHERE date > '2020-10-01';
SELECT * FROM Clients WHERE nom IN ('Dupond', 'Dupont');
SELECT * FROM Films WHERE titre LIKE 'Finding%';
SELECT * FROM Clients WHERE nom LIKE 'D%d';
SELECT * FROM Clients WHERE telephone IS NULL;

-- Hard requests
SELECT * FROM Films ORDER BY titre;
SELECT * FROM Films ORDER BY prix DESC;
SELECT COUNT(*) FROM Locations;
SELECT DISTINCT prix FROM Films;
SELECT COUNT(*) FROM Films GROUP BY prix;
SELECT * FROM Locations;
SELECT DISTINCT Films.num, Films.titre FROM Films INNER JOIN Locations ON Films.num = Locations.num_film;
SELECT Clients.num, Clients.nom, Films.num, Films.titre FROM Locations
    INNER JOIN (Clients, Films) ON Locations.num_client = Clients.num AND Locations.num_film = Films.num;
SELECT Clients.num, Clients.nom, COUNT(*) FROM Clients
    INNER JOIN Locations ON Clients.num = Locations.num_client
    GROUP BY Clients.num;
SELECT Films.num, Films.titre, COUNT(*) FROM Films
    INNER JOIN Locations ON Films.num = Locations.num_film
    GROUP BY Films.num;
SELECT Films.num, Films.titre, COUNT(Locations.num_film) AS locCount FROM Films
    INNER JOIN Locations ON Locations.num_film = Films.num
    GROUP BY Films.num ORDER BY locCount DESC LIMIT 1;
SELECT Clients.num, Clients.nom, ROUND(AVG(Films.prix), 2) FROM Locations
    INNER JOIN (Clients, Films) ON Locations.num_client = Clients.num AND Locations.num_film = Films.num
    GROUP BY Clients.num;