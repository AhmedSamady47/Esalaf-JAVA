package com.example.model;

public class Client {
    private Long client_id ;
    private String nom ;
    private String telephone ;
    private String adresse ;

    public Client() {
    }
    public Client(Long client_id, String nom, String telephone, String adresse) {
        this.client_id = client_id;
        this.nom = nom;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client_id=" + client_id +
                ", nom='" + nom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}
