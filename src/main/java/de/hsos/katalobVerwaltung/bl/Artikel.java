package de.hsos.katalobVerwaltung.bl;

import java.util.Objects;


public class Artikel {
    private int id;
    private String name;
    private double preis;
    private String beschreibung;
    

    public Artikel(String name, double preis, String beschreibung) {
        this.name = name;
        this.preis = preis;
        this.beschreibung = beschreibung;
    }

    public Artikel(int id, String name, double preis, String beschreibung) {
        this.id = id;
        this.name = name;
        this.preis = preis;
        this.beschreibung = beschreibung;
    }
    

    public int getId() {
        return id;
    }
    

    public void setId(int id) {
        this.id = id;
    }
    

    public String getName() {
        return name;
    }
    

    public void setName(String name) {
        // Geschäftsregel: Name darf nicht leer sein
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Der Name darf nicht leer sein");
        }
        this.name = name;
    }
    

    public double getPreis() {
        return preis;
    }
    

    public void setPreis(double preis) {
        // Geschäftsregel: Preis muss positiv sein
        if (preis < 0) {
            throw new IllegalArgumentException("Der Preis darf nicht negativ sein");
        }
        this.preis = preis;
    }
    

    public String getBeschreibung() {
        return beschreibung;
    }
    

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artikel artikel = (Artikel) o;
        return id == artikel.id;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    

    @Override
    public String toString() {
        return "Artikel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", preis=" + preis +
                ", beschreibung='" + beschreibung + '\'' +
                '}';
    }
}