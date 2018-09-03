/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.util;

/**
 *
 * @author Gabriel
 */
public class PacoteDados {
    
    private String requisicao;
    private Object objeto;

    public PacoteDados() {
    }
    
    public PacoteDados(String requisicao) {
        this.requisicao = requisicao;
    }
    
    public PacoteDados(String requisicao, Object objeto) {
        this.requisicao = requisicao;
        this.objeto = objeto;
    }

    public String getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(String requisicao) {
        this.requisicao = requisicao;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
    
}
