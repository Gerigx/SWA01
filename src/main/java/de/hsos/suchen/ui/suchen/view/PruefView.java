package de.hsos.suchen.ui.suchen.view;

import java.util.ArrayList;
import de.hsos.suchen.bl.Produktinformation;
import de.hsos.suchen.bl.Ware;

public class PruefView {
    
  // text von sout per ai

    public void zeigeWareDetail(Ware ware, ArrayList<Produktinformation> produktInfos) {
        System.out.println("\n===== WARENPRÜFUNG =====");
        System.out.println("ID: " + ware.getWarennummer());
        System.out.println("Name: " + ware.getName());
        System.out.println("Preis: " + ware.getPreis() + " €");
        
        if (ware.getBeschreibung() != null && !ware.getBeschreibung().isEmpty()) {
            System.out.println("Beschreibung: " + ware.getBeschreibung());
        }
        
        // Produktinformationen anzeigen
        if (produktInfos != null && !produktInfos.isEmpty()) {
            System.out.println("\nZusätzliche Produktinformationen:");
            
            for (Produktinformation info : produktInfos) {
                System.out.println("- " + info.getBezeichnung() + ": " + info.getInformation());
            }
        } else {
            System.out.println("\nKeine zusätzlichen Produktinformationen verfügbar.");
        }
        
        System.out.println("===========================");
    }
    
    public void zeigeOptionen() {
        System.out.println("\nWas möchten Sie tun?");
        System.out.println("1. Ware zum Warenkorb hinzufügen");
        System.out.println("0. Zurück zum Suchen-Menü");
        System.out.print("Ihre Wahl: ");
    }
    
    public void zeigeFehlermeldung(String nachricht) {
        System.out.println("\nFEHLER: " + nachricht);
    }
    
    public void zeigeErfolgsmeldung(String nachricht) {
        System.out.println("\nERFOLG: " + nachricht);
    }
}