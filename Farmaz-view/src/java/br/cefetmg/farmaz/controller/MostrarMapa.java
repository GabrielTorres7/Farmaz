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
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class MostrarMapa {
    
    public static String executa(HttpServletRequest request) {

        String jsp = "";

        try {
            ManterCidadeProxy manterCidade = new ManterCidadeProxy();
            ManterEstadoProxy manterEstado = new ManterEstadoProxy();
            ManterEnderecoProxy manterEndereco = new ManterEnderecoProxy();
            ManterFarmaciaProxy manterFarmacia = new ManterFarmaciaProxy();
            ManterDisponibilidadeProxy manterDisponibilidade = new ManterDisponibilidadeProxy();
            
            Long disponibilidadeId = Long.parseLong(request.getParameter("DisponibilidadeId"));
            Disponibilidade disponibilidade = manterDisponibilidade.getDisponibilidadeById(disponibilidadeId);
            String farmaciaId = disponibilidade.getFarmaciaCadastro();
            Long clienteId = (Long) request.getSession().getAttribute("cod_cliente");
            Farmacia farmacia;
            String enderecoFarmacia;
            
            request.setAttribute("disponibilidadeId", disponibilidadeId);
            farmacia = manterFarmacia.getFarmaciaById(farmaciaId);
            enderecoFarmacia = farmacia.getRua()+", "+farmacia.getNumero()+" - "+farmacia.getBairro()
                    +", "+manterCidade.getCidadeById(farmacia.getCodCidade()).getNome()+" - "+manterEstado.getEstadoById(farmacia.getCodUf()).getSigla();
            
            request.setAttribute("enderecoFarmacia", enderecoFarmacia);
            
            List<Endereco> enderecos = manterEndereco.getEnderecosByClienteId(clienteId);
            Endereco endereco = enderecos.get(0);
            String enderecoCliente = endereco.getRua()+", "+endereco.getNumero()+" - "+endereco.getBairro()
                    +", "+manterCidade.getCidadeById(endereco.getCodCidade()).getNome()+" - "+manterEstado.getEstadoById(endereco.getCodUf()).getSigla();
            
            request.setAttribute("enderecoCliente", enderecoCliente);
            
            jsp = "Mapa.jsp";
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
