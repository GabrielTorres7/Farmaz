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
public class Endereco implements Serializable{
    
    private Long enderecoId;
    private Long clienteId;
    private Long codCidade;
    private Long codUf;
    private String cep;
    private String bairro;
    private String rua;
    private int numero;
    private String complemento;

    public Endereco() {
    }

    public Endereco(Long enderecoId, Long clienteId, Long codCidade, Long codUf, String cep, String bairro, String rua, int numero, String complemento) {
        this.enderecoId = enderecoId;
        this.clienteId = clienteId;
        this.codCidade = codCidade;
        this.codUf = codUf;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Endereco(Long enderecoId, Long clienteId, Long codCidade, Long codUf, String cep, String bairro, String rua, int numero) {
        this.enderecoId = enderecoId;
        this.clienteId = clienteId;
        this.codCidade = codCidade;
        this.codUf = codUf;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }
    
    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getCodCidade() {
        return codCidade;
    }

    public void setCodCidade(Long codCidade) {
        this.codCidade = codCidade;
    }

    public Long getCodUf() {
        return codUf;
    }

    public void setCodUf(Long codUf) {
        this.codUf = codUf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
}
