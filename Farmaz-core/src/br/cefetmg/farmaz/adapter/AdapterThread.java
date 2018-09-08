/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.adapter;

import br.cefetmg.farmaz.model.daoImpl.CidadeDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.ClienteDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.DisponibilidadeDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.ProdutoDAOImpl;
import br.cefetmg.farmaz.model.dominio.Cidade;
import br.cefetmg.farmaz.model.dominio.Cliente;
import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.dominio.Produto;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterCidade;
import br.cefetmg.farmaz.model.service.ManterCliente;
import br.cefetmg.farmaz.model.service.ManterDisponibilidade;
import br.cefetmg.farmaz.model.service.ManterProduto;
import br.cefetmg.farmaz.model.serviceImpl.ManterCidadeImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterClienteImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterDisponibilidadeImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterProdutoImpl;
import br.cefetmg.farmaz.server.Servidor;
import br.cefetmg.farmaz.util.Conversor;
import br.cefetmg.farmaz.util.PacoteDados;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class AdapterThread implements Runnable {

    private InetAddress IPAddress;
    private int clientPort;
    private int serverPort;
    private PacoteDados pacoteDados;

    public AdapterThread(InetAddress IPAddress, int clientPort, int serverPort, PacoteDados pacoteDados) {
        this.IPAddress = IPAddress;
        this.clientPort = clientPort;
        this.pacoteDados = pacoteDados;
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        try {
            receberRequisicao();
            return;
        } catch (PersistenciaException ex) {
            Logger.getLogger(AdapterThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LogicaNegocioException ex) {
            Logger.getLogger(AdapterThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdapterThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receberRequisicao() throws PersistenciaException, LogicaNegocioException, IOException {

        String requisicao = pacoteDados.getRequisicao();
        String nome;
        String email;
        String dados[];
        
        PacoteDados pacoteResposta = null;
        
        Cidade cidade;
        Cliente cliente;
        Produto produto;
        Disponibilidade disponibilidade;
        
        Long cidadeId;
        Long clienteId;
        Long produtoId;
        Long disponibilidadeId;
        
        ManterCidade manterCidade = new ManterCidadeImpl(CidadeDAOImpl.getInstance());
        ManterCliente manterCliente = new ManterClienteImpl(ClienteDAOImpl.getInstance());
        ManterProduto manterProduto = new ManterProdutoImpl(ProdutoDAOImpl.getInstance());
        ManterDisponibilidade manterDisponibilidade = new ManterDisponibilidadeImpl(DisponibilidadeDAOImpl.getInstance());
        
        switch (requisicao) {
            //REQUISIÇÕES PARA O SERVICO MANTER CIDADE
            case "CadastrarCidade":
                cidade = (Cidade) pacoteDados.getObjeto();
                cidadeId = manterCidade.cadastrarCidade(cidade);
                //Não é necessário lançar uma exceção nesse ponto, pois a classe de serviço já o faz.
                
                pacoteResposta = new PacoteDados(cidadeId);
                break;
                
            case "AtualizarCidade":
                cidade = (Cidade) pacoteDados.getObjeto();
                manterCidade.atualizarCidade(cidade);
                
                pacoteResposta = new PacoteDados("T");
                break;
                
            case "DeletarCidade":
                cidadeId = (Long) pacoteDados.getObjeto();
                manterCidade.deletarCidade(cidadeId);
                
                pacoteResposta = new PacoteDados("T");
                break;
            
            case "GetCidadeById":
                cidadeId = (Long) pacoteDados.getObjeto();
                cidade = manterCidade.getCidadeById(cidadeId);
                
                pacoteResposta = new PacoteDados(cidade);
                break;
                
            case "GetCidadeByNome":
                nome = (String) pacoteDados.getObjeto();
                cidade = manterCidade.getCidadeByNome(nome);
                
                pacoteResposta = new PacoteDados(cidade);
                break;
                
            case "GetAllCidades":
                List<Cidade> cidades = manterCidade.getAll();
                
                pacoteResposta = new PacoteDados(cidades);
                break;
                
                
            //REQUISIÇÕES PARA O SERVICO MANTER CLIENTE
            case "CadastrarCliente":
                cliente = (Cliente) pacoteDados.getObjeto();
                clienteId = manterCliente.cadastrarCliente(cliente);
                //Não é necessário lançar uma exceção nesse ponto, pois a classe de serviço já o faz.
                
                pacoteResposta = new PacoteDados("T", clienteId);
                break;
                
            case "AtualizarCliente":
                cliente = (Cliente) pacoteDados.getObjeto();
                manterCliente.atualizarCliente(cliente);
                
                pacoteResposta = new PacoteDados("T");
                break;
                
            case "DeletarCliente":
                clienteId = (Long) pacoteDados.getObjeto();
                manterCliente.deletarCliente(clienteId);
                
                pacoteResposta = new PacoteDados("T");
                break;
            
            case "GetClienteById":
                clienteId = (Long) pacoteDados.getObjeto();
                cliente = manterCliente.getClienteById(clienteId);
                
                pacoteResposta = new PacoteDados(cliente);
                break;
                
            case "GetClienteByEmail":
                email = (String) pacoteDados.getObjeto();
                cliente = manterCliente.getClienteByEmail(email);
                
                pacoteResposta = new PacoteDados(cliente);
                break;
                
            case "GetClienteByEmailSenha":
                dados = (String[]) pacoteDados.getObjeto();
                cliente = manterCliente.getClienteByEmailSenha(dados[0], dados[1]);
                
                pacoteResposta = new PacoteDados("T", cliente);
                break;
            
            case "GetAllClientes":
                List<Cliente> clientes = manterCliente.getAll();
                
                pacoteResposta = new PacoteDados(clientes);
                break;
            
                
            //REQUISIÇÕES PARA O SERVICO MANTER PRODUTO
            case "CadastrarProduto":
                produto = (Produto) pacoteDados.getObjeto();
                produtoId = manterProduto.cadastrarProduto(produto);
                
                pacoteResposta = new PacoteDados(produtoId);
                break;
            
            case "AtualizarProduto":
                produto = (Produto) pacoteDados.getObjeto();
                manterProduto.atualizarProduto(produto);
                
                pacoteResposta = new PacoteDados("T");
                break;
                
            case "DeletarProduto":
                produtoId = (Long) pacoteDados.getObjeto();
                manterProduto.deletarProduto(produtoId);
                
                pacoteResposta = new PacoteDados("T");
                break;
            
            case "GetProdutoById":
                produtoId = (Long) pacoteDados.getObjeto();
                produto = manterProduto.getProdutoById(produtoId);
                
                pacoteResposta = new PacoteDados(produto);
                break;
                
            case "GetProdutoByNome":
                nome = (String) pacoteDados.getObjeto();
                produto = manterProduto.getProdutoByNome(nome);
                
                pacoteResposta = new PacoteDados(produto);
                break;
                
            case "GetAllProdutos":
                List<Produto> produtos = manterProduto.listAll();
                
                pacoteResposta = new PacoteDados("T", produtos);
                break;
                
                
            //REQUISIÇÕES PARA O SERVICO MANTER DISPONIBILIDADE
            case "InserirDisponibilidade":
                disponibilidade = (Disponibilidade) pacoteDados.getObjeto();
                disponibilidadeId = manterDisponibilidade.inserirDisponibilidade(disponibilidade);
                
                pacoteResposta = new PacoteDados(disponibilidadeId);
                break;
            
            case "AtualizarDisponibilidade":
                disponibilidade = (Disponibilidade) pacoteDados.getObjeto();
                manterDisponibilidade.atualizarDisponibilidade(disponibilidade);
                
                pacoteResposta = new PacoteDados("T");
                break;
                
            case "DeletarDisponibilidade":
                disponibilidadeId = (Long) pacoteDados.getObjeto();
                manterDisponibilidade.deletarDisponibilidade(disponibilidadeId);
                
                pacoteResposta = new PacoteDados("T");
                break;
                
            case "GetDisponibilidadeById":
                disponibilidadeId = (Long) pacoteDados.getObjeto();
                disponibilidade = manterDisponibilidade.getDisponibilidadeById(disponibilidadeId);
                
                pacoteResposta = new PacoteDados(disponibilidade);
                break;
            
            case "GetDisponibilidadeByProdutoId":
                produtoId = (Long) pacoteDados.getObjeto();
                List<Disponibilidade> produtosDisp = manterDisponibilidade.getDisponibilidadeByProdutoId(produtoId);
                
                pacoteResposta = new PacoteDados(produtosDisp);
                break;
                
            case "GetDisponibilidadeByFarmaciaId":
                String farmaciaId = (String) pacoteDados.getObjeto();
                List<Disponibilidade> produtosFarmacia = manterDisponibilidade.getDisponibilidadeByFarmaciaId(farmaciaId);
                
                pacoteResposta = new PacoteDados(produtosFarmacia);
                break;
        }
        
        Servidor.enviarDados(IPAddress, clientPort, pacoteResposta);

    }

}
