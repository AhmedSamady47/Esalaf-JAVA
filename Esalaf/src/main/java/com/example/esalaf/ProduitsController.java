package com.example.esalaf;

import com.example.model.Produits;
import com.example.model.ProduitsDAO;
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

public class ProduitsController implements Initializable {
    @FXML
    private TableView<Produits> prod_tab;
    @FXML
    private TableColumn<Produits, Long> col_id;
    @FXML
    private TableColumn<Produits, String> col_nom_prod;
    @FXML
    private TableColumn<Produits, Long> col_prix;
    @FXML
    private TextField nom_prod;
    @FXML
    private TextField prix;
    @FXML
    private TextField id_up;
    @FXML
    private TextField nom_up;
    @FXML
    private TextField prix_up;
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
        String nom = nom_prod.getText().trim();
        if (nom.isEmpty()) {
            emptyNom.setText("Case de Nom produit vide!!");
            return;
        }
        try {
            ProduitsDAO proddao = new ProduitsDAO();
            Produits prod = new Produits(0L , nom_prod.getText() , Long.parseLong(prix.getText()));
            proddao.save(prod);
            nom_prod.clear();
            prix.clear();
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
            emptyID_Nom.setText("Case d'ID ou Nom produit vide!!");
            return;
        }
        try {
            ProduitsDAO proddao = new ProduitsDAO();
            Produits prod = new Produits(Long.parseLong(id) , nom , Long.parseLong(prix_up.getText()));
            proddao.update(prod);
            id_up.clear();
            nom_up.clear();
            prix_up.clear();
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
                ProduitsDAO proddao = new ProduitsDAO();
                Produits prodent = new Produits(Long.parseLong(id),"",0L);
                proddao.delete(prodent);
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
        col_id.setCellValueFactory(new PropertyValueFactory<Produits,Long>("produit_id"));
        col_nom_prod.setCellValueFactory(new PropertyValueFactory<Produits,String>("nom"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Produits,Long>("prix"));

        prod_tab.setItems(getDataProduits());
    }

    public static ObservableList<Produits> getDataProduits(){

        ProduitsDAO proddao = null;

        ObservableList<Produits> listfx = FXCollections.observableArrayList();

        try {
            proddao = new ProduitsDAO();
            for(Produits ettemp : proddao.getAll())
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
