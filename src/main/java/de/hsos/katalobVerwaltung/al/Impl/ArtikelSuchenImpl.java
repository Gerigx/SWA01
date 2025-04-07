package de.hsos.katalobVerwaltung.al.Impl;

import java.util.ArrayList;
import java.util.List;

import de.hsos.katalobVerwaltung.al.Interfaces.ArtikelSuchen;
import de.hsos.katalobVerwaltung.bl.Artikel;
import de.hsos.katalobVerwaltung.bl.Katalogverwaltung;
import de.hsos.katalobVerwaltung.bl.KatalogverwaltungImpl;

public class ArtikelSuchenImpl implements ArtikelSuchen {

    
    private final Katalogverwaltung katalogverwaltung;
    

    public ArtikelSuchenImpl() {
        this.katalogverwaltung = new KatalogverwaltungImpl();
    }
    

    public ArtikelSuchenImpl(Katalogverwaltung katalogverwaltung) {
        this.katalogverwaltung = katalogverwaltung;
    }

    @Override
    public Artikel findeNachId(int id) {
        try {
            return katalogverwaltung.findArtikelById(id);
        } catch (Exception e) {
            System.err.println("Fehler beim Suchen des Artikels: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Artikel> findeAlle() {
        try {
            return katalogverwaltung.getAlleArtikel();
        } catch (Exception e) {
            System.err.println("Fehler beim Abrufen aller Artikel: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
