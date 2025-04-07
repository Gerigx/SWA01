package de.hsos;

import de.hsos.katalobVerwaltung.ui.controller.KatalogVerwaltungStartController;
import de.hsos.suchen.dal.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        System.out.println("Salut World");

        KatalogVerwaltungStartController kv = new KatalogVerwaltungStartController();

        kv.start();
    }
}