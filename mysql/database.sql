DROP DATABASE IF EXISTS Garage;
CREATE DATABASE Garage;

USE Garage;

/* Tabel voor de gegevens van een klant */
CREATE TABLE IF NOT EXISTS Klant (
	Klantnr int not null auto_increment, 	
	Naam varchar(30) not null, 
	Adres varchar(30), 
	Postcode varchar(6), 
	Woonplaats varchar(30),
	Telnr varchar(11),
	PRIMARY KEY (Klantnr)	
);

ALTER TABLE Klant auto_increment = 10000;

/* Tabel voor de gegevens van een auto */
CREATE TABLE IF NOT EXISTS Auto(
	Kenteken varchar(8) not null,
	Klantnr int not null,
	Merk varchar(20), 
	Model varchar(20), 
	Verzekeringsnr int, 
	PRIMARY KEY (Kenteken),
	FOREIGN KEY (Klantnr) REFERENCES Klant(Klantnr)					/* Meerdere auto's voor 1 klant */
);

/* Tabel voor de gegevens van een monteur */
CREATE TABLE IF NOT EXISTS Monteur(
	Werknemernr int not null auto_increment, 
	Naam varchar(30) not null, 
	Adres varchar(30),
	Postcode varchar(6),
	Woonplaats varchar(30), 
    Telnr varchar(11),
    Wachtwoord varchar(16),
	Specialiteit varchar(30),    				
	PRIMARY KEY (Werknemernr)
);

ALTER TABLE Monteur auto_increment = 100;

/* Tabel voor de gegevens van een reparatieomschrijving */
CREATE TABLE IF NOT EXISTS Omschrijving(
	Omschrijvingsnr int not null auto_increment,
	Naam varchar(30),
	Duur time,
	PRIMARY KEY (Omschrijvingsnr)
);

/* Tabel voor de gegevens van een reparatie */
CREATE TABLE IF NOT EXISTS Reparatie(
	Reparatienr int not null auto_increment, 
	Kenteken varchar(8) not null, 										
	Omschrijvingsnr int not null,
	Begintijd datetime,
	Eindtijd datetime,
	Reparatiestatus boolean default false,
	Betaalstatus boolean default false,										
	PRIMARY KEY (Reparatienr), 
	FOREIGN KEY (Kenteken) REFERENCES Auto(Kenteken), 				/* Meerdere reparaties voor een auto */
	FOREIGN KEY (Omschrijvingsnr) REFERENCES Omschrijving(Omschrijvingsnr)
);

ALTER TABLE Reparatie auto_increment = 100;

/* Tabel voor de gegevens van een planning */
CREATE TABLE IF NOT EXISTS Planning(
	Begintijd datetime not null,
	Eindtijd datetime not null,
	Werknemernr int not null,
	Reparatienr int,
	PRIMARY KEY (BeginTijd, EindTijd, Werknemernr),
	FOREIGN KEY (Werknemernr) REFERENCES Monteur(Werknemernr),		/* Meerdere planningen voor een monteur */
	FOREIGN KEY (Reparatienr) REFERENCES Reparatie(Reparatienr) 	/* Planning voor een reparatie */
);

/* Tabel voor de gegevens van de materialen */
CREATE TABLE IF NOT EXISTS Materiaal(
	Materiaalnr int not null auto_increment, 
	Naam varchar(30), 
	Prijs double not null,
	PRIMARY KEY (Materiaalnr)
);

ALTER TABLE Materiaal auto_increment = 1000;

CREATE TABLE IF NOT EXISTS Voorraad(
	Materiaalnr int,
    Aantal int,
    PRIMARY KEY(Materiaalnr),
    FOREIGN KEY(Materiaalnr) references Materiaal(Materiaalnr)
    );

/* 
 * Koppelt meerdere materialen aan een reparatie 
 * Koppelt meerdere reparaties aan een materiaal
 */
CREATE TABLE IF NOT EXISTS ReparatieMateriaal(
	Reparatienr int not null,
	Materiaalnr int not null, 
    Aantalgebruikt int,
	PRIMARY KEY (Reparatienr, Materiaalnr), 
	FOREIGN KEY (Reparatienr) REFERENCES Reparatie(Reparatienr), 
	FOREIGN KEY (Materiaalnr) REFERENCES Materiaal(Materiaalnr)
);

/* 
 * Koppelt meerdere beschikbaarheidcodes aan een monteur 
 * Koppelt meerdere monteurs aan een beschikbaarheidcode
 */
CREATE TABLE IF NOT EXISTS MonteurBeschikbaarheid(
	Werknemernr int not null,
	Beschikbaarheid varchar(3) not null,
	PRIMARY KEY (Werknemernr, Beschikbaarheid),
	FOREIGN KEY (Werknemernr) REFERENCES Monteur(Werknemernr)
);