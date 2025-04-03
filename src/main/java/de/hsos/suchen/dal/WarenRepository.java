package de.hsos.suchen.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.derby.impl.sql.GenericColumnDescriptor;

import de.hsos.suchen.bl.*;;


public class WarenRepository implements Katalog {
    private WarenSuche suchAlgorithmus;
    
    public WarenRepository() {
        suchAlgorithmus = SuchAlgorithmus.getImplementation(SuchAlgorithmus.KEYWORD_MATCHING);
    }

    @Override
    public void legeBuchungsAlgorithmusFest(SuchAlgorithmus suchAlgorithmus) {
        this.suchAlgorithmus = SuchAlgorithmus.getImplementation(suchAlgorithmus);
    }

    @Override
    public Ware suchen(String warenname) {
        return sucheWare(warenname);
    }

    @Override
    public Ware suchen(Long warennumer) {
        return findById(warennumer);
    }

    @Override
    public void gebeProduktInformation(Ware ware) {
        System.out.println(ware.getBeschreibung());
    }


    public boolean testConnection() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // CREATE

    public boolean createWare(Ware ware) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO waren (name, preis, beschreibung) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            
            statement.setString(1, ware.getName());
            statement.setFloat(2, ware.getPreis());
            statement.setString(3, ware.getBeschreibung());
            
            int affectedRows = statement.executeUpdate();
            
            if (affectedRows == 0) {
                statement.close();
                connection.rollback();
                return false;
            }
            
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                // affectedRow sollte mir ja meine ID geben?
                ware.setWarennummer(generatedKeys.getLong(affectedRows));
            }
            
            generatedKeys.close();
            statement.close();
            connection.commit();
            
            return true;
        } catch (SQLException e) {
            System.err.println("Fehler beim Erstellen der Ware: " + e.getMessage());
            try {
                DatabaseConnection.getConnection().rollback();
            } catch (SQLException ex) {
                System.err.println("Rollback fehlgeschlagen: " + ex.getMessage());
            }
            return false;
        }
    }
    

    // READ 


    public List<Ware> findAllWaren() {

        // alle waren in ein resultset batchen und returnen
        // was wenn keine waren gefunden worden sind?

        List<Ware> alleWaren = new ArrayList<>();
        
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM waren");
            
            if (rs.next()) {
                do {
                    Ware ware = mapResultSetToWare(rs);
                    alleWaren.add(ware);
                } while (rs.next());
            } else {
                System.out.println("AH, keine Waren gefunden. Aber haben sie schonmal von Sylva gehört?");
            }
            
            rs.close();
            statement.close();
            connection.commit();
            
        } catch (SQLException e) {
            System.err.println("Fehler beim Abrufen aller Waren: " + e.getMessage());
            try {
                DatabaseConnection.getConnection().rollback();
            } catch (SQLException ex) {
                System.err.println("Rollback fehlgeschlagen: " + ex.getMessage());
            }
        }
        
        return alleWaren;
    }
    
    public Ware findById(long warenId) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM waren WHERE warennummer = ?"
            );
            statement.setLong(1, warenId);

            long warenID = 0;
            
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                Ware ware = mapResultSetToWare(rs);
                
                rs.close();
                statement.close();
                connection.commit();

                warenID = ware.getWarennummer();
                
                return ware;
            }
            
            rs.close();
            statement.close();

            System.out.println("Die War mit der ID" + warenID + " wurde nicht im System gefunden, aber Sylva bekommt bald die defintiv edition!");
            
            return null;
        } catch (SQLException e) {
            System.err.println("Fehler beim Suchen der Ware: " + e.getMessage());
            try {
                DatabaseConnection.getConnection().rollback();
            } catch (SQLException ex) {
                System.err.println("Rollback fehlgeschlagen: " + ex.getMessage());
            }
            return null;
        }
    }
    
    public Ware sucheWare(String suchbegriff) {
        List<Ware> alleWaren = findAllWaren();

        List<Ware> gefundeneWaren = suchAlgorithmus.sucheWare(suchbegriff, alleWaren);


        try {            
            if (gefundeneWaren.size() > 0) {
                return suchAlgorithmus.sucheWare(suchbegriff, alleWaren).get(0);
            }            
        } catch (Exception e) {
            System.err.println("Fehler beim Suchen der Ware: " + e.getMessage());
            return null;
        }
        return null;    
    }
    

    // UPDATE


    public boolean updateWare(Ware ware) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            
            PreparedStatement statement = connection.prepareStatement(
                "UPDATE waren SET name = ?, preis = ?, beschreibung = ? WHERE warennummer = ?"
            );
            
            statement.setString(1, ware.getName());
            statement.setFloat(2, ware.getPreis());
            statement.setString(3, ware.getBeschreibung());
            statement.setLong(4, ware.getWarennummer());
            
            int affectedRows = statement.executeUpdate();
            
            statement.close();
            
            if (affectedRows == 0) {
                System.out.println("Ware mit nicht gefunden, wahrscheinlich haben selbst Tom und Oli diese ware nicht.");
                connection.rollback();
                return false;
            }
            
            connection.commit();
            return true;
            
        } catch (SQLException e) {
            System.err.println("Fehler beim Aktualisieren der Ware: " + e.getMessage());
            try {
                DatabaseConnection.getConnection().rollback();
            } catch (SQLException ex) {
                System.err.println("Rollback fehlgeschlagen: " + ex.getMessage());
            }
            return false;
        }
    }

    
    // DELETE


    public boolean deleteWare(long warenId) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            
            PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM waren WHERE warennummer = ?"
            );
            
            statement.setLong(1, warenId);
            
            int affectedRows = statement.executeUpdate();
            
            statement.close();
            
            if (affectedRows == 0) {
                System.out.println("keine Ware gefunden, mir fällt aber auch kein weiterer Joke ein");
                connection.rollback();
                return false;
            }
            
            connection.commit();
            return true;
            
        } catch (SQLException e) {
            System.err.println("Fehler beim Löschen der Ware: " + e.getMessage());
            try {
                DatabaseConnection.getConnection().rollback();
            } catch (SQLException ex) {
                System.err.println("Rollback fehlgeschlagen: " + ex.getMessage());
            }
            return false;
        }
    }
    
    // Hilfsmethode zur Konvertierung eines ResultSet in ein Ware-Objekt
    private Ware mapResultSetToWare(ResultSet rs) throws SQLException {
        long warennummer = rs.getLong("warennummer");
        String name = rs.getString("name");
        float preis = rs.getFloat("preis");
        String beschreibung = rs.getString("beschreibung");
        
        Ware ware = new Ware(warennummer,name,preis,beschreibung);

        
        return ware;
    }


}