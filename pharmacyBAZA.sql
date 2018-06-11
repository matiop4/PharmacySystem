-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 11 Cze 2018, 05:05
-- Wersja serwera: 10.1.13-MariaDB
-- Wersja PHP: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `pharmacy`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klient`
--

CREATE TABLE `klient` (
  `id_klienta` int(4) NOT NULL,
  `imie_klienta` varchar(125) NOT NULL,
  `nazwisko_klienta` varchar(125) NOT NULL,
  `kod_pocztowy_klienta` varchar(6) NOT NULL,
  `miejscowosc_klienta` varchar(125) NOT NULL,
  `adres_klienta` varchar(125) NOT NULL,
  `telefon_klienta` varchar(12) DEFAULT NULL,
  `numer_karty` varchar(12) DEFAULT NULL,
  `liczba_punktow` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `klient`
--

INSERT INTO `klient` (`id_klienta`, `imie_klienta`, `nazwisko_klienta`, `kod_pocztowy_klienta`, `miejscowosc_klienta`, `adres_klienta`, `telefon_klienta`, `numer_karty`, `liczba_punktow`) VALUES
(1, 'Mieszko', 'Warder', '12-569', 'Jezowe', 'ul.Polna 15', '789456123', '1245', NULL),
(2, 'Jan', 'Kowalski', '37-400', 'Nisko', 'ul. Rzeszowska 15', '222333444', NULL, 0),
(3, 'Mateusz', 'Dworak', '33-222', 'Budy Glgowoskie', 'Glogow Malopolski', '123422333', NULL, 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `placowka`
--

CREATE TABLE `placowka` (
  `id_placowki` int(4) NOT NULL,
  `telefon_placowki` int(12) NOT NULL,
  `kod_pocztowy_placowki` varchar(6) NOT NULL,
  `miejscowosc_placowki` varchar(255) NOT NULL,
  `adres_placowki` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `placowka`
--

INSERT INTO `placowka` (`id_placowki`, `telefon_placowki`, `kod_pocztowy_placowki`, `miejscowosc_placowki`, `adres_placowki`) VALUES
(1, 146520235, '44-163', 'Rzeszów', 'Lwowska 55'),
(2, 789456123, '44-963', 'Rzeszów', 'ul.Wojka Polskiego 30/28'),
(4, 852369742, '44-165', 'Rzeszów', '56');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pracownik`
--

CREATE TABLE `pracownik` (
  `id_pracownika` int(4) NOT NULL,
  `imie_pracownika` varchar(125) NOT NULL,
  `nazwisko_pracownika` varchar(125) NOT NULL,
  `telefon_pracownika` int(12) NOT NULL,
  `login` varchar(25) NOT NULL,
  `haslo` varchar(25) NOT NULL,
  `rola` varchar(50) NOT NULL,
  `id_placowki` int(4) NOT NULL,
  `status` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `pracownik`
--

INSERT INTO `pracownik` (`id_pracownika`, `imie_pracownika`, `nazwisko_pracownika`, `telefon_pracownika`, `login`, `haslo`, `rola`, `id_placowki`, `status`) VALUES
(2, 'Krzysztof', 'Krzysiek', 733589985, 'michal', 'krzysztof', 'sprzedawca', 1, 'aktywny'),
(3, 'Andrzej', 'Husarz', 124569789, 'lukas', 'has', 'sprzedawca', 1, 'aktywny'),
(4, 'Karolina', 'Kielbasa', 789456125, 'karolina', 'kielba', 'sprzedawca', 1, 'aktywny'),
(5, 'Mateusz', 'Holdas', 147852365, 'mateusz', 'holda', 'Menager', 2, 'aktywny'),
(6, 'Tomasz', 'Papek', 123456789, 'tomasz', 'popiela', 'Menager', 4, 'aktywny'),
(7, 'Artur', 'Polak', 741258963, 'artur', 'kawas', 'sprzedawca', 4, 'aktywny'),
(8, 'Administrator', 'Administrator', 999999999, 'admin', '123', 'admin', 2, 'aktywny'),
(9, 'Mateusz', 'Dworak', 733111222, 'mateusz', 'dworak', 'sprzedawca', 1, 'aktywny'),
(10, 'Kusy', 'Krystian', 999333999, 'kusy', 'krystian', 'Menager', 1, 'aktywny');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `produkty`
--

CREATE TABLE `produkty` (
  `id_produktu` int(4) NOT NULL,
  `nazwa_produktu` varchar(125) NOT NULL,
  `cena_produktu` float NOT NULL,
  `opis_produktu` varchar(255) NOT NULL,
  `ilosc` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `produkty`
--

INSERT INTO `produkty` (`id_produktu`, `nazwa_produktu`, `cena_produktu`, `opis_produktu`, `ilosc`) VALUES
(1, 'Rutinoscorbin', 5, 'Witaminy', 13),
(2, 'Syrop na kaszel', 20, 'Na kaszel', 35),
(3, 'Ibuprom', 5, 'Na bole', 11),
(4, 'Theraflu', 5, 'Na przeziebienie', 43),
(5, 'Apap', 10, 'Na bole', 44),
(6, 'Xenna', 11, 'Na sen', 12),
(7, 'Biotebal', 14, 'Na kaszel', 55),
(8, 'Nospa', 13, 'Na brzuch', 15);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `transakcja`
--

CREATE TABLE `transakcja` (
  `id_transakcji` int(4) NOT NULL,
  `id_pracownika` int(4) NOT NULL,
  `id_klienta` int(4) NOT NULL,
  `data` date NOT NULL,
  `calkowity_koszt` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `transakcja`
--

INSERT INTO `transakcja` (`id_transakcji`, `id_pracownika`, `id_klienta`, `data`, `calkowity_koszt`) VALUES
(1, 2, 1, '2018-05-17', 15),
(2, 2, 1, '2018-05-19', 785),
(3, 3, 1, '2018-05-17', 50),
(4, 3, 1, '2018-05-19', 45),
(5, 4, 1, '2018-05-17', 785),
(6, 7, 1, '2018-05-17', 7845),
(7, 8, 1, '2018-05-12', 78),
(8, 6, 1, '2018-05-19', 7845),
(9, 4, 1, '2018-05-24', 452),
(10, 8, 1, '2018-05-09', 5);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `klient`
--
ALTER TABLE `klient`
  ADD PRIMARY KEY (`id_klienta`);

--
-- Indexes for table `placowka`
--
ALTER TABLE `placowka`
  ADD PRIMARY KEY (`id_placowki`);

--
-- Indexes for table `pracownik`
--
ALTER TABLE `pracownik`
  ADD PRIMARY KEY (`id_pracownika`),
  ADD KEY `pracownik_placowka` (`id_placowki`);

--
-- Indexes for table `produkty`
--
ALTER TABLE `produkty`
  ADD PRIMARY KEY (`id_produktu`);

--
-- Indexes for table `transakcja`
--
ALTER TABLE `transakcja`
  ADD PRIMARY KEY (`id_transakcji`),
  ADD KEY `transakcja_klienta` (`id_klienta`),
  ADD KEY `transakcja_pracownik` (`id_pracownika`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `klient`
--
ALTER TABLE `klient`
  MODIFY `id_klienta` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT dla tabeli `placowka`
--
ALTER TABLE `placowka`
  MODIFY `id_placowki` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  MODIFY `id_pracownika` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT dla tabeli `produkty`
--
ALTER TABLE `produkty`
  MODIFY `id_produktu` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT dla tabeli `transakcja`
--
ALTER TABLE `transakcja`
  MODIFY `id_transakcji` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  ADD CONSTRAINT `pracownik_placowka` FOREIGN KEY (`id_placowki`) REFERENCES `placowka` (`id_placowki`);

--
-- Ograniczenia dla tabeli `transakcja`
--
ALTER TABLE `transakcja`
  ADD CONSTRAINT `transakcja_klienta` FOREIGN KEY (`id_klienta`) REFERENCES `klient` (`id_klienta`),
  ADD CONSTRAINT `transakcja_pracownik` FOREIGN KEY (`id_pracownika`) REFERENCES `pracownik` (`id_pracownika`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
