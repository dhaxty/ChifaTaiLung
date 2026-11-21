package chifatailung.modelo;

// entidad comprobante simulado de consumo en el chifa (boleta o factura, no tributario)
public class Comprobante {
    private String numero, concepto, tipo;
    private double importe;

    public Comprobante(){
    }

    public Comprobante(String numero, String concepto, String tipo, double importe){
        this.numero = numero;
        this.concepto = concepto;
        this.tipo = tipo;
        this.importe = importe;
    }

    public String getNumero(){
        return numero;
    }

    public void setNumero(String numero){
        this.numero = numero;
    }

    public String getConcepto(){
        return concepto;
    }

    public void setConcepto(String concepto){
        this.concepto = concepto;
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public double getImporte(){
        return importe;
    }

    public void setImporte(double importe){
        this.importe = importe;
    }
}
