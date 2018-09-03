/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.daoImpl.DisponibilidadeDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.ProdutoDAOImpl;
import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.dominio.Produto;
import br.cefetmg.farmaz.model.service.ManterDisponibilidade;
import br.cefetmg.farmaz.model.service.ManterProduto;
import br.cefetmg.farmaz.model.serviceImpl.ManterDisponibilidadeImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterProdutoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabriel
 */
public class ListarProdutosCliente {
    
    public static String executa(HttpServletRequest request) {
        String jsp;
        
        try {    
            ManterProduto manterProduto = new ManterProdutoImpl(ProdutoDAOImpl.getInstance());
            ManterDisponibilidade manterDisponibilidade = new ManterDisponibilidadeImpl(DisponibilidadeDAOImpl.getInstance());
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
