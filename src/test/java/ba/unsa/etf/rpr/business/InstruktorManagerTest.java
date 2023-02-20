package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Izuzetak;
import ba.unsa.etf.rpr.dao.InstruktorDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Instruktor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
    @Test
    public void validateNaziv(){
        String correctnaziv="Ime prezime";
        try{
            Mockito.doCallRealMethod().when(instruktorManager).validateNaziv(correctnaziv);
        }catch(Izuzetak e){
            Assertions.fail();
        }
        String incorrectShort="I P";
        Mockito.doCallRealMethod().when(instruktorManager).validateNaziv(incorrectShort);
        Izuzetak instruktorException1=assertThrows(Izuzetak.class, ()-> instruktorManager.validateNaziv(incorrectShort), "Naziv mora imati i ime i prezime najmanje duzine 2!") ;
        Assertions.assertEquals("Naziv mora imati i ime i prezime najmanje duzine 2!",instruktorException1.getMessage());

        String incorrectEmpty="";
        Mockito.doCallRealMethod().when(instruktorManager).validateNaziv(incorrectEmpty);
        Izuzetak instruktorException2=assertThrows(Izuzetak.class,()-> instruktorManager.validateNaziv(incorrectEmpty),"Naziv mora imati i ime i prezime najmanje duzine 2!") ;
        Assertions.assertEquals("Naziv mora imati i ime i prezime najmanje duzine 2!",instruktorException2.getMessage());
    }
}
