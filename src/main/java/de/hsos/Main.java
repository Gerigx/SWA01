package de.hsos;

import de.hsos.katalobVerwaltung.ui.controller.KatalogVerwaltungStartController;
import de.hsos.suchen.dal.DatabaseConnection;
import de.hsos.suchen.ui.suchen.controller.SuchenStartController;
import de.hsos.suchen.ui.suchen.view.SuchenStartView;
import de.hsos.suchen.ui.view.controller.MenueController;
import de.hsos.suchen.ui.view.view.BegruessungView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        System.out.println("Salut World");



        //MenueController menuController = MenueController.getInstance();
        
        
        //menuController.startApplication();

        KatalogVerwaltungStartController kv = new KatalogVerwaltungStartController();
        kv.start();
    }
}