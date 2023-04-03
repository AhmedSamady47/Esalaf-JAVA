package com.example.model;

public class Credit {

    private Long credit_id;
    private String client_nom;
    private Long commande_id;
    private Long total;

    public Credit() {
    }
    public Credit(Long creditId, String client, Long commandeId, Long total) {
        this.credit_id = creditId;
        this.client_nom = client;
        this.commande_id = commandeId;
        this.total = total;
    }

    public Long getCredit_id() {
        return credit_id;
    }

    public void setCredit_nom(Long credit_id) {
        this.credit_id = credit_id;
    }

    public String getClient_nom() {
        return client_nom;
    }

    public void setClient_id(String client_id) {
        this.client_nom = client_id;
    }

    public Long getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(Long commande_id) {
        this.commande_id = commande_id;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "credit_id=" + credit_id +
                ", client_id=" + client_nom +
                ", commande_id=" + commande_id +
                ", total=" + total +
                '}';
    }
}
