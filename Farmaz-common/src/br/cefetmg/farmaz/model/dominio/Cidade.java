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
public class Cidade implements Serializable{
    
    private Long cidadeId;
    private Long ufId;
    private String nome;
    
    public Cidade() {
    }
    
    public Cidade(Long cidadeId, Long ufId, String nome) {
        this.cidadeId = cidadeId;
        this.ufId = ufId;
        this.nome = nome;
    }

    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidadeId) {
        this.cidadeId = cidadeId;
    }

    public Long getUfId() {
        return ufId;
    }

    public void setUfId(Long ufId) {
        this.ufId = ufId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
