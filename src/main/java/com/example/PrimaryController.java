package com.example;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.data.PedidoDao;
import com.example.data.VeiculoDao;
import com.example.model.Pedido;
import com.example.model.Veiculo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController implements Initializable {

    @FXML TextField txtMarca;
    @FXML TextField txtModelo;
    @FXML TextField txtPeso;
    @FXML TextField txtValor;

    @FXML TableView<Veiculo> tabela;

    @FXML TableColumn<Veiculo, String> colMarca;
    @FXML TableColumn<Veiculo, String> colModelo;
    @FXML TableColumn<Veiculo, Integer> colPeso;
    @FXML TableColumn<Veiculo, BigDecimal> colValor;

    public void adicionar(){
        var veiculo = new Veiculo(
            0, 
            txtMarca.getText(), 
            txtModelo.getText(), 
            Integer.valueOf( txtPeso.getText() ), 
            new BigDecimal( txtValor.getText() )
        );

        try {
        VeiculoDao.inserir(veiculo);    
        tabela.getItems().add(veiculo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

   public void carregar(){
        tabela.getItems().clear();
        try {
            var veiculos = VeiculoDao.buscarTodos();
            veiculos.forEach(veiculo -> tabela.getItems().add(veiculo));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colOs.setCellValueFactory(new PropertyValueFactory<>("os") );

        carregar();
        carregarPedido();
    }

    @FXML
    private TableView<Pedido> tabelaPedido;
    @FXML
    private TableColumn<Pedido, Integer> colOs;
    @FXML
    private TextField txtOs;


        @FXML
        public void adicionarPedido(){
        var pedido = new Pedido(
          Integer.valueOf( txtOs.getText() )
           
        );

        try {
        PedidoDao.inserir(pedido);    
        tabelaPedido.getItems().add(pedido);
        } catch (Exception e) {
            e.printStackTrace();
        }
        carregarPedido();
    }

        @FXML
        void atualizarPedido() {

        }

          public void carregarPedido(){
        tabelaPedido.getItems().clear();
        try {
            var pedido = PedidoDao.buscarTodosPedido();
            pedido.forEach(pedidos -> tabelaPedido.getItems().add(pedidos));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        @FXML
        void excluirPedido(ActionEvent event) {

        }


   
}
