package chifatailung.modelo;

// item individual que compone un pedido de consumo en el chifa
public class DetallePedido {
    private String codigoPlato, nombrePlato;
    private int cantidad;
    private double precioUnitario;

    public DetallePedido(){
    }

    public DetallePedido(String codigoPlato, String nombrePlato, int cantidad, double precioUnitario){
        this.codigoPlato = codigoPlato;
        this.nombrePlato = nombrePlato;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // calcula el subtotal de la linea sin acoplarse a cArregloPlatos
    public double calcularSubtotal(){
        return cantidad * precioUnitario;
    }

    public String getCodigoPlato(){
        return codigoPlato;
    }

    public void setCodigoPlato(String codigoPlato){
        this.codigoPlato = codigoPlato;
    }

    public String getNombrePlato(){
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato){
        this.nombrePlato = nombrePlato;
    }

    public int getCantidad(){
        return cantidad;
    }

    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario(){
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario){
        this.precioUnitario = precioUnitario;
    }
}
