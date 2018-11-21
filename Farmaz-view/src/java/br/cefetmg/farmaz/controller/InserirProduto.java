/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.dominio.Produto;
import br.cefetmg.farmaz.proxy.ManterDisponibilidadeProxy;
import br.cefetmg.farmaz.proxy.ManterProdutoProxy;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class InserirProduto {
    
    public static String executa(HttpServletRequest request) {

        String jsp = "";

        try {
            String nome = request.getParameter("nome");
            boolean receita;
            if("sim".equals(request.getParameter("receita"))){
                receita = true;
            }else{
                receita = false;
            }
            String descricao = request.getParameter("descricao");
            String laboratorio = request.getParameter("laboratorio");
            String cadastro = request.getParameter("cadastro");
            int estoque = Integer.parseInt(request.getParameter("estoque"));
            double preco = Double.parseDouble(request.getParameter("preco"));
            
            ManterProdutoProxy manterProduto = new ManterProdutoProxy();
            ManterDisponibilidadeProxy manterDisponibilidade = new ManterDisponibilidadeProxy();
            Produto produto = new Produto();
            Disponibilidade disponibilidade = new Disponibilidade();
            
            if(manterProduto.getProdutoByNome(nome) != null){
                produto = manterProduto.getProdutoByNome(nome);
                
            }else{
                produto.setNome(nome);
                produto.setDescricao(descricao);
                produto.setReceita(receita);
                produto.setCadastroAnvisa(cadastro);
                produto.setLaboratorio(laboratorio);
                produto.setId(manterProduto.cadastrarProduto(produto));  
            }
            
            disponibilidade.setProdutoSeq(produto.getId());
            disponibilidade.setFarmaciaCadastro((String) request.getSession().getAttribute("cod_farmacia"));
            disponibilidade.setEstoque(estoque);
            disponibilidade.setPreco(preco);

            manterDisponibilidade.inserirDisponibilidade(disponibilidade);
            jsp = ListarProdutosFarmacia.executa(request);
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
