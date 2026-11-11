package chifataitlung.modelo;

// [EXTRAPOLADO]
public class Usuario {
    private String usuario, claveHash;
    private Rol rol;

    public Usuario(){
    }

    public Usuario(String usuario, String claveHash, Rol rol){
        this.usuario = usuario;
        this.claveHash = claveHash;
        this.rol = rol;
    }

    public String getUsuario(){
        return usuario;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getClaveHash(){
        return claveHash;
    }

    public void setClaveHash(String claveHash){
        this.claveHash = claveHash;
    }

    public Rol getRol(){
        return rol;
    }

    public void setRol(Rol rol){
        this.rol = rol;
    }
}
