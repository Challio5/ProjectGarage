use garage;

insert into monteur
(naam, adres, postcode, woonplaats, specialiteit, beschikbaarheid)
values 
("Henk", "Hoofdstraat 1", "7468CZ", "Enter", "Banden", "M1"),
("Sjaak", "Dorpstraat 1", "7423HJ", "Rijssen", "Carroserie", "M1"),
("Jan", "Stationsstraat 1", "7323IK", "Rotterdam", "Motor", "M2"),
("Tom", "Enterweg 10", "3453JI", "Bornebroek", "Motor", "M2"),
("Rob", "Rijssenseweg 11", "2393JI", "Nijverdal", "Carroserie", "V3");

insert into klant
(naam, adres, postcode, woonplaats)
values
("Laurens", "Brinkstraat 37", "7468CZ", "Enter"),
("Henk", "Dorpstraat 23", "7324IK", "Rijssen"),
("Annie", "Bornebroekseweg 1", "8234OK", "Almelo"),
("Martijn", "Oldenzaalsestraat 32", "2343KE", "Enschede"); 

insert into auto
(kenteken, merk, model, verzekeringnr)
values
("12-31-AB", "Renault", "Megane", 23),
("34-KB-23", "Porsche", "Cayenne", 10),
("1-KDD-13", "Range Rover", "Sport", 42),
("10-ID-32", "Volvo", "XC90", 32),
("12-IKK-2", "Porsche", "Panamera", 324);

insert into autoklant
(klantnr, kenteken)
values
(10000, "12-31-AB"),
(10001, "34-KB-23"),
(10001, "1-KDD-13"),
(10002, "10-ID-32");

insert into factuur
(reparatienr, klantnr, werknemernr, status)
values
(0, 10000, 100, 0),
(1, 10001, 101, 1),
(2, 10001, 102, 1);

insert into reparatie
(klantnr, werknemernr, kenteken, reparatie, begintijd, eindtijd, status)
values
(10000, 100, "12-31-AB", "Banden", '2014-10-07 13:33:00', '2014-10-07 15:33:00', "bezig"),
(10002, 103, "10-ID-32", "Grote Beurt", '2014-10-10 09:01:00', '2014-10-07 11:55:00', "klaar");

insert into voorraad
(naam, prijs, aantal)
values
("Knalpijp", 100, 3),
("Spruitstuk", 250, 5),
("Motorolie", 10, 30),
("Banden", 50, 25);

insert into reparatievoorraad
(reparatienr, materiaalnr)
values
(1, 1),
(1, 3),
(1, 4);
