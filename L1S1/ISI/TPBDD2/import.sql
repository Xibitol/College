-- creation de la base
-- DROP DATABASE IF EXISTS VideoLoc3000;
-- CREATE DATABASE VideoLoc3000;

-- creation des tables
DROP TABLE IF EXISTS `dbs12263976`.`Locations`;
DROP TABLE IF EXISTS `dbs12263976`.`Films`;
DROP TABLE IF EXISTS `dbs12263976`.`Clients`;

CREATE TABLE `dbs12263976`.`Films` ( `num` INT NOT NULL , `titre` VARCHAR(100) NOT NULL , `prix` DECIMAL(4,2) NOT NULL , PRIMARY KEY (`num`));
CREATE TABLE `dbs12263976`.`Locations`(`id_loc` INT NOT NULL, `num_film` INT, `num_client` INT,`date` DATE,PRIMARY KEY (`id_loc`), CONSTRAINT SYS_FK_78 FOREIGN KEY(`num_film`) REFERENCES `dbs12263976`.`Films`(`num`));
CREATE TABLE `dbs12263976`.`Clients`(`num` INT NOT NULL,`nom` VARCHAR(50),`ville` VARCHAR(50),`telephone` CHAR(10), PRIMARY KEY (`num`));

ALTER TABLE `dbs12263976`.`Locations` ADD CONSTRAINT SYS_FK_74 FOREIGN KEY(`num_client`) REFERENCES `dbs12263976`.`Clients`(`num`);

-- insertion du contenu
INSERT INTO `dbs12263976`.`Films` VALUES(10,'Monster Inc.',3.90);
INSERT INTO `dbs12263976`.`Films` VALUES(20,'Cars',4.90);
INSERT INTO `dbs12263976`.`Films` VALUES(30,'Finding Nemo',3.90);
INSERT INTO `dbs12263976`.`Films` VALUES(40,'The Incredibles',3.90);
INSERT INTO `dbs12263976`.`Films` VALUES(50,'Finding Dory',5.90);
INSERT INTO `dbs12263976`.`Films` VALUES(60,'The incredibles 2',7.90);

INSERT INTO `dbs12263976`.`Clients` VALUES(1,'Durand','La Rochelle','0504030201');
INSERT INTO `dbs12263976`.`Clients` VALUES(2,'Dupont','Aytre','0540302010');
INSERT INTO `dbs12263976`.`Clients` VALUES(3,'Dupond','Aytre',NULL);
INSERT INTO `dbs12263976`.`Clients` VALUES(4,'Dupuis','Angoulins','0506070809');

INSERT INTO `dbs12263976`.`Locations` VALUES(101,10,2,'2018-10-21');
INSERT INTO `dbs12263976`.`Locations` VALUES(102,20,2,'2018-10-23');
INSERT INTO `dbs12263976`.`Locations` VALUES(103,20,3,'2018-11-02');
INSERT INTO `dbs12263976`.`Locations` VALUES(104,40,4,'2018-11-02');
INSERT INTO `dbs12263976`.`Locations` VALUES(105,50,1,'2018-11-10');
INSERT INTO `dbs12263976`.`Locations` VALUES(106,30,2,'2018-11-11');
INSERT INTO `dbs12263976`.`Locations` VALUES(107,60,4,'2018-11-11');