package com.example.model;

import java.util.Date;

public class Commandes {
    private Long commande_id;
    private String client_nom;
    private String produit_nom;
    private Long quantite;
    private Date commande_date;

    public Commandes() {
    }
    public Commandes(Long commande_id, String client_nom, String produit_nom, Long quantite, Date commande_date) {
        this.commande_id = commande_id;
        this.client_nom = client_nom;
        this.produit_nom = produit_nom;
        this.quantite = quantite;
        this.commande_date = commande_date;
    }
    public Long getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(Long commande_id) {
        this.commande_id = commande_id;
    }

    public String getClient_nom() {
        return client_nom;
    }

    public void setClient_nom(String client_nom) {
        this.client_nom = client_nom;
    }

    public String getProduit_nom() {
        return produit_nom;
    }

    public void setProduit_nom(String produit_nom) {
        this.produit_nom = produit_nom;
    }

    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }

    public Date getCommande_date() {
        return commande_date;
    }

    public void setCommande_date(Date commande_date) {
        this.commande_date = commande_date;
    }
    @Override
    public String toString() {
        return "Commandes{" +
                "commande_id=" + commande_id +
                ", client_nom='" + client_nom + '\'' +
                ", produit_nom='" + produit_nom + '\'' +
                ", quantite=" + quantite +
                ", commande_date=" + commande_date +
                '}';
    }
}
