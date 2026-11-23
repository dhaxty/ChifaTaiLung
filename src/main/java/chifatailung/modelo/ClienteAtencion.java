package chifatailung.modelo;

public class ClienteAtencion {
    private int numeroTurno;
    private String nombreCliente;
    private Prioridad prioridad;
    private long ordenLlegada;

    public ClienteAtencion(){
    }

    public ClienteAtencion(int numeroTurno, String nombreCliente, Prioridad prioridad, long ordenLlegada){
        this.numeroTurno = numeroTurno;
        this.nombreCliente = nombreCliente;
        this.prioridad = prioridad;
        this.ordenLlegada = ordenLlegada;
    }

    public int getNumeroTurno(){
        return numeroTurno;
    }

    public void setNumeroTurno(int numeroTurno){
        this.numeroTurno = numeroTurno;
    }

    public String getNombreCliente(){
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente){
        this.nombreCliente = nombreCliente;
    }

    public Prioridad getPrioridad(){
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad){
        this.prioridad = prioridad;
    }

    public long getOrdenLlegada(){
        return ordenLlegada;
    }

    public void setOrdenLlegada(long ordenLlegada){
        this.ordenLlegada = ordenLlegada;
    }
}
