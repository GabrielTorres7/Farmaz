/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.daoImpl;

import br.cefetmg.farmaz.model.dao.ItemPedidoDAO;
import br.cefetmg.farmaz.model.dominio.ItemPedido;
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
public class ItemPedidoDAOImpl implements ItemPedidoDAO{
    
    private static ItemPedidoDAOImpl itemPedidoDAO = null;

    private ItemPedidoDAOImpl() {
    }

    public static ItemPedidoDAOImpl getInstance() {
        if (itemPedidoDAO == null) {
            itemPedidoDAO = new ItemPedidoDAOImpl();
        }
        return itemPedidoDAO;
    }
    
    @Override
    public Long insert(ItemPedido itemPedido) throws PersistenciaException {
        if (itemPedido == null){
            throw new PersistenciaException("Domínio não pode ser nulo.");
        }
        
        Long itemPedidoId = null;
        
        try {
            Connection connection = ManterConexao.getInstance().getConnection();
            
            String sql = "INSERT INTO item_pedido (seq_pedido, seq_produto, quantidade) " +
                         "    VALUES (?, ?, ?) RETURNING seq_item_pedido";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, itemPedido.getPedidoId());
            pstmt.setLong(2, itemPedido.getProdutoId());
            pstmt.setInt(3, itemPedido.getQuantidade());
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                itemPedidoId = rs.getLong("seq_item_pedido");
                itemPedido.setPedidoId(itemPedidoId);
            }

            rs.close();
            pstmt.close();
            connection.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ItemPedidoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex);
        }
        
        return itemPedidoId;
    }

    @Override
    public boolean update(ItemPedido itemPedido) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();
            
            String sql = "UPDATE item_pedido " +
                           " SET seq_pedido = ? " +
                           "     seq_produto = ? " +
                           "     quantidade = ? " +
                         " WHERE seq_item_pedido = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, itemPedido.getPedidoId());
            pstmt.setLong(2, itemPedido.getProdutoId());
            pstmt.setInt(3, itemPedido.getQuantidade());
            pstmt.setLong(3, itemPedido.getItemPedidoId());
            
            pstmt.close();
            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e); 
        }
        
        return true;
    }

    @Override
    public boolean remove(Long itemPedidoId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "DELETE FROM item_pedido WHERE seq_item_pedido = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
 
            pstmt.setLong(1, itemPedidoId);
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
    public ItemPedido getItemPedidoById(Long itemPedidoId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM item_pedido WHERE seq_item_pedido = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, itemPedidoId);
            ResultSet rs = pstmt.executeQuery();

            ItemPedido itemPedido = null;
            if (rs.next()) {
                itemPedido = new ItemPedido();
                itemPedido.setItemPedidoId(itemPedidoId);
                itemPedido.setPedidoId(rs.getLong("seq_pedido"));
                itemPedido.setProdutoId(rs.getLong("seq_produto"));
                itemPedido.setQuantidade(rs.getInt("quantidade"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return itemPedido;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<ItemPedido> getItensPedidoByPedidoId(Long pedidoId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM item_pedido WHERE seq_pedido = ? ORDER BY seq_produto";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, pedidoId);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<ItemPedido> listProdutos = null;
            ItemPedido itemPedido = null;

            if (rs.next()) {
                listProdutos = new ArrayList<>();
                do {
                    itemPedido = new ItemPedido();
                    itemPedido.setItemPedidoId(rs.getLong("seq_item_pedido"));
                    itemPedido.setPedidoId(rs.getLong("seq_pedido"));
                    itemPedido.setProdutoId(rs.getLong("seq_produto"));
                    itemPedido.setQuantidade(rs.getInt("quantidade"));
                    listProdutos.add(itemPedido);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            connection.close();

            return listProdutos;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
    
}
