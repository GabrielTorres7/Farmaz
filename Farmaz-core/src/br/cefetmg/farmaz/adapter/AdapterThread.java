/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.adapter;

import br.cefetmg.farmaz.model.daoImpl.ClienteDAOImpl;
import br.cefetmg.farmaz.model.dominio.Cliente;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterCliente;
import br.cefetmg.farmaz.model.serviceImpl.ManterClienteImpl;
import br.cefetmg.farmaz.server.Servidor;
import br.cefetmg.farmaz.util.Conversor;
import br.cefetmg.farmaz.util.PacoteDados;
import java.io.IOException;
import java.net.InetAddress;
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

        PacoteDados pacoteResposta;

        Cliente cliente;

        Long clienteId;

        ManterCliente manterCliente = new ManterClienteImpl(ClienteDAOImpl.getInstance());

        if (requisicao.equals("CadastrarCliente")) {
            cliente = (Cliente) pacoteDados.getObjeto();
            clienteId = manterCliente.cadastrarCliente(cliente);

            pacoteResposta = new PacoteDados("T", clienteId);

            Servidor.enviarDados(IPAddress, clientPort, pacoteResposta);
        }

    }

}
