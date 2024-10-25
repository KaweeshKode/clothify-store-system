package com.clothifystore.crm.controller;

import com.clothifystore.crm.dto.User;
import com.clothifystore.crm.service.ServiceFactory;
import com.clothifystore.crm.service.custom.LoginService;
import com.clothifystore.crm.util.ServiceType;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginWindowController {

    @FXML
    private JFXTextField txtEmailId;

    @FXML
    private JFXPasswordField txtPassword;

    LoginService loginService = ServiceFactory.getInstance().getServiceType(ServiceType.LOGIN);

    @FXML
    void btnForgotPasswordOnAction(ActionEvent event) {
        //TODO
    }

    @FXML
    void btnLogInOnAction(ActionEvent event) {
        User existingUser = loginService.existsUser(txtEmailId.getText(), txtPassword.getText());
        if (existingUser != null) {
            try {
                Stage stage = new Stage();
                Scene scene;
                if ("Admin".equalsIgnoreCase(existingUser.getRole())) {
                    scene = new Scene(FXMLLoader.load(getClass().getResource("../../../../view/adminuser/dashboard_window.fxml")));
                    stage.setTitle("Clothify Store");
                } else {
                    scene = new Scene(FXMLLoader.load(getClass().getResource("../../../../view/defaultuser/dashboard_window.fxml")));
                    stage.setTitle("Clothify Store");
                }
                stage.setScene(scene);
                stage.show();
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // close the current window
                currentStage.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "EmailID and Password doesn't match.").show();
        }
        clearFields();
    }

    // to clear the text fields
    private void clearFields() {
        txtEmailId.clear();
        txtPassword.clear();
    }
}
