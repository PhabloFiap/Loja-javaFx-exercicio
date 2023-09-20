package com.example.model;

import java.math.BigDecimal;

public class Veiculo {
    
    private long id;
    private String marca;
    private String modelo;
    private Integer peso;
    private BigDecimal valor;

    public Veiculo(long id, String marca, String modelo, Integer peso, BigDecimal valor) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.peso = peso;
        this.valor = valor;
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

    

}
