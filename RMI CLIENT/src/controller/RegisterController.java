package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    Button btnRegister;

    @FXML
    TextField tfEmail, tfPassword, tfName, tfAddress, tfPhone;

    @FXML
    Label lbLogin;

    public void changeScene () throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/design/login.fxml"));
        Stage stage = (Stage) lbLogin.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 500));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void register () {

        // get data tf
        String name = tfName.getText().trim();
        String email = tfEmail.getText().trim();
        String password = tfPassword.getText().trim();
        String phone = tfPhone.getText().trim();
        String address = tfAddress.getText().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            return;
        }else {
            try {
               String status = Main.authSercive.register(name, email, password, address, phone); // call method register from RMI registry

                // check status return;
               if (status.equals("\"success\"")) {
                   setTextNull();
                   changeScene(); // call method change scene
               }else {
                   setTextNull();
                   System.out.println("Failllllllllllll");
               }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void setTextNull () {
        tfName.setText("");
        tfEmail.setText("");
        tfAddress.setText("");
        tfPassword.setText("");
        tfPhone.setText("");
    }

}
