package com.example.kyrsach;
import com.example.kyrsach.client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class RegistrationController {
    Client obj = Client.getInstance();
    public Button registerButton;
    public PasswordField passwordField;
    public TextField loginField;

    @FXML
    void initialize(){
        registerButton.setOnAction(event -> {  // зарегистрироваться

            if (loginField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                FieldsEmptyMessage();
            }else{
                String answer = "";
                answer = obj.Run("register" + "+" + loginField.getText() + "+" + passwordField.getText());
                if (!Objects.equals(answer, "not")) {
                    System.out.println("Пользователь зареган - " + answer);
                    ProductApplication.user_id = answer;
                    loadMainWindow();
                } else {
                    System.out.println("чето не то пошло");
                    UserAlreadyExistsMessageShow();
                }
            }
        });
    }
    void loadMainWindow() {
        registerButton.getScene().getWindow().hide();

        FXMLLoader fxmlLoader = new FXMLLoader(ProductApplication.class.getResource("main-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1020, 540);
        }catch (IOException e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Учет товаров в магазине");
        stage.setScene(scene);
        stage.show();
    }

    void UserAlreadyExistsMessageShow(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Упс....");
        alert.setHeaderText(null);
        alert.setContentText("Кажется такой пользователь уже существует, выберите другое имя");

        alert.showAndWait();
    }
    void FieldsEmptyMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Упс....");
        alert.setHeaderText(null);
        alert.setContentText("Заполните все поля");

        alert.showAndWait();
    }
}

