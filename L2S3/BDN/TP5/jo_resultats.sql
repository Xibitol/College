--1-- Prénom et nom des athlètes français, par ordre alphabétique de nom
"prenom"	"nom"
"Manon"	"Apithy-Brunet"
"Sara"	"Balzer"
"Cassandre"	"Beaugrand"
"Pauline"	"FerrandPrévot"
"Labous"	"Juliette"
"Christophe"	"Laporte"
"Emma"	"Lombardi"
"Valentin"	"Madouas"
"Florent"	"Manaudou"
"Leon"	"Marchand"
"Teddy"	"Riner"

--2-- Prénom et nom des athlètes, par ordre alphabétique de nom, ayant remporté une médaille d'or
"prenom"	"nom"
"Manon"	"Apithy-Brunet"
"Cassandre"	"Beaugrand"
"Grace"	"Brown"
"Remco"	"Evenepoel"
"Remco"	"Evenepoel"
"Kristen"	"Faulkner"
"Pauline"	"FerrandPrévot"
"Lee"	"Kiefer"
"Leon"	"Marchand"
"Leon"	"Marchand"
"Cameron"	"McEvoy"
"Kaylee"	"McKeown"
"Summer"	"McKintosh"
"Teddy"	"Riner"

--3-- Prénom et Nom des athlètes français, par ordre alphabétique de nom, ayant remporté une médaille
"prenom"	"nom"
"Manon"	"Apithy-Brunet"
"Sara"	"Balzer"
"Cassandre"	"Beaugrand"
"Pauline"	"FerrandPrévot"
"Christophe"	"Laporte"
"Valentin"	"Madouas"
"Florent"	"Manaudou"
"Leon"	"Marchand"
"Leon"	"Marchand"
"Teddy"	"Riner"

--4-- Prénom, nom, date de naissance et pays de l'athlète le plus vieux
-- Proposez 3 solutions : avec LIMIT, avec ALL et avec MIN
"prenom"	"nom"	"datedenaissance"	"pays"
"Marianne"	"Vos"	"1987-05-13"	"NED"

--5-- Prénom, nom, AGE et pays de l'athlète le plus jeune
-- Nouveauté : utilisation de la fonction FLOOR
"prenom"	"nom"	"pays"	"age"
"Summer"	"McKintosh"	"AUS"	18

--6-- Prénom et nom des athlètes, par ordre alphabétique de nom, n'ayant remporté aucune médaille, 
-- dans aucune des disciplines auxquelles ils participent.
"prenom"	"nom"
"Labous"	"Juliette"
"Emma"	"Lombardi"
"Puck"	"Pieterse"
"Demi"	"Vollering"

--7-- Prénom, nom des athlètes, par ordre alphabétique de nom, et pays, ayant remporté plusieurs médailles
"prenom"	"nom"	"pays"
"Remco"	"Evenepoel"	"BEL"
"Leon"	"Marchand"	"FRA"
"Kaylee"	"McKeown"	"AUS"

--8-- Prénom, nom des athlètes, par ordre alphabétique de nom, et pays, ayant remporté plusieurs médailles d'or
"prenom"	"nom"	"pays"
"Remco"	"Evenepoel"	"BEL"
"Leon"	"Marchand"	"FRA"

--9-- Nombre de médailles d'or (alias NombreDeMedailles) par pays, par ordre décroissant de nombre de médailles
"paysnom"	"nombredemedailles"
"France"	6
"Australie"	4
"Belgique"	2
"États-Unis"	2

--10-- Pays ayant remporté une médaille dans chaque sport
-- On ne tiendra pas compte du fait qu'en Judo, le 4ième est aussi médaillé
"paysnom"
"France"

--11-- Prénom, nom des athlètes, par ordre alphabétique de nom, 
-- et nombre de médailles remportées (alias NombreDeMedailles)
-- y compris les athlètes n'ayant remporté aucune médaille
-- Nouveauté : utilisation de la clause CASE
"prenom"	"nom"	"nombredemedailles"
"Manon"	"Apithy-Brunet"	1
"Sara"	"Balzer"	1
"Haley"	"Batten"	1
"Cassandre"	"Beaugrand"	1
"Katharine"	"Berkoff"	1
"Grace"	"Brown"	1
"Julie"	"Derron"	1
"Kate"	"Douglas"	1
"Chloe"	"Dygert"	1
"Remco"	"Evenepoel"	2
"Kristen"	"Faulkner"	1
"Pauline"	"FerrandPrévot"	1
"Carson"	"Foster"	1
"Filippo"	"Ganna"	1
"Eleanor"	"Harvey"	1
"Anna"	"Henderson"	1
"Labous"	"Juliette"	0
"Olha"	"Kharlan"	1
"Ilya"	"Kharun"	1
"Lee"	"Kiefer"	1
"Lotte"	"Kopecky"	1
"Christophe"	"Laporte"	1
"Emma"	"Lombardi"	0
"Valentin"	"Madouas"	1
"Florent"	"Manaudou"	1
"Leon"	"Marchand"	2
"Tomoyuki"	"Matsushita"	1
"Cameron"	"McEvoy"	1
"Kaylee"	"McKeown"	2
"Summer"	"McKintosh"	1
"Kristof"	"Milak"	1
"Kim"	"Minjong"	1
"Puck"	"Pieterse"	0
"Beth"	"Potter"	1
"Benjamin"	"Proud"	1
"Temur"	"Rakhimov"	1
"Teddy"	"Riner"	1
"Jenny"	"Rissveds"	1
"Lauren"	"Scruggs"	1
"Regan"	"Smith"	1
"Wout"	"vanAert"	1
"Demi"	"Vollering"	0
"Marianne"	"Vos"	1
