package com.example.model;

public class Produits {

    private Long produit_id;
    private String nom;
    private Long prix;

    public Produits(){

    }
    public Produits(Long produit_id, String nom, Long prix) {
        this.produit_id = produit_id;
        this.nom = nom;
        this.prix = prix;
    }
    public Long getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(Long produit_id) {
        this.produit_id = produit_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getPrix() {
        return prix;
    }

    public void setPrix(Long prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produits{" +
                "produit_id=" + produit_id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                '}';
    }

}
