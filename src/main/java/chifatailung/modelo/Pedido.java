package chifatailung.modelo;

public class Pedido {
    private String numero, nombreCliente;
    private int numeroMesa;
    private DetallePedido detalles[];
    private int cantidadDetalles;

    public Pedido(){
        this.detalles = new DetallePedido[10];
        this.cantidadDetalles = 0;
    }

    public Pedido(String numero, String nombreCliente, int numeroMesa){
        this.numero = numero;
        this.nombreCliente = nombreCliente;
        this.numeroMesa = numeroMesa;
        this.detalles = new DetallePedido[10];
        this.cantidadDetalles = 0;
    }

    public boolean agregarDetalle(DetallePedido detalle){
        boolean agregado = false;
        if(detalle != null && detalle.getCantidad() >= 1 && detalle.getPrecioUnitario() >= 0 && cantidadDetalles < 10){
            detalles[cantidadDetalles] = detalle;
            cantidadDetalles++;
            agregado = true;
        }
        return agregado;
    }

    public boolean editarDetalle(int indice, DetallePedido detalle){
        boolean editado = false;
        if(indice >= 0 && indice < cantidadDetalles && detalle != null && detalle.getCantidad() >= 1 && detalle.getPrecioUnitario() >= 0){
            detalles[indice] = detalle;
            editado = true;
        }
        return editado;
    }

    public boolean quitarDetalle(int indice){
        boolean quitado = false;
        if(indice >= 0 && indice < cantidadDetalles){
            // desplaza los elementos a la izquierda tras eliminar
            for(int i = indice; i < cantidadDetalles - 1; i++){
                detalles[i] = detalles[i + 1];
            }
            detalles[cantidadDetalles - 1] = null;
            cantidadDetalles--;
            quitado = true;
        }
        return quitado;
    }

    public double calcularTotal(){
        double total = 0.0;
        for(int i = 0; i < cantidadDetalles; i++){
            if(detalles[i] != null){
                total += detalles[i].calcularSubtotal();
            }
        }
        return total;
    }

    public DetallePedido[] listarDetalles(){
        DetallePedido resultado[] = new DetallePedido[cantidadDetalles];
        for(int i = 0; i < cantidadDetalles; i++){
            resultado[i] = detalles[i];
        }
        return resultado;
    }

    public DetallePedido obtenerDetalle(int indice){
        DetallePedido detalle = null;
        if(indice >= 0 && indice < cantidadDetalles){
            detalle = detalles[indice];
        }
        return detalle;
    }

    public String getNumero(){
        return numero;
    }

    public void setNumero(String numero){
        this.numero = numero;
    }

    public String getNombreCliente(){
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente){
        this.nombreCliente = nombreCliente;
    }

    public int getNumeroMesa(){
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa){
        this.numeroMesa = numeroMesa;
    }

    public DetallePedido[] getDetalles(){
        return detalles;
    }

    public void setDetalles(DetallePedido[] detalles){
        this.detalles = detalles;
    }

    public int getCantidadDetalles(){
        return cantidadDetalles;
    }

    public void setCantidadDetalles(int cantidadDetalles){
        this.cantidadDetalles = cantidadDetalles;
    }
}
