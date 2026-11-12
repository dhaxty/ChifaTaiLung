package chifatailung.modelo;

// plato de la carta con su codigo unico, precio y disponibilidad
public class Plato {
    private String codigo, nombre, categoria;
    private double precio;
    private boolean disponible;

    public Plato(){
    }

    public Plato(String codigo, String nombre, String categoria, double precio, boolean disponible){
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.disponible = disponible;
    }

    public String getCodigo(){
        return codigo;
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getCategoria(){
        return categoria;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    public double getPrecio(){
        return precio;
    }

    public void setPrecio(double precio){
        this.precio = precio;
    }

    public boolean isDisponible(){
        return disponible;
    }

    public void setDisponible(boolean disponible){
        this.disponible = disponible;
    }
}
