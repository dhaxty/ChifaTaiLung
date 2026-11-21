package chifatailung.estructuras.colas;

import chifatailung.modelo.ClienteAtencion;

// nodo enlazado simple para la cola con prioridad segun estilo cNodo del profesor
public class cNodoCola {
    private ClienteAtencion valor;
    private cNodoCola sig;

    public cNodoCola(ClienteAtencion valor){
        this.valor = valor;
        this.sig = null;
    }

    public ClienteAtencion getValor(){
        return valor;
    }

    public void setValor(ClienteAtencion valor){
        this.valor = valor;
    }

    public ClienteAtencion getDato(){
        return valor;
    }

    public void setDato(ClienteAtencion dato){
        this.valor = dato;
    }

    public cNodoCola getSig(){
        return sig;
    }

    public void setSig(cNodoCola sig){
        this.sig = sig;
    }
}
