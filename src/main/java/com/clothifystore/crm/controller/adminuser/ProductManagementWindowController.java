package com.clothifystore.crm.controller.adminuser;

import com.clothifystore.crm.dto.Product;
import com.clothifystore.crm.service.ServiceFactory;
import com.clothifystore.crm.service.custom.ProductService;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProductManagementWindowController implements Initializable {
    @FXML
    private ComboBox<String> cmbCategory;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableView<Product> tblProduct;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtProductId;

    @FXML
    private JFXTextField txtProductName;

    @FXML
    private JFXTextField txtQuantity;

    @FXML
    private JFXTextField txtSize;

    ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize the table columns at the window load
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        loadTable();

        // initialize the combo box and set values to select the category
        ObservableList<String> categoryList = FXCollections.observableArrayList();
        categoryList.add("Ladies");
        categoryList.add("Gents");
        categoryList.add("Kids");
        cmbCategory.setItems(categoryList);

        //set selected row values in table to field
        tblProduct.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                addValuesToField(newValue);
            }
        });
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Product product = new Product(
                null,
                txtProductName.getText(),
                txtSize.getText(),
                Double.valueOf(txtPrice.getText()),
                Integer.valueOf(txtQuantity.getText()),
                cmbCategory.getValue()
        );
        if (productService.addProduct(product)) {
            new Alert(Alert.AlertType.INFORMATION, "Product Added Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
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
        if (productService.deleteProduct(Integer.valueOf(txtProductId.getText()))) {
            new Alert(Alert.AlertType.INFORMATION, "Product Deleted Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong").show();
        }
        clearFields();
        loadTable();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Product product = new Product(
                Integer.valueOf(txtProductId.getText()),
                txtProductName.getText(),
                txtSize.getText(),
                Double.valueOf(txtPrice.getText()),
                Integer.valueOf(txtQuantity.getText()),
                cmbCategory.getValue()
        );
        if (productService.updateProduct(product)) {
            new Alert(Alert.AlertType.INFORMATION, "Product Updated Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
        clearFields();
        loadTable();
    }

    // to clear the text fields
    private void clearFields() {
        txtProductId.clear();
        txtProductName.clear();
        txtSize.clear();
        txtPrice.clear();
        txtQuantity.clear();
        cmbCategory.setValue(null);
    }

    // to load table data
    private void loadTable() {
        ObservableList<Product> allProducts = FXCollections.observableArrayList();
        List<Product> products = productService.viewProducts();
        for (Product product : products) {
            allProducts.add(product);
        }
        tblProduct.setItems(allProducts);
    }

    // add selected values to fields in UI
    private void addValuesToField(Product product) {
        txtProductId.setText(String.valueOf(product.getProductId()));
        txtProductName.setText(product.getProductName());
        txtSize.setText(product.getSize());
        txtPrice.setText(String.valueOf(product.getPrice()));
        txtQuantity.setText(String.valueOf(product.getQuantity()));
        cmbCategory.setValue(product.getCategory());
    }

    // navigations to the other windows
    @FXML
    void btnProductsManagementWindow(ActionEvent event) {
        //TODO
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
