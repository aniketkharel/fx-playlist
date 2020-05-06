package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import sample.model.ActivitiesModel;
import sample.utils.DatabaseUtils;
import sample.utils.UsernameFileUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Button btnSaveActivity;
    @FXML
    private TextArea txtActivityDescription;
    @FXML
    private Label lblValidationTxt;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //controller started
        //set lbl to current timestamp
        //lblTimeStamp.setText(new Date().toString());
    }

    @FXML
    void saveAction(ActionEvent event) throws IOException {
        if (txtActivityDescription.getText().isEmpty()) {
            lblValidationTxt.setTextFill(Color.RED);
        } else {
            ActivitiesModel activitiesModel = new ActivitiesModel(new UsernameFileUtils().getUsername(), new Date(), txtActivityDescription.getText());
            new DatabaseUtils().insertDocument(activitiesModel);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Activity Saved");
            alert.setHeaderText("Activity by " + new UsernameFileUtils().getUsername());
            alert.setContentText("Your activity has been saved to your database !");
            alert.showAndWait();
            txtActivityDescription.clear();
        }
    }


}
