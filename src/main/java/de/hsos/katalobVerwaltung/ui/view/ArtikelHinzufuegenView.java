package de.hsos.katalobVerwaltung.ui.view;

/*
 * 
 * Ausgabetext ist AI generiert, Methoden wurden selbst überlegt
 * 
 */


public class ArtikelHinzufuegenView {

    public void zeigeFormular() {
        System.out.println("\n===== ARTIKEL HINZUFÜGEN =====");
        System.out.println("Bitte geben Sie die Daten des neuen Artikels ein:");
    }
    

    public void zeigeDatenZurBestaetigung(String name, double preis, String beschreibung) {
        System.out.println("\n===== DATEN ÜBERPRÜFEN =====");
        System.out.println("Name: " + name);
        System.out.println("Preis: " + preis);
        System.out.println("Beschreibung: " + beschreibung);
        System.out.println("===========================");
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