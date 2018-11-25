/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.dominio.Endereco;
import br.cefetmg.farmaz.model.dominio.Farmacia;
import br.cefetmg.farmaz.proxy.ManterCidadeProxy;
import br.cefetmg.farmaz.proxy.ManterDisponibilidadeProxy;
import br.cefetmg.farmaz.proxy.ManterEnderecoProxy;
import br.cefetmg.farmaz.proxy.ManterEstadoProxy;
import br.cefetmg.farmaz.proxy.ManterFarmaciaProxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class ListarFarmacias {
    
    public static String executa(HttpServletRequest request) {
        String jsp;
        
        try {
            List<Disponibilidade> listDisponibilidade;
            ArrayList<Farmacia> listFarmacia = new ArrayList();
            
            Long produtoId = Long.parseLong(request.getParameter("CodProduto"));
            Long clienteId = (Long) request.getSession().getAttribute("cod_cliente");
            ManterDisponibilidadeProxy manterDisponibilidade = new ManterDisponibilidadeProxy();
            ManterFarmaciaProxy manterFarmacia = new ManterFarmaciaProxy();
            ManterEnderecoProxy manterEndereco = new ManterEnderecoProxy();
            ManterEstadoProxy manterEstado = new ManterEstadoProxy();
            ManterCidadeProxy manterCidade = new ManterCidadeProxy();
            
            List<Endereco> enderecos = manterEndereco.getEnderecosByClienteId(clienteId);
            Endereco endereco = enderecos.get(0);
            
            listDisponibilidade = manterDisponibilidade.getDisponibilidadeByProdutoId(produtoId);
            for(int i=0; i<listDisponibilidade.size(); i++){
                if(listDisponibilidade.get(i).getEstoque() == 0){
                    listDisponibilidade.remove(listDisponibilidade.get(i));
                }else{
                    listFarmacia.add(manterFarmacia.getFarmaciaById(listDisponibilidade.get(i).getFarmaciaCadastro()));
                }
            }
            
            //Somente farmácias que estão na mesma cidade do cliente
            for(int i=0; i<listFarmacia.size(); i++){
                if(!Objects.equals(listFarmacia.get(i).getCodCidade(), endereco.getCodUf())){
                    listFarmacia.remove(listFarmacia.get(i));
                }
            }
            
            String enderecoCliente = endereco.getRua()+", "+endereco.getNumero()+" - "+endereco.getBairro()
                    +", "+manterCidade.getCidadeById(endereco.getCodCidade()).getNome()+" - "+manterEstado.getEstadoById(endereco.getCodUf()).getSigla();
            
            request.setAttribute("enderecoCliente", enderecoCliente);
            request.setAttribute("farmacias", listFarmacia);
            request.setAttribute("disponibilidade", listDisponibilidade);
            
            jsp = "ListarFarmacias.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
