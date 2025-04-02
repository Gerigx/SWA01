package de.hsos.suchen.dal;

import java.util.List;

import de.hsos.suchen.bl.Ware;

public interface WarenSuche {
    List<Ware> sucheWare(String suchbegriff, List<Ware> alleWaren);
}
