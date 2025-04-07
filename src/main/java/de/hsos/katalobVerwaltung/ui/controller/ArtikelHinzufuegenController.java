package de.hsos.katalobVerwaltung.ui.controller;

import de.hsos.katalobVerwaltung.al.Interfaces.*;
import de.hsos.katalobVerwaltung.al.Impl.ArtikelHinzufuegenImpl;
import de.hsos.katalobVerwaltung.ui.view.ArtikelHinzufuegenView;

import java.util.Scanner;


public class ArtikelHinzufuegenController {
    private ArtikelHinzufuegenView view;
    private ArtikelHinzufuegen artikelHinzufuegen;
    private Scanner scanner;


    public ArtikelHinzufuegenController() {
        this.view = new ArtikelHinzufuegenView();
        this.artikelHinzufuegen = new ArtikelHinzufuegenImpl();
        this.scanner = new Scanner(System.in);
    }


    public void artikelHinzufuegen() {
        view.zeigeFormular();
        
        // Artikeldaten einlesen
        String name = leseString("Name: ");
        double preis = leseDouble("Preis: ");
        String beschreibung = leseString("Beschreibung: ");
        
        // Bestätigung einholen
        view.zeigeDatenZurBestaetigung(name, preis, beschreibung);
        boolean bestaetigt = leseBestaetigung();
        
        if (bestaetigt) {
            // Artikel zur Datenbank hinzufügen über Application Layer
            boolean erfolgreich = artikelHinzufuegen.hinzufuegen(name, preis, beschreibung);
            
            if (erfolgreich) {
                view.zeigeErfolgsmeldung("Artikel wurde erfolgreich hinzugefügt.");
            } else {
                view.zeigeFehlermeldung("Beim Hinzufügen des Artikels ist ein Fehler aufgetreten.");
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


    private double leseDouble(String prompt) {
        double wert = 0.0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
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
}