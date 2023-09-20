package com.example.data;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Veiculo;

public class VeiculoDao {

     
    static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    static final String USER = "rm550687";
    static final String PASS = "151097";



    
    public static void inserir(Veiculo veiculo) throws SQLException {
        var conexao = DriverManager.getConnection(URL, USER, PASS);

        var sql = "INSERT INTO produtos (marca, modelo , peso, valor) VALUES (?, ?, ?, ?) ";
        var comando = conexao.prepareStatement(sql);
        comando.setString(1, veiculo.getMarca());
        comando.setString(2, veiculo.getModelo());
        comando.setInt(3, veiculo.getPeso());
        comando.setBigDecimal(4, veiculo.getValor());
        comando.executeUpdate();

        conexao.close();

    }

    public static List<Veiculo> buscarTodos() throws SQLException{
        var lista = new ArrayList<Veiculo>();

        var conexao = DriverManager.getConnection(URL, USER, PASS);
        var comando = conexao.prepareStatement("SELECT * FROM produtos");
        var resultado = comando.executeQuery();

        while(resultado.next()){
            lista.add (new Veiculo(
                resultado.getLong("id"), 
                resultado.getString("marca"), 
                resultado.getString("modelo"), 
                resultado.getInt("peso"), 
                resultado.getBigDecimal("valor")
            ));
        }

        conexao.close();
        return lista;
    }
    
}
