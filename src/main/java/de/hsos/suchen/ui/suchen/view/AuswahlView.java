package de.hsos.suchen.ui.suchen.view;

import de.hsos.suchen.bl.Ware;

public class AuswahlView {

    // text von sout per ai
    
    public void zeigeWare(Ware ware) {
        System.out.println("\n===== WARE AUSWÄHLEN =====");
        System.out.println("Sie haben folgende Ware ausgewählt:");
        System.out.println("ID: " + ware.getWarennummer());
        System.out.println("Name: " + ware.getName());
        System.out.println("Preis: " + ware.getPreis() + " €");
        
        if (ware.getBeschreibung() != null && !ware.getBeschreibung().isEmpty()) {
            System.out.println("Beschreibung: " + ware.getBeschreibung());
        }
        
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