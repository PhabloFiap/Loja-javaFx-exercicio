package com.example.model;

import java.math.BigDecimal;

public class Veiculo {

    private long id;
    private String marca;
    private String modelo;
    private Integer peso;
    private BigDecimal valor;
    private Pedido pedido;

    public Veiculo(long id, String marca, String modelo, Integer peso, BigDecimal valor, Pedido pedido) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.peso = peso;
        this.valor = valor;
        this.pedido = pedido;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Veiculo marca(String marca) {
        this.marca = marca;
        return this;
    }

      public Veiculo modelo(String modelo) {
        this.modelo = modelo;
        return this;
    }
      public Veiculo peso(Integer peso) {
        this.peso = peso;
        return this;
    }
     public Veiculo valor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    

}
