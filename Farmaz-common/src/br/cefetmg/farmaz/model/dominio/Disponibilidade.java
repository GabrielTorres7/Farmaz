/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.dominio;

/**
 *
 * @author Gabriel
 */
public class Disponibilidade {
    
    private Long id;
    private Long produtoSeq;
    private String farmaciaCadastro;
    private int estoque;
    private double preco;
    private int avaliacao;

    public Disponibilidade() {
    }

    public Disponibilidade(Long produtoSeq, String farmaciaCadastro, int estoque, double preco, int avaliacao) {
        this.produtoSeq = produtoSeq;
        this.farmaciaCadastro = farmaciaCadastro;
        this.estoque = estoque;
        this.preco = preco;
        this.avaliacao = avaliacao;
    }
    
    public Long getProdutoSeq() {
        return produtoSeq;
    }

    public void setProdutoSeq(Long produtoSeq) {
        this.produtoSeq = produtoSeq;
    }

    public String getFarmaciaCadastro() {
        return farmaciaCadastro;
    }

    public void setFarmaciaCadastro(String farmaciaCadastro) {
        this.farmaciaCadastro = farmaciaCadastro;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }   
}
