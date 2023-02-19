package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 */
public class DaoFactory {
    private static final PredmetDao predmetDao = PredmetDaoSQLImpl.getInstance();
    private static final DostupanDao dostupanDao = DostupanDaoSQLImpl.getInstance();
    private static final InstruktorDao instruktorDao = InstruktorDaoSQLImpl.getInstance();
    private static final MedjutabelaDao medjutabelaDao = MedjutabelaDaoSQLImpl.getInstance();

    private DaoFactory(){
    }
    public static PredmetDao predmetDao(){
        return predmetDao;
    }
    public static DostupanDao dostupanDao(){
        return dostupanDao;
    }
    public static MedjutabelaDao medjutabelaDao(){
        return medjutabelaDao;
    }
    public static InstruktorDao instruktorDao(){
        return instruktorDao;
    }

}
