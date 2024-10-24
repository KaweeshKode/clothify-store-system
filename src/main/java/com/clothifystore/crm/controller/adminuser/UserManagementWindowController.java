package com.clothifystore.crm.controller.adminuser;

import com.clothifystore.crm.dto.User;
import com.clothifystore.crm.service.ServiceFactory;
import com.clothifystore.crm.service.custom.UserService;
import com.clothifystore.crm.util.ServiceType;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class UserManagementWindowController implements Initializable {

    @FXML
    private ComboBox<String> cmbRole;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colFName;

    @FXML
    private TableColumn<?, ?> colLName;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableView<?> tblUser;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtFName;

    @FXML
    private JFXTextField txtLName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUserId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> roleList = FXCollections.observableArrayList();
        roleList.add("Admin");
        roleList.add("User(Employee)");
        cmbRole.setItems(roleList);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        UserService userService = ServiceFactory.getInstance().getServiceType(ServiceType.USER);
        User user = new User(
                null,
                txtFName.getText(),
                txtLName.getText(),
                txtEmail.getText(),
                txtPassword.getText(),
                cmbRole.getValue()
        );
        if (userService.addUser(user)) {
            new Alert(Alert.AlertType.INFORMATION, "User Added Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong").show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }
}
