package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.InstruktorDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Instruktor;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstruktorManagerTest {
    private InstruktorManager instruktorManager;
    private Instruktor instruktor;
    private InstruktorDaoSQLImpl instruktorDaoSQLMock;
    private List<Instruktor> instruktori;

    @BeforeEach
    public void initializeObjectsWeNeed(){
        instruktorManager= Mockito.mock(InstruktorManager.class);
        instruktor=new Instruktor(0, "Naziv", "1", 1., "Grad");
        instruktorDaoSQLMock=Mockito.mock(InstruktorDaoSQLImpl.class);
        instruktori=new ArrayList<>();
        instruktori.addAll(Arrays.asList(instruktor, new Instruktor(0, "Naziv2", "2", 2., "Grad2"),
                new Instruktor(0, "Naziv3", "3", 3., "Grad3")));
    }
}
