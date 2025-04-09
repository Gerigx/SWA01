package de.hsos.suchen.ui.bezahlen.view;

import de.hsos.suchen.ui.view.controller.MenueController;

public class BezahlenStartView {
    private MenueController menuController;
    
    public BezahlenStartView(MenueController menuController) {
        this.menuController = menuController;
    }

    // souts von ai
    
    public void startView() {
        System.out.println("\n===== BEZAHLEN =====");
        System.out.println("Ihr Warenkorb ist leer. Es gibt nichts zu bezahlen.");
        System.out.println("====================");
        
        boolean validInput = false;
        
        while (!validInput) {
            System.out.println("\n1) Weiter einkaufen");
            System.out.println("0) Zurück zum Hauptmenü");
            
            int input = menuController.readInteger("Ihre Wahl: ");
            
            switch (input) {
                case 1:
                    validInput = true;
                    menuController.visitSuchenStartView();
                    break;
                case 0:
                    validInput = true;
                    menuController.showMainMenu();
                    break;
                default:
                    System.out.println("\nFEHLER: Ungültige Eingabe. Bitte wählen Sie erneut.");
                    break;
            }
        }
    }
}