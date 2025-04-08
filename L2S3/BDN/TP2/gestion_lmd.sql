-- INSERT tauxhoraire

insert into tauxhoraire values (1, 20.15 );
insert into tauxhoraire values (2, 24.35 );
insert into tauxhoraire values (3, 30.12 );
insert into tauxhoraire values (4, 35.13 );

-- INSERT clients

insert into clients VALUES  ( 101,'Rivoire',null,'18 rue ronde','La Rochelle','17000','05-46-41-56-56');
insert into clients VALUES  ( 102,'Favero','André','43 rue Beaujolais','Poitiers','86000','05-49-35-63-62');
insert into clients VALUES  ( 103,'Provent','Catherine','38 rue du stade','Poitiers','86000','05-49-49-45-46');
insert into clients VALUES  ( 104,'Labric',null,'35 rue des fleurs','Poitiers','86000','05-49-46-45-48');
insert into clients VALUES  ( 105,'Dallalon','Jean','5 Rue Jean Moulin','La Rochelle','17000','05-46-35-37-39');
insert into clients VALUES  ( 108,'Usturritz','Noa','27 rue des gentilshommes','Poitiers','86000','05-49-46-45-52');
insert into clients VALUES  ( 109,'Lavalee','Amelia','14 Bd de Gaulle','Poitiers','86000','05-49-46-45-50');

-- INSERT factures

insert into factures values ( 1000,'2019-01-01','R');
insert into factures values ( 1001,'2019-02-12','R');
insert into factures values ( 1002,'2019-03-17','R');
insert into factures values ( 1003,'2019-04-24','R');
insert into factures values ( 1004,'2019-05-16','R');
insert into factures values ( 1005,'2019-07-08','R');
insert into factures values ( 1006,'2019-07-08','R');
insert into factures values ( 1007,'2019-07-15','R');
insert into factures values ( 1008,'2019-07-15','R');
insert into factures values ( 1009,'2019-07-22','C');
insert into factures values ( 1010,'2019-07-22','C');
insert into factures values ( 1011,'2019-07-29','C');
insert into factures values ( 1012,'2019-08-30','R');
insert into factures values ( 1013,'2019-10-19','R');

-- INSERT produits

insert into produits values ( 'DT010','Disjoncteur 10A',7.21,30,15);
insert into produits values ( 'DT180','Bloc Huger',6.12,40,15);
insert into produits values ( 'DT802','Boite controle',68.35,40,15);
insert into produits values ( 'DT711','cellule',25.36,40,15);
insert into produits values ( 'DT125','Bloc Soc',6.89,40,15);
insert into produits values ( 'DT015','Disjoncteur 15A',14.94,30,15);
insert into produits values ( 'DT205','Bruleur Huger',153.37,20,10);
insert into produits values ( 'DT310','bruleur soc',200.20,20,10);
insert into produits values ( 'DT120','Connecteur',20.35,40,15);
insert into produits values ( 'DT121','Connecteur1',40.25,40,15);
insert into produits values ( 'DT122','Connecteur2',34.35,40,15);

-- INSERT interventions

insert into  interventions values ( 1039,'2019-07-03','Mauras','Saultier',1,101,1001,1);
insert into  interventions values ( 1040,'2019-07-03','Foucher','Saultier',1,103,1002,1);
insert into  interventions values ( 1041,'2019-07-03','Foucher','Saultier',2,103,1002,1);
insert into  interventions values ( 1042,'2019-07-03','Foucher','Saultier',1,101,1003,1);
insert into  interventions values ( 1043,'2019-07-03','Mauras','Saultier',2,105,1005,1);
insert into  interventions values ( 1044,'2019-07-04','Mauras','Saultier',0.5,101,1006,1);
insert into  interventions values ( 1045,'2019-07-08','Mauras','Bonnaz',1.5,102,1007,3);
insert into  interventions values ( 1046,'2019-07-10','Foucher','Crespin',1,102,1007,2);
insert into  interventions values ( 1047,'2019-07-11','Mauras','Crespin',2,103,1008,2);
insert into  interventions values ( 1048,'2019-07-15','Foucher','Bonnaz',1,105,1009,3);
insert into  interventions values ( 1049,'2019-07-18','Foucher','Saultier',1.5,101,1010,1);
insert into  interventions values ( 1050,'2019-07-22','Foucher','Saultier',0.5,104,1011,1);
insert into  interventions values ( 1051,'2019-07-23','Mauras','Bonnaz',2.5,104,1011,3);
insert into  interventions values ( 1052,'2019-07-29','Mauras','Saultier',1.5,104,1011,1);

-- INSERT remplacements

insert into remplacements values ('DT802',1043,1);
insert into remplacements values ('DT711',1043,2);
insert into remplacements values ('DT180',1043,1);
insert into remplacements values ('DT205',1044,1);
insert into remplacements values ('DT125',1045,2);
insert into remplacements values ('DT010',1045,1);
insert into remplacements values ('DT310',1046,1);
insert into remplacements values ('DT711',1047,3);
insert into remplacements values ('DT120',1047,2);
insert into remplacements values ('DT015',1048,1);
insert into remplacements values ('DT180',1049,4);
insert into remplacements values ('DT711',1049,2);
insert into remplacements values ('DT205',1050,1);
insert into remplacements values ('DT711',1051,2);
insert into remplacements values ('DT120',1051,1);
insert into remplacements values ('DT120',1052,3);

