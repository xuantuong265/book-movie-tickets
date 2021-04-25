package main;

import api.AuthService;
import api.ProductService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main extends Application {

    public static AuthService authSercive;
    public static ProductService productService;

    @Override
    public void start(Stage stage) throws Exception{

        Registry registry = LocateRegistry.getRegistry("localhost", 1234);

        authSercive = (AuthService) registry.lookup("auth");
        productService = (ProductService) registry.lookup("product"); // send req to registry

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/design/register.fxml"));

        Parent root = loader.load();

        stage.setScene(new Scene(root));

        stage.setTitle("Hello world");

        stage.show();

        System.out.println("Client connected");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
