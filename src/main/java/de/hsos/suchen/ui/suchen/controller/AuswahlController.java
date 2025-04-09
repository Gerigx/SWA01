package de.hsos.suchen.ui.suchen.controller;

import java.util.Scanner;

import de.hsos.suchen.al.EinkaueferIn;
import de.hsos.suchen.bl.Ware;
import de.hsos.suchen.ui.suchen.view.AuswahlView;

public class AuswahlController {
    private AuswahlView view;
    private SuchenStartController mainController;
    private EinkaueferIn einkaueferIn;
    private Scanner scanner;
    private Ware aktuelleWare;

    public AuswahlController(SuchenStartController mainController, EinkaueferIn einkaueferIn) {
        this.mainController = mainController;
        this.einkaueferIn = einkaueferIn;
        this.view = new AuswahlView();
        this.scanner = new Scanner(System.in);
        this.aktuelleWare = null;
    }

    public void waehleWare(Ware ware) {
        if (ware == null) {
            view.zeigeFehlermeldung("Keine Ware zur Auswahl verfügbar.");
            return;
        }
        
        this.aktuelleWare = ware;
        
        view.zeigeWare(ware);
        
        boolean bestaetigt = leseJaNein("Möchten Sie diese Ware zum Warenkorb hinzufügen? (j/n): ");
        
        if (bestaetigt) {
            boolean erfolg = einkaueferIn.wareZuWarenKorbHinzufügen(ware);
            
            if (erfolg) {
                view.zeigeErfolgsmeldung("Ware '" + ware.getName() + "' wurde zum Warenkorb hinzugefügt.");
                mainController.setAusgewaehlteWare(null); // Auswahl zurücksetzen
            } else {
                view.zeigeFehlermeldung("Ware konnte nicht zum Warenkorb hinzugefügt werden.");
            }
        } else {
            view.zeigeAbbruchMeldung();
        }
    }

    private boolean leseJaNein(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            
            if (input.equals("j")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                view.zeigeFehlermeldung("Bitte geben Sie 'j' für Ja oder 'n' für Nein ein.");
            }
        }
    }
}