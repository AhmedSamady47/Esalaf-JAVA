package com.example.esalaf;

import com.example.model.Commandes;
import com.example.model.CommandesDAO;
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
import java.util.Date;
import java.util.ResourceBundle;

public class CommandesController implements Initializable {

    @FXML
    private TableView<Commandes> comm_tab;
    @FXML
    private TableColumn<Commandes, Long> col_id;
    @FXML
    private TableColumn<Commandes, String> col_nom_client;
    @FXML
    private TableColumn<Commandes, String> col_prod;
    @FXML
    private TableColumn<Commandes, Long> col_qtty;
    @FXML
    private TableColumn<Commandes, Date> col_date;
    @FXML
    private TextField nom_cli;
    @FXML
    private TextField nom_prod;
    @FXML
    private DatePicker date_comm;
    @FXML
    private TextField qtty;
    @FXML
    private TextField id_up;
    @FXML
    private TextField client_nom_up;
    @FXML
    private TextField prod_nom_up;
    @FXML
    private TextField qtty_up;
    @FXML
    private DatePicker date_up;
    @FXML
    private Label emptyID_Nom;
    @FXML
    private Label emptyNom_prod;
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
        String nom = nom_cli.getText().trim();
        String prod_nom = nom_prod.getText().trim();
        String qttys = qtty.getText().trim();
        if (nom.isEmpty() || prod_nom.isEmpty() || qttys.isEmpty()) {
            emptyNom_prod.setText("Case de Nom du client ou Produit ou Quantité vide!!");
            return;
        }
        try {
            CommandesDAO commdao = new CommandesDAO();
            Commandes comm = new Commandes(0L , nom_cli.getText() , nom_prod.getText(), Long.parseLong(qtty.getText()), java.sql.Date.valueOf(date_comm.getValue()));
            commdao.save(comm);
            nom_cli.clear();
            nom_cli.clear();
            qtty.clear();
            date_comm.setValue(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateTable();
    }
    @FXML
    protected void onUpdateButtonClick() {
        String id = id_up.getText().trim();
        String nom = client_nom_up.getText().trim();
        String prod_nom = prod_nom_up.getText().trim();
        if (id.isEmpty() || nom.isEmpty() || prod_nom.isEmpty()) {
            emptyID_Nom.setText("Case d'ID, Nom ou Commande ID vide!!");
            return;
        }
        try {
            CommandesDAO commdao = new CommandesDAO();
            Commandes comm = new Commandes(Long.parseLong(id_up.getText()), client_nom_up.getText() , prod_nom_up.getText(), Long.parseLong(qtty_up.getText()), java.sql.Date.valueOf(date_up.getValue()));
            commdao.update(comm);
            id_up.clear();
            client_nom_up.clear();
            prod_nom_up.clear();
            qtty_up.clear();
            date_up.setValue(null);
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
                CommandesDAO commdao = new CommandesDAO();
                Commandes commit = new Commandes(Long.parseLong(id),"","", 0L, new Date());
                commdao.delete(commit);
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
        col_id.setCellValueFactory(new PropertyValueFactory<Commandes,Long>("commande_id"));
        col_nom_client.setCellValueFactory(new PropertyValueFactory<Commandes,String>("client_nom"));
        col_prod.setCellValueFactory(new PropertyValueFactory<Commandes,String>("produit_nom"));
        col_qtty.setCellValueFactory(new PropertyValueFactory<Commandes,Long>("quantite"));
        col_date.setCellValueFactory(new PropertyValueFactory<Commandes,Date>("commande_date"));

        comm_tab.setItems(getDataCommandes());
    }
    public static ObservableList<Commandes> getDataCommandes(){

        CommandesDAO commdao = null;

        ObservableList<Commandes> listfx = FXCollections.observableArrayList();

        try {
            commdao = new CommandesDAO();
            for(Commandes ettemp : commdao.getAll())
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
