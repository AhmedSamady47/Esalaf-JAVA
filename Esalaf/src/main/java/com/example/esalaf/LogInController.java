package com.example.esalaf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @FXML
    private Label incorrectPass;
    @FXML
    private TextField nom;
    @FXML
    private PasswordField password = new PasswordField();
    @FXML
    private Button LogInButt;
    @FXML
    private void onLogInButtonClick(ActionEvent event) {
        String unom = nom.getText();
        String password1 = password.getText();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/esalaf", "root", "")) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE nom=? AND password=?");
            stmt.setString(1, unom);
            stmt.setString(2, password1);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Stage currentStage = (Stage) LogInButt.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("clients.fxml"));
                Scene scene = new Scene(root);
                currentStage.setScene(scene);
                currentStage.show();
            } else {
                // Login failed
                incorrectPass.setText("Nom ou Mot de passe incorrect!!");
                nom.clear();
                password.clear();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}