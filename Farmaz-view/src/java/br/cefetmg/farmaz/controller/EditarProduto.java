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
public class EditarProduto {

    public static String executa(HttpServletRequest request) {

        String jsp = "";

        try {
            ManterProdutoProxy manterProduto = new ManterProdutoProxy();
            ManterDisponibilidadeProxy manterDisponibilidade = new ManterDisponibilidadeProxy();
            Disponibilidade disponibilidade = new Disponibilidade();
            Produto produto = new Produto();

            disponibilidade = manterDisponibilidade.getDisponibilidadeById(Long.parseLong(request.getParameter("CodProduto")));
            produto = manterProduto.getProdutoById(disponibilidade.getProdutoSeq());

            request.setAttribute("disponibilidade", disponibilidade);
            request.setAttribute("produto", produto);

            jsp = "EditarProduto.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
