/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.daoImpl.CidadeDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.DisponibilidadeDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.EnderecoDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.EstadoDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.FarmaciaDAOImpl;
import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.dominio.Endereco;
import br.cefetmg.farmaz.model.dominio.Farmacia;
import br.cefetmg.farmaz.model.service.ManterCidade;
import br.cefetmg.farmaz.model.service.ManterDisponibilidade;
import br.cefetmg.farmaz.model.service.ManterEndereco;
import br.cefetmg.farmaz.model.service.ManterEstado;
import br.cefetmg.farmaz.model.service.ManterFarmacia;
import br.cefetmg.farmaz.model.serviceImpl.ManterCidadeImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterDisponibilidadeImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterEnderecoImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterEstadoImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterFarmaciaImpl;
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
            ManterCidade manterCidade = new ManterCidadeImpl(CidadeDAOImpl.getInstance());
            ManterEstado manterEstado = new ManterEstadoImpl(EstadoDAOImpl.getInstance());
            ManterEndereco manterEndereco = new ManterEnderecoImpl(EnderecoDAOImpl.getInstance());
            ManterFarmacia manterFarmacia = new ManterFarmaciaImpl(FarmaciaDAOImpl.getInstance());
            ManterDisponibilidade manterDisponibilidade = new ManterDisponibilidadeImpl(DisponibilidadeDAOImpl.getInstance());
            
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
