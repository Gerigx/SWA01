package de.hsos.katalobVerwaltung.bl;

import de.hsos.katalobVerwaltung.dal.KatalogRepository;
import java.util.List;


public class KatalogverwaltungImpl implements Katalogverwaltung {
    
    private final KatalogRepository repository;
    

    public KatalogverwaltungImpl() {
        this.repository = new KatalogRepository();
    }
    

    public KatalogverwaltungImpl(KatalogRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean artikelHinzufuegen(Artikel artikel) {
        // Geschäftslogik-Validierung
        validierArtikel(artikel);
        
        // Delegieren an das Repository
        return repository.speichereArtikel(artikel);
    }

    @Override
    public boolean artikelAktualisieren(Artikel artikel) {
        // Prüfen, ob Artikel existiert
        if (artikel.getId() <= 0 || findArtikelById(artikel.getId()) == null) {
            return false;
        }
        
        // Geschäftslogik-Validierung
        validierArtikel(artikel);
        
        // Delegieren an das Repository
        return repository.aktualisiereArtikel(artikel);
    }

    @Override
    public boolean artikelLoeschen(int artikelId) {
        // Prüfen, ob Artikel existiert
        if (artikelId <= 0 || findArtikelById(artikelId) == null) {
            return false;
        }
        
        // Delegieren an das Repository
        return repository.loescheArtikel(artikelId);
    }

    @Override
    public Artikel findArtikelById(int artikelId) {
        if (artikelId <= 0) {
            return null;
        }
        
        // Delegieren an das Repository
        return repository.findeArtikelById(artikelId);
    }



    @Override
    public List<Artikel> getAlleArtikel() {
        // Delegieren an das Repository
        return repository.getAlleArtikel();
    }
    

    private void validierArtikel(Artikel artikel) {
        if (artikel == null) {
            throw new IllegalArgumentException("Artikel darf nicht null sein");
        }
        
        if (artikel.getName() == null || artikel.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Artikelname darf nicht leer sein");
        }
        
        if (artikel.getPreis() < 0) {
            throw new IllegalArgumentException("Artikelpreis darf nicht negativ sein");
        }
        
    }
}