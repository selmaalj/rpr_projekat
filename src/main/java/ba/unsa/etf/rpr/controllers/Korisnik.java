package ba.unsa.etf.rpr.controllers;
import ba.unsa.etf.rpr.Izuzetak;
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
    public ChoiceBox<String> choiceBoxNivo, choiceBoxGrad;
    public Text text;
    public TextField predmetfield;
    public TextField sliderText;

    @FXML
    void initialize() {
        slider.setValue(0);
        choiceBoxNivo.getItems().addAll("Osnovna", "Srednja", "Fakultet");
        choiceBoxNivo.getSelectionModel().select(0);
        choiceBoxGrad.getItems().addAll("Sarajevo", "Mostar", "Tuzla", "Zenica", "Banja Luka", "Brcko");
        choiceBoxGrad.getSelectionModel().select(0);
        sliderText.setText(String.valueOf(0));
        slider.valueProperty().addListener((ov, old_val, new_val) -> sliderText.setText(String.valueOf(Math.round((Double) new_val))));
    }

    public void akcijaDugmeta(ActionEvent actionEvent) throws IOException {
        PrenosPodataka sk = PrenosPodataka.getInstance();
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        sk.setCijena(Double.parseDouble(sliderText.getText()));
        sk.setGrad(choiceBoxGrad.getValue());
        sk.setNivo(choiceBoxNivo.getValue());
        sk.setPredmet(predmetfield.getText());
        stage.close();
        try {
            PredmetDaoSQLImpl pd = PredmetDaoSQLImpl.getInstance();
            int id = pd.getId(sk.getPredmet(), sk.getNivo());//id predmeta
            if (id == -1) throw new Izuzetak("Nemamo predmet "+sk.getPredmet()+" u ponudi.");
            MedjutabelaDaoSQLImpl m = new MedjutabelaDaoSQLImpl();
            List<Integer> instruktorIds = m.getByPredmet(id);
            if (instruktorIds.isEmpty()) throw new Izuzetak("Trenutno nije slobodan nijedan instruktor za predmet "+sk.getPredmet()+".");
            for (int i = 0; i < instruktorIds.size(); i++) {
                InstruktorDaoSQLImpl ins = InstruktorDaoSQLImpl.getInstance();
                if (!(sk.getGrad().equals(ins.getById(instruktorIds.get(i)).getGrad()))) {
                    instruktorIds.remove(i--);
                }
            }
            if (instruktorIds.isEmpty()) throw new Izuzetak("Trenutno nemamo nijednog prijavljenog instruktora u gradu "+sk.getGrad()+" za predmet "+sk.getPredmet()+".");
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
            alert.showAndWait();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/korisnik.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("Popunite zahtjeve:");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void akcijaDugmetaNazad(ActionEvent actionEvent) throws IOException {
        Node node= (Node) actionEvent.getSource();
        Stage stage= (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/odabirwindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 400, 200);
        stage.setTitle("Dobro došli!");
        stage.setScene(scene);
        stage.show();
    }
}
