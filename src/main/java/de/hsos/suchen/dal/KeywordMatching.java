package de.hsos.suchen.dal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hsos.suchen.bl.Ware;
import java.sql.*;


public class KeywordMatching implements WarenSuche {

    
        private final WarenRepository warenRepository;
    
        public KeywordMatching(WarenRepository warenRepository) {
            this.warenRepository = warenRepository;
        }
        
        @Override
        public List<Ware> sucheWare(String suchbegriff) {
            if (suchbegriff == null || suchbegriff.trim().isEmpty()) {
                return new ArrayList<>();
            }
            
            // Holen aller Waren aus dem Repository
            List<Ware> alleWaren = warenRepository.findAllWaren();
            List<Ware> gefundeneWaren = new ArrayList<>();
            
            // Einfache Keyword-Suche im Arbeitsspeicher
            String suchbegriffLowerCase = suchbegriff.toLowerCase().trim();
            
            for (Ware ware : alleWaren) {
                // Suche im Namen
                if (ware.getName().toLowerCase().contains(suchbegriffLowerCase)) {
                    gefundeneWaren.add(ware);
                    continue;
                }
                
                // Suche in der Beschreibung
                if (ware.getBeschreibung().toLowerCase().contains(suchbegriffLowerCase)) {
                    gefundeneWaren.add(ware);
                }
            }
            
            return gefundeneWaren;
        }

}
