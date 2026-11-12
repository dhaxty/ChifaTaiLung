package chifatailung.controlador;

import chifatailung.modelo.Rol;
import chifatailung.modelo.Usuario;

// controlador de inicio de sesion para conectar FrmLogin con el servicio
public class AuthController {
    private AutenticacionService servicio;
    private Usuario usuarioAutenticado;

    public AuthController(){
        this.servicio = new AutenticacionService();
        this.usuarioAutenticado = null;
    }

    public AuthController(AutenticacionService servicio){
        this.servicio = (servicio != null) ? servicio : new AutenticacionService();
        this.usuarioAutenticado = null;
    }

    // valida campos y autentica credenciales; devuelve "" si es correcto o el mensaje de error
    public String iniciarSesion(String usuario, String clave){
        if(usuario == null || usuario.trim().isEmpty()){
            return "El nombre de usuario es obligatorio.";
        }
        if(clave == null || clave.trim().isEmpty()){
            return "La contraseña es obligatoria.";
        }

        Usuario u = servicio.autenticar(usuario, clave);
        if(u == null){
            return "Credenciales incorrectas. Verifique usuario y contraseña.";
        }

        this.usuarioAutenticado = u;
        return "";
    }

    public Usuario getUsuarioAutenticado(){
        return usuarioAutenticado;
    }

    public Rol getRolAutenticado(){
        return (usuarioAutenticado != null) ? usuarioAutenticado.getRol() : null;
    }

    public boolean estaAutenticado(){
        return usuarioAutenticado != null;
    }

    public void cerrarSesion(){
        this.usuarioAutenticado = null;
    }

    public AutenticacionService getServicio(){
        return servicio;
    }

    // las cuentas de prueba vienen listas en el servicio de autenticacion
    public String cargarDatosPrueba(){
        return "";
    }
}
