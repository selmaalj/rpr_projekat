package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.PredmetDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Predmet;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PredmetManagerTest {
    private PredmetManager predmetManager;
    private Predmet predmet;
    private PredmetDaoSQLImpl predmetDaoSQLMock;
    private List<Predmet> predmeti;

    @BeforeEach
    public void initializeObjectsWeNeed(){
        predmetManager= Mockito.mock(PredmetManager.class);
        predmet=new Predmet(0, "Naziv1", "Nivo1");
        predmetDaoSQLMock=Mockito.mock(PredmetDaoSQLImpl.class);
        predmeti=new ArrayList<>();
        predmeti.addAll(Arrays.asList(predmet, new Predmet(0, "Naziv2", "Nivo2"),
                new Predmet(0, "Naziv3", "Nivo3")));
    }

}