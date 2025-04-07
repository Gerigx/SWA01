package de.hsos.katalobVerwaltung.al.Interfaces;

import de.hsos.katalobVerwaltung.bl.Artikel;

public interface ArtikelLoeschen {

    boolean existiert(int artikelId);

    Artikel getArtikel(int artikelId);

    boolean loeschen(int artikelId);

}
