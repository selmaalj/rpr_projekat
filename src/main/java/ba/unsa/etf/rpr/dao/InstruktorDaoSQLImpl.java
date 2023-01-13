package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.tabele.Instruktor;
import ba.unsa.etf.rpr.controllers.Izuzetak;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;


public class InstruktorDaoSQLImpl extends AbstractDao<Instruktor> implements InstruktorDao{
    private static  InstruktorDaoSQLImpl instance = null;
    private InstruktorDaoSQLImpl() {
        super("Instruktor");
    }

    public static InstruktorDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new InstruktorDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }
    @Override
    public Instruktor row2object(ResultSet rs) throws Izuzetak {
        try {
            Instruktor i=new Instruktor();
            i.setId(rs.getInt("id"));
            i.setNazivInstruktora(rs.getString("naziv"));
            i.setTelefonskiBroj(rs.getString("broj"));
            i.setCijenaPoCasu(rs.getDouble("cijena"));
            i.setGrad(rs.getString("grad"));
            return i;
        } catch (SQLException e) {
            throw new Izuzetak(e.getMessage());
        }
    }

    @Override
    public Map<String, Object> object2row(Instruktor object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("naziv", object.getNazivInstruktora());
        row.put("broj", object.getTelefonskiBroj());
        row.put("cijena", object.getCijenaPoCasu());
        row.put("grad", object.getGrad());
        return row;
    }
    @Override
    public Instruktor getByNazivTel(String naziv,String tel){
      return super.executeQueryUnique("SELECT * FROM Instruktor WHERE naziv=? AND broj=?;",new Object[]{naziv,tel});
    }
}
