/*
														Auteur: Rohdri FRIMAT
														Date: 21/09/2016
*/

/*
					1 – Scripts SQL : Travail réalisé

					Insérer des villes
					Insérer des personnes en les liant à des villes
					Faire une requête renvoyant la liste des villes
					Faire une requête renvoyant la liste des personnes d’une ville
					Faire une requête renvoyant la liste des personnes avec le nom et le code postal de leurs villes, la trier par code postal
					Insérez une personne avec un id de ville inexistant, que se passe t'il ?
					Supprimer une ville ayant des personnes liées, que se passe t'il ?
					Comment rajouter une personne sans la lier à une ville ?
					En ayant des personnes avec et des personnes sans ville dans la base, faire une requête qui renvoie la liste des personnes avec les informations sur leurs villes (cf question e), que remarquez vous ?
					Ensuite faire une requête qui renvoie la liste de toutes les personnes avec les informations sur leur ville s’ils en ont une de liée

*/




-- CREATE DATABASE annu ;

-- GRANT ALL PRIVILEGES ON annu.* TO 'sa'@'localhost' IDENTIFIED BY 'sa';

-- USE annu;

/**/
DROP TABLE IF EXISTS person CASCADE;
DROP TABLE IF EXISTS city CASCADE;


CREATE TABLE city (

id_city INT NOT NULL AUTO_INCREMENT,

name VARCHAR (50),

mayor VARCHAR(100),

inhabitants INT,

postalcode INT(6),

PRIMARY KEY(id_city),

INDEX city_name (name)

) engine=innodb ;


CREATE TABLE person (

id_person INT NOT NULL AUTO_INCREMENT,

id_city INT,

firstname VARCHAR (50),

lastname VARCHAR (50),

emails VARCHAR(100),

phone VARCHAR(20),

PRIMARY KEY(id_person),

FOREIGN KEY person_city (id_city) references city (id_city) ON DELETE SET null,

INDEX person_lastname(lastname)

) engine=innodb;





/*
INSERT INTO city (name,mayor,inhabitants,postalcode) VALUES('Paris'    ,'Anne Hidalgo',12405000,75001);
INSERT INTO city (name,mayor,inhabitants,postalcode) VALUES('Lyon'     ,'Gérard Collomb',2237000,69000);
INSERT INTO city (name,mayor,inhabitants,postalcode) VALUES('Marseille','Jean-Claude Gaudin',1734000,130000);
INSERT INTO city (name,mayor,inhabitants,postalcode) VALUES('Toulouse' ,'Jean-Luc Moudenc',1291000,31000);



INSERT INTO person (id_city,firstname,lastname,emails,phone) VALUES(1,'Nicolas','HANG','nhang@nsis.com','000000000');
INSERT INTO person (id_city,firstname,lastname,emails,phone) VALUES(1,'François','HANG','fhang@nsis.com','1111111111');
INSERT INTO person (id_city,firstname,lastname,emails,phone) VALUES(2,'Frank','THIBAULT','fthibault@nsis.com','2222222222');
INSERT INTO person (id_city,firstname,lastname,emails,phone) VALUES(2,'Loic','GOLVIN','lgolvin@nsis.com','3333333333');
INSERT INTO person (id_city,firstname,lastname,emails,phone) VALUES(3,'Christophe','PITREY','@nsis.com','4444444444');
INSERT INTO person (id_city,firstname,lastname,emails,phone) VALUES(3,'Rohdri','FRIMAT','rfrimat@nsis.com','5555555555');
INSERT INTO person (id_city,firstname,lastname,emails,phone) VALUES(4,'Laurent','MERCIER','lmercier@nsis.com','6666666666');

*/





/*Faire une requête renvoyant la liste des villes*/
-- SELECT * FROM city;

/*Faire une requête renvoyant la liste des personnes d’une ville*/
-- SELECT p.firstname, p.lastname, c.name FROM person p JOIN city c ON p.id_city=c.id_city AND c.name='Paris'; 

/*Faire une requête renvoyant la liste des personnes avec le nom et le code postal de leurs villes, la trier par code postal*/
-- SELECT p.lastname, c.postalcode FROM person p JOIN city c ON p.id_city=c.id_city ORDER BY c.postalcode DESC;


/*Insérez une personne avec un id de ville inexistant, que se passe t'il ?*/
-- INSERT INTO person (firstname,lastname,emails,phone) VALUES('Inco','Nu','inu@nsis.com','77777');
-- SELECT * FROM person WHERE firstname="Inco";
-- Il ne se passe rien, la table person n'est pas configuere pour gérer les id_city non renseigné

/*Supprimer une ville ayant des personnes liées, que se passe t'il ?*/
-- DELETE FROM city WHERE id_city=1;
-- La ligne est supprimer

/*Comment rajouter une personne sans la lier à une ville ?*/
-- ?? Comme on l'a fait précédemment

/*En ayant des personnes avec et des personnes sans ville dans la base, faire une requête qui renvoie la liste des personnes avec les informations sur leurs villes (cf question e), que remarquez vous ?*/
SELECT p.firstname,c.name FROM person p LEFT JOIN city c ON p.id_city=c.id_city;
-- Certaine personne ont des champs NULL

/*Ensuite faire une requête qui renvoie la liste de toutes les personnes avec les informations sur leur ville s’ils en ont une de liée*/
SELECT p.firstname,c.name FROM person p JOIN city c ON p.id_city=c.id_city AND c.id_city IS NOT NULL;






























