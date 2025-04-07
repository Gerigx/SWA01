package de.hsos.katalobVerwaltung.dal;

import de.hsos.katalobVerwaltung.bl.Artikel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class KatalogRepository {
    
    private static final String DB_URL = "jdbc:derby:katalogDB;create=true";
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    

    public KatalogRepository() {
        try {
            initializeDatabase();
        } catch (SQLException e) {
            System.err.println("Fehler beim Initialisieren der Datenbank: " + e.getMessage());
        }
    }
    

    private void initializeDatabase() throws SQLException {
        Connection connection = null;
        Statement stmt = null;
        
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL);
            
            // Prüfen, ob die Tabelle bereits existiert
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet tables = meta.getTables(null, null, "ARTIKEL", null);
            
            if (!tables.next()) {
                stmt = connection.createStatement();
                
                // Tabelle erstellen
                String createTableSQL = "CREATE TABLE artikel (" +
                        "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                        "name VARCHAR(200) NOT NULL, " +
                        "preis DOUBLE NOT NULL, " +
                        "beschreibung CLOB, " +
                        "PRIMARY KEY (id))";
                
                stmt.executeUpdate(createTableSQL);
                System.out.println("Tabelle 'artikel' wurde erstellt.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Derby JDBC-Treiber nicht gefunden: " + e.getMessage());
            throw new SQLException("Derby JDBC-Treiber nicht gefunden", e);
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
    }
    

    // Create

    public boolean speichereArtikel(Artikel artikel) {
        if (artikel == null) {
            return false;
        }
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            connection = DriverManager.getConnection(DB_URL);
            
            String sql = "INSERT INTO artikel (name, preis, beschreibung) VALUES (?, ?, ?)";
            stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, artikel.getName());
            stmt.setDouble(2, artikel.getPreis());
            stmt.setString(3, artikel.getBeschreibung());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                return false;
            }
            
            // ID abrufen und im Artikel setzen
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                artikel.setId(generatedKeys.getInt(1));
            }
            
            return true;
        } catch (SQLException e) {
            System.err.println("Fehler beim Speichern des Artikels: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Fehler beim Schließen der Datenbankverbindung: " + e.getMessage());
            }
        }
    }
    

    // Update

    public boolean aktualisiereArtikel(Artikel artikel) {
        if (artikel == null || artikel.getId() <= 0) {
            return false;
        }
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            connection = DriverManager.getConnection(DB_URL);
            
            String sql = "UPDATE artikel SET name = ?, preis = ?, beschreibung = ? WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, artikel.getName());
            stmt.setDouble(2, artikel.getPreis());
            stmt.setString(3, artikel.getBeschreibung());
            stmt.setInt(4, artikel.getId());
            
            int affectedRows = stmt.executeUpdate();
            
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Fehler beim Aktualisieren des Artikels: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Fehler beim Schließen der Datenbankverbindung: " + e.getMessage());
            }
        }
    }
    


    // delete

    public boolean loescheArtikel(int artikelId) {
        if (artikelId <= 0) {
            return false;
        }
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            connection = DriverManager.getConnection(DB_URL);
            
            String sql = "DELETE FROM artikel WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, artikelId);
            
            int affectedRows = stmt.executeUpdate();
            
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Fehler beim Löschen des Artikels: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Fehler beim Schließen der Datenbankverbindung: " + e.getMessage());
            }
        }
    }
    

    // read

    public Artikel findeArtikelById(int artikelId) {
        if (artikelId <= 0) {
            return null;
        }
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            connection = DriverManager.getConnection(DB_URL);
            
            String sql = "SELECT * FROM artikel WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, artikelId);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToArtikel(rs);
            }
            
            return null;
        } catch (SQLException e) {
            System.err.println("Fehler beim Suchen des Artikels: " + e.getMessage());
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Fehler beim Schließen der Datenbankverbindung: " + e.getMessage());
            }
        }
    }
    

    // helper
    private Artikel mapResultSetToArtikel(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        double preis = rs.getDouble("preis");
        String beschreibung = rs.getString("beschreibung");
        
        return new Artikel(id, name, preis, beschreibung);
    }



    // read

    public List<Artikel> getAlleArtikel() {
        List<Artikel> alleArtikel = new ArrayList<>();
        
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            connection = DriverManager.getConnection(DB_URL);
            
            String sql = "SELECT * FROM artikel ORDER BY id";
            stmt = connection.createStatement();
            
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                alleArtikel.add(mapResultSetToArtikel(rs));
            }
            
            return alleArtikel;
        } catch (SQLException e) {
            System.err.println("Fehler beim Abrufen aller Artikel: " + e.getMessage());
            return alleArtikel;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Fehler beim Schließen der Datenbankverbindung: " + e.getMessage());
            }
        }
    }
    

}
