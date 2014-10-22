use garage;

insert into klant
(naam, adres, postcode, woonplaats, telnr)
values
("Laurens", "Brinkstraat 37", "7468CZ", "Enter", "0548-123456"),
("Henk", "Dorpstraat 23", "7324IK", "Rijssen", "1111-987654"),
("Annie", "Bornebroekseweg 1", "8234OK", "Almelo", "06-42536475"),
("Martijn", "Oldenzaalsestraat 32", "2343KE", "Enschede", "9432-674391"); 

insert into auto
(kenteken, klantnr, merk, model, verzekeringsnr)
values
("12-31-AB", 10000, "Renault", "Megane", 23),
("34-KB-23", 10000, "Porsche", "Cayenne", 10),
("1-KDD-13", 10001, "Range Rover", "Sport", 42),
("10-ID-32", 10002, "Volvo", "XC90", 32),
("12-IKK-2", 10003, "Porsche", "Panamera", 324);

insert into monteur
(naam, adres, postcode, woonplaats, TelNr, Wachtwoord, specialiteit)
values 
("Henk", "Hoofdstraat 1", "7468CZ", "Enter", "06-12345678", "Henk123", "Banden"),
("Sjaak", "Dorpstraat 1", "7423HJ", "Rijssen","06-87654321", "Sjaak45", "Carroserie"),
("Jan", "Stationsstraat 1", "7323IK", "Rotterdam", "06-18273645", "JanJan", "Motor"),
("Tom", "Enterweg 10", "3453JI", "Bornebroek", "06-12387645", "Tom94", "Motor"),
("Rob", "Rijssenseweg 11", "2393JI", "Nijverdal", "06-09128365", "Moyboy88", "Carroserie");

insert into reparatie
(kenteken, omschrijving, begintijd, eindtijd, reparatiestatus, betaalstatus)
values
("12-31-AB", "Banden", '2014-10-30 10:55:12', '2014-10-30 15:05:12', false, false),
("10-ID-32", "Grote Beurt", '2014-10-03 13:46:44', '2014-10-03 15:23:01', true, false),
("12-IKK-2", "Carroserie", '2014-10-01 14:22:38', '2014-10-01 17:00:12', true, true);

insert into planning
(begintijd, eindtijd, werknemernr, reparatienr)
values
('2014-10-22 12:00:12', '2014-10-03 12:59:11', 100, 100),
('2014-10-23 12:00:12', '2014-10-03 12:59:11', 101, 101),
('2014-10-26 12:00:12', '2014-10-03 12:59:11', 103, 102);

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
(100, 1000),
(100, 1002),
(100, 1003);

insert into monteurbeschikbaarheid
(werknemernr, beschikbaarheid)
values
(100, "MO1"),
(100, "TU3"),
(100, "TH3"),
(101, "TU2"),
(101, "FR3"),
(102, "MO3"),
(102, "WE3"),
(102, "FR1");