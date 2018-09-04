/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.client;

import br.cefetmg.farmaz.util.Conversor;
import br.cefetmg.farmaz.util.PacoteDados;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Gabriel
 */
public class ClienteDistribuicao {

    private static ClienteDistribuicao cliente;

    private DatagramSocket clienteSocket;
    private String servidor;
    private int porta;
    private InetAddress IPAddress;

    private ClienteDistribuicao() throws SocketException, UnknownHostException {
        clienteSocket = new DatagramSocket();
        servidor = "localhost";
        porta = 4445;
        IPAddress = InetAddress.getByName(servidor);
    }

    public static ClienteDistribuicao getInstance() throws SocketException, UnknownHostException {
        if (cliente == null) {
            cliente = new ClienteDistribuicao();
        }

        return cliente;
    }

    public PacoteDados requisicao(PacoteDados pacoteDados) throws IOException, ClassNotFoundException {

        final int BYTE_LENGTH = 1024;

        byte[] dadosEnviados;
        byte[] dadosRecebidos = new byte[BYTE_LENGTH];

        //converte os dados em bytes para serem enviado
        dadosEnviados = Conversor.toByteArray(pacoteDados);

        //envia o pacote para o servidor
        DatagramPacket packet = new DatagramPacket(dadosEnviados,
                dadosEnviados.length, IPAddress, porta);
        clienteSocket.send(packet);

        //recebe o pacote de volta com o objeto requerido 
        DatagramPacket receiveConfirmation = new DatagramPacket(dadosRecebidos,
                dadosRecebidos.length);
        clienteSocket.receive(receiveConfirmation);
        
        //transforma os bytes no objeto e retorna para ser utilizado
        PacoteDados pacoteConfirmacao = (PacoteDados) Conversor.toObject(receiveConfirmation.getData());

        return pacoteConfirmacao;
    }
}
