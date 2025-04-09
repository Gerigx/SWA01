package de.hsos.suchen.al;

public interface WarenSubject {

        

    void registriereBeobachter(WarenBeobachter beobachter);
    

    void entferneBeobachter(WarenBeobachter beobachter);
    

    void benachrichtigeBeobachter(de.hsos.suchen.bl.Ware ware);

}
