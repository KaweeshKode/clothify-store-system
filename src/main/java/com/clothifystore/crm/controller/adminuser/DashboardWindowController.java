package com.clothifystore.crm.controller.adminuser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardWindowController {

    // navigations to the other windows
    @FXML
    void btnProductsManagementWindow(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/adminuser/product_management_window.fxml"))));
            stage.setTitle("Clothify Store");
            stage.show();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // close the current window
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnOrdersManagementWindow(ActionEvent event) {
        //TODO
    }

    @FXML
    void btnSuppliersManagementWindow(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/adminuser/supplier_management_window.fxml"))));
            stage.setTitle("Clothify Store");
            stage.show();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // close the current window
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnReportsWindow(ActionEvent event) {
        //TODO
    }

    @FXML
    void btnUsersManagementWindow(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/adminuser/user_management_window.fxml"))));
            stage.setTitle("Clothify Store");
            stage.show();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // close the current window
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../../view/login_window.fxml"))));
            stage.setTitle("Clothify Store");
            stage.show();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // close the current window
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
