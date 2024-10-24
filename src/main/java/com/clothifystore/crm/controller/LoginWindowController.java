package com.clothifystore.crm.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginWindowController {

    @FXML
    private JFXTextField txtEmailId;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    void btnForgotPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void btnLogInOnAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/adminuser/user_management_window.fxml"))));
            stage.setTitle("Clothify Store");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
