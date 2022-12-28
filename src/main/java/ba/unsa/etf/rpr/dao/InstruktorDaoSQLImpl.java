package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.tabele.Instruktor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class InstruktorDaoSQLImpl implements InstruktorDao {
    private Connection con;
    private static InstruktorDaoSQLImpl instance=null;

    public InstruktorDaoSQLImpl(){
        try {
            FileReader f = new FileReader("konekcija.properties");
            Properties pr=new Properties();
            pr.load(f);
            con=DriverManager.getConnection(pr.getProperty("url"),pr.getProperty("username"), pr.getProperty("password"));
        }
        catch(SQLException | FileNotFoundException e){
            System.out.println("Greska prilikom rada sa bazom podataka");
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static InstruktorDaoSQLImpl getInstance(){
        if(instance==null)
            instance=new InstruktorDaoSQLImpl();
        return instance;
    }
    public static void removeInstance() throws SQLException {
        instance.con.close();
        instance=null;
    }
    @Override
    public Instruktor getbyId(int id) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Instruktor WHERE idInstruktor = ?");
            statement.setInt(1,id);
            ResultSet rs=statement.executeQuery();
            if(rs.next()){
                Instruktor instruktor=new Instruktor();
                instruktor.setIdInstruktor(rs.getInt("idInstruktor"));
                instruktor.setCijenaPoCasu(rs.getDouble("cijena_po_casu"));
                instruktor.setNazivInstruktora(rs.getString("naziv_instruktora"));
                instruktor.setTelefonskiBroj(rs.getString("telefonski_broj"));
                instruktor.setGrad(rs.getString("grad"));
                rs.close();
                return instruktor;
            }
            else return null;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Instruktor add(Instruktor element) {
        try{
            PreparedStatement statement=con.prepareStatement("INSERT INTO Instruktor VALUES( ? , ? , ? , ? , ?)");
            statement.setInt(1, getMaxId());
            statement.setString(2,element.getNazivInstruktora());
            statement.setString(3, element.getTelefonskiBroj());
            statement.setDouble(4,element.getCijenaPoCasu());
            statement.setString(5, element.getGrad());
            element.setIdInstruktor(getMaxId());
            statement.executeUpdate();
            return element;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Instruktor update(Instruktor element) {
        try{
            PreparedStatement statement= con.prepareStatement("UPDATE Instruktor SET naziv_instruktora=?,telefonski_broj=?,cijena_po_casu=?, grad=? WHERE idInstruktor=?");
            statement.setString(1,element.getNazivInstruktora());
            statement.setString(2, element.getTelefonskiBroj());
            statement.setDouble(3,element.getCijenaPoCasu());
            statement.setInt(4,element.getIdInstruktor());
            statement.setString(5, element.getGrad());
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
            PreparedStatement statement=con.prepareStatement("DELETE FROM Instruktor WHERE idInstruktor=?");
            statement.setInt(1,id);
            statement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List getAll() {
        List<Instruktor> lista=new ArrayList<>();
        try{
            PreparedStatement statement=con.prepareStatement("SELECT * FROM Instruktor");
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                Instruktor instruktor=new Instruktor();
                instruktor.setIdInstruktor(rs.getInt("idInstruktor"));
                instruktor.setNazivInstruktora(rs.getString("naziv_instruktora"));
                instruktor.setTelefonskiBroj(rs.getString("telefonski_broj"));
                instruktor.setCijenaPoCasu(rs.getDouble("cijena_po_casu"));
                instruktor.setGrad(rs.getString("grad"));
                lista.add(instruktor);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
    private int getMaxId(){
        int id=100;
        try{
            PreparedStatement statement=con.prepareStatement("SELECT MAX(idInstruktor)+1 FROM Instruktor");
            ResultSet rs=statement.executeQuery();
            if(rs.next()){
                id=rs.getInt(1);
                rs.close();
                return id;
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return id;
    }
}
