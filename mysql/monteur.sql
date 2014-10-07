insert into monteurs 
(naam, adres, postcode, woonplaats, specialiteit, beschikbaarheid)
values 
("Henk", "Hoofdstraat 1", "7468CZ", "Enter", "Banden", "M1"),
("Sjaak", "Dorpstraat 1", "7423HJ", "Rijssen", "Carroserie", "M1"),
("Jan", "Stationsstraat 1", "7323IK", "Rotterdam", "Motor", "M2"),
("Tom", "Enterweg 10", "3453JI", "Bornebroek", "Motor", "M2"),
("Rob", "Rijssenseweg 11", "2393JI", "Nijverdal", "Carroserie", "V3");

insert into klanten
(naam, adres, postcode, woonplaats)
values
("Laurens", "Brinkstraat 37", "7468CZ", "Enter"),
("Henk", "Dorpstraat 23", "7324IK", "Rijssen"),
("Annie", "Bornebroekseweg 1", "8234OK", "Almelo"),
("Martijn", "Oldenzaalsestraat 32", "2343KE", "Enschede"); 

insert into auto
(kenteken, merk, type, verzekeringnr)
values
("12-31-AB", "Renault", "Megane", 23),
("34-KB-23", "Porsche", "Cayenne", 10),
("1-KDD-13", "Range Rover", "Sport", 42),
("10-ID-32", "Volvo", "XC90", 32),
("12-IKK-21", "Porsche", "Panamera", 324);

insert into autoklant
(klantnr, kenteken)
values
(10000, "12-31-AB"),
(10001, "34-KB-23"),
(10001, "1-KDD-13"),
(10002, "10-ID-32");

insert into facturen
(reparatienr, klantnr, werknemernr, status)
values
(0, 10000, 100, 0),
(1, 10001, 101, 1),
(2, 10001, 102, 1);

insert into reparaties
(klantnr, werknemernr, kenteken, reparatie, begintijd, eindtijd, status)
values
(10000, 100, "12-31-AB", "Banden", 2014-10-07 13:33:00, 2014-10-07 15:33:00, "bezig");

