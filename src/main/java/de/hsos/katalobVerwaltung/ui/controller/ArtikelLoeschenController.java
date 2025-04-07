package de.hsos.katalobVerwaltung.ui.controller;

import de.hsos.katalobVerwaltung.al.Interfaces.*;
import de.hsos.katalobVerwaltung.al.Impl.ArtikelLoeschenImpl;
import de.hsos.katalobVerwaltung.ui.view.ArtikelLoeschenView;

import java.util.Scanner;

/**
 * Controller zur Verwaltung des Artikel-Löschen-Dialogs
 */
public class ArtikelLoeschenController {
    private ArtikelLoeschenView view;
    private ArtikelLoeschen artikelLoeschen;
    private Scanner scanner;


    public ArtikelLoeschenController() {
        this.view = new ArtikelLoeschenView();
        this.artikelLoeschen = new ArtikelLoeschenImpl();
        this.scanner = new Scanner(System.in);
    }


    public void artikelLoeschen() {
        view.zeigeFormular();
        
        int artikelId = leseInteger("Artikel-ID: ");
        
        // early return, falls ware nicht im sys
        if (!artikelLoeschen.existiert(artikelId)) {
            view.zeigeFehlermeldung("Ein Artikel mit der ID " + artikelId + " existiert nicht.");
            return;
        }
        
        // sneidermannnnnnnnnnnnnnnn
        view.zeigeArtikelDaten(artikelLoeschen.getArtikel(artikelId));
        
        // Bestätigung einholen mit deutlicher Warnung
        view.zeigeLoeschWarnung();
        boolean bestaetigt = leseBestaetigung();
        
        if (bestaetigt) {
            boolean finalBestaetigung = leseFinaleBestaetigung(artikelId);
            
            if (finalBestaetigung) {
                boolean erfolgreich = artikelLoeschen.loeschen(artikelId);
                
                if (erfolgreich) {
                    view.zeigeErfolgsmeldung("Artikel wurde erfolgreich gelöscht.");
                } else {
                    view.zeigeFehlermeldung("Beim Löschen des Artikels ist ein Fehler aufgetreten.");
                }
            } else {
                view.zeigeAbbruchMeldung();
            }
        } else {
            view.zeigeAbbruchMeldung();
        }
    }


    // Helper Methoden - gemacht mit AI

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
            String input = leseString("Möchten Sie den Artikel wirklich löschen? (j/n): ").toLowerCase();
            if (input.equals("j")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                view.zeigeFehlermeldung("Bitte geben Sie 'j' für Ja oder 'n' für Nein ein.");
            }
        }
    }
    

    private boolean leseFinaleBestaetigung(int artikelId) {
        view.zeigeFinaleLoeschschrittWarnung(artikelId);
        
        try {
            int eingegebeneId = leseInteger("Artikel-ID zur Bestätigung: ");
            return eingegebeneId == artikelId;
        } catch (Exception e) {
            return false;
        }
    }
    

    private String leseString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}