-- Mise à jour 2023-09-02
-- Compatible SQLITE
-- TP2 et TP3 OK

-- TABLE tauxhoraire

create table tauxhoraire(
	codetaux smallint not null,
	txhoraire float not null,
	check ( codetaux >0 )
);

-- TABLE clients

create table clients (
noclient integer not null check ( noclient > 100 ),
nom char(30) not null , 
prenom char(30),
adresse char(50) not null,
ville char(25) not null,
cp char(5) not null,
tel char(14) not null check ( tel like '__-__-__-__-__')
);

-- TABLE factures

create table factures(
 nofacture integer not null ,
 datefacture date not null ,
 etat char(1) not null default 'C' check ( etat in ('R','C'))
);

-- TABLE produits

create table produits (
 reference char(5) not null  check ( reference like 'DT___'),
 designation char(50) not null,
 prixht numeric (9,2) not null,
 qtestock smallint default 1,
 qtesecurite smallint default 0,
 check (qtestock >= qtesecurite)
 );

-- TABLE interventions

create table interventions ( 
	nointerv integer not null ,
	dateinterv date not null,
	nomresponsable char(30) not null,
	nominterv char(30) not null,
	temps float not null check ( temps !=0 AND  temps between 0 and 8),
	noclient integer not null ,
	nofacture integer not null,
	codetaux smallint not null default 1 check ( codetaux>0)
);

-- TABLE remplacements

create table remplacements (
	reference char(5) not null check ( reference like 'DT%'),
	nointerv integer not null,
	qteremplacee smallint
);
