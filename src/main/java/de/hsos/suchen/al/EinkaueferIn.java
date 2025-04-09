package de.hsos.suchen.al;

import java.util.ArrayList;
import java.util.List;

import de.hsos.suchen.bl.Katalog;
import de.hsos.suchen.bl.Produktinformation;
import de.hsos.suchen.bl.Ware;
import de.hsos.suchen.dal.WarenRepository;

public class EinkaueferIn {
    
    private Katalog katalog;
    private List<Ware> warenkorb;
    private List<WarenBeobachter> beobachter;
    
    public EinkaueferIn() {
        this.katalog = new WarenRepository();
        this.warenkorb = new ArrayList<>();
        this.beobachter = new ArrayList<>();
    }
    
    public EinkaueferIn(Katalog katalog) {
        this.katalog = katalog;
        this.warenkorb = new ArrayList<>();
        this.beobachter = new ArrayList<>();
    }
    
    // SucheWare-Methoden
    
    public Ware sucheWare(long warennummer) {
        try {
            return katalog.suchen(warennummer);
        } catch (Exception e) {
            System.err.println("Fehler beim Suchen der Ware: " + e.getMessage());
            return null;
        }
    }
    
    public Ware sucheWare(String name) {
        try {
            return katalog.suchen(name);
        } catch (Exception e) {
            System.err.println("Fehler beim Suchen der Ware: " + e.getMessage());
            return null;
        }
    }
    
    public ArrayList<Ware> sucheWaren(String suchbegriff) {
        if (suchbegriff == null || suchbegriff.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        try {
            ArrayList<Ware> ergebnisse = new ArrayList<>();
            
            // Da das Katalog-Interface nur eine Ware zurückgibt,
            // müssten wir für eine vollständige Suche das Repository direkt verwenden
            Ware gefundeneWare = katalog.suchen(suchbegriff);
            if (gefundeneWare != null) {
                ergebnisse.add(gefundeneWare);
            }
            
            // Wenn wir Zugriff auf das Repository haben, könnten wir mehr Ergebnisse bekommen
            if (katalog instanceof WarenRepository) {
                WarenRepository repo = (WarenRepository) katalog;
                // Liste aller Waren holen und nach Stichwort filtern
                for (Ware ware : repo.findAllWaren()) {
                    // Ware nicht doppelt hinzufügen
                    if (!ergebnisse.contains(ware) && 
                        (ware.getName().toLowerCase().contains(suchbegriff.toLowerCase()) || 
                         (ware.getBeschreibung() != null && ware.getBeschreibung().toLowerCase().contains(suchbegriff.toLowerCase())))) {
                        ergebnisse.add(ware);
                    }
                }
            }
            
            return ergebnisse;
        } catch (Exception e) {
            System.err.println("Fehler beim Suchen der Waren: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public ArrayList<Ware> sucheAlleWaren() {
        try {
            if (katalog instanceof WarenRepository) {
                WarenRepository repo = (WarenRepository) katalog;
                return new ArrayList<>(repo.findAllWaren());
            }
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Fehler beim Abrufen aller Waren: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    // PruefeWare-Methoden
    
    public ArrayList<Produktinformation> holeDetailInformationen(Ware ware) {
        if (ware == null) {
            return new ArrayList<>();
        }
        
        try {
            // Produktinformationen sammeln
            ArrayList<Produktinformation> infos = new ArrayList<>();
            
            // Wenn bereits eine Produktinformation vorhanden ist, diese hinzufügen
            if (ware.getProduktinfo() != null) {
                infos.add(ware.getProduktinfo());
            }
            
            // Weitere Informationen über die Ware anzeigen
            katalog.gebeProduktInformation(ware);
            
            return infos;
        } catch (Exception e) {
            System.err.println("Fehler beim Holen der Detailinformationen: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public boolean existiert(long id) {
        return sucheWare(id) != null;
    }
    
    public Ware getWare(long id) {
        return sucheWare(id);
    }
    
    // WaehleWare-Methoden
    
    public boolean wareZuWarenKorbHinzufügen(Ware ware) {
        if (ware == null) {
            return false;
        }
        
        try {
            warenkorb.add(ware);
            
            // Beobachter benachrichtigen
            for (WarenBeobachter beobachter : beobachter) {
                beobachter.wareWurdeAusgewaehlt(ware);
            }
            
            return true;
        } catch (Exception e) {
            System.err.println("Fehler beim Hinzufügen der Ware zum Warenkorb: " + e.getMessage());
            return false;
        }
    }
    
    // WarenSubject-Methoden
    
    public void registriereBeobachter(WarenBeobachter beobachter) {
        if (beobachter != null && !this.beobachter.contains(beobachter)) {
            this.beobachter.add(beobachter);
        }
    }
    
    public void entferneBeobachter(WarenBeobachter beobachter) {
        if (beobachter != null) {
            this.beobachter.remove(beobachter);
        }
    }
    
    // Warenkorb-Methoden
    
    public List<Ware> getWarenkorb() {
        return new ArrayList<>(warenkorb);
    }
}