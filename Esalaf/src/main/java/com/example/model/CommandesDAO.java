package com.example.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandesDAO extends BaseDAO<Commandes>{
    public CommandesDAO() throws SQLException {
        super();
    }
    @Override
    public void save(Commandes object) throws SQLException {
        String req = "INSERT INTO commandes (client_id, produit_id, quantite, commande_date) SELECT cl.client_id, p.produit_id, ?, ? FROM clients cl , produits p WHERE cl.nom = ? AND p.nom = ? ;";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setLong(1 , object.getQuantite());
        this.preparedStatement.setDate(2 , new java.sql.Date(object.getCommande_date().getTime()));
        this.preparedStatement.setString(3 , object.getClient_nom());
        this.preparedStatement.setString(4 , object.getProduit_nom());
        this.preparedStatement.execute();
    }
    @Override
    public void update(Commandes object) throws SQLException {
        String req = "UPDATE commandes SET client_id = (SELECT client_id FROM clients WHERE nom = ?), " +
                     "produit_id = (SELECT produit_id FROM produits WHERE nom = ?), quantite = ? , commande_date = ? " +
                     "WHERE commande_id = ?";

            this.preparedStatement = this.connection.prepareStatement(req);
            this.preparedStatement.setString(1, object.getClient_nom());
            this.preparedStatement.setString(2, object.getProduit_nom());
            this.preparedStatement.setLong(3, object.getQuantite());
            this.preparedStatement.setDate(4 , new java.sql.Date(object.getCommande_date().getTime()));
            this.preparedStatement.setLong(5, object.getCommande_id());
            this.preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Commandes object) throws SQLException {
        String req = "DELETE FROM commandes WHERE commande_id = ?";

        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setLong(1, object.getCommande_id());
        this.preparedStatement.executeUpdate();
    }
    @Override
    public Commandes getOne(Long id) throws SQLException {
        return null;
    }
    @Override
    public List<Commandes> getAll() throws SQLException {

        List<Commandes> mylist = new ArrayList<Commandes>();
        String req = "SELECT c.commande_id, cl.nom, p.nom, c.quantite , c.commande_date FROM commandes c " +
                     "INNER JOIN clients cl ON c.client_id = cl.client_id " +
                     "INNER JOIN produits p ON c.produit_id = p.produit_id";
        this.statement = this.connection.createStatement();

        this.resultSet =  this.statement.executeQuery(req);

        while (this.resultSet.next()){
            mylist.add( new Commandes(this.resultSet.getLong(1) , this.resultSet.getString(2),
                    this.resultSet.getString(3), this.resultSet.getLong(4) ,
                    this.resultSet.getDate(5)));
        }
        return mylist;
    }
}
