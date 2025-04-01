package de.hsos.suchen.bl;



public class Ware {
    private long warennummer;
    private String name;
    private Geld preis;
    private String beschreibung;
    private Produktinformation produktinfo;

    public Ware(long warennummer, String name, Geld preis, String beschreibung) {
        this.warennummer = warennummer;
        this.name = name;
        this.preis = preis;
        this.beschreibung = beschreibung;
    }

    public long getWarennummer() {
        return warennummer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Geld getPreis() {
        return preis;
    }

    public void setPreis(Geld preis) {
        this.preis = preis;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Produktinformation getProduktinfo() {
        return produktinfo;
    }

    public void setProduktinfo(Produktinformation produktinfo) {
        this.produktinfo = produktinfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ware ware = (Ware) o;
        return warennummer == ware.warennummer;
    }
}
