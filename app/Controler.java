package app;
import java.io.IOException;
import java.lang.ModuleLayer.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.io.IOException;

public class Controler {


    private Stage mainwindow;
    public void setMainwindow(Stage mainwindow) {
        this.mainwindow = mainwindow;
    }

    @FXML
    void onBtnClick(ActionEvent event) throws IOException {
        GUI.loginPage(mainwindow);
    }

    @FXML
    void onAdminsiteClick(ActionEvent event) throws IOException {
        GUI.AdminLogin(mainwindow);
    }


}
