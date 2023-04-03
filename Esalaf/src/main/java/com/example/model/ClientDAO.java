package com.example.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends BaseDAO<Client> {
    public ClientDAO() throws SQLException {
        super();
    }

    // mapping objet --> relation
    @Override
    public void save(Client object) throws SQLException {

        String req = "insert into clients (nom , telephone, adresse) values (? , ? , ?) ;";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setString(2 , object.getTelephone());
        this.preparedStatement.setString(3 , object.getAdresse());

        this.preparedStatement.execute();

    }

    @Override
    public void update(Client object) throws SQLException {

        String req = "update clients set nom = ?, telephone = ?, adresse = ? where client_id = ?";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setString(2 , object.getTelephone());
        this.preparedStatement.setString(3 , object.getAdresse());
        this.preparedStatement.setLong(4, object.getClient_id());

        this.preparedStatement.executeUpdate();

    }

    @Override
    public void delete(Client object) throws SQLException {

        String req = "delete from clients where client_id = ?";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setLong(1, object.getClient_id());

        this.preparedStatement.executeUpdate();

    }

    @Override
    public Client getOne(Long client_id) throws SQLException {
        return null;
    }

    // mapping relation --> objet
    @Override
    public List<Client> getAll() throws SQLException{

        List<Client> mylist = new ArrayList<Client>();
        String req = "SELECT * FROM clients" ;

        this.statement = this.connection.createStatement();

        this.resultSet =  this.statement.executeQuery(req);

        while (this.resultSet.next()){
            mylist.add( new Client(this.resultSet.getLong(1) , this.resultSet.getString(2),
                    this.resultSet.getString(3) , this.resultSet.getString(4)));
        }
        return mylist;
    }
}
