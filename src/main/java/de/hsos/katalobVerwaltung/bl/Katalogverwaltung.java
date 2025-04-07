package de.hsos.katalobVerwaltung.bl;

import java.util.List;


public interface Katalogverwaltung {
    

    boolean artikelHinzufuegen(Artikel artikel);
    

    boolean artikelAktualisieren(Artikel artikel);
    

    boolean artikelLoeschen(int artikelId);
    

    Artikel findArtikelById(int artikelId);
    


    

    List<Artikel> getAlleArtikel();
}