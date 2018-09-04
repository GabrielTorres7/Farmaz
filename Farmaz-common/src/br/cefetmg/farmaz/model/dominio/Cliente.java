/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.dominio;

import java.io.Serializable;

/**
 *
 * @author Gabriel
 */
public class Cliente implements Serializable{
    
    private Long id;
    private String nome;
    private String documentoIdentificacao;
    private String numeroTelefone;
    private String email;
    private String senha;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String documentoIdentificacao, String numeroTelefone, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.documentoIdentificacao = documentoIdentificacao;
        this.numeroTelefone = numeroTelefone;
        this.email = email;
        this.senha = senha;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumentoIdentificacao() {
        return documentoIdentificacao;
    }

    public void setDocumentoIdentificacao(String documentoIdentificacao) {
        this.documentoIdentificacao = documentoIdentificacao;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
