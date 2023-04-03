package com.example.esalaf;

import com.example.model.Client;
import com.example.model.ClientDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {
    @FXML
    private TableView<Client> mytab;
    @FXML
    private TableColumn<Client, Long> col_id;
    @FXML
    private TableColumn<Client, String> col_nom;
    @FXML
    private TableColumn<Client, String> col_tele;
    @FXML
    private TableColumn<Client, String> col_adresse;
    @FXML
    private TextField nom;
    @FXML
    private TextField tele;
    @FXML
    private TextField adresse;
    @FXML
    private TextField id_up;
    @FXML
    private TextField nom_up;
    @FXML
    private TextField tel_up;
    @FXML
    private TextField adr_up;
    @FXML
    private Label emptyID_Nom;
    @FXML
    private Label emptyNom;
    @FXML
    private TextField id_del;
    @FXML
    private Label emptyID_Delete;
    @FXML
    private Button CreditTabButt;
    @FXML
    private Button ProduitsTabButt;
    @FXML
    private Button CommandesTabButt;
    @FXML
    private Button ClientsTabButt;
    @FXML
    protected void onSaveButtonClick(){
        String nomtosave = nom.getText().trim();
        if (nomtosave.isEmpty()) {
            emptyNom.setText("Case Nom vide!!");
            return;
        }
        try {
            ClientDAO clidao = new ClientDAO();
            Client cli = new Client(0L , nom.getText() , tele.getText(), adresse.getText());
            clidao.save(cli);
            nom.clear();
            tele.clear();
            adresse.clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateTable();
    }

    @FXML
    protected void onUpdateButtonClick() {
        String id = id_up.getText().trim();
        String nom = nom_up.getText().trim();
        if (id.isEmpty() || nom.isEmpty()) {
            emptyID_Nom.setText("Case d'ID ou Nom vide!!");
            return;
        }
        try {
            ClientDAO clidao = new ClientDAO();
            Client cli = new Client(Long.parseLong(id), nom, tel_up.getText(), adr_up.getText());
            clidao.update(cli);
            id_up.clear();
            nom_up.clear();
            tel_up.clear();
            adr_up.clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateTable();
    }
    @FXML
    protected void onDeleteButtonClick() {
        String id = id_del.getText();
        try {
            if (id.isEmpty()) {
                emptyID_Delete.setText("Case d'ID vide!!");
            } else {
                ClientDAO clidao = new ClientDAO();
                Client client = new Client(Long.parseLong(id),"","","");
                clidao.delete(client);
                id_del.clear();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        UpdateTable();
    }
    @FXML
    protected void onCreditTabButtonClick() throws IOException {
        Stage currentStage = (Stage) CreditTabButt.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("credit.fxml"));
        Scene scene = new Scene(root);
        currentStage.setScene(scene);
        currentStage.show();
    }
    @FXML
    protected void onProduitsTabButtonClick() throws IOException {
        Stage currentStage = (Stage) ProduitsTabButt.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("produits.fxml"));
        Scene scene = new Scene(root);
        currentStage.setScene(scene);
        currentStage.show();
    }
    @FXML
    protected void onCommandesTabButtonClick() throws IOException {
        Stage currentStage = (Stage) CommandesTabButt.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("commandes.fxml"));
        Scene scene = new Scene(root);
        currentStage.setScene(scene);
        currentStage.show();
    }
    @FXML
    protected void onClientsTabButtonClick() throws IOException {
        Stage currentStage = (Stage) ClientsTabButt.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("clients.fxml"));
        Scene scene = new Scene(root);
        currentStage.setScene(scene);
        currentStage.show();
    }
    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Client,Long>("client_id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
        col_tele.setCellValueFactory(new PropertyValueFactory<Client,String>("telephone"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<Client,String>("adresse"));

        mytab.setItems(getDataClients());
    }

    public static ObservableList<Client> getDataClients(){

        ClientDAO clidao = null;

        ObservableList<Client> listfx = FXCollections.observableArrayList();

        try {
            clidao = new ClientDAO();
            for(Client ettemp : clidao.getAll())
                listfx.add(ettemp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }

}
