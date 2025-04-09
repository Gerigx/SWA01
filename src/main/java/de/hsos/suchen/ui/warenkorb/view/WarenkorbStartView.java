package de.hsos.suchen.ui.warenkorb.view;

import de.hsos.suchen.ui.view.controller.MenueController;

public class WarenkorbStartView {
    private MenueController menuController;
    
    public WarenkorbStartView(MenueController menuController) {
        this.menuController = menuController;
    }
    
    // text von souts per ai

    public void startView() {
        System.out.println("\n===== WARENKORB =====");
        System.out.println("Der Warenkorb ist derzeit leer.");
        System.out.println("=====================");
        
        boolean validInput = false;
        
        while (!validInput) {
            System.out.println("\n1) Weiter einkaufen");
            System.out.println("2) Zur Bezahlung");
            System.out.println("0) Zurück zum Hauptmenü");
            
            int input = menuController.readInteger("Ihre Wahl: ");
            
            switch (input) {
                case 1:
                    validInput = true;
                    menuController.visitSuchenStartView();
                    break;
                case 2:
                    validInput = true;
                    menuController.visitBezahlenStartView();
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