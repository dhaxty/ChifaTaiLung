package chifatailung.controlador;

import chifatailung.modelo.Rol;
import chifatailung.modelo.Usuario;

// [EXTRAPOLADO]
// Controlador del módulo de Login / Autenticación.
// Conecta el formulario FrmLogin con el servicio de autenticación y gestiona la sesión activa.
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

    // [EXTRAPOLADO]
    // Algoritmo de Inicio de Sesión:
    // 1. Valida que los campos de usuario y contraseña no estén vacíos.
    // 2. Solicita al servicio de autenticación la validación contra las cuentas locales.
    // 3. Al autenticar con éxito, almacena la referencia del usuario autenticado en la sesión.
    // 4. Retorna "" si el acceso es concedido, o el mensaje de error para mostrar en la vista.
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

    // [EXTRAPOLADO]
    // Carga de datos de prueba para login:
    // Retorna "" confirmando que las cuentas 'admin' y 'empleado' están preparadas.
    public String cargarDatosPrueba(){
        return "";
    }
}
