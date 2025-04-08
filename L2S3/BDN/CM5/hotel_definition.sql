drop table if exists reservations, chambres, hotels,clients;

-- TABLE hotels

create table hotels ( 
	numhotel serial primary key,
	nomhotel char(30) not null,
	codepostal integer not null ,
	ville char(30) not null ,
	unique (ville, nomhotel)
);

-- TABLE clients

create table clients ( 
	numclient integer  primary key ,
	nomclient char(20) not null,
	pays char(35) not null
);

-- TABLE chambres

create table chambres (  
	numhotel integer references hotels(numhotel),
	numchambre integer,
	typechambre char(30) not null ,
	prix numeric(9,2) not null ,
	primary key ( numhotel, numchambre)
);

-- TABLE reservations

create table reservations (
	numhotel integer,
	numclient integer references clients ( numclient),
	date_arrivee date not null default current_date,
	date_depart date default current_date + 1,
	numchambre integer,
	primary key ( numhotel, date_arrivee, numchambre),
	foreign key ( numhotel, numchambre) references chambres (numhotel,numchambre),
	check ( date_arrivee < date_depart)
);

-- INSERT hotels

insert into hotels (numhotel,nomhotel,codepostal,ville)values(1, 'Hotel de la gare', 79000, 'Niort');
insert into hotels (numhotel,nomhotel,codepostal,ville)values(2, 'Hotel de la Paix', 79000, 'Niort');
insert into hotels (numhotel,nomhotel,codepostal,ville)values(3, 'Hotel du Parc', 79000, 'Niort');
insert into hotels (numhotel,nomhotel,codepostal,ville)values(4, 'Hotel Le Paris', 79000, 'Niort');
insert into hotels (numhotel,nomhotel,codepostal,ville)values(5, 'Hotel Sandrina',79000 , 'Niort');
insert into hotels (numhotel,nomhotel,codepostal,ville)values(6, 'Hotel Ibis',79000 , 'Niort');
insert into hotels (numhotel,nomhotel,codepostal,ville)values(7, 'Hotel Ambassadeur',79000 , 'Niort');
insert into hotels (numhotel,nomhotel,codepostal,ville)values(8, 'Hotel Mercure ', 17000, 'La Rochelle');
insert into hotels (numhotel,nomhotel,codepostal,ville)values(9, 'Hotel Ibis', 17000, 'La Rochelle');
insert into hotels (numhotel,nomhotel,codepostal,ville)values(10, 'Hotel Francois 1er ', 17000, 'La Rochelle');
insert into hotels (numhotel,nomhotel,codepostal,ville)values(11, 'Hotel Richelieu', 17000, 'La Rochelle');
insert into hotels (numhotel,nomhotel,codepostal,ville)values(12, 'France Hotel', 17000, 'La Rochelle');
insert into hotels (numhotel,nomhotel,codepostal,ville)values(13, 'Etap Hotel', 17000, 'La Rochelle');
insert into hotels (numhotel,nomhotel,codepostal,ville)values(14, 'Hotel de la Monnaie', 17000, 'La Rochelle');
insert into hotels (numhotel,nomhotel,codepostal,ville)values(15,  'Novotel', 17000, 'La Rochelle');
insert into hotels (numhotel,nomhotel,codepostal,ville)values(16, 'Hotel de la Tour de Nesle', 17000, 'La Rochelle');

-- INSERT clients

insert into clients values ( 1,'Martin', 'France');
insert into clients values ( 2,'Dupont','France');
insert into clients values ( 3,'Ravaud','France');
insert into clients values ( 4,'Deferre','France');
insert into clients values ( 5,'Drouet','France');
insert into clients values ( 6,'Barre','France');
insert into clients values ( 7,'Claveau','France');
insert into clients values ( 8,'Dejollat','France');
insert into clients values ( 9,'Arnault','France');
insert into clients values ( 10,'Breart','France');
insert into clients values ( 11,'Walter', 'Royaume Uni');
insert into clients values ( 12,'Mc Lellan','Royaume Uni');
insert into clients values ( 13,'Davidson','Royaume Uni');
insert into clients values ( 14,'Powell','Royaume Uni');
insert into clients values ( 15,'Arvell','Royaume Uni');
insert into clients values ( 16,'Rodriguez','Espagne');
insert into clients values ( 17,'Speriando','Espagne');
insert into clients values ( 18,'Daido','Espagne');
insert into clients values ( 19,'Gomez','Espagne');
insert into clients values ( 20,'Arisbendi','Espagne');
insert into clients values ( 21,'Maurat','Suisse');
insert into clients values ( 22,'Hay','Suisse');
insert into clients values ( 23,'Garway', 'Etats Unis');
insert into clients values ( 24,'Wilson','Etats Unis');
insert into clients values ( 25,'Petton','Etats Unis');
insert into clients values ( 26,'Cardwindon','Etats Unis');
insert into clients values ( 27,'Gamazti','Etats Unis');
insert into clients values ( 28,'Bonwell','Etats Unis');
insert into clients values ( 29,'Tromway','Etats Unis');
insert into clients values ( 30,'Fisher', 'Allemagne');
insert into clients values ( 31,'Guntarson','Allemagne');
insert into clients values ( 32,'Cheam','Allemagne');
insert into clients values ( 33,'Hesse','Allemagne');
insert into clients values ( 34,'Karlson','Allemagne');
insert into clients values ( 35,'Karminte','Allemagne');
insert into clients values ( 36,'Fassbinder','Allemagne');
insert into clients values ( 37,'Dower','Allemagne');

-- INSERT chambres

insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la gare'), 1, 'individuel', 40);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la gare'), 2, 'individuel', 40);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la gare'), 3, 'individuel', 40);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la gare'), 4, 'double', 60);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la gare'), 5, 'double', 60);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la gare'), 6, 'double', 60);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la gare'), 7, 'double', 60);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la gare'), 8, 'double', 60);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la gare'), 9, 'triple', 80);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la gare'), 10, 'triple', 80);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 1, 'individuel', 55);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 2, 'individuel', 55);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 3, 'individuel', 55);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 4, 'individuel', 55);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 5, 'individuel', 55);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 6, 'individuel', 55);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 7, 'double', 80);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 8, 'double', 80);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 9, 'double', 80);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 10, 'double', 80);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 11, 'double', 80);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 12, 'double', 80);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 13, 'double', 80);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 1, 'individuel', 60.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 2, 'individuel', 60.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 3, 'individuel', 60.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 4, 'individuel', 60.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 5, 'individuel', 60.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 6, 'individuel', 60.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 7, 'individuel', 60.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 8, 'individuel', 60.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 9, 'individuel', 60.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 10, 'double', 82.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 11, 'double', 82.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 12, 'double', 82.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 13, 'double', 82.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 14, 'double', 82.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 15, 'double', 82.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Le Paris'), 1, 'individuel', 50.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Le Paris'), 2, 'individuel', 50.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Le Paris'), 3, 'individuel', 50.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Le Paris'), 4, 'individuel', 50.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Le Paris'), 5, 'individuel', 50.50);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 1, 'individuel', 72.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 2, 'individuel', 72.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 3, 'individuel', 72.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 4, 'individuel', 72.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 5, 'individuel', 72.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 6, 'individuel', 72.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 7, 'individuel', 72.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 8, 'individuel', 72.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 9, 'individuel', 72.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 10, 'individuel', 72.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 11, 'double', 100);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 12, 'double', 100);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 13, 'double', 100);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 14, 'double', 100);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 15, 'double', 100);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 16, 'double', 100);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 17, 'double', 100);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 18, 'double', 100);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 19, 'double', 100);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 20, 'triple', 140.25);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 21, 'triple', 140.25);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 22, 'triple', 140.25);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 23, 'triple', 140.25);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 24, 'triple', 140.25);
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 1, 'individuel', 64.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 2, 'individuel', 64.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 3, 'individuel', 64.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='La Rochelle'), 4, 'individuel', 64.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 5, 'individuel', 64.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 6, 'individuel', 64.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 7, 'individuel', 64.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 8, 'individuel', 64.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 9, 'double', 79.00 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 10, 'double', 79.00 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 11, 'double', 79.00 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 12, 'double', 79.00 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 13, 'double', 79.00 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 14, 'double', 79.00 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 15, 'double', 79.00 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 1, 'individuel', 92.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 2, 'individuel', 92.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 3, 'individuel', 92.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 4, 'individuel', 92.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 5, 'individuel', 92.50 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 6, 'double', 120.00 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 7, 'double', 120.00 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 8, 'double', 120.00 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 9, 'double', 120.00 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 10, 'double', 120.00 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 11, 'double', 120.00 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 12, 'triple', 190.00 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 13, 'triple', 190.00 );
insert into chambres values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 14, 'triple', 190.00 );

-- INSERT reservations

insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel de la gare'), 1, '2010-08-12', '2010-08-15', 1);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel de la gare'), 1, '2010-08-17', '2010-08-20', 6);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel de la gare'), 14, '2010-08-12', '2010-08-14', 2);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel de la gare'), 15, '2010-08-13', '2010-08-15', 7);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel de la gare'), 21, '2010-08-12', '2010-08-15', 4);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel de la gare'), 24, '2010-08-12', '2010-08-15', 9);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel de la gare'), 29, '2010-08-12', '2010-08-15', 5);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel de la gare'), 30, '2010-08-12', '2010-08-15', 10);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel de la gare'), 36, '2010-08-12', '2010-08-15', 8);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 2, '2010-08-12', '2010-08-14', 2);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 30, '2010-08-12', '2010-08-15', 3);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 4, '2010-08-12', '2010-08-13', 4);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 5, '2010-08-12', '2010-08-18', 9);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 9, '2010-08-15', '2010-08-18', 2);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 19, '2010-08-12', '2010-08-14', 11);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 26, '2010-08-14', '2010-08-17', 4);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel de la Paix'), 2, '2010-08-18', '2010-08-20', 2);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 20, '2010-08-12','2010-08-14',14);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 33, '2010-08-12','2010-08-19',10);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 30, '2010-08-19','2010-08-20',10);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 27, '2010-08-12','2010-08-19',4);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 23, '2010-08-12','2010-08-15',1);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 10, '2010-08-12','2010-08-14',3);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 23, '2010-08-15','2010-08-19',3);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel du Parc'), 19, '2010-08-18','2010-08-20',6);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Le Paris'), 21, '2010-08-12','2010-08-16',1);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Le Paris'), 22, '2010-08-17','2010-08-19',1);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Le Paris'), 29, '2010-08-16','2010-08-17',5);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Le Paris'), 30, '2010-08-12','2010-08-16',2);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Le Paris'), 34, '2010-08-12','2010-08-16',3);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Le Paris'), 37, '2010-08-12','2010-08-16',4);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 21, '2010-08-12','2010-08-13',1);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 22, '2010-08-13','2010-08-16',1);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 30, '2010-08-16','2010-08-20',1);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 6, '2010-08-12','2010-08-20',2);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 7, '2010-08-12','2010-08-16',3);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 22, '2010-08-14','2010-08-16',4);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 21, '2010-08-19','2010-08-20',23);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 11, '2010-08-12','2010-08-13',21);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 12, '2010-08-15','2010-08-16',17);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 7, '2010-08-18','2010-08-20',14);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 22, '2010-08-19','2010-08-20',12);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 12, '2010-08-19','2010-08-20',13);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 28, '2010-08-19','2010-08-22',11);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 26, '2010-08-19','2010-08-20',8);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Sandrina'), 11, '2010-08-16','2010-08-17',20);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 16, '2010-08-13','2010-08-17',15);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 17, '2010-08-19','2010-08-20',1);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 16, '2010-08-13','2010-08-17',3);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 30, '2010-08-15','2010-08-16',11);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='La Rochelle'), 1, '2010-08-15','2010-08-16',4);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='La Rochelle'), 18, '2010-08-19','2010-08-20',4);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 18, '2010-08-13','2010-08-17',14);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 30, '2010-08-13','2010-08-14',10);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 30, '2010-08-15','2010-08-18',1);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 10, '2010-08-18','2010-08-20',7);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 27, '2010-08-20','2010-08-21',8);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 37, '2010-08-17','2010-08-18',9);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 26, '2010-08-13','2010-08-17',4);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 23, '2010-08-18','2010-08-21',3);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Ambassadeur'), 30, '2010-08-19','2010-08-20',2);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 19, current_date, null,1);
insert into reservations values ( (select numhotel from hotels where nomhotel='Hotel Ibis' and ville='Niort'), 20, current_date, null,2);
