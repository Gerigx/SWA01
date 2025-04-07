package de.hsos.katalobVerwaltung.ui.view;

import de.hsos.katalobVerwaltung.bl.Artikel;
import java.util.List;

/*
 * 
 * Ausgabetext ist AI generiert, Methoden wurden selbst überlegt
 * 
 */


public class ArtikelSuchenView {
    

    public void zeigeSuchmenue() {
        System.out.println("\n===== ARTIKEL SUCHEN =====");
        System.out.println("1. Suche nach ID");
        System.out.println("3. Alle Artikel anzeigen");
        System.out.println("0. Zurück zum Hauptmenü");
        System.out.println("===========================");
        System.out.print("Ihre Wahl: ");
    }
    

    public void zeigeArtikelDetail(Artikel artikel) {
        System.out.println("\n===== ARTIKEL DETAILS =====");
        System.out.println("ID: " + artikel.getId());
        System.out.println("Name: " + artikel.getName());
        System.out.println("Preis: " + artikel.getPreis());
        System.out.println("Beschreibung: " + artikel.getBeschreibung());
        System.out.println("===========================");
    }
    

    public void zeigeArtikelListe(List<Artikel> artikel) {
        System.out.println("\n===== ARTIKELLISTE =====");
        System.out.println("Gefundene Artikel: " + artikel.size());
        
        for (int i = 0; i < artikel.size(); i++) {
            Artikel a = artikel.get(i);
            System.out.println((i + 1) + ". " + a.getName() + " (ID: " + a.getId() + ", Preis: " + a.getPreis() + ")");
        }
        
        System.out.println("===========================");
    }
    

    public void zeigeDetailOptionen() {
        System.out.println("\nWas möchten Sie mit diesem Artikel tun?");
        System.out.println("1. Artikel ändern");
        System.out.println("2. Artikel löschen");
        System.out.println("0. Zurück zur Suche");
        System.out.print("Ihre Wahl: ");
    }
    

    public void zeigeDetailAuswahlOption() {
        System.out.println("\nMöchten Sie einen Artikel im Detail ansehen?");
    }
    

    public void zeigeIndexauswahlInfo(int maxIndex) {
        System.out.println("\nBitte wählen Sie einen Artikel aus (1-" + maxIndex + ") oder 0 für Abbruch:");
    }
    

    public void zeigeFehlermeldung(String nachricht) {
        System.out.println("\nFEHLER: " + nachricht);
    }
    

    public void zeigeZurueckMeldung() {
        System.out.println("\nZurück zum Hauptmenü.");
    }
}