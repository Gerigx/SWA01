package de.hsos.suchen.ui.suchen.controller;

import java.util.ArrayList;
import java.util.Scanner;

import de.hsos.suchen.al.EinkaueferIn;
import de.hsos.suchen.bl.Produktinformation;
import de.hsos.suchen.bl.Ware;
import de.hsos.suchen.ui.suchen.view.PruefView;

public class PruefController {
    private PruefView view;
    private SuchenStartController mainController;
    private EinkaueferIn einkaueferIn;
    private Scanner scanner;
    private Ware aktuelleWare;

    public PruefController(SuchenStartController mainController, EinkaueferIn einkaueferIn) {
        this.mainController = mainController;
        this.einkaueferIn = einkaueferIn;
        this.view = new PruefView();
        this.scanner = new Scanner(System.in);
        this.aktuelleWare = null;
    }

    public void pruefenWare(Ware ware) {
        if (ware == null) {
            view.zeigeFehlermeldung("Keine Ware zum Prüfen ausgewählt.");
            return;
        }
        
        this.aktuelleWare = ware;
        
        ArrayList<Produktinformation> produktInfos = einkaueferIn.holeDetailInformationen(ware);
        
        view.zeigeWareDetail(ware, produktInfos);
        
        zeigeOptionen();
    }

    private void zeigeOptionen() {
        view.zeigeOptionen();
        
        int auswahl = leseInteger("Ihre Wahl: ");
        
        switch (auswahl) {
            case 1:
                boolean erfolg = einkaueferIn.wareZuWarenKorbHinzufügen(aktuelleWare);
                if (erfolg) {
                    view.zeigeErfolgsmeldung("Ware '" + aktuelleWare.getName() + "' wurde zum Warenkorb hinzugefügt.");
                } else {
                    view.zeigeFehlermeldung("Ware konnte nicht zum Warenkorb hinzugefügt werden.");
                }
                break;
            case 0:
                break;
            default:
                view.zeigeFehlermeldung("Ungültige Auswahl.");
                break;
        }
    }

    private int leseInteger(String prompt) {
        int wert = 0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                wert = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                view.zeigeFehlermeldung("Bitte geben Sie eine gültige Zahl ein.");
            }
        }
        
        return wert;
    }
}