package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import sample.utils.DatabaseUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewController implements Initializable {

    StringBuilder contentInHere = new StringBuilder();

    @FXML
    private TextArea txtViewDescription;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtViewDescription.setText("Your list of documents resides here");
        ArrayList<String> contentLists = new DatabaseUtils().getAllDocuments();
        for (String lists : contentLists
        ) {
            contentInHere.append(lists);
        }
        txtViewDescription.setText(String.valueOf(contentInHere));
    }

}
