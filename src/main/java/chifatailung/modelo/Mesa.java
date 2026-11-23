package chifatailung.modelo;

public class Mesa {
    private int numero, capacidad, fila, columna;
    private EstadoMesa estado;

    public Mesa(){
    }

    public Mesa(int numero, int capacidad, int fila, int columna, EstadoMesa estado){
        this.numero = numero;
        this.capacidad = capacidad;
        this.fila = fila;
        this.columna = columna;
        this.estado = estado;
    }

    public int getNumero(){
        return numero;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }

    public int getCapacidad(){
        return capacidad;
    }

    public void setCapacidad(int capacidad){
        this.capacidad = capacidad;
    }

    public int getFila(){
        return fila;
    }

    public void setFila(int fila){
        this.fila = fila;
    }

    public int getColumna(){
        return columna;
    }

    public void setColumna(int columna){
        this.columna = columna;
    }

    public EstadoMesa getEstado(){
        return estado;
    }

    public void setEstado(EstadoMesa estado){
        this.estado = estado;
    }
}
