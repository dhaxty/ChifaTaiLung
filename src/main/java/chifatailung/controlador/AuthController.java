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
        String mensaje = "";
        if(usuario == null || usuario.trim().isEmpty()){
            mensaje = "El nombre de usuario es obligatorio.";
        } else if(clave == null || clave.trim().isEmpty()){
            mensaje = "La contraseña es obligatoria.";
        } else {
            Usuario u = servicio.autenticar(usuario, clave);
            if(u == null){
                mensaje = "Credenciales incorrectas. Verifique usuario y contraseña.";
            } else {
                this.usuarioAutenticado = u;
            }
        }
        return mensaje;
    }

    public Usuario getUsuarioAutenticado(){
        return usuarioAutenticado;
    }

    public Rol getRolAutenticado(){
        Rol rol = null;
        if(usuarioAutenticado != null){
            rol = usuarioAutenticado.getRol();
        }
        return rol;
    }

    public boolean estaAutenticado(){
        boolean autenticado = false;
        if(usuarioAutenticado != null){
            autenticado = true;
        }
        return autenticado;
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
