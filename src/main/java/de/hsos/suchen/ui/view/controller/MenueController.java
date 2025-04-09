package de.hsos.suchen.ui.view.controller;

import java.util.Scanner;

import de.hsos.suchen.ui.bezahlen.view.BezahlenStartView;
import de.hsos.suchen.ui.suchen.controller.SuchenStartController;
import de.hsos.suchen.ui.view.view.BegruessungView;
import de.hsos.suchen.ui.view.view.MenueView;
import de.hsos.suchen.ui.warenkorb.view.WarenkorbStartView;

public class MenueController {
    // Singleton-Pattern
    private static MenueController instance;
    
    // Views
    private BegruessungView begruessungView;
    private MenueView menueView;
    private BezahlenStartView bezahlenStartView;
    private WarenkorbStartView warenkorbStartView;
    
    // Controller
    private SuchenStartController suchenStartController;
    
    // Scanner für Benutzereingabe
    private Scanner scanner;
    
    // Verhindert direkte Instanziierung von außen
    private MenueController() {
        this.begruessungView = new BegruessungView(this);
        this.menueView = new MenueView(this);
        this.bezahlenStartView = new BezahlenStartView(this);
        this.warenkorbStartView = new WarenkorbStartView(this);
        this.suchenStartController = new SuchenStartController(this);
        this.scanner = new Scanner(System.in);
    }
    
    // singleton
    public static synchronized MenueController getInstance() {
        if (instance == null) {
            instance = new MenueController();
        }
        return instance;
    }
    

    public void startApplication() {
        visitBegruessungView();
    }
    
    public void visitBegruessungView() {
        begruessungView.startView();
    }

    public void showMainMenu() {
        menueView.startView();
    }
    

    public void visitBezahlenStartView() {
        bezahlenStartView.startView();
    }
    

    public void visitWarenkorbStartView() {
        warenkorbStartView.startView();
    }

    public void visitSuchenStartView() {
        suchenStartController.start();
    }
    
    // helper sind per ai

    public int readInteger(String prompt) {
        int value = 0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                value = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("\nFEHLER: Bitte geben Sie eine Zahl ein.");
            }
        }
        
        return value;
    }
    

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    

    public void exitApplication() {
        System.out.println("\nDanke für die Nutzung der Anwendung. Auf Wiedersehen!");
        System.exit(0);
    }
}