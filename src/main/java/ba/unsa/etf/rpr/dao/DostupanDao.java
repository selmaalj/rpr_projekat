package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.tabele.Dostupan;
import ba.unsa.etf.rpr.tabele.Instruktor;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class DostupanDao implements Dao<Dostupan>{
    Connection con;

    public DostupanDao() {
        try{
            File f = new File("konekcija.txt");
            Scanner ulaz = new Scanner(f);
            con = DriverManager.getConnection(ulaz.nextLine(), ulaz.nextLine(), ulaz.nextLine());
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        catch (FileNotFoundException f){
            System.out.println(f.getMessage());
        }
    }

    @Override
    public Dostupan getbyId(int id) {
        try{
            PreparedStatement statement= con.prepareStatement("SELECT * FROM Dostupan WHERE idDostupan=?");
            statement.setInt(1,id);
            ResultSet rs=statement.executeQuery();
            if(rs.next()){
                Dostupan dostupan=new Dostupan();
                InstruktorDao instruktor=new InstruktorDao();
                dostupan.setId(id);
                dostupan.setDan(rs.getString("dan"));
                int index=rs.getInt("idInstruktor");
                dostupan.setIns(instruktor.getbyId(index));
                rs.close();
                return dostupan;
            }
            else return null;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Dostupan add(Dostupan element) {
        return null;
    }

    @Override
    public Dostupan update(Dostupan element) {
        return null;
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public List getAll() {
        return null;
    }
}
