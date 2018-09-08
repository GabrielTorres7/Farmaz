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
public class ListarProdutosCliente {
    
    public static String executa(HttpServletRequest request) {
        String jsp;
        
        try {    
            ManterProdutoProxy manterProduto = new ManterProdutoProxy();
            ManterDisponibilidadeProxy manterDisponibilidade = new ManterDisponibilidadeProxy();
            List<Produto> listProduto;
            List<Disponibilidade> listDisponibilidade;
            
                    
            if(request.getSession().getAttribute("MeuCarrinho") == null){
                listProduto = manterProduto.listAll();

                request.setAttribute("produtos", listProduto);

            }else{
                listDisponibilidade = (List<Disponibilidade>) request.getSession().getAttribute("MeuCarrinho");
                listDisponibilidade = manterDisponibilidade.getDisponibilidadeByFarmaciaId(listDisponibilidade.get(0).getFarmaciaCadastro());
                listProduto = new ArrayList();
                for(Disponibilidade disponibilidade: listDisponibilidade){
                    listProduto.add(manterProduto.getProdutoById(disponibilidade.getProdutoSeq()));
                }
                request.setAttribute("produtos", listProduto);
                
            }
            
            jsp = "ListarProdutosCliente.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
}
