package ba.unsa.etf.rpr.dao;

import java.sql.*;
import java.util.List;

public class PredmetDao implements Dao{
    private Connection con;

    public PredmetDao() {
        try{
            con=DriverManager.getConnection();
        }
        catch(SQLException e){
            System.out.println("Greska prilikom rada sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }
    @Override
    public Object getbyId(int id) {
        PreparedStatement statement = null;
        try{
            statement=this.con.prepareStatement("SELECT * FROM Predmet WHERE idPredmet = ?");
            statement.setInt(1,idPredmet);
            ResultSet rs=statement.executeQuery();
            if(rs.next()){
                Predmet pr=new Predmet();
            }
            else
                return null;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Object add(Object element) {
        return null;
    }

    @Override
    public Object update(Object element) {
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
