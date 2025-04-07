package de.hsos.katalobVerwaltung.ui.controller;

import de.hsos.katalobVerwaltung.al.Interfaces.*;
import de.hsos.katalobVerwaltung.al.Impl.ArtikelAendernImpl;
import de.hsos.katalobVerwaltung.ui.view.ArtikelAendernView;

import java.util.Scanner;


public class ArtikelAendernController {
    private ArtikelAendernView view;
    private ArtikelAendern artikelAendern;
    private Scanner scanner;


    public ArtikelAendernController() {
        this.view = new ArtikelAendernView();
        this.artikelAendern = new ArtikelAendernImpl();
        this.scanner = new Scanner(System.in);
    }


    public void artikelAendern() {
        view.zeigeFormular();
        
        // Artikel-ID einlesen
        int artikelId = leseInteger("Artikel-ID: ");
        
        // Prüfen, ob Artikel existiert
        if (!artikelAendern.existiert(artikelId)) {
            view.zeigeFehlermeldung("Ein Artikel mit der ID " + artikelId + " existiert nicht.");
            return;
        }
        
        // Aktuelle Daten anzeigen
        view.zeigeAktuelleArtikelDaten(artikelAendern.getArtikel(artikelId));
        
        // Neue Daten einlesen
        view.zeigeAenderungsFormular();
        String name = leseString("Neuer Name (leer lassen für keine Änderung): ");
        double preis = leseDoubleOrNegative("Neuer Preis (negative Zahl für keine Änderung): ");
        String beschreibung = leseString("Neue Beschreibung (leer lassen für keine Änderung): ");
        
        // Bestätigung einholen
        view.zeigeDatenZurBestaetigung(artikelId, name, preis, beschreibung);
        boolean bestaetigt = leseBestaetigung();
        
        if (bestaetigt) {
            // Artikel in der Datenbank aktualisieren
            boolean erfolgreich = artikelAendern.aendern(artikelId, name, preis, beschreibung);
            
            if (erfolgreich) {
                view.zeigeErfolgsmeldung("Artikel wurde erfolgreich aktualisiert.");
            } else {
                view.zeigeFehlermeldung("Beim Aktualisieren des Artikels ist ein Fehler aufgetreten.");
            }
        } else {
            view.zeigeAbbruchMeldung();
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


    private double leseDoubleOrNegative(String prompt) {
        double wert = -1.0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                
                if (input.isEmpty()) {
                    // Leere Eingabe bedeutet keine Änderung
                    return -1.0;
                }
                
                wert = Double.parseDouble(input);
                validInput = true;
            } catch (NumberFormatException e) {
                view.zeigeFehlermeldung("Bitte geben Sie eine gültige Zahl ein.");
            }
        }
        
        return wert;
    }


    private boolean leseBestaetigung() {
        while (true) {
            String input = leseString("Möchten Sie die Änderungen speichern? (j/n): ").toLowerCase();
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