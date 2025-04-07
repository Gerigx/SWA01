package de.hsos.katalobVerwaltung.ui.view;

/*
 * 
 * Ausgabetext ist AI generiert, Methoden wurden selbst überlegt
 * 
 */


public class KatalogVerwaltungStartView {
    

    public void zeigeHauptmenue() {
        System.out.println("\n===== KATALOGVERWALTUNG =====");
        System.out.println("1. Artikel hinzufügen");
        System.out.println("2. Artikel ändern");
        System.out.println("3. Artikel löschen");
        System.out.println("4. Artikel suchen");
        System.out.println("0. Beenden");
        System.out.println("===========================");
        System.out.print("Bitte wählen Sie eine Option: ");
    }
    

    public void zeigeFehlermeldung(String nachricht) {
        System.out.println("\nFEHLER: " + nachricht);
    }
    

    public void zeigeErfolgsmeldung(String nachricht) {
        System.out.println("\nERFOLG: " + nachricht);
    }

    public void zeigeBeendenMeldung() {
        System.out.println("\nVielen Dank für die Nutzung der Katalogverwaltung. Auf Wiedersehen!");
    }
}