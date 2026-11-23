package chifatailung.estructuras.listas;

import chifatailung.modelo.Pedido;

public class cNodoPedido {
    private Pedido valor;
    private cNodoPedido sig;

    public cNodoPedido(Pedido valor){
        this.valor = valor;
        this.sig = null;
    }

    public Pedido getValor(){
        return valor;
    }

    public void setValor(Pedido valor){
        this.valor = valor;
    }

    public Pedido getDato(){
        return valor;
    }

    public void setDato(Pedido dato){
        this.valor = dato;
    }

    public cNodoPedido getSig(){
        return sig;
    }

    public void setSig(cNodoPedido sig){
        this.sig = sig;
    }
}
