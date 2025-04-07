package de.hsos.katalobVerwaltung.ui.view;

import de.hsos.katalobVerwaltung.bl.Artikel;

/*
 * 
 * Ausgabetext ist AI generiert, Methoden wurden selbst überlegt
 * 
 */

public class ArtikelAendernView {
    

    public void zeigeFormular() {
        System.out.println("\n===== ARTIKEL ÄNDERN =====");
        System.out.println("Bitte geben Sie die ID des zu ändernden Artikels ein:");
    }
    

    public void zeigeAktuelleArtikelDaten(Artikel artikel) {
        System.out.println("\n===== AKTUELLE ARTIKELDATEN =====");
        System.out.println("ID: " + artikel.getId());
        System.out.println("Name: " + artikel.getName());
        System.out.println("Preis: " + artikel.getPreis());
        System.out.println("Beschreibung: " + artikel.getBeschreibung());
        System.out.println("================================");
    }
    

    public void zeigeAenderungsFormular() {
        System.out.println("\nBitte geben Sie die neuen Daten ein (leere Eingabe = keine Änderung):");
    }
    

    public void zeigeDatenZurBestaetigung(int id, String name, double preis, String beschreibung) {
        System.out.println("\n===== NEUE ARTIKELDATEN =====");
        System.out.println("ID: " + id);
        
        if (!name.isEmpty()) {
            System.out.println("Name: " + name);
        } else {
            System.out.println("Name: <keine Änderung>");
        }
        
        if (preis >= 0) {
            System.out.println("Preis: " + preis);
        } else {
            System.out.println("Preis: <keine Änderung>");
        }
        
        if (!beschreibung.isEmpty()) {
            System.out.println("Beschreibung: " + beschreibung);
        } else {
            System.out.println("Beschreibung: <keine Änderung>");
        }
        
        System.out.println("============================");
    }
    

    public void zeigeFehlermeldung(String nachricht) {
        System.out.println("\nFEHLER: " + nachricht);
    }
    

    public void zeigeErfolgsmeldung(String nachricht) {
        System.out.println("\nERFOLG: " + nachricht);
    }
    

    public void zeigeAbbruchMeldung() {
        System.out.println("\nVorgang wurde abgebrochen.");
    }
}