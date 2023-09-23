package com.example.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Pedido;

public class PedidoDao {

    private Connection conexao;

    
     
    public PedidoDao() throws SQLException {
    conexao = ConnectionFactory.getConnection();

    }

    static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    static final String USER = "rm550687";
    static final String PASS = "151097";



    
    public void inserir(Pedido pedido) throws SQLException {
        

        var sql = "INSERT INTO pedidos (os, statuspedido) VALUES (?,?) ";
        var comando = conexao.prepareStatement(sql);
        comando.setInt(1, pedido.getOs());
        comando.setString(2, pedido.getStatusPedido());
        comando.executeUpdate();

        

    }

    public  List<Pedido> buscarTodosPedido() throws SQLException{
        var tabelaPedido = new ArrayList<Pedido>();

        var comando = conexao.prepareStatement("SELECT * FROM pedidos");
        var resultado = comando.executeQuery();

        while(resultado.next()){
            tabelaPedido.add (new Pedido(
                resultado.getInt("os"),
                resultado.getString("statuspedido") 
                
            ));
        }

        
        return tabelaPedido;
    }
    
}
