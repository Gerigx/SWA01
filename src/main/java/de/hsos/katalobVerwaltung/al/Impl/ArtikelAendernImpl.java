package de.hsos.katalobVerwaltung.al.Impl;

import de.hsos.katalobVerwaltung.al.Interfaces.ArtikelAendern;
import de.hsos.katalobVerwaltung.bl.Artikel;
import de.hsos.katalobVerwaltung.bl.Katalogverwaltung;
import de.hsos.katalobVerwaltung.bl.KatalogverwaltungImpl;

public class ArtikelAendernImpl implements ArtikelAendern {
    
    private final Katalogverwaltung katalogverwaltung;
    

    public ArtikelAendernImpl() {
        this.katalogverwaltung = new KatalogverwaltungImpl();
    }
    

    public ArtikelAendernImpl(Katalogverwaltung katalogverwaltung) {
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
    public boolean aendern(int id, String name, double preis, String beschreibung) {
        try {
            // Prüfen, ob Artikel existiert
            Artikel artikel = katalogverwaltung.findArtikelById(id);
            if (artikel == null) {
                return false;
            }
            
            // Nur Werte aktualisieren, die geändert werden sollen
            if (name != null && !name.trim().isEmpty()) {
                artikel.setName(name);
            }
            
            if (preis >= 0) {
                artikel.setPreis(preis);
            }
            
            if (beschreibung != null) {  // Leere Beschreibung ist erlaubt
                artikel.setBeschreibung(beschreibung);
            }
            
            // Delegiere an Business Layer
            return katalogverwaltung.artikelAktualisieren(artikel);
        } catch (IllegalArgumentException e) {
            // Fehler bei der Validierung
            System.err.println("Fehler beim Ändern des Artikels: " + e.getMessage());
            return false;
        } catch (Exception e) {
            // Unerwarteter Fehler
            System.err.println("Unerwarteter Fehler beim Ändern des Artikels: " + e.getMessage());
            return false;
        }
    }
}
