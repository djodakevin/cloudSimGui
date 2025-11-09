package com.monsupercloud.cloudsimgui;  
import com.monsupercloud.gui.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainView mainView = new MainView();
        primaryStage.setTitle("CloudSim GUI");
        primaryStage.setScene(mainView.createMainScene());
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}