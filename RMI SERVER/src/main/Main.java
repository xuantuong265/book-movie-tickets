package main;

import api.AuthService;
import api.ProductService;
import data.AuthIpml;
import data.ProductIpml;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Student;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Registry registry = LocateRegistry.createRegistry(1234);

        AuthIpml authIpml = new AuthIpml();
        ProductIpml productIpml = new ProductIpml();


        AuthService authService = (AuthService) UnicastRemoteObject.exportObject(authIpml, 0);
        ProductService productService = (ProductService) UnicastRemoteObject.exportObject(productIpml, 0);

        registry.rebind("auth", authService);
        registry.rebind("product", productService);

        System.out.println("Server ready !!!");


    }


    public static void main(String[] args) {
        launch(args);
    }
}
