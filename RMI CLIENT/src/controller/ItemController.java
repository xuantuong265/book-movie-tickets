package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.MyListener;
import model.Products;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class ItemController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label lbPrice;

    @FXML
    private ImageView img;

//    @FXML
//    private void click(MouseEvent mouseEvent) {
//        myListener.onClickListener(product);
//    }

    private Products product;
    //private MyListener myListener;

    public void setData(Products product) throws MalformedURLException {
        this.product = product;
        nameLabel.setText(product.getName());
        lbPrice.setText("$ " + product.getPrice_current());

        System.out.println("hihihi " + product.getName());

        Image image = new Image(product.getImg());
        img.setImage(image);
    }
}
