/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.adapter;

import br.cefetmg.farmaz.model.daoImpl.CidadeDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.ClienteDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.DisponibilidadeDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.EnderecoDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.EstadoDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.FarmaciaDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.ItemPedidoDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.PedidoDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.ProdutoDAOImpl;
import br.cefetmg.farmaz.model.dominio.Cidade;
import br.cefetmg.farmaz.model.dominio.Cliente;
import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.dominio.Endereco;
import br.cefetmg.farmaz.model.dominio.Estado;
import br.cefetmg.farmaz.model.dominio.Farmacia;
import br.cefetmg.farmaz.model.dominio.ItemPedido;
import br.cefetmg.farmaz.model.dominio.Pedido;
import br.cefetmg.farmaz.model.dominio.Produto;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterCidade;
import br.cefetmg.farmaz.model.service.ManterCliente;
import br.cefetmg.farmaz.model.service.ManterDisponibilidade;
import br.cefetmg.farmaz.model.service.ManterEndereco;
import br.cefetmg.farmaz.model.service.ManterEstado;
import br.cefetmg.farmaz.model.service.ManterFarmacia;
import br.cefetmg.farmaz.model.service.ManterItemPedido;
import br.cefetmg.farmaz.model.service.ManterPedido;
import br.cefetmg.farmaz.model.service.ManterProduto;
import br.cefetmg.farmaz.model.serviceImpl.ManterCidadeImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterClienteImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterDisponibilidadeImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterEnderecoImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterEstadoImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterFarmaciaImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterItemPedidoImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterPedidoImpl;
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
        Disponibilidade disponibilidade;
        Endereco endereco;
        Estado estado;
        Farmacia farmacia;
        ItemPedido itemPedido;
        Pedido pedido;
        Produto produto;
        
        Long dominioId;
        String farmaciaId;
        List<Pedido> pedidos;
        
        ManterCidade manterCidade = new ManterCidadeImpl(CidadeDAOImpl.getInstance());
        ManterCliente manterCliente = new ManterClienteImpl(ClienteDAOImpl.getInstance());
        ManterDisponibilidade manterDisponibilidade = new ManterDisponibilidadeImpl(DisponibilidadeDAOImpl.getInstance());
        ManterEndereco manterEndereco = new ManterEnderecoImpl(EnderecoDAOImpl.getInstance());
        ManterEstado manterEstado = new ManterEstadoImpl(EstadoDAOImpl.getInstance());
        ManterFarmacia manterFarmacia = new ManterFarmaciaImpl(FarmaciaDAOImpl.getInstance());
        ManterItemPedido manterItemPedido = new ManterItemPedidoImpl(ItemPedidoDAOImpl.getInstance());
        ManterPedido manterPedido = new ManterPedidoImpl(PedidoDAOImpl.getInstance());
        ManterProduto manterProduto = new ManterProdutoImpl(ProdutoDAOImpl.getInstance());
        
        switch (requisicao) {
            //REQUISIÇÕES PARA O SERVICO MANTER CIDADE
            case "CadastrarCidade":
                cidade = (Cidade) pacoteDados.getObjeto();
                dominioId = manterCidade.cadastrarCidade(cidade);
                //Não é necessário lançar uma exceção nesse ponto, pois a classe de serviço já o faz.
                
                pacoteResposta = new PacoteDados(dominioId);
                break;
                
            case "AtualizarCidade":
                cidade = (Cidade) pacoteDados.getObjeto();
                manterCidade.atualizarCidade(cidade);
                
                pacoteResposta = new PacoteDados("T");
                break;
                
            case "DeletarCidade":
                dominioId = (Long) pacoteDados.getObjeto();
                manterCidade.deletarCidade(dominioId);
                
                pacoteResposta = new PacoteDados("T");
                break;
            
            case "GetCidadeById":
                dominioId = (Long) pacoteDados.getObjeto();
                cidade = manterCidade.getCidadeById(dominioId);
                
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
                dominioId = manterCliente.cadastrarCliente(cliente);
                //Não é necessário lançar uma exceção nesse ponto, pois a classe de serviço já o faz.
                
                pacoteResposta = new PacoteDados("T", dominioId);
                break;
                
            case "AtualizarCliente":
                cliente = (Cliente) pacoteDados.getObjeto();
                manterCliente.atualizarCliente(cliente);
                
                pacoteResposta = new PacoteDados("T");
                break;
                
            case "DeletarCliente":
                dominioId = (Long) pacoteDados.getObjeto();
                manterCliente.deletarCliente(dominioId);
                
                pacoteResposta = new PacoteDados("T");
                break;
            
            case "GetClienteById":
                dominioId = (Long) pacoteDados.getObjeto();
                cliente = manterCliente.getClienteById(dominioId);
                
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
            
                
            //REQUISIÇÕES PARA O SERVICO MANTER DISPONIBILIDADE
            case "InserirDisponibilidade":
                disponibilidade = (Disponibilidade) pacoteDados.getObjeto();
                dominioId = manterDisponibilidade.inserirDisponibilidade(disponibilidade);
                
                pacoteResposta = new PacoteDados(dominioId);
                break;
            
            case "AtualizarDisponibilidade":
                disponibilidade = (Disponibilidade) pacoteDados.getObjeto();
                manterDisponibilidade.atualizarDisponibilidade(disponibilidade);
                
                pacoteResposta = new PacoteDados("T");
                break;
                
            case "DeletarDisponibilidade":
                dominioId = (Long) pacoteDados.getObjeto();
                manterDisponibilidade.deletarDisponibilidade(dominioId);
                
                pacoteResposta = new PacoteDados("T");
                break;
                
            case "GetDisponibilidadeById":
                dominioId = (Long) pacoteDados.getObjeto();
                disponibilidade = manterDisponibilidade.getDisponibilidadeById(dominioId);
                
                pacoteResposta = new PacoteDados(disponibilidade);
                break;
            
            case "GetDisponibilidadeByProdutoId":
                dominioId = (Long) pacoteDados.getObjeto();
                List<Disponibilidade> produtosDisp = manterDisponibilidade.getDisponibilidadeByProdutoId(dominioId);
                
                pacoteResposta = new PacoteDados(produtosDisp);
                break;
                
            case "GetDisponibilidadeByFarmaciaId":
                farmaciaId = (String) pacoteDados.getObjeto();
                List<Disponibilidade> produtosFarmacia = manterDisponibilidade.getDisponibilidadeByFarmaciaId(farmaciaId);
                
                pacoteResposta = new PacoteDados(produtosFarmacia);
                break;
            
                
            //REQUISIÇÕES PARA O SERVICO MANTER ENDERECO
            case "InserirEndereco":
                endereco = (Endereco) pacoteDados.getObjeto();
                dominioId = manterEndereco.inserirEndereco(endereco);
                
                pacoteResposta = new PacoteDados(dominioId);
                break;
                
            case "AtualizarEndereco":
                endereco = (Endereco) pacoteDados.getObjeto();
                manterEndereco.atualizarEndereco(endereco);
                
                pacoteResposta = new PacoteDados("T");
                break;
                
            case "DeletarEndereco":
                dominioId = (Long) pacoteDados.getObjeto();
                manterEndereco.deletarEndereco(dominioId);
                
                pacoteResposta = new PacoteDados("T");
                break;
                
            case "GetEnderecoById":
                dominioId = (Long) pacoteDados.getObjeto();
                endereco = manterEndereco.getEnderecoById(dominioId);
                
                pacoteResposta = new PacoteDados(endereco);
                break;
            
            case "GetEnderecosByClienteId":
                dominioId = (Long) pacoteDados.getObjeto();
                List<Endereco> enderecos = manterEndereco.getEnderecosByClienteId(dominioId);
                
                pacoteResposta = new PacoteDados(enderecos);
                break;
                
                
            //REQUISIÇÕES PARA O SERVICO MANTER ESTADO
            case "CadastrarEstado":
                estado = (Estado) pacoteDados.getObjeto();
                dominioId = manterEstado.cadastrarEstado(estado);
                
                pacoteResposta = new PacoteDados(dominioId);
                break;
                
            case "AtualizarEstado":
                estado = (Estado) pacoteDados.getObjeto();
                manterEstado.atualizarEstado(estado);
                
                pacoteResposta = new PacoteDados("T");
                break;
                
            case "DeletarEstado":
                dominioId = (Long) pacoteDados.getObjeto();
                manterEstado.deletarEstado(dominioId);
                
                pacoteResposta = new PacoteDados("T");
                break;
                
            case "GetEstadoById":
                dominioId = (Long) pacoteDados.getObjeto();
                estado = manterEstado.getEstadoById(dominioId);
                
                pacoteResposta = new PacoteDados(estado);
                break;
                
            case "GetEstadoBySigla":
                String sigla = (String) pacoteDados.getObjeto();
                estado = manterEstado.getEstadoBySigla(sigla);
                
                pacoteResposta = new PacoteDados(estado);
                break;
                
            case "GetAllEstados":
                List<Estado> estados = manterEstado.getAll();
                
                pacoteResposta = new PacoteDados("T", estados);
                break;
                
                
            //REQUISIÇÕES PARA O SERVICO MANTER FARMACIA
            case "CadastrarFarmacia":
                farmacia = (Farmacia) pacoteDados.getObjeto();
                dominioId = manterFarmacia.cadastrarFarmacia(farmacia);
                
                pacoteResposta = new PacoteDados(dominioId);
                break;
                
            case "AtualizarFarmacia":
                farmacia = (Farmacia) pacoteDados.getObjeto();
                manterFarmacia.atualizarFarmacia(farmacia);
                
                pacoteResposta = new PacoteDados("T");
                break;
                
            case "DeletarFarmacia":
                farmaciaId = (String) pacoteDados.getObjeto();
                manterFarmacia.deletarFarmacia(farmaciaId);
                
                pacoteResposta = new PacoteDados("T");
                break;
                
            case "GetFarmaciaById":
                farmaciaId = (String) pacoteDados.getObjeto();
                farmacia = manterFarmacia.getFarmaciaById(farmaciaId);
                
                pacoteResposta = new PacoteDados(farmacia);
                break;
            
            case "GetFarmaciaByEmail":
                email = (String) pacoteDados.getObjeto();
                farmacia = manterFarmacia.getFarmaciaByEmail(email);
                
                pacoteResposta = new PacoteDados(farmacia);
                break;
            
            case "GetFarmaciaByEmailSenha":
                dados = (String[]) pacoteDados.getObjeto();
                farmacia = manterFarmacia.getFarmaciaByEmailSenha(dados[0], dados[1]);
                
                pacoteResposta = new PacoteDados(farmacia);
                break;
                
            case "GetAllFarmacias":
                List<Farmacia> farmacias = manterFarmacia.listAll();
                
                pacoteResposta = new PacoteDados(farmacias);
                break;
                
                
            //REQUISIÇÕES PARA O SERVICO MANTER ITEM PEDIDO
            case "InserirItemPedido":
                itemPedido = (ItemPedido) pacoteDados.getObjeto();
                dominioId = manterItemPedido.inserirItemPedido(itemPedido);
                
                pacoteResposta = new PacoteDados(dominioId);
                break;
                
            case "AtualizarItemPedido":
                itemPedido = (ItemPedido) pacoteDados.getObjeto();
                manterItemPedido.atualizarItemPedido(itemPedido);
                
                pacoteResposta = new PacoteDados("T");
                break;
                
            case "DeletarItemPedido":
                dominioId = (Long) pacoteDados.getObjeto();
                manterItemPedido.deletarItemPedido(dominioId);
                
                pacoteResposta = new PacoteDados("T");
                break;
                
            case "GetItemPedidoById":
                dominioId = (Long) pacoteDados.getObjeto();
                itemPedido = manterItemPedido.getItemPedidoById(dominioId);
                
                pacoteResposta = new PacoteDados(itemPedido);
                break;
                
            case "GetItensPedidoByPedidoId":
                dominioId = (Long) pacoteDados.getObjeto();
                List<ItemPedido> itensPedido = manterItemPedido.getItensPedidoByPedidoId(dominioId);
                
                pacoteResposta = new PacoteDados(itensPedido);
                break;
            
                
            //REQUISIÇÕES PARA O SERVICO MANTER PEDIDO
            case "CriarPedido":
                pedido = (Pedido) pacoteDados.getObjeto();
                dominioId = manterPedido.criarPedido(pedido);
                
                pacoteResposta = new PacoteDados(dominioId);
                break;
                
            case "AtualizarPedido":
                pedido = (Pedido) pacoteDados.getObjeto();
                manterPedido.atualizarPedido(pedido);
                
                pacoteResposta = new PacoteDados("T");
                break;
             
            case "DeletarPedido":
                dominioId = (Long) pacoteDados.getObjeto();
                manterPedido.deletarPedido(dominioId);
                
                pacoteResposta = new PacoteDados("T");
                break;
                
            case "GetPedidoById":
                dominioId = (Long) pacoteDados.getObjeto();
                pedido = manterPedido.getPedidoById(dominioId);
                
                pacoteResposta = new PacoteDados(pedido);
                break;
                
            case "GetPedidosByClienteId":
                dominioId = (Long) pacoteDados.getObjeto();
                pedidos = manterPedido.getPedidosByClienteId(dominioId);
                
                pacoteResposta = new PacoteDados(pedidos);
                break;
            
            case "GetPedidosByFarmaciaId":
                dominioId = Long.parseLong((String) pacoteDados.getObjeto());
                pedidos = manterPedido.getPedidosByFarmaciaId(dominioId);
                
                pacoteResposta = new PacoteDados(pedidos);
                break;
                
            case "GetPedidosByClienteIdAndStatus":
                Object[] dados1 = (Object[]) pacoteDados.getObjeto();
                pedidos = manterPedido.getPedidosByClienteIdAndStatus((Long) dados1[0], (char) dados1[1]);
                
                pacoteResposta = new PacoteDados(pedidos);
                break;
                
            case "GetPedidosByFarmaciaIdAndStatus":
                Object[] dados2 = (Object[]) pacoteDados.getObjeto();
                pedidos = manterPedido.getPedidosByFarmaciaIdAndStatus(Long.parseLong((String) dados2[0]), (char) dados2[1]);
                
                pacoteResposta = new PacoteDados(pedidos);
                break;
                
                
            //REQUISIÇÕES PARA O SERVICO MANTER PRODUTO
            case "CadastrarProduto":
                produto = (Produto) pacoteDados.getObjeto();
                dominioId = manterProduto.cadastrarProduto(produto);
                
                pacoteResposta = new PacoteDados(dominioId);
                break;
            
            case "AtualizarProduto":
                produto = (Produto) pacoteDados.getObjeto();
                manterProduto.atualizarProduto(produto);
                
                pacoteResposta = new PacoteDados("T");
                break;
                
            case "DeletarProduto":
                dominioId = (Long) pacoteDados.getObjeto();
                manterProduto.deletarProduto(dominioId);
                
                pacoteResposta = new PacoteDados("T");
                break;
            
            case "GetProdutoById":
                dominioId = (Long) pacoteDados.getObjeto();
                produto = manterProduto.getProdutoById(dominioId);
                
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
            
        }
        
        Servidor.enviarDados(IPAddress, clientPort, pacoteResposta);

    }

}
