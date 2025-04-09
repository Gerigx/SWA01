package de.hsos.suchen.ui.view.view;

import de.hsos.suchen.ui.view.controller.MenueController;

public class BegruessungView {
    private MenueController menuController;
    
    public BegruessungView(MenueController menuController) {
        this.menuController = menuController;
    }
    
    // text von sout per ai

    public void startView() {
        System.out.println("\n===== WILLKOMMEN IM SHOP =====");
        System.out.println("Hier können Sie Waren suchen, prüfen und kaufen.");
        
        boolean validInput = false;
        while (!validInput) {
            System.out.println("\n1) Weiter zum Hauptmenü");
            System.out.println("0) Anwendung beenden");
            
            int input = menuController.readInteger("Ihre Wahl: ");
            
            switch (input) {
                case 1:
                    validInput = true;
                    menuController.showMainMenu();
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