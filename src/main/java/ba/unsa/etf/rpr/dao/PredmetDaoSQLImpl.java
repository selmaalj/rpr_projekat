package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.controllers.Izuzetak;
import ba.unsa.etf.rpr.tabele.Predmet;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;


public class PredmetDaoSQLImpl extends AbstractDao<Predmet> implements PredmetDao{
    private static  PredmetDaoSQLImpl instance = null;
    private PredmetDaoSQLImpl() {
        super("Predmet");
    }
    public static PredmetDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new PredmetDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }
    @Override
    public Predmet row2object(ResultSet rs) throws Izuzetak {
        try {
            Predmet p=new Predmet();
            p.setId(rs.getInt("id"));
            p.setNazivPredmeta(rs.getString("naziv"));
            p.setNivoSkolovanja(rs.getString("nivo"));
            return p;
        } catch (SQLException e) {
            throw new Izuzetak(e.getMessage());
        }
    }

    @Override
    public Map<String, Object> object2row(Predmet object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("naziv", object.getNazivPredmeta());
        row.put("nivo", object.getNivoSkolovanja());
        return row;
    }

    @Override
    public int getId(String predmet, String nivo) {
        int indeks=-1;
        try {
            Connection con=AbstractDao.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT id FROM Predmet WHERE nivo=? AND Lower(naziv)=?");
            statement.setString(1, nivo);
            statement.setString(2, predmet.toLowerCase());
            ResultSet rs = statement.executeQuery();
            if(rs.next())
            indeks=rs.getInt("id");
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return indeks;
    }
}
