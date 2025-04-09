package de.hsos.suchen.ui.suchen.view;

import java.util.ArrayList;
import de.hsos.suchen.bl.Ware;

public class SuchenStartView {
    
     // text von sout per ai

    public void zeigeHauptmenue() {
        System.out.println("\n===== SUCHEN-MODUL =====");
        System.out.println("1. Waren suchen");
        System.out.println("2. Ausgewählte Ware prüfen");
        System.out.println("3. Ausgewählte Ware zum Warenkorb hinzufügen");
        System.out.println("4. Warenkorb anzeigen");
        System.out.println("0. Zurück zum Hauptmenü");
        System.out.println("===========================");
        System.out.print("Bitte wählen Sie eine Option: ");
    }
    
    public void zeigeFehlermeldung(String nachricht) {
        System.out.println("\nFEHLER: " + nachricht);
    }
    
    public void zeigeErfolgsmeldung(String nachricht) {
        System.out.println("\nERFOLG: " + nachricht);
    }
    
    public void zeigeBeendenMeldung() {
        System.out.println("\nSie kehren zum Hauptmenü zurück.");
    }
    
    public void zeigeWarenkorb(ArrayList<Ware> warenkorb) {
        System.out.println("\n===== WARENKORB =====");
        
        if (warenkorb.isEmpty()) {
            System.out.println("Der Warenkorb ist leer.");
        } else {
            System.out.println("Anzahl der Artikel im Warenkorb: " + warenkorb.size());
            
            for (int i = 0; i < warenkorb.size(); i++) {
                Ware ware = warenkorb.get(i);
                System.out.println((i + 1) + ". " + ware.getName() + " (ID: " + ware.getWarennummer() + ", Preis: " + ware.getPreis() + " €)");
            }
        }
        
        System.out.println("===========================");
        System.out.println("Drücken Sie Enter, um fortzufahren...");
        new java.util.Scanner(System.in).nextLine();
    }
}