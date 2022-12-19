package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.tabele.Instruktor;
import ba.unsa.etf.rpr.tabele.Medjutabela;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MedjutabelaDaoSQLImpl implements MedjutabelaDao{
    private Connection con;
    private static MedjutabelaDaoSQLImpl instance=null;
    public MedjutabelaDaoSQLImpl(){
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
    public static MedjutabelaDaoSQLImpl getInstance(){
        if(instance==null)
            instance=new MedjutabelaDaoSQLImpl();
        return instance;
    }
    public static void removeInstance() throws SQLException {
        instance.con.close();
        instance=null;
    }
    @Override
    public Medjutabela getbyId(int id) {
        return null;
    }
    @Override
    public Medjutabela add(Medjutabela element) {
        return null;
    }
    @Override
    public Medjutabela update(Medjutabela element) {
        return null;
    }
    @Override
    public void delete(int id) {
    }
    @Override
    public List getAll() {
        List<Medjutabela> lista=new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Medjutabela");
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                Medjutabela m=new Medjutabela();
                InstruktorDaoSQLImpl ins=new InstruktorDaoSQLImpl();
                PredmetDaoSQLImpl pr=new PredmetDaoSQLImpl();
                m.setIns(ins.getbyId(rs.getInt(1)));
                m.setPredmet(pr.getbyId(rs.getInt(2)));
                lista.add(m);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Integer> getbyPredmet(int predmetId) {
        List<Integer> instruktorIds=new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement("SELECT idInstruktor FROM Medjutabela WHERE idPredmet=?");
            statement.setInt(1,predmetId);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                instruktorIds.add(rs.getInt(1));
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return instruktorIds;
    }
}
