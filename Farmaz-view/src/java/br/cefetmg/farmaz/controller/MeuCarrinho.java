/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class MeuCarrinho {
    
     public static String executa(HttpServletRequest request) throws ServletException, IOException {
        
        String jsp;
        
        try {                    
            if(request.getSession().getAttribute("MeuCarrinho") == null){
                String erro = "Você ainda não adicionou nenhum produto no carrinho!";
                request.setAttribute("erro", erro);
                jsp = "/Erro.jsp";
            }else{
                jsp = "/MeuCarrinho.jsp";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}