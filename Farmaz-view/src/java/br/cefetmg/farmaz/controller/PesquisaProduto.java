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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class PesquisaProduto {
    public static String executa(HttpServletRequest request) {
        String jsp;
        
        try {    
            String pesquisa = request.getParameter("pesquisa");
            ManterProdutoProxy manterProduto = new ManterProdutoProxy();
            ManterDisponibilidadeProxy manterDisponibilidade = new ManterDisponibilidadeProxy();
            List<Produto> listProduto;
            List<Disponibilidade> listDisponibilidade;
            List<Produto> listProdutoNova = new ArrayList();
                    
            if(request.getSession().getAttribute("MeuCarrinho") == null){
                listProduto = manterProduto.listAll();
                
                if(!pesquisa.equals("")){
                    for(int i=0; i<listProduto.size(); i++){
                        if(listProduto.get(i).getNome().toLowerCase().contains(pesquisa.toLowerCase())){
                            listProdutoNova.add(listProduto.get(i));
                        }            
                    }
                }else{
                    listProdutoNova = listProduto;
                }
                request.setAttribute("produtos", listProdutoNova);

            }else{
                listDisponibilidade = (List<Disponibilidade>) request.getSession().getAttribute("MeuCarrinho");
                listDisponibilidade = manterDisponibilidade.getDisponibilidadeByFarmaciaId(listDisponibilidade.get(0).getFarmaciaCadastro());
                listProduto = new ArrayList();
                for(Disponibilidade disponibilidade: listDisponibilidade){
                    listProduto.add(manterProduto.getProdutoById(disponibilidade.getProdutoSeq()));
                }
                if(!pesquisa.equals("")){
                    for(int i=0; i<listProduto.size(); i++){
                        if(listProduto.get(i).getNome().toLowerCase().contains(pesquisa.toLowerCase())){
                            listProdutoNova.add(listProduto.get(i));
                        }            
                    }
                }else{
                    listProdutoNova = listProduto;
                }
                request.setAttribute("produtos", listProdutoNova);
                
            }
            
            jsp = "ListarProdutosCliente.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
