/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.daoImpl.DisponibilidadeDAOImpl;
import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.service.ManterDisponibilidade;
import br.cefetmg.farmaz.model.serviceImpl.ManterDisponibilidadeImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class AdicionarCarrinho {
    
    public static String executa(HttpServletRequest request) throws ServletException, IOException {
        
        String jsp;
        
        try {
            ManterDisponibilidade manterDisponibilidade = new ManterDisponibilidadeImpl(DisponibilidadeDAOImpl.getInstance());
            ArrayList<Disponibilidade> carrinho = new ArrayList();
            Disponibilidade item = new Disponibilidade();
            
            item = manterDisponibilidade.getDisponibilidadeById(Long.parseLong(request.getParameter("disponibilidadeId")));
            item.setEstoque(Integer.parseInt(request.getParameter("quantidadeProduto")));
            item.setPreco(item.getPreco() * item.getEstoque());
            
            if(request.getSession().getAttribute("MeuCarrinho") != null){
                carrinho = (ArrayList<Disponibilidade>) request.getSession().getAttribute("MeuCarrinho");
            }
            
            carrinho.add(item);
            request.getSession().setAttribute("MeuCarrinho", carrinho);
            
            jsp = ListarProdutosCliente.executa(request);
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}