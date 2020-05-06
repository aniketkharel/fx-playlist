package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.utils.DatabaseNameUtils;
import sample.utils.UsernameFileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public Hyperlink hyperMainAddUsername;
    public Hyperlink hyperMainSetupDatabase;
    public Button btnMainAbout;
    public Button btnMainHelp;
    @FXML
    private Button btnMainView, btnMainNew;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!new File(UsernameFileUtils.USER_FILE_NAME).exists() || !new File(DatabaseNameUtils.DATABASE_FILE_NAME).exists()) {
            TextInputDialog usernameDialog = new TextInputDialog("awesomeUser");
            usernameDialog.setTitle("Username");
            usernameDialog.setHeaderText("Enter an Username");
            usernameDialog.setContentText("Username : ");
            Optional<String> usernameResult = usernameDialog.showAndWait();
            usernameResult.ifPresent(name ->
                    {
                        try {
                            new UsernameFileUtils().MakeNewFile();
                            new UsernameFileUtils().saveUsername(name);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

            );
            TextInputDialog databaseDialog = new TextInputDialog("YOUR DATABASE URL");
            databaseDialog.setTitle("Database Address");
            databaseDialog.setHeaderText("Enter Database Address");
            Optional<String> databaseResult = databaseDialog.showAndWait();
            databaseResult.ifPresent(name ->
                    {
                        try {
                            new DatabaseNameUtils().MakeNewDatabaseFile();
                            new DatabaseNameUtils().saveDatabaseURL(name);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

            );
            btnMainView.setDisable(true);
            btnMainNew.setDisable(true);
        } else {
            btnMainView.setDisable(false);
            btnMainNew.setDisable(false);
        }
    }

    @FXML
    void newAction(ActionEvent event) throws IOException {
        Stage newStage = new Stage();
        newStage.setScene(new Scene(Main.loadFXML("AddActivity")));
        newStage.setTitle("Add Activity");
        //newStage.getIcons().add(new Image(MainController.class.getResourceAsStream("src\\sample\\at.png")));
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.show();
    }

    @FXML
    void viewAction(ActionEvent event) throws IOException {
        Stage newStage = new Stage();
        newStage.setScene(new Scene(Main.loadFXML("viewActivity")));
        newStage.setTitle("Your Activity");
        //newStage.getIcons().add(new Image(MainController.class.getResourceAsStream("src\\sample\\at.png")));
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.show();
    }

    @FXML
    void hyperMainSetupDatabaseAction(ActionEvent event) throws IOException {
        TextInputDialog databaseDialog = new TextInputDialog("YOUR DATABASE URL");
        databaseDialog.setTitle("Database Address");
        databaseDialog.setHeaderText("Enter Database Address");
        Optional<String> databaseResult = databaseDialog.showAndWait();
        databaseResult.ifPresent(name ->
                {
                    try {
                        new DatabaseNameUtils().MakeNewDatabaseFile();
                        new DatabaseNameUtils().saveDatabaseURL(name);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

        );
    }

    @FXML
    void hyperMainAddUsernameAction(ActionEvent event) throws IOException {
        TextInputDialog usernameDialog = new TextInputDialog("awesomeUser");
        usernameDialog.setTitle("Username");
        usernameDialog.setHeaderText("Enter an Username");
        usernameDialog.setContentText("Username : ");
        Optional<String> usernameResult = usernameDialog.showAndWait();
        usernameResult.ifPresent(name ->
                {
                    try {
                        new UsernameFileUtils().MakeNewFile();
                        new UsernameFileUtils().saveUsername(name);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

        );
    }

    public void btnMainAboutAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About activitiesTracker");
        alert.setHeaderText("activitiesTracker v.1.0 || MIT");
        alert.setContentText("activitesTracker is an Open Source Software, where you can save what activities you did at your own cloud database address (default MongoDB) and view over any period of time. ONLY FOR NEEDED INDIVIDUALS.\n Brought to you by TheDeadliners Team.\nCredits: Biswash Lamichhane, Anup Lamichhane, Nishant Ghimire, Aniket kharel");
        alert.showAndWait();
    }

    public void btnMainHelpAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("activitiesTracker v.1.0 || MIT");
        alert.setContentText("First of all head over to https://account.mongodb.com/account/login and login to your database (create if necessary) \n Then, create your cluster, and then add user to your database and copy the connect string on database setup dialog. ");
        alert.showAndWait();
    }
}