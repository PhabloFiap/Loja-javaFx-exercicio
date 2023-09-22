package com.example.model;


public class Pedido {
    
    private Integer os;
    private String statusPedido;
    
    

    public Pedido(Integer os ) {
        this.os = os;
        
    }

    public Integer getOs() {
        return os;
    }

    public void setOs(Integer os) {
        this.os = os;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return statusPedido;
    }

    public Pedido(Integer os, String statusPedido) {
        this.os = os;
        this.statusPedido = statusPedido;
    }

    

    

}
