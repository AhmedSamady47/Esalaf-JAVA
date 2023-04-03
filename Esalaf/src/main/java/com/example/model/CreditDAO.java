package com.example.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditDAO extends BaseDAO<Credit>{
    public CreditDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Credit object) throws SQLException {
        String req = "INSERT INTO credit (client_id, commande_id, total) SELECT cl.client_id, ?, ? FROM clients cl WHERE cl.nom = ? ;";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setLong(1 , object.getCommande_id());
        this.preparedStatement.setLong(2 , object.getTotal());
        this.preparedStatement.setString(3 , object.getClient_nom());
        this.preparedStatement.execute();
    }
    @Override
    public void update(Credit object) throws SQLException {
        String selectReq = "SELECT credit_id FROM credit WHERE commande_id = ?";

        this.preparedStatement = this.connection.prepareStatement(selectReq);
        this.preparedStatement.setLong(1, object.getCommande_id());
        resultSet = this.preparedStatement.executeQuery();
        String req = "UPDATE credit SET client_id = (SELECT client_id FROM clients WHERE nom = ?), commande_id = ? , total = ? WHERE credit_id = ?";

        if (resultSet.next()) {
            this.preparedStatement = this.connection.prepareStatement(req);
            this.preparedStatement.setString(1, object.getClient_nom());
            this.preparedStatement.setLong(2, object.getCommande_id());
            this.preparedStatement.setLong(3, object.getTotal());
            this.preparedStatement.setLong(4, resultSet.getLong("credit_id"));
            this.preparedStatement.execute();
        }
    }

    @Override
    public void delete(Credit object) throws SQLException {
        String req = "delete from credit where credit_id = ?";

        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setLong(1, object.getCredit_id());
        this.preparedStatement.executeUpdate();
    }
    @Override
    public Credit getOne(Long id) throws SQLException {
        return null;
    }
    @Override
    public List<Credit> getAll() throws SQLException {

        List<Credit> mylist = new ArrayList<Credit>();
        String req = "SELECT c.credit_id, cl.nom, c.commande_id, c.total FROM credit c INNER JOIN clients cl ON c.client_id = cl.client_id";
        this.statement = this.connection.createStatement();

        this.resultSet =  this.statement.executeQuery(req);

        while (this.resultSet.next()){
            mylist.add( new Credit(this.resultSet.getLong(1) , this.resultSet.getString(2),
                    this.resultSet.getLong(3) , this.resultSet.getLong(4)));
        }
        return mylist;
    }

}
