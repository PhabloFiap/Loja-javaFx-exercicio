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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.IntegerStringConverter;

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
    @FXML TableColumn<Veiculo, Pedido> colPedido;

    @FXML ComboBox<Pedido> cbPedido;

    PedidoDao pedidoDao;
    VeiculoDao veiculoDao;

    public void adicionar(){
        var veiculo = new Veiculo(
            0, 
            txtMarca.getText(), 
            txtModelo.getText(), 
            Integer.valueOf( txtPeso.getText() ), 
            new BigDecimal( txtValor.getText() ),
            cbPedido.getSelectionModel().getSelectedItem()
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

    public void apagarVeiculo(){
        var veiculo = tabela.getSelectionModel().getSelectedItem();
        if(veiculo == null){

            mostraMensagem("Erro","voce deve selecionar um pedido para apagar");
            return;
        }

        try {
            VeiculoDao.apagar(veiculo.getId());
            tabela.getItems().remove(veiculo);
        } catch (SQLException e) {
         mostraMensagem("Erro", "Erro ao apagar");
            e.printStackTrace();
        }
    }
    public void atualizarProduto(Veiculo produto){
        try {
            VeiculoDao.atualizar(produto);
        } catch (SQLException e) {
           mostraMensagem("erro","Erro ao atualizar");
            e.printStackTrace();
        }
    }

    private void mostraMensagem(String titulo, String mensagem) {

        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colMarca.setCellFactory(TextFieldTableCell.forTableColumn());
         colMarca.setOnEditCommit(e-> atualizarProduto(e.getRowValue().marca(e.getNewValue())));
        
        
        
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colModelo.setCellFactory(TextFieldTableCell.forTableColumn());
        colModelo.setOnEditCommit(e-> atualizarProduto(e.getRowValue().modelo(e.getNewValue())));

        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colPeso.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colPeso.setOnEditCommit(e-> atualizarProduto(e.getRowValue().peso(e.getNewValue())));

        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colValor.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        colValor.setOnEditCommit(e-> atualizarProduto(e.getRowValue().valor(e.getNewValue())));
        colPedido.setCellValueFactory(new PropertyValueFactory<>("pedido"));

        colOs.setCellValueFactory(new PropertyValueFactory<>("os") );
        colStatusPedido.setCellValueFactory(new PropertyValueFactory<>("statusPedido") );

        
        try {
            pedidoDao = new PedidoDao();
            veiculoDao = new VeiculoDao();
            cbPedido.getItems().addAll(pedidoDao.buscarTodosPedido());
        } catch (SQLException e1) {
            mostraMensagem("Erro","Erro ao buscar pedidos");
            e1.printStackTrace();
        }

        carregar();
        carregarPedido();

        tabela.setEditable(true);
        tabelaPedido.setEditable(true);
    }

    @FXML
    private TableView<Pedido> tabelaPedido;
    @FXML
    private TableColumn<Pedido, Integer> colOs;
     @FXML
    private TableColumn<Pedido, String> colStatusPedido;
    @FXML
    private TextField txtOs;
    @FXML
    private TextField txtStatusPedido;


        @FXML
        public void adicionarPedido(){
        var pedido = new Pedido(
          Integer.valueOf( txtOs.getText()),
          txtStatusPedido.getText()
           
        );

        try {
        pedidoDao.inserir(pedido);    
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
            var pedido = pedidoDao.buscarTodosPedido();
            pedido.forEach(pedidos -> tabelaPedido.getItems().add(pedidos));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 

        @FXML
        public void excluirPedido() {
             var pedido = tabelaPedido.getSelectionModel().getSelectedItem();
        if(pedido == null){

            mostraMensagem("Erro","voce deve selecionar um pedido para apagar");
            return;
        }

        try {
            VeiculoDao.apagar(pedido.getOs());
            tabelaPedido.getItems().remove(pedido);
        } catch (SQLException e) {
         mostraMensagem("Erro", "Erro ao apagar");
            e.printStackTrace();
        }

        }


   
}
