package de.hsos.suchen.bl;

class Geld {
    private double betrag;
    private String waehrung;

    public Geld(double betrag, String waehrung) {
        this.betrag = betrag;
        this.waehrung = waehrung;
    }

    public double getBetrag() {
        return betrag;
    }

    public String getWaehrung() {
        return waehrung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Geld geld = (Geld) o;
        return Double.compare(geld.betrag, betrag) == 0 && waehrung.equals(geld.waehrung);
    }

}
