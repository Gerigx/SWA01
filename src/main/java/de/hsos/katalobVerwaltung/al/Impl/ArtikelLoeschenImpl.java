package de.hsos.katalobVerwaltung.al.Impl;

import de.hsos.katalobVerwaltung.al.Interfaces.ArtikelLoeschen;
import de.hsos.katalobVerwaltung.bl.Artikel;
import de.hsos.katalobVerwaltung.bl.Katalogverwaltung;
import de.hsos.katalobVerwaltung.bl.KatalogverwaltungImpl;

public class ArtikelLoeschenImpl implements ArtikelLoeschen {

    
    private final Katalogverwaltung katalogverwaltung;
    

    public ArtikelLoeschenImpl() {
        this.katalogverwaltung = new KatalogverwaltungImpl();
    }
    

    public ArtikelLoeschenImpl(Katalogverwaltung katalogverwaltung) {
        this.katalogverwaltung = katalogverwaltung;
    }

    @Override
    public boolean existiert(int id) {
        return katalogverwaltung.findArtikelById(id) != null;
    }

    @Override
    public Artikel getArtikel(int id) {
        return katalogverwaltung.findArtikelById(id);
    }

    @Override
    public boolean loeschen(int id) {
        try {
            // Direkt an die Business-Layer delegieren
            return katalogverwaltung.artikelLoeschen(id);
        } catch (Exception e) {
            // Unerwarteter Fehler
            System.err.println("Unerwarteter Fehler beim Löschen des Artikels: " + e.getMessage());
            return false;
        }
    }

}
