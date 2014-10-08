
CREATE DATABASE IF NOT EXISTS Garage;

USE Garage;

/* Tabel voor de gegevens van een klant */
CREATE TABLE IF NOT EXISTS Klanten (
	Klantnr int not null auto_increment, 	
	Naam varchar(30), 
	Adres varchar(30), 
	Postcode varchar(6), 
	Woonplaats varchar(30), 
	PRIMARY KEY (Klantnr)
);

ALTER TABLE Klanten auto_increment = 10000;

/* Tabel voor de gegevens van een monteur */
CREATE TABLE IF NOT EXISTS Monteurs(
	Werknemernr int not null auto_increment, 
	Naam varchar(30), 
	Adres varchar(30),
	Postcode varchar(6),
	Woonplaats varchar(30),  
	Specialiteit varchar(30), 
	Beschikbaarheid varchar(3),    				
	PRIMARY KEY (Werknemernr)
);

ALTER TABLE Monteurs auto_increment = 100;

/* Tabel voor de gegevens van een factuur */
CREATE TABLE IF NOT EXISTS Facturen(			
	Factuurnr int not null auto_increment, 
	Reparatienr int not null, 
	Klantnr int not null, 
	Werknemernr int not null, 
	Status boolean,
	PRIMARY KEY (Factuurnr, Reparatienr), 
	FOREIGN KEY (Klantnr) REFERENCES Klanten(Klantnr), 
	FOREIGN KEY (Werknemernr) REFERENCES Monteurs(Werknemernr)
);

ALTER TABLE Facturen auto_increment = 100;

/* Tabel voor de gegevens van de materialen */
CREATE TABLE IF NOT EXISTS Voorraad(
	Materiaalnr int not null auto_increment, 
	Naam varchar(30), 
	Prijs double not null, 
	Aantal int not null default 0, 
	PRIMARY KEY (Materiaalnr)
);

/* Tabel voor de gegevens van een reparatie */
CREATE TABLE IF NOT EXISTS Reparaties(
	Reparatienr int not null auto_increment, 
	Klantnr int not null, 
	Werknemernr int not null, 
	Kenteken varchar(8), 
	Reparatie varchar(30), 
	Beginttijd datetime, 
	Eindtijd datetime,
	Status varchar(10),
	PRIMARY KEY(Reparatienr), 
	FOREIGN KEY (Klantnr) REFERENCES Klanten(Klantnr), 
	FOREIGN KEY (Werknemernr) REFERENCES Monteurs(Werknemernr)
);

/* Tabel voor de gegevens van een auto */
CREATE TABLE IF NOT EXISTS Auto(
	Kenteken varchar(8), 
	Merk varchar(20), 
	Type varchar(20), 
	Verzekeringnr int, 
	PRIMARY KEY (Kenteken)
);

/* Koppelt meerdere auto's aan een klant */
CREATE TABLE IF NOT EXISTS AutoKlant(
	Klantnr int not null, 
	Kenteken varchar(8), 
	PRIMARY KEY (Klantnr, Kenteken), 
	FOREIGN KEY (Klantnr) REFERENCES Klanten(Klantnr), 
	FOREIGN KEY (Kenteken) REFERENCES Auto(Kenteken)
);

/* Koppelt meerdere materialen aan een reparatie */
CREATE TABLE IF NOT EXISTS ReparatieVoorraad(
	Reparatienr int not null,
	Materiaalnr int not null, 
	PRIMARY KEY (Reparatienr, Materiaalnr), 
	FOREIGN KEY (Reparatienr) REFERENCES Reparaties(Reparatienr), 
	FOREIGN KEY (Materiaalnr) REFERENCES Voorraad(Materiaalnr)
);

