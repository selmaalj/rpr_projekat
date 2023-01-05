package ba.unsa.etf.rpr.controllers;
import ba.unsa.etf.rpr.dao.InstruktorDaoSQLImpl;
import ba.unsa.etf.rpr.dao.MedjutabelaDaoSQLImpl;
import ba.unsa.etf.rpr.dao.PredmetDaoSQLImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Korisnik {
    public Button potvrda;
    public Slider slider;
    public ChoiceBox<String> choiceBox1, choiceBox2;
    public Text text;
    public TextField textfield;
    public Separator separator;

    @FXML
    void initialize() {
        choiceBox1.getItems().addAll("Osnovna", "Srednja", "Fakultet");
        choiceBox1.getSelectionModel().select(0);
        choiceBox2.getItems().addAll("Sarajevo", "Mostar", "Tuzla", "Zenica", "Banja Luka", "Brcko");
        choiceBox2.getSelectionModel().select(0);
        slider.setMin(5.);
        slider.setMax(65.);
        slider.setBlockIncrement(10);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
    }

    public void akcijaDugmeta(ActionEvent actionEvent) throws IOException {
        SingletonKlasa sk = SingletonKlasa.getInstance();
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        sk.setCijena(slider.getValue());
        sk.setGrad(choiceBox2.getValue());
        sk.setNivo(choiceBox1.getValue());
        sk.setPredmet(textfield.getText());
        stage.close();
        try {
            PredmetDaoSQLImpl pd = new PredmetDaoSQLImpl();
            int id = pd.getId(sk.getPredmet(), sk.getNivo());//id predmeta
            if (id == -1) throw new Izuzetak("Nemamo traženi predmet u ponudi.");
            MedjutabelaDaoSQLImpl m = new MedjutabelaDaoSQLImpl();
            List<Integer> instruktorIds = m.getbyPredmet(id);
            if (instruktorIds.isEmpty()) throw new Izuzetak("Trenutno nemamo instruktora za vaše zahtjeve.");
            for (int i = 0; i < instruktorIds.size(); i++) {
                InstruktorDaoSQLImpl ins = new InstruktorDaoSQLImpl();
                if (!(sk.getGrad().equals(ins.getbyId(instruktorIds.get(i)).getGrad()))) {
                    instruktorIds.remove(i--);
                }
            }
            if (instruktorIds.isEmpty()) throw new Izuzetak("Trenutno nemamo instruktora u vašem gradu.");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/tabelawindow.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("Vaši instruktori!");
            stage.setScene(scene);
            stage.show();
        } catch (Izuzetak e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            Stage st = (Stage) alert.getDialogPane().getScene().getWindow();
            st.getIcons().add(new Image("file:///C:/Users/WIN10/Downloads/book.png"));
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }
}
