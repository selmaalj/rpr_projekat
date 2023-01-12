package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.tabele.Medjutabela;
import java.sql.*;
import java.util.*;

public class MedjutabelaDaoSQLImpl implements MedjutabelaDao{
    private static  MedjutabelaDaoSQLImpl instance = null;

    public static MedjutabelaDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new MedjutabelaDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }
    @Override
    public List<Integer> getbyPredmet(int predmetId) {
        List<Integer> instruktorIds=new ArrayList<>();
        try {
            Connection con=AbstractDao.getConnection();
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

    @Override
    public Medjutabela getById(int id) {
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
    public List<Medjutabela> getAll() {
        return null;
    }
}
