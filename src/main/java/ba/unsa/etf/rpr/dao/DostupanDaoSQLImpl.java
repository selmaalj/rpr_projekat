package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.tabele.Dostupan;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DostupanDaoSQLImpl implements DostupanDao{
    private Connection con;
    private static DostupanDaoSQLImpl instance=null;

    public DostupanDaoSQLImpl() {
        try{
            FileReader f = new FileReader("konekcija.properties");
            Properties pr=new Properties();
            pr.load(f);
            con=DriverManager.getConnection(pr.getProperty("url"),pr.getProperty("username"), pr.getProperty("password"));
        }
        catch(SQLException | FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static DostupanDaoSQLImpl getInstance(){
        if(instance==null)
            instance=new DostupanDaoSQLImpl();
        return instance;
    }
    public static void removeInstance() throws SQLException {
        instance.con.close();
        instance=null;
    }
    @Override
    public Dostupan getbyId(int id) {
        try{
            PreparedStatement statement= con.prepareStatement("SELECT * FROM Dostupan WHERE idDostupan=?");
            statement.setInt(1,id);
            ResultSet rs=statement.executeQuery();
            if(rs.next()){
                Dostupan dostupan=new Dostupan();
                InstruktorDaoSQLImpl instruktor=new InstruktorDaoSQLImpl();
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
        try{
            PreparedStatement statement=con.prepareStatement("INSERT INTO Dostupan VALUES( ? , ? , ? )");
            statement.setInt(1, getMaxId());
            statement.setString(2,element.getDan());
            statement.setInt(3,element.getIns().getIdInstruktor());
            element.setId(getMaxId());
            statement.executeUpdate();
            return element;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public Dostupan update(Dostupan element) {
        try
        {
            PreparedStatement statement=con.prepareStatement("UPDATE Dostupan SET dan=?,idInstruktor=? WHERE idDostupan=?");
            statement.setString(1, element.getDan());
            statement.setInt(2, element.getIns().getIdInstruktor());
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
            PreparedStatement statement=con.prepareStatement("DELETE FROM Dostupan WHERE idDostupan=?");
            statement.setInt(1,id);
            statement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Dostupan> getAll() {
        List<Dostupan> lista=new ArrayList<>();
        try{
            PreparedStatement statement=con.prepareStatement("SELECT * FROM Dostupan");
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                Dostupan dostupan=new Dostupan();
                dostupan.setId(rs.getInt("idDostupan"));
                dostupan.setDan(rs.getString("dan"));
                InstruktorDaoSQLImpl instruktor=new InstruktorDaoSQLImpl();
                dostupan.setIns(instruktor.getbyId(rs.getInt("idInstruktor")));
                lista.add(dostupan);
            }
            rs.close();
            return lista;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
    private int getMaxId(){
        int id=1;
        try{
            PreparedStatement statement=con.prepareStatement("SELECT MAX(idDostupan)+1 FROM Dostupan");
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
