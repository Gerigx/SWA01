package de.hsos.suchen.bl;

import java.util.Objects;

public class Produktinformation {
    private String bezeichnung;
    // Information ist richtig shit. es ist sooooo ungenau
    // todo: -ändern oder entfernen
    private Object information;

    public Produktinformation(String bezeichnung, Object information) {
        this.bezeichnung = bezeichnung;
        this.information = information;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public Object getInformation() {
        return information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produktinformation that = (Produktinformation) o;
        return Objects.equals(bezeichnung, that.bezeichnung) && 
               Objects.equals(information, that.information);
    }

}
