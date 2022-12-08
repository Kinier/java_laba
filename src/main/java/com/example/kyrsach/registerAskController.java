package com.example.kyrsach;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class registerAskController {
    public Button RegisterYes;
    public Button RegisterNo;



    @FXML
    void initialize(){
        RegisterYes.setOnAction(event -> {  // соглашаемся на регистрацию

            loadRegisterWindow();

        });
        RegisterNo.setOnAction(event -> {  // отказываемся от регистрации

            loadAuthorizationWindow();


        });
    }


    void loadRegisterWindow(){
        RegisterYes.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(ProductApplication.class.getResource("registration.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 320, 290);
        }catch (IOException e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Учет товаров в магазине");
        stage.setScene(scene);
        stage.show();
    }

    void loadAuthorizationWindow(){
        RegisterYes.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(ProductApplication.class.getResource("authorization.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 320, 250);
        }catch (IOException e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Учет товаров в магазине");
        stage.setScene(scene);
        stage.show();
    }
}
