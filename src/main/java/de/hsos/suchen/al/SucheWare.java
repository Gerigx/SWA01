package de.hsos.suchen.al;

import java.util.ArrayList;

import de.hsos.suchen.bl.Ware;

public interface SucheWare {
        
    Ware sucheWare(long warennummer);
    

    ArrayList<Ware> sucheWaren(String suchbegriff);

}
