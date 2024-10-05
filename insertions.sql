-- Insertion : UTILISATEUR
INSERT INTO UTILISATEUR VALUES (1);
INSERT INTO UTILISATEUR VALUES (2);
INSERT INTO UTILISATEUR VALUES (3);
INSERT INTO UTILISATEUR VALUES (4);
INSERT INTO UTILISATEUR VALUES (5);
INSERT INTO UTILISATEUR VALUES (6);
INSERT INTO UTILISATEUR VALUES (7);
INSERT INTO UTILISATEUR VALUES (9);
INSERT INTO UTILISATEUR VALUES (10);
INSERT INTO UTILISATEUR VALUES (11);
INSERT INTO UTILISATEUR VALUES (12);
INSERT INTO UTILISATEUR VALUES (13);
INSERT INTO UTILISATEUR VALUES (14);
INSERT INTO UTILISATEUR VALUES (15);

-- Insertion : MEMBRE
INSERT INTO MEMBRE VALUES ('user12@example.com',12, 'Riham','PDE','00 lapplacien st',0,0,'password12');
INSERT INTO MEMBRE VALUES ('user8@example.com',8, 'jesus','holy','145 Church st',0,0,'password8');
INSERT INTO MEMBRE VALUES ('user6@example.com',6, 'pierre','rock','11 Maths st',0,0,'password6');
INSERT INTO MEMBRE VALUES ('user5@example.com',5, 'Zakaria','Awesome','101 coolRich st',0,0,'password5');
INSERT INTO MEMBRE VALUES ('user4@example.com',4, 'micheal','Jackson','208 coolRich Blanche st',0,0,'password4');
INSERT INTO MEMBRE VALUES ('user1@example.com',1, 'Will','Smith','147 NYC st',140,0,'password1');
INSERT INTO MEMBRE VALUES ('user7@example.com',7,'jason','momoa','11 GameOfThrones st',0,0,'password7');
INSERT INTO MEMBRE VALUES ('user2@example.com',2,'Smith','jane','456 Oak St',1154,0,'password2');
INSERT INTO MEMBRE VALUES ('user3@example.com',3,'Malak','Charki','178 Agdal St',747,0,'password3');
INSERT INTO MEMBRE VALUES ('user9@example.com',9,'Mr','SQL','24 JDBC st',0,0,'password9');
INSERT INTO MEMBRE VALUES ('user10@example.com',10,'Youssef','ITALIANO','950 Capre st',0,0,'password10');
INSERT INTO MEMBRE VALUES ('user11@example.com',11,'smart','pants','59 pantalon st',0,0,'password11');
INSERT INTO MEMBRE VALUES ('user13@example.com',13,'ilias','Java','00 interface st',0,0,'password13');
INSERT INTO MEMBRE VALUES ('user14@example.com',14,'vorname','nachname','174 adresse st',0,0,'password14');
INSERT INTO MEMBRE VALUES ('user15@example.com',15,'out of','ideas','505 burnout  st',0,0,'password15');


-- Insertion : ADHERENT
INSERT INTO ADHERENT VALUES (1,3);
INSERT INTO ADHERENT VALUES (2,2);
INSERT INTO ADHERENT VALUES (3,5);
INSERT INTO ADHERENT VALUES (4,7);
INSERT INTO ADHERENT VALUES (5,9);
INSERT INTO ADHERENT VALUES (6,11);
INSERT INTO ADHERENT VALUES (7,13);
INSERT INTO ADHERENT VALUES (8,15);

-- Insertion : REFUGES
INSERT INTO REFUGES VALUES ('refuge1@example.com' , 'REFUGEA' , 'SecteurA' , TO_DATE('01-01-2023','DD-MM-YYYY'), TO_DATE('14-02-2025','DD-MM-YYYY'),3,5,20,'Welcome to Refuge A! Providing shelter and meals.');
INSERT INTO REFUGES VALUES ('refuge2@example.com' , 'REFUGEB' , 'SecteurB' , TO_DATE('01-02-2023','DD-MM-YYYY'), TO_DATE('10-06-2025','DD-MM-YYYY'),6,5,15,'Explore the beauty of Secteur B at Refuge B.');
INSERT INTO REFUGES VALUES ('refuge3@example.com' , 'REFUGEC' , 'SecteurC' , TO_DATE('15-08-2022','DD-MM-YYYY'), TO_DATE('20-08-2026','DD-MM-YYYY'),5,4,25,'Welcome to the best refuge in the whole world !!.');
INSERT INTO REFUGES VALUES ('refuge4@example.com' , 'REFUGED' , 'SecteurD' , TO_DATE('18-03-2022','DD-MM-YYYY'), TO_DATE('14-10-2024','DD-MM-YYYY'),6,5,34,'Marrakech refuge guides you through Moroccos beauty');
INSERT INTO REFUGES VALUES ('refuge5@example.com' , 'REFUGEE' , 'SecteurE' , TO_DATE('28-07-2021','DD-MM-YYYY'), TO_DATE('24-11-2026','DD-MM-YYYY'),3,4,58,'Welcome to VENICE , the best refuge in italy');
INSERT INTO REFUGES VALUES ('refuge6@example.com' , 'REFUGEF' , 'SecteurF' , TO_DATE('13-02-2022','DD-MM-YYYY'), TO_DATE('24-07-2025','DD-MM-YYYY'),4,4,28,'Welcome to LA JOCANDE , the refuge with the best rating in the france');


-- Insertion : TELEPHONE
INSERT INTO TELEPHONE VALUES ( '0687955123' , 'refuge1@example.com');
INSERT INTO TELEPHONE VALUES ( '0745879632' , 'refuge2@example.com');
INSERT INTO TELEPHONE VALUES ( '0611223344' , 'refuge4@example.com');
INSERT INTO TELEPHONE VALUES ( '0654455445' , 'refuge5@example.com');

-- Insertion : RESERVEREFUGE
INSERT INTO RESERVEREFUGE VALUES (3,5,TO_DATE(03-01-2024,'DD-MM-YYYY'),'12:00:00',1,0,58,'refuge5@example.com');
INSERT INTO RESERVEREFUGE VALUES (2,1,TO_DATE(01-01-2024,'DD-MM-YYYY'),'12:00:00',15,2,807,'refuge3@example.com');
INSERT INTO RESERVEREFUGE VALUES (3,4,TO_DATE(03-01-2024,'DD-MM-YYYY'),'12:00:00',2,3,287,'refuge5@example.com');
INSERT INTO RESERVEREFUGE VALUES (1,6,TO_DATE(01-03-2024,'DD-MM-YYYY'),'12:00:00',1,2,140,'refuge1@example.com');
INSERT INTO RESERVEREFUGE VALUES (2,2,TO_DATE(01-01-2024,'DD-MM-YYYY'),'12:00:00',2,3,287,'refuge5@example.com');
INSERT INTO RESERVEREFUGE VALUES (3,3,TO_DATE(03-01-2024,'DD-MM-YYYY'),'12:00:00',3,3,402,'refuge5@example.com');

-- Insertion : RESERVEFORMATION

-- Insertion : REPAS
INSERT INTO REPAS VALUES ('souper');
INSERT INTO REPAS VALUES ('dîner');
INSERT INTO REPAS VALUES ('déjeuner');
INSERT INTO REPAS VALUES ('casse-croûte à emporter');

-- Insertion : PAIEMENT
INSERT INTO UTILISATEUR VALUES ('CB');
INSERT INTO UTILISATEUR VALUES ('Chèque');
INSERT INTO UTILISATEUR VALUES ('Espèces');


-- Insertion : REFUGE_PAIEMENT
INSERT INTO REFUGE_PAIEMENT VALUES ('CB', 'refuge1@example.com');
INSERT INTO REFUGE_PAIEMENT VALUES ('CB', 'refuge2@example.com');
INSERT INTO REFUGE_PAIEMENT VALUES ('CB', 'refuge3@example.com');
INSERT INTO REFUGE_PAIEMENT VALUES ('CB', 'refuge4@example.com');
INSERT INTO REFUGE_PAIEMENT VALUES ('CB', 'refuge5@example.com');
INSERT INTO REFUGE_PAIEMENT VALUES ('CB', 'refuge6@example.com');
INSERT INTO REFUGE_PAIEMENT VALUES ('Chèque', 'refuge1@example.com');
INSERT INTO REFUGE_PAIEMENT VALUES ('Chèque', 'refuge2@example.com');
INSERT INTO REFUGE_PAIEMENT VALUES ('Chèque', 'refuge4@example.com');
INSERT INTO REFUGE_PAIEMENT VALUES ('Chèque', 'refuge5@example.com');
INSERT INTO REFUGE_PAIEMENT VALUES ('Espèces', 'refuge1@example.com');
INSERT INTO REFUGE_PAIEMENT VALUES ('Espèces', 'refuge2@example.com');
INSERT INTO REFUGE_PAIEMENT VALUES ('Espèces', 'refuge5@example.com');
INSERT INTO REFUGE_PAIEMENT VALUES ('Espèces', 'refuge6@example.com');

-- Insertion : PROPOSE_REPAS
INSERT INTO PROPOSE_REPAS VALUES ('refuge1@example.com', 'déjeuner',10);
INSERT INTO PROPOSE_REPAS VALUES ('refuge1@example.com', 'dîner',50);
INSERT INTO PROPOSE_REPAS VALUES ('refuge2@example.com', 'dîner',10);
INSERT INTO PROPOSE_REPAS VALUES ('refuge2@example.com', 'casse-croûte à emporter',60);
INSERT INTO PROPOSE_REPAS VALUES ('refuge2@example.com', 'déjeuner',20);
INSERT INTO PROPOSE_REPAS VALUES ('refuge3@example.com', 'déjeuner',14);
INSERT INTO PROPOSE_REPAS VALUES ('refuge3@example.com', 'souper',13);
INSERT INTO PROPOSE_REPAS VALUES ('refuge4@example.com', 'casse-croûte à emporter',12);
INSERT INTO PROPOSE_REPAS VALUES ('refuge4@example.com', 'déjeuner',15);
INSERT INTO PROPOSE_REPAS VALUES ('refuge4@example.com', 'souper',15);
INSERT INTO PROPOSE_REPAS VALUES ('refuge5@example.com', 'déjeuner',11);
INSERT INTO PROPOSE_REPAS VALUES ('refuge5@example.com', 'casse-croûte à emporter',21);
INSERT INTO PROPOSE_REPAS VALUES ('refuge5@example.com', 'déjeuner',21);


-- Insertions : ACTIVITE
INSERT INTO ACTIVITE VALUES ('Dance');
INSERT INTO ACTIVITE VALUES ('alpinisme');
INSERT INTO ACTIVITE VALUES ('basket');
INSERT INTO ACTIVITE VALUES ('cascade de glace');
INSERT INTO ACTIVITE VALUES ('escalade');
INSERT INTO ACTIVITE VALUES ('football');
INSERT INTO ACTIVITE VALUES ('randonne');
INSERT INTO ACTIVITE VALUES ('ski de rando');
INSERT INTO ACTIVITE VALUES ('speleologie');

-- Insertion : MATCAT
INSERT INTO MATCAT VALUES ('Category1');
INSERT INTO MATCAT VALUES ('Category2');
INSERT INTO MATCAT VALUES ('sous-categorie1');
INSERT INTO MATCAT VALUES ('sous-categorie2');
INSERT INTO MATCAT VALUES ('sous-categorie21');

-- Insertion : CATEGORIEMERE
INSERT INTO CATEGORIEMERE VALUES (sous_categorie1,Category1);
INSERT INTO CATEGORIEMERE VALUES (sous_categorie2,Category1);
INSERT INTO CATEGORIEMERE VALUES (sous_categorie21,sous_categorie2);

-- Insertion : FORMATION
INSERT INTO FORMATION VALUES (3,2022,'Yoga Basics',TO_DATE('12-08-2024','DD-MM-YYYY'),20,3,'Learn the basics of Yoga and chill',50);
INSERT INTO FORMATION VALUES (4,2022,'Parachute Basics',TO_DATE('30-12-2023','DD-MM-YYYY'),30,3,'Learn the basics of hiking in this 5-day course.',140);
INSERT INTO FORMATION VALUES (1,2023,'Hiking Basis',TO_DATE('20-03-2023','DD-MM-YYYY'),5,4,'Learn the basics of Yoga and chill',30);
INSERT INTO FORMATION VALUES (2,2022,'Skiing Basis',TO_DATE('08-12-2024','DD-MM-YYYY'),15,1,'Learn the basics of dancing in this 15-day course.',60);

--Insertion : FORMATIONACTIVITE
INSERT INTO FORMATIONACTIVITE VALUES ('Dance',2023,1);
INSERT INTO FORMATIONACTIVITE VALUES ('alpinisme',2023,1);
INSERT INTO FORMATIONACTIVITE VALUES ('Dance',2023,2);
INSERT INTO FORMATIONACTIVITE VALUES ('basket',2023,2);
INSERT INTO FORMATIONACTIVITE VALUES ('cascade de glace',2023,1);
INSERT INTO FORMATIONACTIVITE VALUES ('cascade de glace',2023,2);
INSERT INTO FORMATIONACTIVITE VALUES ('escalade',2023,1);
INSERT INTO FORMATIONACTIVITE VALUES ('escalade',2023,2);
INSERT INTO FORMATIONACTIVITE VALUES ('football',2023,2);
INSERT INTO FORMATIONACTIVITE VALUES ('randonne',2023,1);
INSERT INTO FORMATIONACTIVITE VALUES ('ski e rando',2023,1);
INSERT INTO FORMATIONACTIVITE VALUES ('ski de rando',2023,2);
INSERT INTO FORMATIONACTIVITE VALUES ('speleologie',2023,1);
INSERT INTO FORMATIONACTIVITE VALUES ('speleologie',2023,2);

-- Insertion : LOTMATERIEL
INSERT INTO LOMATERIEL VALUES ('BMW','SerieS',2023,20,50,'Category1');
INSERT INTO LOMATERIEL VALUES ('BrandX','ModelA',2023,50,200,'Category1');
INSERT INTO LOMATERIEL VALUES ('BrandY','ModelB',2023,30,150,'Category2');
INSERT INTO LOMATERIEL VALUES ('Brandriham','ModelB',2023,30,150,'sous_categorie21');
INSERT INTO LOMATERIEL VALUES ('Brandzakaria','ModelB',2023,30,150,'sous_categorie2');

-- Insertion : INFOMATERIEL
INSERT INTO INFOMATERIEL VALUES ('Info1','BrandX','ModelA',2023);
INSERT INTO INFOMATERIEL VALUES ('Info2','BrandX','ModelA',2023);
INSERT INTO INFOMATERIEL VALUES ('Info3','BrandY','ModelB',2023);

-- Insertion : LOCATIONS
INSERT INTO LOCATIONS VALUES (1,20,TO_DATE('15-03-2023','DD-MM-YYYY'),TO_DATE('20-03-2023','DD-MM-YYYY'),2,'BrandX','ModelA',2023);
INSERT INTO LOCATIONS VALUES (3,20,TO_DATE('20-12-2023','DD-MM-YYYY'),TO_DATE('30-12-2023','DD-MM-YYYY'),2,'BrandX','ModelA',2023,1);
INSERT INTO LOCATIONS VALUES (2,20,TO_DATE('20-12-2023','DD-MM-YYYY'),TO_DATE('30-12-2023','DD-MM-YYYY'),5,'Brandriham','ModelB',2023,2);
INSERT INTO LOCATIONS VALUES (4,10,TO_DATE('01-01-2024','DD-MM-YYYY'),TO_DATE('05-01-2024','DD-MM-YYYY'),2,'Brandzakaria','ModelB',2023,2);
INSERT INTO LOCATIONS VALUES (5,20,TO_DATE('02-01-2024','DD-MM-YYYY'),TO_DATE('06-01-2024','DD-MM-YYYY'),2,'Brandzakaria','ModelB',2023,2);


-- Insertion : MATERIELPERISSABLE
INSERT INTO MATERIELPERISSABLE VALUES ('BrandX','ModelA',2023,TO_DATE('01-06-2023','DD-MM-YYYY'));
INSERT INTO MATERIELPERISSABLE VALUES ('BrandY','ModelB',2023,TO_DATE('10-12-2023','DD-MM-YYYY'));

-- Insertion : MATUTILACTIV
INSERT INTO MATUTILACTIV VALUES ('alpinisme','BMW','SerieS',2017);
INSERT INTO MATUTILACTIV VALUES ('escalade','BrandY','ModelB',2023);
INSERT INTO MATUTILACTIV VALUES ('randonne','BrandX','ModelA',2023);
INSERT INTO MATUTILACTIV VALUES ('randonne','BrandY','ModelB',2023);









