package de.hsos.suchen.ui.suchen.view;

import java.util.ArrayList;
import de.hsos.suchen.bl.Ware;

public class SuchView {
    
    // text von sout per ai

    public void zeigeSuchmenue() {
        System.out.println("\n===== WAREN SUCHEN =====");
        System.out.println("1. Suche nach Warennummer");
        System.out.println("2. Suche nach Stichwort (kommt im nächsten Update!)");
        System.out.println("3. Alle Waren anzeigen");
        System.out.println("0. Zurück zum Suchen-Menü");
        System.out.println("===========================");
        System.out.print("Ihre Wahl: ");
    }
    
    public void zeigeWareDetail(Ware ware) {
        System.out.println("\n===== WAREN-DETAILS =====");
        System.out.println("ID: " + ware.getWarennummer());
        System.out.println("Name: " + ware.getName());
        System.out.println("Preis: " + ware.getPreis() + " €");
        
        if (ware.getBeschreibung() != null && !ware.getBeschreibung().isEmpty()) {
            System.out.println("Beschreibung: " + ware.getBeschreibung());
        }
        
        System.out.println("===========================");
    }
    
    public void zeigeWarenListe(ArrayList<Ware> waren) {
        System.out.println("\n===== WARENLISTE =====");
        System.out.println("Gefundene Waren: " + waren.size());
        
        for (int i = 0; i < waren.size(); i++) {
            Ware ware = waren.get(i);
            System.out.println((i + 1) + ". " + ware.getName() + " (ID: " + ware.getWarennummer() + ", Preis: " + ware.getPreis() + " €)");
        }
        
        System.out.println("===========================");
    }
    
    public void zeigeIndexauswahlInfo(int maxIndex) {
        System.out.println("\nBitte wählen Sie eine Ware aus (1-" + maxIndex + ") oder 0 für Abbruch:");
    }
    
    public void zeigeFehlermeldung(String nachricht) {
        System.out.println("\nFEHLER: " + nachricht);
    }
    
    public void zeigeErfolgsmeldung(String nachricht) {
        System.out.println("\nERFOLG: " + nachricht);
    }
    
    public void zeigeZurueckMeldung() {
        System.out.println("\nZurück zum Suchen-Menü.");
    }
}