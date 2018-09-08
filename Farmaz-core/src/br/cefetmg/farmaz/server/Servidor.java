/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.server;

import br.cefetmg.farmaz.adapter.AdapterThread;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.util.Conversor;
import br.cefetmg.farmaz.util.PacoteDados;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Gabriel
 */
public class Servidor {

    private static DatagramSocket servidorSocket;
    private static final int port = 4445;

    public static void main(String args[]) throws SocketException, IOException, PersistenciaException, LogicaNegocioException, ClassNotFoundException {
        servidorSocket = new DatagramSocket(port);
        while (true) {
            receberDados();
        }
    }

    private static synchronized void receberDados() throws IOException, PersistenciaException, LogicaNegocioException, ClassNotFoundException {

        final int BYTE_LENGTH = 1024;
        byte[] dadosRecebidos = new byte[BYTE_LENGTH];

        PacoteDados pacoteDados;

        //espera ate que algum cliente envie uma requisicao
        DatagramPacket receivePacket = new DatagramPacket(dadosRecebidos,
                dadosRecebidos.length);
        servidorSocket.receive(receivePacket);

        pacoteDados = (PacoteDados) Conversor.toObject(receivePacket.getData());

        InetAddress IPAddress = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();

        //cria uma Thread para tratar a requisicao do cliente
        AdapterThread adaptador = new AdapterThread(IPAddress, clientPort, port, pacoteDados);
        Thread adapterThread = new Thread(adaptador);
        System.out.println("Thread criada!");
        adapterThread.start();
    }

    public static void enviarDados(InetAddress IPAddress, int clientPort, PacoteDados pacoteResposta) throws IOException {

        byte[] enviarDados;

        //transforma o objeto resposta em vetor de bytes para transmissao
        enviarDados = Conversor.toByteArray(pacoteResposta);
        
        //envia a resposta ao cliente
        DatagramPacket sendPacket = new DatagramPacket(enviarDados,
                enviarDados.length, IPAddress, clientPort);
        servidorSocket.send(sendPacket);

    }

}
