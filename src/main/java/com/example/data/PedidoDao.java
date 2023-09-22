package com.example.data;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Veiculo;
import com.example.model.Pedido;

public class PedidoDao {

     
    static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    static final String USER = "rm550687";
    static final String PASS = "151097";



    
    public static void inserir(Pedido pedido) throws SQLException {
        var conexao = DriverManager.getConnection(URL, USER, PASS);

        var sql = "INSERT INTO pedidos (os, statuspedido) VALUES (?,?) ";
        var comando = conexao.prepareStatement(sql);
        comando.setInt(1, pedido.getOs());
        comando.setString(2, pedido.getStatusPedido());
        comando.executeUpdate();

        conexao.close();

    }

    public static List<Pedido> buscarTodosPedido() throws SQLException{
        var tabelaPedido = new ArrayList<Pedido>();

        var conexao = DriverManager.getConnection(URL, USER, PASS);
        var comando = conexao.prepareStatement("SELECT * FROM pedidos");
        var resultado = comando.executeQuery();

        while(resultado.next()){
            tabelaPedido.add (new Pedido(
                resultado.getInt("os"),
                resultado.getString("statuspedido") 
                
            ));
        }

        conexao.close();
        return tabelaPedido;
    }
    
}
