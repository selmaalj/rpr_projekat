package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.tabele.Instruktor;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class InstruktorDao implements Dao<Instruktor> {
    Connection con;

    public InstruktorDao(){
        try {
            File f = new File("konekcija.txt");
            Scanner ulaz = new Scanner(f);
            con = DriverManager.getConnection(ulaz.nextLine(), ulaz.nextLine(), ulaz.nextLine());
        }
        catch(SQLException e){
            System.out.println("Greska prilikom rada sa bazom podataka");
            System.out.println(e.getMessage());
        }
        catch(FileNotFoundException n){
            System.out.println(n.getMessage());
        }
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
            PreparedStatement statement=con.prepareStatement("INSERT INTO Instruktor VALUES( ? , ? , ? , ? )");
            statement.setInt(1, getMaxId());
            statement.setString(2,element.getNazivInstruktora());
            statement.setString(3, element.getTelefonskiBroj());
            statement.setDouble(4,element.getCijenaPoCasu());
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
            PreparedStatement statement= con.prepareStatement("UPDATE Instruktor SET naziv_instruktora=?,telefonski_broj=?,cijena_po_casu=? WHERE idInstruktor=?");
            statement.setString(1,element.getNazivInstruktora());
            statement.setString(2, element.getTelefonskiBroj());
            statement.setDouble(3,element.getCijenaPoCasu());
            statement.setInt(4,element.getIdInstruktor());
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
        return null;
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
