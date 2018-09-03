/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class ExcluirProdutoCarrinho {

    public static String executa(HttpServletRequest request) {
        String jsp = "";
        try {
            Long idDisponibilidade = Long.parseLong(request.getParameter("cod"));
            ArrayList<Disponibilidade> carrinho = (ArrayList<Disponibilidade>) request.getSession().getAttribute("MeuCarrinho");
            Disponibilidade item = new Disponibilidade();

            for (int i = 0; i < carrinho.size(); i++) {
                item = carrinho.get(i);
                if (item.getId() == idDisponibilidade) {
                    carrinho.remove(item);
                }
            }

            if (!carrinho.isEmpty()) {
                jsp = "MeuCarrinho.jsp";
                request.getSession().setAttribute("MeuCarrinho", carrinho);
            } else {
                request.getSession().removeAttribute("MeuCarrinho");
                jsp = ListarProdutosCliente.executa(request);
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
