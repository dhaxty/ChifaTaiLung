package chifatailung.estructuras.arboles;

import chifatailung.modelo.Plato;

public class cNodoABB {
    private Plato valor;
    private cNodoABB izq, der;

    public cNodoABB(Plato valor){
        this.valor = valor;
    }

    public Plato getValor(){
        return valor;
    }

    public void setValor(Plato valor){
        this.valor = valor;
    }

    public cNodoABB getIzq(){
        return izq;
    }

    public void setIzq(cNodoABB izq){
        this.izq = izq;
    }

    public cNodoABB getDer(){
        return der;
    }

    public void setDer(cNodoABB der){
        this.der = der;
    }
}
