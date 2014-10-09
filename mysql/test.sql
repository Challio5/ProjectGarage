use garage;

insert into monteur
(naam, adres, postcode, woonplaats, TelNr, Wachtwoord, specialiteit, beschikbaarheid)
values 
("Henk", "Hoofdstraat 1", "7468CZ", "Enter", "06-12345678", "Henk123", "Banden", "M1"),
("Sjaak", "Dorpstraat 1", "7423HJ", "Rijssen","06-87654321", "Sjaak45", "Carroserie", "M1"),
("Jan", "Stationsstraat 1", "7323IK", "Rotterdam", "06-18273645", "JanJan", "Motor", "M2"),
("Tom", "Enterweg 10", "3453JI", "Bornebroek", "06-12387645", "Tom94", "Motor", "M2"),
("Rob", "Rijssenseweg 11", "2393JI", "Nijverdal", "06-09128365", "Moyboy88", "Carroserie", "V3");

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
(klantnr, werknemernr, kenteken, reparatie, duur, status)
values
(10000, 100, "12-31-AB", "Banden", 3.15, "bezig"),
(10002, 103, "10-ID-32", "Grote Beurt", 2.50, "klaar");

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

insert into factuurreparatie
(factuurnr, reparatienr)
values
(100, 1),
(100, 2),
(101, 1),
(102, 2);

