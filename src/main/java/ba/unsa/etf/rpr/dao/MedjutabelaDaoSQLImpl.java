package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Medjutabela;

import java.sql.*;
import java.util.*;

public class MedjutabelaDaoSQLImpl implements MedjutabelaDao {
    private static MedjutabelaDaoSQLImpl instance = null;

    public static MedjutabelaDaoSQLImpl getInstance() {
        if (instance == null)
            instance = new MedjutabelaDaoSQLImpl();
        return instance;
    }

    public static void removeInstance() {
        if (instance != null)
            instance = null;
    }

    @Override
    public List<Integer> getbyPredmet(int predmetId) {
        List<Integer> instruktorIds = new ArrayList<>();
        try {
            Connection con = AbstractDao.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT idInstruktor FROM Medjutabela WHERE idPredmet=?");
            statement.setInt(1, predmetId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                instruktorIds.add(rs.getInt(1));
            }
            rs.close();
        } catch (SQLException e) {
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
        try {
            Connection con = AbstractDao.getConnection();
            PreparedStatement statement = con.prepareStatement("INSERT INTO Medjutabela VALUES(?,?);");
            statement.setInt(1, element.getIns().getId());
            statement.setInt(2, element.getPredmet().getId());
            statement.executeUpdate();
            return element;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Medjutabela update(Medjutabela element) {
        return null;
    }

    @Override
    public void delete(int id) {
        try {
            Connection con = AbstractDao.getConnection();
            PreparedStatement statement = con.prepareStatement("DELETE FROM Medjutabela WHERE idPredmet=?;");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Medjutabela> getAll() {
        return null;
    }
}
