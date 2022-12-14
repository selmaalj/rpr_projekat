package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.tabele.Predmet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class PredmetDaoSQLImpl implements PredmetDao{
    private Connection con;
    public PredmetDaoSQLImpl() {
        try{
            FileReader f = new FileReader("konekcija.properties");
            Properties pr=new Properties();
            pr.load(f);
            con=DriverManager.getConnection(pr.getProperty("url"),pr.getProperty("username"), pr.getProperty("password"));
        }
        catch(SQLException e){
            System.out.println("Greska prilikom rada sa bazom podataka");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Predmet getbyId(int id) {
        try{
            PreparedStatement statement=this.con.prepareStatement("SELECT * FROM Predmet WHERE idPredmet = ?");
            statement.setInt(1,id);
            ResultSet rs=statement.executeQuery();
            if(rs.next()){
                Predmet predmet=new Predmet();
                predmet.setId(rs.getInt("idPredmet"));
                predmet.setNazivPredmeta(rs.getString("naziv_predmeta"));
                predmet.setNivoSkolovanja(rs.getString("nivo_skolovanja"));
                rs.close();
                return predmet;
            }
            else {
                return null; //nema elemenata
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public Predmet add(Predmet element) {
        try{
            PreparedStatement statement=this.con.prepareStatement("INSERT INTO Predmet VALUES( ? , ? , ? )");
            statement.setInt(1,getMaxId());
            statement.setString(2,element.getNazivPredmeta());
            statement.setString(3, element.getNivoSkolovanja());
            element.setId(getMaxId());
            statement.executeUpdate();
            return element;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Predmet update(Predmet element) {
        try{
            PreparedStatement statement=con.prepareStatement("UPDATE Predmet SET naziv_predmeta=?,nivo_skolovanja=? WHERE idPredmet=?");
            statement.setString(1, element.getNazivPredmeta());
            statement.setString(2, element.getNivoSkolovanja());
            statement.setInt(3, element.getId());
            statement.executeUpdate();
            return element;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public void delete(int id) {
        try{
            PreparedStatement statement=this.con.prepareStatement("DELETE FROM Predmet WHERE idPredmet = ?");
            statement.setInt(1,id);
            statement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List getAll() {
        List<Predmet> lista=new ArrayList<>();
        try{
            PreparedStatement statement=this.con.prepareStatement("SELECT * FROM Predmet");
            ResultSet rs=statement.executeQuery(); //cita slog po slog
            while(rs.next()){
                Predmet predmet=new Predmet();
                predmet.setId(rs.getInt("idPredmet"));
                predmet.setNivoSkolovanja(rs.getString("nivo_skolovanja"));
                predmet.setNazivPredmeta(rs.getString("naziv_predmeta"));
                lista.add(predmet);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
    private int getMaxId(){
        int id=1;
        try {
            PreparedStatement statement = this.con.prepareStatement("SELECT MAX(idPredmet)+1 FROM Predmet");
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);
                rs.close();
                return id;
            }
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return id;
    }
}
