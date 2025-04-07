package de.hsos.katalobVerwaltung.ui.controller;

import de.hsos.katalobVerwaltung.al.Interfaces.*;
import de.hsos.katalobVerwaltung.al.Impl.ArtikelSuchenImpl;
import de.hsos.katalobVerwaltung.ui.view.ArtikelSuchenView;
import de.hsos.katalobVerwaltung.bl.Artikel;

import java.util.List;
import java.util.Scanner;


public class ArtikelSuchenController {
    private ArtikelSuchenView view;
    private ArtikelSuchen artikelSuchen;
    private Scanner scanner;


    public ArtikelSuchenController() {
        this.view = new ArtikelSuchenView();
        this.artikelSuchen = new ArtikelSuchenImpl();
        this.scanner = new Scanner(System.in);
    }


    public void artikelSuchen() {
        view.zeigeSuchmenue();
        
        int auswahl = leseInteger("Ihre Wahl: ");
        
        switch (auswahl) {
            case 1:
                // Suche nach ID
                sucheNachId();
                break;
            case 3:
                // Alle Artikel anzeigen
                zeigeAlleArtikel();
                break;
            case 0:
                // Zurück zum Hauptmenü
                view.zeigeZurueckMeldung();
                break;
            default:
                view.zeigeFehlermeldung("Ungültige Auswahl. Bitte versuchen Sie es erneut.");
                break;
        }
    }


    private void sucheNachId() {
        int artikelId = leseInteger("Artikel-ID: ");
        
        Artikel artikel = artikelSuchen.findeNachId(artikelId);
        
        if (artikel != null) {
            view.zeigeArtikelDetail(artikel);
            
        } else {
            view.zeigeFehlermeldung("Ein Artikel mit der ID " + artikelId + " wurde nicht gefunden.");
        }
    }



    private void zeigeAlleArtikel() {
        List<Artikel> alleArtikel = artikelSuchen.findeAlle();
        
        if (alleArtikel.isEmpty()) {
            view.zeigeFehlermeldung("Es sind keine Artikel im System vorhanden.");
        } else {
            view.zeigeArtikelListe(alleArtikel);
            
            // Option, einen Artikel im Detail anzusehen
            view.zeigeDetailAuswahlOption();
            boolean detailsAnzeigen = leseBestaetigung();
            
            if (detailsAnzeigen && !alleArtikel.isEmpty()) {
                int indexWahl = leseIndexAuswahl(alleArtikel.size());
                
                if (indexWahl >= 0) {
                    Artikel ausgewaehlterArtikel = alleArtikel.get(indexWahl);
                    view.zeigeArtikelDetail(ausgewaehlterArtikel);

                }
            }
        }
    }


    // Helper Methoden - gemacht mit AI


    private String leseString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
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


    private boolean leseBestaetigung() {
        while (true) {
            String input = leseString("Möchten Sie fortfahren? (j/n): ").toLowerCase();
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
                return index - 1;
            } else {
                view.zeigeFehlermeldung("Bitte geben Sie eine Zahl zwischen 1 und " + maxIndex + " ein.");
            }
        }
    }
}