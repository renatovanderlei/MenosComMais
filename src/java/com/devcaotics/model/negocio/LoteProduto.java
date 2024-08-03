package com.devcaotics.model.negocio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class LoteProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @ManyToOne
    private Mercadinho mercadinho;
    private String nomeProduto;
    private String marca;
    private String lote;
    private double precoInicial;
    private String validade;
    private double precoFinal;
    @ManyToOne
    private ONG ongInteressada;

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mercadinho getMercadinho() {
        return mercadinho;
    }

    public void setMercadinho(Mercadinho mercadinho) {
        this.mercadinho = mercadinho;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public double getPrecoInicial() {
        return precoInicial;
    }

    public void setPrecoInicial(double precoInicial) {
        this.precoInicial = precoInicial;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public ONG getOngInteressada() {
        return ongInteressada;
    }

    public void setOngInteressada(ONG ongInteressada) {
        this.ongInteressada = ongInteressada;
    }
}
