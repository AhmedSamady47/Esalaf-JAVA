package com.example.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ProduitsDAO extends BaseDAO<Produits> {

    public ProduitsDAO() throws SQLException {
        super();
    }
    @Override
    public void save(Produits object) throws SQLException {

        String req = "insert into produits (nom , prix) values (? , ?) ;";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setLong(2 , object.getPrix());

        this.preparedStatement.execute();

    }

    @Override
    public void update(Produits object) throws SQLException {

        String req = "update produits set nom = ?, prix = ? where produit_id = ?";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setLong(2 , object.getPrix());
        this.preparedStatement.setLong(3 , object.getProduit_id());

        this.preparedStatement.executeUpdate();

    }

    @Override
    public void delete(Produits object) throws SQLException {

        String req = "delete from produits where produit_id = ?";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setLong(1, object.getProduit_id());

        this.preparedStatement.executeUpdate();

    }

    @Override
    public Produits getOne(Long produit_id) throws SQLException {
        return null;
    }

    // mapping relation --> objet
    @Override
    public List<Produits> getAll() throws SQLException{

        List<Produits> mylist = new ArrayList<Produits>();
        String req = "SELECT * FROM produits" ;

        this.statement = this.connection.createStatement();

        this.resultSet =  this.statement.executeQuery(req);

        while (this.resultSet.next()){
            mylist.add( new Produits(this.resultSet.getLong(1) , this.resultSet.getString(2),
                    this.resultSet.getLong(3)));
        }
        return mylist;
    }
}
