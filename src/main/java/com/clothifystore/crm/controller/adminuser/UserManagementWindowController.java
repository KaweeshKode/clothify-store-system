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
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
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
    private TableView<User> tblUser;

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

    UserService userService = ServiceFactory.getInstance().getServiceType(ServiceType.USER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize the table columns at the window load
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colFName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        loadTable();

        // initialize the combo box and set values to select the roles
        ObservableList<String> roleList = FXCollections.observableArrayList();
        roleList.add("Admin");
        roleList.add("User(Employee)");
        cmbRole.setItems(roleList);

        //set selected row values in table to field
        tblUser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                addValuesToField(newValue);
            }
        });
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
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
        clearFields();
        loadTable();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        loadTable();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (userService.deleteUser(Integer.valueOf(txtUserId.getText()))) {
            new Alert(Alert.AlertType.INFORMATION, "User Deleted Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong").show();
        }
        clearFields();
        loadTable();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        User user = new User(
                Integer.valueOf(txtUserId.getText()),
                txtFName.getText(),
                txtLName.getText(),
                txtEmail.getText(),
                txtPassword.getText(),
                cmbRole.getValue()
        );
        if (userService.updateUser(user)) {
            new Alert(Alert.AlertType.INFORMATION, "User Updated Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong").show();
        }
        clearFields();
        loadTable();
    }

    // to clear the text fields and combo box
    private void clearFields() {
        txtUserId.clear();
        txtFName.clear();
        txtLName.clear();
        txtEmail.clear();
        txtPassword.clear();
        cmbRole.valueProperty().setValue(null);
    }

    // to load table data
    private void loadTable() {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        List<User> users = userService.viewUsers();
        for (User user : users) {
            allUsers.add(user);
        }
        tblUser.setItems(allUsers);
    }

    // add selected values to fields in UI
    private void addValuesToField(User user) {
        txtUserId.setText(String.valueOf(user.getUserId()));
        txtFName.setText(user.getFirstName());
        txtLName.setText(user.getLastName());
        txtEmail.setText(user.getEmail());
        txtPassword.setText(user.getPassword());
        cmbRole.setValue(user.getRole());
    }
}
