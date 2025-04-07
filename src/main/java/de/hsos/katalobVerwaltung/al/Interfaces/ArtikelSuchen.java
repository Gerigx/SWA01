package de.hsos.katalobVerwaltung.al.Interfaces;

import java.util.List;

import de.hsos.katalobVerwaltung.bl.Artikel;

public interface ArtikelSuchen {

    Artikel findeNachId(int artikelId);



    List<Artikel> findeAlle();

}
