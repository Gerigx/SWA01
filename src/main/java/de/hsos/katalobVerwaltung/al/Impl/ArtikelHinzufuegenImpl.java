package de.hsos.katalobVerwaltung.al.Impl;

import de.hsos.katalobVerwaltung.al.Interfaces.ArtikelHinzufuegen;
import de.hsos.katalobVerwaltung.bl.Artikel;
import de.hsos.katalobVerwaltung.bl.Katalogverwaltung;
import de.hsos.katalobVerwaltung.bl.KatalogverwaltungImpl;

public class ArtikelHinzufuegenImpl implements ArtikelHinzufuegen {

    
    private final Katalogverwaltung katalogverwaltung;
    

    public ArtikelHinzufuegenImpl() {
        this.katalogverwaltung = new KatalogverwaltungImpl();
    }
    

    public ArtikelHinzufuegenImpl(Katalogverwaltung katalogverwaltung) {
        this.katalogverwaltung = katalogverwaltung;
    }

    @Override
    public boolean hinzufuegen(String name, double preis, String beschreibung) {
        try {
            // Erzeuge neues Artikel-Objekt
            Artikel artikel = new Artikel(name, preis, beschreibung);
            
            // Delegiere an Business Layer
            return katalogverwaltung.artikelHinzufuegen(artikel);
        } catch (IllegalArgumentException e) {
            // Fehler bei der Validierung
            System.err.println("Fehler beim Hinzufügen des Artikels: " + e.getMessage());
            return false;
        } catch (Exception e) {
            // Unerwarteter Fehler
            System.err.println("Unerwarteter Fehler beim Hinzufügen des Artikels: " + e.getMessage());
            return false;
        }
    }

}
