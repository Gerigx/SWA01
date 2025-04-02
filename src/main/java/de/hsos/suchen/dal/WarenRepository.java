package de.hsos.suchen.dal;

import java.sql.*;
import de.hsos.suchen.bl.Ware;

public class WarenRepository {

    public boolean testConnection() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // CRUD Stuff

    // TESTMETHODE VON CLAUDE todo: -remove
    public Ware findById(long warenId) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            
            // SQL-Statement vorbereiten
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM waren WHERE warennummer = ?"
            );
            stmt.setLong(1, warenId);
            
            // Statement ausführen
            ResultSet rs = stmt.executeQuery();
            
            // Ergebnis verarbeiten
            if (rs.next()) {
                Ware ware = mapResultSetToWare(rs);
                
                // Ressourcen schließen (Statement und ResultSet, nicht die Connection)
                rs.close();
                stmt.close();
                
                // Commit der Transaktion
                conn.commit();
                
                return ware;
            }
            
            // Ressourcen schließen
            rs.close();
            stmt.close();
            
            return null; // Keine Ware gefunden
        } catch (SQLException e) {
            System.err.println("Fehler beim Suchen der Ware: " + e.getMessage());
            try {
                // Bei Fehler: Rollback
                DatabaseConnection.getConnection().rollback();
            } catch (SQLException ex) {
                System.err.println("Rollback fehlgeschlagen: " + ex.getMessage());
            }
            return null;
        }
    }
}
