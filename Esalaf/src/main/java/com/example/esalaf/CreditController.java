package com.example.esalaf;

import com.example.model.Credit;
import com.example.model.CreditDAO;
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

public class CreditController implements Initializable{

    @FXML
    private TableView<Credit> cred_tab;
    @FXML
    private TableColumn<Credit, Long> col_id;
    @FXML
    private TableColumn<Credit, String> col_client;
    @FXML
    private TableColumn<Credit, Long> col_comm;
    @FXML
    private TableColumn<Credit, Long> col_total;
    @FXML
    private TextField nom_cli;
    @FXML
    private TextField total;
    @FXML
    private TextField commande_id;
    @FXML
    private TextField id_up;
    @FXML
    private TextField nom_up;
    @FXML
    private TextField comm_id_up;
    @FXML
    private TextField total_up;
    @FXML
    private Label emptyID_Nom;
    @FXML
    private Label emptyNom_IDcomm;
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
        String comm_id = commande_id.getText().trim();
        if (nom.isEmpty() || comm_id.isEmpty()) {
            emptyNom_IDcomm.setText("Case de Nom du client ou Commande ID vide!!");
            return;
        }
        try {
            CreditDAO clidao = new CreditDAO();
            Credit cred = new Credit(0L , nom_cli.getText() , Long.parseLong(commande_id.getText()), Long.parseLong(total.getText()));
            clidao.save(cred);
            nom_cli.clear();
            total.clear();
            commande_id.clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateTable();
    }
    @FXML
    protected void onUpdateButtonClick() {
        String id = id_up.getText().trim();
        String nom = nom_up.getText().trim();
        String comm_id = comm_id_up.getText().trim();
        if (id.isEmpty() || nom.isEmpty() || comm_id.isEmpty()) {
            emptyID_Nom.setText("Case d'ID, Nom ou Commande ID vide!!");
            return;
        }
        try {
                CreditDAO creddao = new CreditDAO();
                Credit cred = new Credit(Long.parseLong(id_up.getText()), nom_up.getText(), Long.parseLong(comm_id_up.getText()), Long.parseLong(total_up.getText()));
                creddao.update(cred);
                id_up.clear();
                nom_up.clear();
                comm_id_up.clear();
                total_up.clear();
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
                CreditDAO creddao = new CreditDAO();
                Credit credit = new Credit(Long.parseLong(id),"", 0L, 0L);
                creddao.delete(credit);
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
        col_id.setCellValueFactory(new PropertyValueFactory<Credit,Long>("credit_id"));
        col_client.setCellValueFactory(new PropertyValueFactory<Credit,String>("client_nom"));
        col_comm.setCellValueFactory(new PropertyValueFactory<Credit,Long>("commande_id"));
        col_total.setCellValueFactory(new PropertyValueFactory<Credit,Long>("total"));

        cred_tab.setItems(getDataCredit());
    }
    public static ObservableList<Credit> getDataCredit(){

        CreditDAO creddao = null;

        ObservableList<Credit> listfx = FXCollections.observableArrayList();

        try {
            creddao = new CreditDAO();
            for(Credit ettemp : creddao.getAll())
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
