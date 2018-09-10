/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.proxy.ManterDisponibilidadeProxy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class FinalizarCompra {
    
    public static String executa(HttpServletRequest request) throws ServletException, IOException {
        
        String jsp;
        
        try {
            ManterDisponibilidadeProxy manterDisponibilidade = new ManterDisponibilidadeProxy();
            Disponibilidade item = new Disponibilidade();
            List<Disponibilidade> carrinho;
            
            if("Mapa".equals(request.getParameter("ultimoJsp"))){
                item = manterDisponibilidade.getDisponibilidadeById(Long.parseLong(request.getParameter("disponibilidadeId")));
                item.setEstoque(Integer.parseInt(request.getParameter("quantidadeProduto")));
                item.setPreco(item.getPreco() * item.getEstoque());
                
                if(request.getSession().getAttribute("MeuCarrinho") == null){
                    carrinho = new ArrayList();                        
                }else{
                    carrinho = (List<Disponibilidade>) request.getSession().getAttribute("MeuCarrinho");
                }          
                
                carrinho.add(item);
                request.getSession().setAttribute("MeuCarrinho", carrinho);
                jsp = "MeuCarrinho.jsp";
                
            }else if("Carrinho".equals(request.getParameter("ultimoJsp"))){
                jsp = "FinalizarCompra.jsp";
                
            }else{
                String erro = "Falha!";
                request.setAttribute("erro", erro);
                jsp = "/Erro.jsp";
            }
             
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
