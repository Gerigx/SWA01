package de.hsos;

import de.hsos.suchen.dal.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // JDBC-Treiber laden
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            
            // Verbindung zur Datenbank herstellen
            System.out.println("Verbindung zur Datenbank wird hergestellt...");
            connection = DriverManager.getConnection("jdbc:derby:shoppingDB;create=true");
            
            // AutoCommit ausschalten (wie in Aufgabe 1.4 gefordert)
            connection.setAutoCommit(false);
            
            System.out.println("Verbindung erfolgreich hergestellt!");
            
            // Optional: Test-Tabelle erstellen
            Statement stmt = connection.createStatement();
            try {
                stmt.executeUpdate("CREATE TABLE test_table (id INT, name VARCHAR(50))");
                stmt.executeUpdate("INSERT INTO test_table VALUES (1, 'Test')");
                connection.commit();
                System.out.println("Test-Tabelle erstellt und Daten eingefügt!");
            } catch (SQLException e) {
                // Tabelle existiert möglicherweise bereits
                connection.rollback();
                System.out.println("Test-Tabelle konnte nicht erstellt werden: " + e.getMessage());
            }
            
            stmt.close();
            
        } catch (ClassNotFoundException e) {
            System.err.println("Derby JDBC-Treiber nicht gefunden: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Datenbankfehler: " + e.getMessage());
            // Bei Fehler: Rollback durchführen
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                System.err.println("Rollback fehlgeschlagen: " + ex.getMessage());
            }
        } finally {
            // Verbindung schließen
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Fehler beim Schließen der Verbindung: " + e.getMessage());
            }
        }
    }
}