package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Dostupan;
import ba.unsa.etf.rpr.controllers.Izuzetak;
import ba.unsa.etf.rpr.domain.Instruktor;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class DostupanDaoSQLImpl extends AbstractDao<Dostupan> implements DostupanDao{
    private static  DostupanDaoSQLImpl instance = null;
    private DostupanDaoSQLImpl() {
        super("Dostupan");
    }

    public static DostupanDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new DostupanDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }
    @Override
    public Dostupan row2object(ResultSet rs) throws Izuzetak {
        try {
            Dostupan d = new Dostupan();
            d.setId(rs.getInt("id"));
            d.setDan(rs.getString("dan"));
            InstruktorDaoSQLImpl i;
            i=InstruktorDaoSQLImpl.getInstance();
            d.setIns(i.getById(rs.getInt("idInstruktor")));
            return d;
        } catch (SQLException e) {
            throw new Izuzetak(e.getMessage());
        }
    }

    @Override
    public Map<String, Object> object2row(Dostupan object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("dan", object.getDan());
        row.put("idInstruktor", object.getIns().getId());
        return row;
    }
    @Override
    public List<Dostupan> getByInstruktor(Instruktor ins){
        return executeQuery("SELECT * FROM Dostupan WHERE idInstruktor=?;", new Object[]{ins.getId()});
    }
    @Override
    public void deleteByInstruktor(Instruktor ins){
        try {
            PreparedStatement statement= getConnection().prepareStatement("DELETE FROM Dostupan WHERE idInstruktor=?");
            statement.setInt(1, ins.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
