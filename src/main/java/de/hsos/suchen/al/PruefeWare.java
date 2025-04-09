package de.hsos.suchen.al;

import java.util.ArrayList;

import de.hsos.suchen.bl.Produktinformation;
import de.hsos.suchen.bl.Ware;

public interface PruefeWare {

    ArrayList<Produktinformation> holeDetailInformationen(Ware ware);
}
