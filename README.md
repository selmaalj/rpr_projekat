# rpr_projekat
Program s ciljem pronalaska ili prijave instruktora.
# GUI
mvn package -P gui-app
java --module-path "$path_to_javafx$" --add-modules javafx.controls, javafx.fxml -jar bet366-gui-jar-with-dependencies.jar
# CLI
mvn package -P cli-app
java -jar bet366-cli-jar-with-dependencies.jar [option] (parameters)
