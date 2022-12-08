package com.example.kyrsach;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductApplication extends Application {
    public static String user_id;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoginWindow = new FXMLLoader(ProductApplication.class.getResource("authorization.fxml"));

        Scene scene = new Scene(fxmlLoginWindow.load(), 320, 240);

        stage.setTitle("Учет товаров в магазине");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}