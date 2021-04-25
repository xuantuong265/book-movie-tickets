package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import main.Main;
import model.Person;
import model.Student;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    Button btnLogin;

    @FXML
    Label lbRegister, lbName;

    @FXML
    TextField tfEmail, tfPassword;

    @FXML
    RadioButton radioStudent, radioLecturer;

    @FXML
    TilePane tilePane;

    @FXML
    ImageView img;

    @FXML
    Pane pane;

    public static Person person;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void changeScene () throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/design/register.fxml"));
        Stage stage = (Stage) lbRegister.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 500));

    }

    public void login () throws IOException {

        // get data tf
        String email = tfEmail.getText().trim();
        String password = tfPassword.getText().trim();

        // check data
        if (email.isEmpty() || password.isEmpty()) {
            return;
        }else {

            if (Main.authSercive.login(email, password) != null) {
                person = Main.authSercive.login(email, password);

                Parent root = FXMLLoader.load(getClass().getResource("/design/dashboard.fxml"));
                Stage stage = (Stage) lbRegister.getScene().getWindow();
                stage.setScene(new Scene(root, 600, 500));

//                DashboardController dashboardController = new DashboardController();
//                dashboardController.setUser(person);

            }else {
                System.out.println("sai cmnr");
            }


        }

    }





}
