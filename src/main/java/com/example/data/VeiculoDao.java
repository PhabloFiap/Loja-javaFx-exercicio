package com.example.data;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Pedido;
import com.example.model.Veiculo;

public class VeiculoDao {

     
    static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    static final String USER = "rm550687";
    static final String PASS = "151097";



    
    public static void inserir(Veiculo veiculo) throws SQLException {
        var conexao = DriverManager.getConnection(URL, USER, PASS);

        var sql = "INSERT INTO produtos (marca, modelo , peso, valor, pedido_os) VALUES (?, ?, ?, ?,?) ";
        var comando = conexao.prepareStatement(sql);
        comando.setString(1, veiculo.getMarca());
        comando.setString(2, veiculo.getModelo());
        comando.setInt(3, veiculo.getPeso());
        comando.setBigDecimal(4, veiculo.getValor());
        comando.setInt(5,veiculo.getPedido().getOs());
        comando.executeUpdate();

        conexao.close();

    }

    public static List<Veiculo> buscarTodos() throws SQLException{
        var lista = new ArrayList<Veiculo>();

        var conexao = DriverManager.getConnection(URL, USER, PASS);
        var comando = conexao.prepareStatement("SELECT produtos.*,pedidos.os, pedidos.statuspedido FROM produtos INNER JOIN pedidos ON produtos.pedido_os=pedidos.os");
        var resultado = comando.executeQuery();

        while(resultado.next()){
            lista.add (new Veiculo(
                resultado.getLong("id"), 
                resultado.getString("marca"), 
                resultado.getString("modelo"), 
                resultado.getInt("peso"), 
                resultado.getBigDecimal("valor"),
                new Pedido(
                    resultado.getInt("os"),
                    resultado.getString("statusPedido")
                    )

            ));
        }

        conexao.close();
        return lista;
    }

    public static void apagar(long id) throws SQLException {
        var conexao = DriverManager.getConnection(URL, USER, PASS);
        var comando = conexao.prepareStatement("DELETE FROM produtos where id=?");
        comando.setLong(1, id);
        comando.executeUpdate();
        conexao.close();


    }

    public static void atualizar(Veiculo produto) throws SQLException{
        var conexao = DriverManager.getConnection(URL, USER, PASS);
        var comando = conexao.prepareStatement("update produtos SET marca=?, modelo=?, peso=?, valor=? WHERE id=?");
        
        comando.setString(1, produto.getMarca());
        comando.setString(2, produto.getModelo());
        comando.setInt(3, produto.getPeso());
        comando.setBigDecimal(4, produto.getValor());
        comando.setLong(5,produto.getId());

         comando.executeUpdate();
        conexao.close();

    }
    
}
