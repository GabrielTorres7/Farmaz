/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.daoImpl;

import br.cefetmg.farmaz.model.dao.ClienteDAO;
import br.cefetmg.farmaz.model.dominio.Cliente;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.util.bd.ManterConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class ClienteDAOImpl implements ClienteDAO {

    private static ClienteDAOImpl clienteDAO = null;

    private ClienteDAOImpl() {
    }

    public static ClienteDAOImpl getInstance() {
        if (clienteDAO == null) {
            clienteDAO = new ClienteDAOImpl();
        }
        return clienteDAO;
    }

    @Override
    public Long insert(Cliente cliente) throws PersistenciaException {
        if (cliente == null) {
            throw new PersistenciaException("Domínio não pode ser nulo.");
        }

        Long clienteId = null;

        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "INSERT INTO cliente (nome, email, senha, documento_identificacao, telefone) "
                    + "    VALUES (?, ?, md5(?), ?, ?) RETURNING seq_cliente";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getSenha());
            pstmt.setString(4, cliente.getDocumentoIdentificacao());
            pstmt.setString(5, cliente.getNumeroTelefone());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                clienteId = rs.getLong("seq_cliente");
                cliente.setId(clienteId);
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex);
        }

        return clienteId;
    }

    @Override
    public boolean update(Cliente cliente) throws PersistenciaException {
        try {

            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "UPDATE cliente "
                    + " SET nome = ?, "
                    + "     email = ? "
                    + "     documento_identificacao = ? "
                    + "     telefone = ? "
                    + "     senha = ? "
                    + " WHERE seq_cliente = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getDocumentoIdentificacao());
            pstmt.setString(4, cliente.getNumeroTelefone());
            pstmt.setString(5, cliente.getSenha());
            pstmt.setLong(6, cliente.getId());
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public boolean remove(Long clienteId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "DELETE FROM cliente WHERE seq_cliente = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, clienteId);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public Cliente getClienteById(Long clienteId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM cliente WHERE seq_cliente = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, clienteId);
            ResultSet rs = pstmt.executeQuery();

            Cliente cliente = null;
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(clienteId);
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setDocumentoIdentificacao(rs.getString("documento_identificacao"));
                cliente.setNumeroTelefone(rs.getString("telefone"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public Cliente getClienteByEmail(String email) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM cliente WHERE email = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            Cliente cliente = null;
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getLong("seq_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setDocumentoIdentificacao(rs.getString("documento_identificacao"));
                cliente.setNumeroTelefone(rs.getString("telefone"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
    
    @Override
    public Cliente getClienteByEmailSenha(String email, String senha) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM cliente WHERE email = ? AND senha = md5(?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, senha);
            ResultSet rs = pstmt.executeQuery();

            Cliente cliente = null;
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getLong("seq_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setDocumentoIdentificacao(rs.getString("documento_identificacao"));
                cliente.setNumeroTelefone(rs.getString("telefone"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Cliente> listAll() throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM cliente ORDER BY nome";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Cliente> listAll = null;
            Cliente cliente = null;

            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    cliente = new Cliente();
                    cliente.setId(rs.getLong("seq_cliente"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setSenha(rs.getString("senha"));
                    cliente.setDocumentoIdentificacao(rs.getString("documento_identificacao"));
                    cliente.setNumeroTelefone(rs.getString("telefone"));
                    listAll.add(cliente);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            connection.close();

            return listAll;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

}
