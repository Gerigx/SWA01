package de.hsos.suchen.ui.suchen.controller;

import java.util.ArrayList;
import java.util.Scanner;

import de.hsos.suchen.al.EinkaueferIn;
import de.hsos.suchen.bl.Ware;
import de.hsos.suchen.ui.suchen.view.SuchView;

public class SuchController {
    private SuchView view;
    private SuchenStartController mainController;
    private EinkaueferIn einkaueferIn;
    private Scanner scanner;

    public SuchController(SuchenStartController mainController, EinkaueferIn einkaueferIn) {
        this.mainController = mainController;
        this.einkaueferIn = einkaueferIn;
        this.view = new SuchView();
        this.scanner = new Scanner(System.in);
    }

    public void starteWarenSuche() {
        view.zeigeSuchmenue();
        
        int auswahl = leseInteger("Ihre Wahl: ");
        
        switch (auswahl) {
            case 1:
                sucheNachId();
                break;
            case 2:
                sucheNachStichwort();
                break;
            case 3:
                zeigeAlleWaren();
                break;
            case 0:
                view.zeigeZurueckMeldung();
                break;
            default:
                view.zeigeFehlermeldung("Ungültige Auswahl. Bitte versuchen Sie es erneut.");
                break;
        }
    }

    private void sucheNachId() {
        long warennummer = leseLong("Warennummer: ");
        
        Ware ware = einkaueferIn.sucheWare(warennummer);
        
        if (ware != null) {
            view.zeigeWareDetail(ware);
            
            boolean auswahl = leseJaNein("Möchten Sie diese Ware auswählen? (j/n): ");
            if (auswahl) {
                mainController.setAusgewaehlteWare(ware);
            }
        } else {
            view.zeigeFehlermeldung("Eine Ware mit der Warennummer " + warennummer + " wurde nicht gefunden.");
        }
    }

    private void sucheNachStichwort() {
        String stichwort = leseString("Suchbegriff: ");
        
        ArrayList<Ware> ergebnisse = einkaueferIn.sucheWaren(stichwort);
        
        if (ergebnisse.isEmpty()) {
            view.zeigeFehlermeldung("Keine Waren mit dem Suchbegriff '" + stichwort + "' gefunden.");
        } else {
            view.zeigeWarenListe(ergebnisse);
            
            boolean detailsAnzeigen = leseJaNein("Möchten Sie eine Ware auswählen? (j/n): ");
            
            if (detailsAnzeigen) {
                int indexWahl = leseIndexAuswahl(ergebnisse.size());
                
                if (indexWahl >= 0) {
                    Ware ausgewaehlteWare = ergebnisse.get(indexWahl);
                    mainController.setAusgewaehlteWare(ausgewaehlteWare);
                    view.zeigeWareDetail(ausgewaehlteWare);
                }
            }
        }
    }

    private void zeigeAlleWaren() {
        ArrayList<Ware> alleWaren = einkaueferIn.sucheAlleWaren();
        
        if (alleWaren.isEmpty()) {
            view.zeigeFehlermeldung("Es sind keine Waren im System vorhanden.");
        } else {
            view.zeigeWarenListe(alleWaren);
            
            boolean detailsAnzeigen = leseJaNein("Möchten Sie eine Ware auswählen? (j/n): ");
            
            if (detailsAnzeigen) {
                int indexWahl = leseIndexAuswahl(alleWaren.size());
                
                if (indexWahl >= 0) {
                    Ware ausgewaehlteWare = alleWaren.get(indexWahl);
                    mainController.setAusgewaehlteWare(ausgewaehlteWare);
                    view.zeigeWareDetail(ausgewaehlteWare);
                }
            }
        }
    }

    // helper von ai

    private String leseString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private long leseLong(String prompt) {
        long wert = 0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                wert = Long.parseLong(input);
                validInput = true;
            } catch (NumberFormatException e) {
                view.zeigeFehlermeldung("Bitte geben Sie eine gültige Zahl ein.");
            }
        }
        
        return wert;
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

    private boolean leseJaNein(String prompt) {
        while (true) {
            String input = leseString(prompt).toLowerCase();
            if (input.equals("j")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                view.zeigeFehlermeldung("Bitte geben Sie 'j' für Ja oder 'n' für Nein ein.");
            }
        }
    }

    private int leseIndexAuswahl(int maxIndex) {
        view.zeigeIndexauswahlInfo(maxIndex);
        
        while (true) {
            int index = leseInteger("Artikelnummer (1-" + maxIndex + ") oder 0 für Abbruch: ");
            
            if (index == 0) {
                return -1;
            } else if (index >= 1 && index <= maxIndex) {
                return index - 1; // Umrechnung auf 0-basierten Index
            } else {
                view.zeigeFehlermeldung("Bitte geben Sie eine Zahl zwischen 1 und " + maxIndex + " ein.");
            }
        }
    }
}