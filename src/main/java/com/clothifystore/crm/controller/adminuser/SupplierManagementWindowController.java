package com.clothifystore.crm.controller.adminuser;

import com.clothifystore.crm.dto.Supplier;
import com.clothifystore.crm.service.ServiceFactory;
import com.clothifystore.crm.service.custom.SupplierService;
import com.clothifystore.crm.util.ServiceType;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class SupplierManagementWindowController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colContactInfo;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private TableView<Supplier> tblSupplier;

    @FXML
    private JFXTextField txtCompany;

    @FXML
    private JFXTextField txtContactInfo;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtSupplierId;

    @FXML
    private JFXTextField txtSupplierName;

    SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize the table columns at the window load
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colContactInfo.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
        loadTable();

        //set selected row values in table to field
        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                addValuesToField(newValue);
            }
        });
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Supplier supplier = new Supplier(
                null,
                txtSupplierName.getText(),
                txtEmail.getText(),
                txtCompany.getText(),
                txtContactInfo.getText()
        );
        if (supplierService.addSupplier(supplier)) {
            new Alert(Alert.AlertType.INFORMATION, "Supplier Added Successfully").show();
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
        if (supplierService.deleteSupplier(Integer.valueOf(txtSupplierId.getText()))) {
            new Alert(Alert.AlertType.INFORMATION, "Supplier Deleted Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong").show();
        }
        clearFields();
        loadTable();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Supplier supplier = new Supplier(
                Integer.valueOf(txtSupplierId.getText()),
                txtSupplierName.getText(),
                txtEmail.getText(),
                txtCompany.getText(),
                txtContactInfo.getText()
        );
        if (supplierService.updateSupplier(supplier)) {
            new Alert(Alert.AlertType.INFORMATION, "Supplier Added Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong").show();
        }
        clearFields();
        loadTable();
    }

    // to clear the text fields and combo box
    private void clearFields() {
        txtSupplierId.clear();
        txtSupplierName.clear();
        txtEmail.clear();
        txtCompany.clear();
        txtContactInfo.clear();
    }

    // to load table data
    private void loadTable() {
        ObservableList<Supplier> allSuppliers = FXCollections.observableArrayList();
        List<Supplier> suppliers = supplierService.viewSuppliers();
        for (Supplier supplier : suppliers) {
            allSuppliers.add(supplier);
        }
        tblSupplier.setItems(allSuppliers);
    }

    // add selected values to fields in UI
    private void addValuesToField(Supplier supplier) {
        txtSupplierId.setText(String.valueOf(supplier.getSupplierId()));
        txtSupplierName.setText(supplier.getSupplierName());
        txtEmail.setText(supplier.getEmail());
        txtCompany.setText(supplier.getCompany());
        txtContactInfo.setText(supplier.getContactInfo());
    }

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
        //TODO
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
