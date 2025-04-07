package de.hsos.suchen.dal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hsos.suchen.bl.Ware;
import java.sql.*;


public class KeywordMatching implements WarenSuche {
    
    @Override
    public List<Ware> sucheWare(String suchbegriff, List<Ware> alleWaren) {
        if (suchbegriff == null || suchbegriff.trim().isEmpty() || alleWaren == null) {
            return new ArrayList<>();
        }
        
        List<Ware> gefundeneWaren = new ArrayList<>();
        
        String suchbegriffLowerCase = suchbegriff.toLowerCase().trim();
        
        for (Ware ware : alleWaren) {
            if (ware.getName().toLowerCase().contains(suchbegriffLowerCase)) {
                gefundeneWaren.add(ware);
                continue;
            }
            
        }
        
        return gefundeneWaren;
    }
}
