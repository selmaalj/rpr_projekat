package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Medjutabela;
import ba.unsa.etf.rpr.domain.Predmet;

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
    public List<Integer> getByPredmet(int predmetId) {
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
    public List<Predmet> getByInstruktor(int id){
        List<Predmet> lista = new ArrayList<>();
        try {
            Connection con = AbstractDao.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT idPredmet FROM Medjutabela WHERE idInstruktor=?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                lista.add(PredmetDaoSQLImpl.getInstance().getById(rs.getInt(1)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
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
    public void delete(int id) { //deleteByPredmet
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
    public void deleteByInstruktor(int instruktorId) {
        try {
            Connection con = AbstractDao.getConnection();
            PreparedStatement statement = con.prepareStatement("DELETE FROM Medjutabela WHERE idInstruktor=?;");
            statement.setInt(1, instruktorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteByBoth(int instruktorId, int predmetId){
        try {
            Connection con = AbstractDao.getConnection();
            PreparedStatement statement = con.prepareStatement("DELETE FROM Medjutabela WHERE idInstruktor=? AND idPredmet=?;");
            statement.setInt(1, instruktorId);
            statement.setInt(2, predmetId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean postoji(int instruktorId, int predmetId) {
        try {
            Connection con = AbstractDao.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Medjutabela WHERE idInstruktor=? AND idPredmet=?;");
            statement.setInt(1, instruktorId);
            statement.setInt(2, predmetId);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Medjutabela> getAll() {
        return null;
    }
}
