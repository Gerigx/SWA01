package de.hsos.katalobVerwaltung.ui.view;

import de.hsos.katalobVerwaltung.bl.Artikel;

/*
 * 
 * Ausgabetext ist AI generiert, Methoden wurden selbst überlegt
 * 
 */


public class ArtikelLoeschenView {

    public void zeigeFormular() {
        System.out.println("\n===== ARTIKEL LÖSCHEN =====");
        System.out.println("Bitte geben Sie die ID des zu löschenden Artikels ein:");
    }

    public void zeigeArtikelDaten(Artikel artikel) {
        System.out.println("\n===== ZU LÖSCHENDER ARTIKEL =====");
        System.out.println("ID: " + artikel.getId());
        System.out.println("Name: " + artikel.getName());
        System.out.println("Preis: " + artikel.getPreis());
        System.out.println("Beschreibung: " + artikel.getBeschreibung());
        System.out.println("================================");
    }
    

    public void zeigeLoeschWarnung() {
        System.out.println("\nACHTUNG: Dieser Vorgang kann nicht rückgängig gemacht werden!");
    }
    

    public void zeigeFinaleLoeschschrittWarnung(int artikelId) {
        System.out.println("\n===== LETZTE WARNUNG =====");
        System.out.println("Sie sind dabei, den Artikel mit der ID " + artikelId + " unwiderruflich zu löschen.");
        System.out.println("Um fortzufahren, geben Sie bitte die Artikel-ID zur Bestätigung ein:");
    }
    

    public void zeigeFehlermeldung(String nachricht) {
        System.out.println("\nFEHLER: " + nachricht);
    }
    

    public void zeigeErfolgsmeldung(String nachricht) {
        System.out.println("\nERFOLG: " + nachricht);
    }
    

    public void zeigeAbbruchMeldung() {
        System.out.println("\nLöschvorgang wurde abgebrochen.");
    }
}