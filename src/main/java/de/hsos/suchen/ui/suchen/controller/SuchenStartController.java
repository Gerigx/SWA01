package de.hsos.suchen.ui.suchen.controller;

import java.util.ArrayList;
import java.util.Scanner;

import de.hsos.suchen.al.EinkaueferIn;
import de.hsos.suchen.bl.Ware;
import de.hsos.suchen.ui.suchen.view.SuchenStartView;
import de.hsos.suchen.ui.view.controller.MenueController;

public class SuchenStartController {
    private SuchenStartView view;
    private SuchController suchController;
    private PruefController pruefController;
    private AuswahlController auswahlController;
    private MenueController menuController;
    private EinkaueferIn einkaueferIn;
    private Scanner scanner;
    private boolean running;
    
    private Ware ausgewaehlteWare;
    private ArrayList<Ware> gefundeneWaren;

    public SuchenStartController(MenueController menuController) {
        this.view = new SuchenStartView();
        this.einkaueferIn = new EinkaueferIn();
        this.suchController = new SuchController(this, einkaueferIn);
        this.pruefController = new PruefController(this, einkaueferIn);
        this.auswahlController = new AuswahlController(this, einkaueferIn);
        this.menuController = menuController;
        this.scanner = new Scanner(System.in);
        this.running = false;
        this.ausgewaehlteWare = null;
        this.gefundeneWaren = new ArrayList<>();
    }

    public void start() {
        this.running = true;
        while (running) {
            view.zeigeHauptmenue();
            int auswahl = leseBenutzerEingabe();
            verarbeiteAuswahl(auswahl);
        }
    }


    private void verarbeiteAuswahl(int auswahl) {
        switch (auswahl) {
            case 1:
                suchController.starteWarenSuche();
                break;
            case 2:
                if (ausgewaehlteWare != null) {
                    pruefController.pruefenWare(ausgewaehlteWare);
                } else {
                    view.zeigeFehlermeldung("Es wurde noch keine Ware ausgewählt. Bitte suchen Sie zuerst eine Ware.");
                }
                break;
            case 3:
                if (ausgewaehlteWare != null) {
                    auswahlController.waehleWare(ausgewaehlteWare);
                } else {
                    view.zeigeFehlermeldung("Es wurde noch keine Ware ausgewählt. Bitte suchen Sie zuerst eine Ware.");
                }
                break;
            case 4:
                zeigeWarenkorb();
                break;
            case 0:
                beenden();
                break;
            default:
                view.zeigeFehlermeldung("Ungültige Auswahl. Bitte versuchen Sie es erneut.");
                break;
        }
    }

    private void zeigeWarenkorb() {
        ArrayList<Ware> warenkorb = new ArrayList<>(einkaueferIn.getWarenkorb());
        view.zeigeWarenkorb(warenkorb);
    }

    private void beenden() {
        view.zeigeBeendenMeldung();
        this.running = false;
        menuController.showMainMenu();
    }

    public void setAusgewaehlteWare(Ware ware) {
        this.ausgewaehlteWare = ware;
        if (ware != null) {
            view.zeigeErfolgsmeldung("Ware '" + ware.getName() + "' wurde ausgewählt.");
        }
    }

    public Ware getAusgewaehlteWare() {
        return ausgewaehlteWare;
    }

    public void setGefundeneWaren(ArrayList<Ware> waren) {
        this.gefundeneWaren = waren;
    }

    public ArrayList<Ware> getGefundeneWaren() {
        return gefundeneWaren;
    }

    // helper

    private int leseBenutzerEingabe() {
        int eingabe = 0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                String input = scanner.nextLine().trim();
                eingabe = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                view.zeigeFehlermeldung("Bitte geben Sie eine Zahl ein.");
            }
        }
        
        return eingabe;
    }
}