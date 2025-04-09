package de.hsos.suchen.ui.view.view;

import de.hsos.suchen.ui.view.controller.MenueController;

public class MenueView {
    private MenueController menuController;
    
    public MenueView(MenueController menuController) {
        this.menuController = menuController;
    }
    
    // text von sout per ai

    public void startView() {
        boolean validInput = false;
        
        while (!validInput) {
            System.out.println("\n===== HAUPTMENÜ =====");
            System.out.println("1) Suchen");
            System.out.println("2) Warenkorb");
            System.out.println("3) Bezahlen");
            System.out.println("0) Anwendung beenden");
            System.out.println("======================");
            
            int input = menuController.readInteger("Ihre Wahl: ");
            
            switch (input) {
                case 1:
                    validInput = true;
                    menuController.visitSuchenStartView();
                    break;
                case 2:
                    validInput = true;
                    menuController.visitWarenkorbStartView();
                    break;
                case 3:
                    validInput = true;
                    menuController.visitBezahlenStartView();
                    break;
                case 0:
                    validInput = true;
                    menuController.exitApplication();
                    break;
                default:
                    System.out.println("\nFEHLER: Ungültige Eingabe. Bitte wählen Sie erneut.");
                    break;
            }
        }
    }
}