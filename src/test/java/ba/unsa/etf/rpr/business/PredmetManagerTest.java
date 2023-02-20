package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Izuzetak;
import ba.unsa.etf.rpr.dao.PredmetDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Predmet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
    @Test
    public void validateNaziv(){
        String correctnaziv="Predmet";
        try{
            Mockito.doCallRealMethod().when(predmetManager).validateNaziv(correctnaziv);
        }catch(Izuzetak e){
            Assertions.fail();
        }
        String incorrectShort="1";
        Mockito.doCallRealMethod().when(predmetManager).validateNaziv(incorrectShort);
        Izuzetak predmetException1=assertThrows(Izuzetak.class, ()-> predmetManager.validateNaziv(incorrectShort), "Naziv mora imati i ime i prezime najmanje duzine 2!") ;
        Assertions.assertEquals("Naziv mora biti duzine 2 minimum!",predmetException1.getMessage());

        String incorrectEmpty="";
        Mockito.doCallRealMethod().when(predmetManager).validateNaziv(incorrectEmpty);
        Izuzetak predmetException2=assertThrows(Izuzetak.class,()-> predmetManager.validateNaziv(incorrectEmpty),"Naziv mora imati i ime i prezime najmanje duzine 2!") ;
        Assertions.assertEquals("Naziv mora biti duzine 2 minimum!",predmetException2.getMessage());
    }
}