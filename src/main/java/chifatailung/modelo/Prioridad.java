package chifatailung.modelo;

public enum Prioridad {
    PREFERENCIAL(1, "Preferencial"),
    REGULAR(2, "Regular");

    private int nivel;
    private String descripcion;

    Prioridad(int nivel, String descripcion){
        this.nivel = nivel;
        this.descripcion = descripcion;
    }

    public int getNivel(){
        return nivel;
    }

    public String getDescripcion(){
        return descripcion;
    }
}
