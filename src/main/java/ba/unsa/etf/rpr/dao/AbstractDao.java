package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Izuzetak;
import ba.unsa.etf.rpr.domain.Idable;

import java.sql.*;
import java.util.*;

/**
 * Abstract class za implementaciju CRUD metoda
 * @param <T>
 */
public abstract class AbstractDao<T extends Idable> implements Dao<T> {
    private static Connection connection = null;
    private final String tableName;

    public AbstractDao(String tableName) {
        this.tableName = tableName;
        if (connection == null) createConnection();
    }

    private static void createConnection() {
        if (AbstractDao.connection == null) {
            try {
                Properties p = new Properties();
                p.load(ClassLoader.getSystemResource("konekcija.properties").openStream());
                String hostname = p.getProperty("url");
                String username = p.getProperty("username");
                String password = p.getProperty("password");
                AbstractDao.connection = DriverManager.getConnection(hostname, username, password);

                Thread pingThread = new Thread(() -> {
                    try {
                        while (connection != null) {
                            Statement s = connection.createStatement();
                            s.execute("SELECT 1 FROM dual");
                            Thread.sleep(50000);
                        }
                    } catch (SQLException | InterruptedException ignored) {
                    }
                });
                pingThread.start();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }


    public static Connection getConnection() {
        return AbstractDao.connection;
    }


    public static void closeConnection() {
        System.out.println("pozvana metoda za zatvaranje konekcije");
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                //throw new RuntimeException(e);
                e.printStackTrace();
                System.out.println("REMOVE CONNECTION METHOD ERROR: Unable to close connection on database");
            }
        }
    }

    /**
     * Method for mapping ResultSet into Object
     *
     * @param rs - result set from database
     * @return a Bean object for specific table
     * @throws Izuzetak in case of error with db
     */
    public abstract T row2object(ResultSet rs) throws Izuzetak;

    /**
     * Method for mapping Object into Map
     *
     * @param object - a bean object for specific table
     * @return key, value sorted map of object
     */
    public abstract Map<String, Object> object2row(T object);

    public T getById(int id) throws Izuzetak {
        return executeQueryUnique("SELECT * FROM " + this.tableName + " WHERE id = ?", new Object[]{id});
    }

    public List<T> getAll() throws Izuzetak {
        return executeQuery("SELECT * FROM " + tableName, null);
    }

    public void delete(int id) throws Izuzetak {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Izuzetak(e.getMessage());
        }
    }

    public T add(T item) throws Izuzetak {
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try {
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));

            return item;
        } catch (SQLException e) {
            throw new Izuzetak(e.getMessage());
        }
    }

    public T update(T item) throws Izuzetak {
        Map<String, Object> row = object2row(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE id = ?");

        try {
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            throw new Izuzetak(e.getMessage());
        }
    }

    /**
     * Utility method for executing any kind of query
     *
     * @param query  - SQL query
     * @param params - params for query
     * @return List of objects from database
     * @throws Izuzetak in case of error with db
     */
    public List<T> executeQuery(String query, Object[] params) throws Izuzetak {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            if (params != null) {
                for (int i = 1; i <= params.length; i++) {
                    stmt.setObject(i, params[i - 1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<T> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(row2object(rs));
            }
            return resultList;
        } catch (SQLException e) {
            throw new Izuzetak(e.getMessage());
        }
    }

    /**
     * Utility for query execution that always return single record
     *
     * @param query  - query that returns single record
     * @param params - list of params for sql query
     * @return Object
     * @throws Izuzetak in case when object is not found
     */
    public T executeQueryUnique(String query, Object[] params) throws Izuzetak {
        List<T> result = executeQuery(query, params);
        if (result != null && result.size() == 1) {
            return result.get(0);
        } else {
            throw new Izuzetak("Object not found");
        }
    }

    /**
     * Accepts KV storage of column names and return CSV of columns and question marks for insert statement
     * Example: (id, name, date) ?,?,?
     */
    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row) {
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry : row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue;
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<>(columns.toString(), questions.toString());
    }

    /**
     * Prepare columns for update statement id=?, name=?, ...
     *
     * @param row - row to be converted intro string
     * @return String for update statement
     */
    private String prepareUpdateParts(Map<String, Object> row) {
        StringBuilder columns = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry : row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue;
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != counter) {
                columns.append(",");
            }
        }
        return columns.toString();
    }
}
