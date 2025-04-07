package de.hsos.katalobVerwaltung.al.Interfaces;

import de.hsos.katalobVerwaltung.bl.Artikel;

public interface ArtikelAendern {

    boolean existiert(int artikelId);

    Artikel getArtikel(int artikelId);

    boolean aendern(int artikelId, String name, double preis, String beschreibung);

}
