package de.hsos.katalobVerwaltung.ui.controller;

import de.hsos.katalobVerwaltung.ui.view.KatalogVerwaltungStartView;
import de.hsos.katalobVerwaltung.ui.controller.ArtikelHinzufuegenController;
import de.hsos.katalobVerwaltung.ui.controller.ArtikelAendernController;
import de.hsos.katalobVerwaltung.ui.controller.ArtikelLoeschenController;
import de.hsos.katalobVerwaltung.ui.controller.ArtikelSuchenController;

import java.util.Scanner;


public class KatalogVerwaltungStartController {
    private KatalogVerwaltungStartView view;
    private ArtikelHinzufuegenController hinzufuegenController;
    private ArtikelAendernController aendernController;
    private ArtikelLoeschenController loeschenController;
    private ArtikelSuchenController suchenController;
    private Scanner scanner;
    private boolean running;

    public KatalogVerwaltungStartController() {
        this.view = new KatalogVerwaltungStartView();
        this.hinzufuegenController = new ArtikelHinzufuegenController();
        this.aendernController = new ArtikelAendernController();
        this.loeschenController = new ArtikelLoeschenController();
        this.suchenController = new ArtikelSuchenController();
        this.scanner = new Scanner(System.in);
        this.running = false;
    }


    public void start() {
        this.running = true;
        while (running) {
            view.zeigeHauptmenue();
            int auswahl = leseBenutzerEingabe();
            verarbeiteAuswahl(auswahl);
        }
    }


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


    private void verarbeiteAuswahl(int auswahl) {
        switch (auswahl) {
            case 1:
                // Artikel hinzufügen
                hinzufuegenController.artikelHinzufuegen();
                break;
            case 2:
                // Artikel ändern
                aendernController.artikelAendern();
                break;
            case 3:
                // Artikel löschen
                loeschenController.artikelLoeschen();
                break;
            case 4:
                // Artikel suchen
                suchenController.artikelSuchen();
                break;
            case 0:
                // Beenden
                beenden();
                break;
            default:
                view.zeigeFehlermeldung("Ungültige Auswahl. Bitte versuchen Sie es erneut.");
                break;
        }
    }


    private void beenden() {
        view.zeigeBeendenMeldung();
        this.running = false;
        this.scanner.close();
    }
}