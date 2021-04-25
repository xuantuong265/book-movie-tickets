package controller;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import main.Main;
import model.Fruit;
import main.MyListener;
import model.Person;
import model.Products;
import model.Student;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label fruitNameLable;

    @FXML Label lbUsername;

    @FXML
    private Label fruitPriceLabel;

    @FXML
    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Fruit> fruits = new ArrayList<>();
    private ArrayList<Products> list = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    private Person person;



    private List<Fruit> getData() {
        List<Fruit> fruits = new ArrayList<>();
        Fruit fruit;

        fruit = new Fruit();
        fruit.setName("Kiwi");
        fruit.setPrice(2.99);
        fruit.setImgSrc("/img/kiwi.png");
        fruit.setColor("6A7324");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Coconut");
        fruit.setPrice(3.99);
        fruit.setImgSrc("/img/coconut.png");
        fruit.setColor("A7745B");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Peach");
        fruit.setPrice(1.50);
        fruit.setImgSrc("/img/peach.png");
        fruit.setColor("F16C31");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Grapes");
        fruit.setPrice(0.99);
        fruit.setImgSrc("/img/grapes.png");
        fruit.setColor("291D36");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Watermelon");
        fruit.setPrice(4.99);
        fruit.setImgSrc("/img/watermelon.png");
        fruit.setColor("22371D");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Orange");
        fruit.setPrice(2.99);
        fruit.setImgSrc("/img/orange.png");
        fruit.setColor("FB5D03");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("StrawBerry");
        fruit.setPrice(0.99);
        fruit.setImgSrc("/img/strawberry.png");
        fruit.setColor("80080C");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Mango");
        fruit.setPrice(0.99);
        fruit.setImgSrc("/img/mango.png");
        fruit.setColor("FFB605");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Cherry");
        fruit.setPrice(0.99);
        fruit.setImgSrc("/img/cherry.png");
        fruit.setColor("5F060E");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Banana");
        fruit.setPrice(1.99);
        fruit.setImgSrc("/img/banana.png");
        fruit.setColor("E7C00F");
        fruits.add(fruit);

        return fruits;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // set username
        String name = LoginController.person.getName();
        lbUsername.setText(name);

        Thread thread = new Thread();
        thread.start();
        while (true) {
            try {
                list = Main.productService.getProduct();
                //setProducts (list);
                Thread.sleep(5000);
            } catch (RemoteException | InterruptedException e) {
                e.printStackTrace();
            }
        }



    }

    private void setChoseProduct(Products product) {
        fruitNameLable.setText(product.getName());
        fruitPriceLabel.setText("$ " + product.getPrice_current());

        Image image = new Image(product.getImg());
        fruitImg.setImage(image);
    }

    public void getProducts () {

        new AnimationTimer(){

            @Override
            public void handle(long now) {
                try {
                    list = Main.productService.getProduct();
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(list.get(i).getName());
                    }
                    Thread.sleep(3000);
                } catch (RemoteException | InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }.start();

    }

    public void setProducts (ArrayList<Products> arrayList) {



        try {
            int column = 0;
            int row = 1;
            int i;

            System.out.println("cái quái gì vậy");

            for (i = 0; i < arrayList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/design/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                // truyen du lieu qua item controller
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(arrayList.get(i));

                // click item
                int finalI = i;
                anchorPane.setOnMouseClicked(event -> {
                    setChoseProduct(arrayList.get(finalI));
                });

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void hienthi () {

//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                ArrayList<Student> list = new ArrayList<>();
//                list.clear();
//
//                while (true) {
//                    try {
//                        list = Main.authSercive.getUsers();
//                        System.out.println(list.get(0).getName());
//                        ArrayList<Student> finalList = list;
//                        Platform.runLater(() -> lbName.setText(finalList.get(0).getName()));
//                        Thread.sleep(5000);
//                    } catch (InterruptedException | RemoteException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        thread.start();

//        ArrayList<Student> list = new ArrayList<>();
//        try {
//            list = Main.authSercive.getUsers();
//
//            setData(list.get(0).getName());
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }


    }

}
