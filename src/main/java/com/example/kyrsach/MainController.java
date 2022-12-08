package com.example.kyrsach;


import com.example.kyrsach.client.Client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;



import java.util.*;
public class MainController {
    Client obj = Client.getInstance();

    public TableView<ManufacturerItem> TAB21;
    public TableColumn<ManufacturerItem, String> TC211;
    public TableColumn<ManufacturerItem, String> TC221;
    public TableColumn<ManufacturerItem, String> TC231;
    public TextField TF211;
    public TextField TF221;
    public TextField TF231;
    public TextField TF241;
    public Button BTN211;
    public Button BTN221;
    public Button BTN231;
    public Button BTN241;




    public TableView<ProductItem> TAB2;
    public TableColumn<ProductItem, String> TC21;
    public TableColumn<ProductItem, String> TC22;
    public TableColumn<ProductItem, String> TC23;
    public TableColumn<ProductItem, String> TC24;
    public TableColumn<ProductItem, String> TC25;
    public TableColumn<ProductItem, String> TC26;




    public TextField TF21;
    public TextField TF22;

    public TextField TF23;
    public TextField TF24;
    public TextField TF25;
    public TextField TF26;

    public Button BTN21;
    public Button BTN22;
    public Button BTN23;
    public Button OBN1;
    public Button BTN24;
    public Button BTC1;
    @FXML
    public Button BTN25;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    ObservableList<UserItem> observableList = FXCollections.observableArrayList(
            //new UserItem("1", "maxim", "123123")
    );

    //
    @FXML
    void initialize(){



        unloadAndLoadAllProductsAndManufacturers();
        clearTextFields();

        //> товары
        TC21.setCellValueFactory(new PropertyValueFactory<>("id"));
        TC22.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        TC23.setCellValueFactory(new PropertyValueFactory<>("manufacturer_id"));
        TC24.setCellValueFactory(new PropertyValueFactory<>("name"));
        TC25.setCellValueFactory(new PropertyValueFactory<>("amount"));
        TC26.setCellValueFactory(new PropertyValueFactory<>("price"));
        //<
        //> производители
        TC211.setCellValueFactory(new PropertyValueFactory<>("id"));
        TC221.setCellValueFactory(new PropertyValueFactory<>("name"));
        TC231.setCellValueFactory(new PropertyValueFactory<>("city"));
        //<

        BTN21.setOnAction(event -> { // добавить товар

            String answer = "";

            answer = obj.Run("addproduct" + "+" + ProductApplication.user_id + "+" + TF23.getText() + "+" + TF24.getText() + "+" + TF25.getText() + "+" + TF26.getText());
            if (Objects.equals(answer, "not")){
                noManufacturerExists();
            }else{
                clearTextFields();
            }
            //> очистка таблички для новых параметров с базы
            unloadAndLoadAllProductsAndManufacturers();
            //<


        });

        BTN211.setOnAction(event -> {  // добавить прозводителя

            String answer = "";

            answer = obj.Run("addmanufacturer" + "+" + TF231.getText() + "+" + TF241.getText());
            //> очистка таблички для новых параметров с базы
            unloadAndLoadAllProductsAndManufacturers();
            //<
            clearTextFields();
        });

        BTN23.setOnAction(event -> {  // удалить товар

            String answer = "";

            answer = obj.Run("deleteproduct" + "+" + TF21.getText() + "+" + TF22.getText() + "+" + TF23.getText() + "+" + TF24.getText() + "+" + TF25.getText() + "+" + TF26.getText());
            if (Objects.equals(answer, "not"))
                noProductsDeleted();
            //> очистка таблички для новых параметров с базы
            unloadAndLoadAllProductsAndManufacturers();
            //<
            clearTextFields();
        });

        BTN231.setOnAction(event -> {  // удалить производителя

            String answer = "";

            answer = obj.Run("deletemanufacturer" + "+" + TF211.getText() + "+" + TF231.getText() + "+" + TF241.getText());
            if (Objects.equals(answer, "not"))
                noManufacturersDeleted();
            //> очистка таблички для новых параметров с базы
            unloadAndLoadAllProductsAndManufacturers();
            //<
            clearTextFields();
        });
        BTN24.setOnAction(event -> { // поиск товара
            String answer = "";
            if (TF23.getText().isEmpty() && TF24.getText().isEmpty() && TF25.getText().isEmpty() && TF26.getText().isEmpty()) {
                unloadAndLoadAllProductsAndManufacturers();
            }else{
                answer = obj.Run("findproduct" + "+" + TF21.getText() + "+" + TF22.getText() + "+" + TF23.getText() + "+" + TF24.getText() + "+" + TF25.getText() + "+" + TF26.getText());
                //> очистка таблички для новых параметров с базы
                TAB2.getItems().clear();
                //unloadAndLoadAllProductsAndManufacturers();
                //<

                clearTextFields();
                if (!Objects.equals(answer, "not")) {
                    List<String[]> rowList = new ArrayList<String[]>(); // продукты
                    String[] productsNotSliced = answer.split("="); // делю месиво из всего ответа на row
                    for (int i = 0; i < productsNotSliced.length; i++) {
                        rowList.add(i, productsNotSliced[i].split("\\+")); // делим каждый row в одну ячейку массива rowlist
                    }
                    for (int i = 0; i < rowList.size(); i++) {
                        TAB2.getItems().add(new ProductItem(rowList.get(i)[0], rowList.get(i)[1], rowList.get(i)[2], rowList.get(i)[3], rowList.get(i)[4], rowList.get(i)[5]));
                    }
                }else{
                    ProductsNotFoundShow();
                }
            }
        });
        BTN241.setOnAction(event -> { // поиск производителя
            String answer = "";
            if (TF221.getText().isEmpty() && TF231.getText().isEmpty() && TF241.getText().isEmpty()) {
                unloadAndLoadAllProductsAndManufacturers();
            }else{
                answer = obj.Run("findmanufacturer" + "+" + TF211.getText() + "+" + TF221.getText() + "+" + TF231.getText() + "+" + TF241.getText());
                //> очистка таблички для новых параметров с базы
                TAB21.getItems().clear();
                //unloadAndLoadAllProductsAndManufacturers();
                //<

                clearTextFields();
                if (!Objects.equals(answer, "not")) {
                    List<String[]> rowList = new ArrayList<String[]>(); // продукты
                    String[] manufacturersNotSliced = answer.split("="); // делю месиво из всего ответа на row
                    for (int i = 0; i < manufacturersNotSliced.length; i++) {
                        rowList.add(i, manufacturersNotSliced[i].split("\\+")); // делим каждый row в одну ячейку массива rowlist
                    }
                    for (int i = 0; i < rowList.size(); i++) {
                        TAB21.getItems().add(new ManufacturerItem(rowList.get(i)[0], rowList.get(i)[1], rowList.get(i)[2]));
                    }
                }else{
                    ManufacturersNotFoundShow();
                }
            }
        });
        BTN22.setOnAction(event -> { // редактировать товар

            String answer = "";

            answer = obj.Run("editproduct" + "+" + TF21.getText() + "+" + TF22.getText() + "+" + TF23.getText() + "+" + TF24.getText() + "+" + TF25.getText() + "+" + TF26.getText());
            unloadAndLoadAllProductsAndManufacturers();
            clearTextFields();

        });
        BTN221.setOnAction(event -> { // редактировать товар

            String answer = "";

            answer = obj.Run("editmanufacturer" + "+" + TF211.getText() + "+" + TF231.getText() + "+" + TF241.getText());            //> очистка таблички для новых параметров с базы
            unloadAndLoadAllProductsAndManufacturers();
            clearTextFields();

        });

        TAB2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> { // выделенная информация в форму
            if (newSelection != null) {
                ProductItem selectedProduct = TAB2.getSelectionModel().getSelectedItem();
                TF21.setText(selectedProduct.getId());
                TF22.setText(selectedProduct.getUser_id());
                TF23.setText(selectedProduct.getManufacturer_id());
                TF24.setText(selectedProduct.getName());
                TF25.setText(selectedProduct.getAmount());
                TF26.setText(selectedProduct.getPrice());
            }
        });

        TAB21.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ManufacturerItem selectedManufacturer = TAB21.getSelectionModel().getSelectedItem();
                TF211.setText(selectedManufacturer.getId());
                //TF221.setText(selectedManufacturer.getUser_id());
                TF231.setText(selectedManufacturer.getName());
                TF241.setText(selectedManufacturer.getCity());
            }
        });

    }
    public void unloadAndLoadAllProductsAndManufacturers(){

        List<String[]> rowList = new ArrayList<String[]>(); // продукты
        List<String[]> rowList2 = new ArrayList<String[]>(); // производители

        String[] productsNotSliced = obj.Run("showproducts").split("="); // делю месиво из всего ответа на row
        for (int i = 0; i < productsNotSliced.length; i++){
            rowList.add(i, productsNotSliced[i].split("\\+")); // делим каждый row в одну ячейку массива rowlist
        }

        String[] manufacturersNotSliced = obj.Run("showmanufacturers").split("=");
        for (int i = 0; i < manufacturersNotSliced.length; i++){
            rowList2.add(i, manufacturersNotSliced[i].split("\\+"));
        }
        //<

        //> очистка таблички для новых параметров с базы
        TAB2.getItems().clear();
        TAB21.getItems().clear();
        //<
            if (!rowList.isEmpty()) {


                for (int i = 0; i < rowList.size(); i++) {

                    TAB2.getItems().add(new ProductItem(rowList.get(i)[0], rowList.get(i)[1], rowList.get(i)[2], rowList.get(i)[3], rowList.get(i)[4], rowList.get(i)[5]));

                }
            }
            if (!rowList2.isEmpty()) {
                for (int i = 0; i < rowList2.size(); i++) {

                    TAB21.getItems().add(new ManufacturerItem(rowList2.get(i)[0], rowList2.get(i)[1], rowList2.get(i)[2]));

                }
            }
        //<
    }
    public void clearTextFields(){
        TF21.clear();
        TF22.setText(ProductApplication.user_id);
        TF23.clear();
        TF24.clear();
        TF25.clear();
        TF26.clear();

        TF211.clear();
        TF221.clear();
        TF231.clear();
        TF241.clear();
    }

    void ProductsNotFoundShow(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Упс....");
        alert.setHeaderText(null);
        alert.setContentText("Таких товаров кажется не существует");

        alert.showAndWait();
    }
    void ManufacturersNotFoundShow(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Упс....");
        alert.setHeaderText(null);
        alert.setContentText("Таких производителей кажется не существует");

        alert.showAndWait();
    }

    void noProductsDeleted(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Упс....");
        alert.setHeaderText(null);
        alert.setContentText("Такого товара не найдено");

        alert.showAndWait();
    }
    void noManufacturersDeleted(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Упс....");
        alert.setHeaderText(null);
        alert.setContentText("Такого производителя не найдено");

        alert.showAndWait();
    }

    void noManufacturerExists(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Упс....");
        alert.setHeaderText(null);
        alert.setContentText("Такого производителя не найдено");

        alert.showAndWait();
    }
}